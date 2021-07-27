package com.musinsa.shared.application.bus.event

import com.musinsa.shared.domain.aggregate.DomainEvent

interface EventBus {
    fun publish(events: List<DomainEvent?>?)
}
