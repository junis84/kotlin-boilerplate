package com.musinsa.shared.infrastructure.bus.event

import com.musinsa.shared.domain.aggregate.DomainEvent
import com.musinsa.shared.domain.service.Service
import java.lang.reflect.InvocationTargetException
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import org.reflections.Reflections

@Service
class DomainEventDiscovery(packageName: String) {
    private val indexedDomainEvents: HashMap<String, KClass<out DomainEvent>>

    init {
        val reflections = Reflections(packageName)
        val classes: Set<KClass<out DomainEvent>> =
            reflections.getSubTypesOf(DomainEvent::class.java).map { it.kotlin }.toSet()
        indexedDomainEvents = formatEvents(classes)
    }

    fun discover(name: String): KClass<out DomainEvent>? {
        return indexedDomainEvents[name]
    }

    fun discover(domainEventClass: KClass<out DomainEvent>): String? {
        return indexedDomainEvents.entries.stream().filter { (_, value) ->
            value == domainEventClass
        }.map { it.key }.findFirst().orElse(null)
    }

    @Throws(
        NoSuchMethodException::class,
        IllegalAccessException::class,
        InvocationTargetException::class,
        InstantiationException::class
    )
    private fun formatEvents(
        domainEvents: Set<KClass<out DomainEvent>>
    ): HashMap<String, KClass<out DomainEvent>> {
        val events: HashMap<String, KClass<out DomainEvent>> = HashMap()
        for (domainEvent in domainEvents) {
            if (domainEvent.isAbstract || domainEvent.java.isAnonymousClass) {
                continue
            }
            val domainEventInstance: DomainEvent = domainEvent.objectInstance ?: domainEvent.createInstance()
            events[domainEventInstance.eventName()] = domainEvent
        }
        return events
    }
}
