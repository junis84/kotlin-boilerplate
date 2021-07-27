package com.musinsa.shared.domain.aggregate

@Suppress("UnnecessaryAbstractClass")
abstract class AggregateRoot {
    private val domainEvents: MutableList<DomainEvent> = mutableListOf()

    fun recordEvent(event: DomainEvent) {
        domainEvents.add(event)
    }

    fun pullEvents(): List<DomainEvent> {
        val events = this.domainEvents.toList()
        domainEvents.clear()
        return events
    }
}
