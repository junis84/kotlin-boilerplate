package com.musinsa.shared.infrastructure.bus.event

import com.musinsa.shared.domain.aggregate.DomainEvent

const val STUB_EVENT_NAME = "stubEvent"

class DomainEventStub : DomainEvent("stubId") {
    override fun eventName(): String {
        return STUB_EVENT_NAME
    }
}

const val STUB_SINGLETON_EVENT_NAME = "stubSingletonEvent"

object SingletonDomainEventStub : DomainEvent("stubSingletonId") {
    override fun eventName(): String {
        return STUB_SINGLETON_EVENT_NAME
    }
}

abstract class AbstractDomainEventDummy(id: String) : DomainEvent(id)
