package com.musinsa.shared.infrastructure.bus.query

import com.musinsa.shared.application.bus.query.Query
import com.musinsa.shared.application.bus.query.QueryHandler
import com.musinsa.shared.application.bus.query.QueryHandlerExecutionException
import com.musinsa.shared.application.bus.query.Response
import com.musinsa.shared.application.container.Container
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

private fun createQuery(): Query {
    return mockk<Query>()
}

private fun createResponse(): Response {
    return mockk<Response>()
}

private fun createQueryHandler(): QueryHandler<Query, Response> {
    return mockk<QueryHandler<Query, Response>>()
}

private fun createQueryHandlerDiscovery(): QueryHandlerDiscovery {
    return mockk<QueryHandlerDiscovery>()
}

private fun createContainer(): Container {
    return mockk<Container>()
}

class SimpleQueryBusSpec : DescribeSpec(
    {
        val query = createQuery()
        val response = createResponse()
        var queryHandler: QueryHandler<Query, Response>
        var queryHandlerDiscovery: QueryHandlerDiscovery
        var container: Container
        var queryBus: SimpleQueryBus

        describe("ask()") {
            it("should return a response when a query passed") {
                queryHandler = createQueryHandler()
                every { queryHandler.invoke(query) } returns response
                queryHandlerDiscovery = createQueryHandlerDiscovery()
                every { queryHandlerDiscovery.discover(query::class) } returns queryHandler::class
                container = createContainer()
                every { container.get(queryHandler::class) } returns queryHandler
                queryBus = SimpleQueryBus(queryHandlerDiscovery, container)

                queryBus.ask<Response>(query) shouldBe response
            }
            it("should return null if couldn't get a handler from a container") {
                queryHandler = createQueryHandler()
                every { queryHandler.invoke(query) } returns response
                queryHandlerDiscovery = createQueryHandlerDiscovery()
                every { queryHandlerDiscovery.discover(query::class) } returns queryHandler::class
                container = createContainer()
                every { container.get(queryHandler::class) } returns null
                queryBus = SimpleQueryBus(queryHandlerDiscovery, container)

                queryBus.ask<Response>(query) shouldBe null
            }
            it("should throw when an exception occurs during execution") {
                queryHandler = createQueryHandler()
                every { queryHandler.invoke(query) }.throws(RuntimeException())
                queryHandlerDiscovery = createQueryHandlerDiscovery()
                every { queryHandlerDiscovery.discover(query::class) } returns queryHandler::class
                container = createContainer()
                every { container.get(queryHandler::class) } returns queryHandler
                queryBus = SimpleQueryBus(queryHandlerDiscovery, container)

                shouldThrow<QueryHandlerExecutionException> {
                    queryBus.ask<Response>(query)
                }
            }
        }
    }
)
