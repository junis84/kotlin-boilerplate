package com.musinsa.shared.infrastructure.bus.event

import com.musinsa.shared.application.bus.event.DomainEventSubscriber
import com.musinsa.shared.domain.aggregate.DomainEvent
import com.musinsa.shared.domain.service.Service
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation
import org.reflections.Reflections

@Service
class DomainEventSubscriberDiscovery(packageName: String) {
    private val indexedSubscribers: HashMap<KClass<*>, List<KClass<out DomainEvent>>>

    init {
        val reflections = Reflections(packageName)
        val classes: Set<KClass<*>> = reflections.getTypesAnnotatedWith(
            DomainEventSubscriber::class.java
        ).map { it.kotlin }.toSet()
        indexedSubscribers = formatSubscribers(classes)
    }

    fun all(): List<Pair<KClass<*>, List<KClass<out DomainEvent>>>> {
        return indexedSubscribers.entries.map { it.key to it.value }
    }

    private fun formatSubscribers(
        subscribers: Set<KClass<*>>
    ): HashMap<KClass<*>, List<KClass<out DomainEvent>>> {
        val subscribersRegistry: HashMap<KClass<*>, List<KClass<out DomainEvent>>> = HashMap()
        for (subscriberClass in subscribers) {
            val annotation: DomainEventSubscriber = subscriberClass.findAnnotation<DomainEventSubscriber>()!!
            subscribersRegistry[subscriberClass] = annotation.value.toList()
        }
        return subscribersRegistry
    }
}
