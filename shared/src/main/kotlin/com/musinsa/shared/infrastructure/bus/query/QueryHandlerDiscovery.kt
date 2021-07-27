package com.musinsa.shared.infrastructure.bus.query

import com.musinsa.shared.application.bus.query.Query
import com.musinsa.shared.application.bus.query.QueryHandler
import com.musinsa.shared.application.bus.query.QueryNotRegisteredException
import com.musinsa.shared.domain.service.Service
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass
import org.reflections.Reflections

@Service
class QueryHandlerDiscovery(packageName: String) {
    private var indexedQueryHandlers: HashMap<KClass<out Query>, KClass<out QueryHandler<*, *>>>

    constructor(packageClass: KClass<*>) : this(packageClass.java.packageName)

    init {
        val reflections = Reflections(packageName)
        val classes: Set<KClass<out QueryHandler<*, *>>> =
            reflections.getSubTypesOf(QueryHandler::class.java).map { it.kotlin }.toSet()
        indexedQueryHandlers = formatHandlers(classes)
    }

    @Throws(QueryNotRegisteredException::class)
    fun discover(queryClass: KClass<out Query>): KClass<out QueryHandler<*, *>> {
        return indexedQueryHandlers[queryClass]
            ?: throw QueryNotRegisteredException(queryClass)
    }

    private fun formatHandlers(
        queryHandlers: Set<KClass<out QueryHandler<*, *>>>
    ): HashMap<KClass<out Query>, KClass<out QueryHandler<*, *>>> {
        val handlers: HashMap<KClass<out Query>, KClass<out QueryHandler<*, *>>> = HashMap()
        for (handler in queryHandlers) {
            val paramType = handler.java.genericInterfaces[0] as ParameterizedType

            @Suppress("UNCHECKED_CAST")
            val queryClass = paramType.actualTypeArguments[0] as Class<out Query>
            handlers[queryClass.kotlin] = handler
        }
        return handlers
    }
}
