package com.musinsa.shared.infrastructure.bus.query

import com.musinsa.shared.application.bus.query.Query
import com.musinsa.shared.application.bus.query.QueryBus
import com.musinsa.shared.application.bus.query.QueryHandler
import com.musinsa.shared.application.bus.query.QueryHandlerExecutionException
import com.musinsa.shared.application.bus.query.Response
import com.musinsa.shared.application.container.Container
import com.musinsa.shared.domain.service.Service
import kotlin.reflect.KClass

@Service
class SimpleQueryBus(
    private val handlerDiscovery: QueryHandlerDiscovery,
    private val container: Container
) : QueryBus {

    override fun <R : Response> ask(query: Query): R? {
        try {
            @Suppress("UNCHECKED_CAST")
            val handlerClass = handlerDiscovery.discover(query::class) as KClass<QueryHandler<Query, R>>

            return (container.get(handlerClass))?.invoke(query)
        } catch (@Suppress("TooGenericExceptionCaught") e: Throwable) {
            throw QueryHandlerExecutionException(e)
        }
    }
}
