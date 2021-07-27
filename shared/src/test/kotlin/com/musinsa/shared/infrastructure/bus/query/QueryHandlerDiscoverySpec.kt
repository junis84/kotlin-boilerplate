package com.musinsa.shared.infrastructure.bus.query

import com.musinsa.shared.application.bus.query.Query
import com.musinsa.shared.application.bus.query.QueryHandler
import com.musinsa.shared.application.bus.query.QueryNotRegisteredException
import com.musinsa.shared.application.bus.query.Response
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import org.reflections.ReflectionsException

class QueryDummy : Query

class ResponseDummy : Response

class QueryHandlerDummy : QueryHandler<QueryDummy, ResponseDummy> {
    override fun invoke(query: QueryDummy): ResponseDummy {
        return ResponseDummy()
    }
}

class QueryHandlerDiscoverySpec : DescribeSpec(
    {
        describe("discover()") {
            it("should return a appropriate handler class class when a query class passed") {
                val packageName = QueryDummy::class.java.packageName
                val discovery = QueryHandlerDiscovery(packageName)
                discovery.discover(QueryDummy::class) shouldBe QueryHandlerDummy::class
            }
            it("should throw an exception when a package does not exists") {
                val packageName = "notExistPackage"
                shouldThrow<ReflectionsException> {
                    val discovery = QueryHandlerDiscovery(packageName)
                    discovery.discover(QueryDummy::class)
                }
            }
            it("should throw an exception when an unregistered query class passed") {
                val packageName = QueryDummy::class.java.packageName
                shouldThrow<QueryNotRegisteredException> {
                    val discovery = QueryHandlerDiscovery(packageName)
                    discovery.discover(mockk<Query>()::class)
                }
            }
        }
        describe("constructor()") {
            it("should return a appropriate handler class even if be instantiated by KClass") {
                val discovery = QueryHandlerDiscovery(QueryDummy::class)
                discovery.discover(QueryDummy::class) shouldBe QueryHandlerDummy::class
            }
        }
    }
)
