package com.musinsa.shared.infrastructure.bus.event

import com.musinsa.shared.domain.aggregate.DomainEvent
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DomainEventDiscoverySpec : DescribeSpec(
    {
        val packageName = DomainEventStub::class.java.packageName
        val domainEventDiscovery = DomainEventDiscovery(packageName)

        describe("discover()") {
            it("should return a kotlin class of event name passed if exists") {
                domainEventDiscovery.discover(STUB_EVENT_NAME) shouldBe DomainEventStub::class.java.kotlin
                domainEventDiscovery.discover(STUB_SINGLETON_EVENT_NAME).shouldBe(
                    SingletonDomainEventStub::class.java.kotlin
                )
            }
            it("should return null if the event name is not exists") {
                domainEventDiscovery.discover("notExistEvent") shouldBe null
            }
            it("should return a kotlin class of event class passed if exists") {
                domainEventDiscovery.discover(DomainEventStub::class) shouldBe STUB_EVENT_NAME
                domainEventDiscovery.discover(SingletonDomainEventStub::class) shouldBe STUB_SINGLETON_EVENT_NAME
            }
            it("should return null if an event class is not exists") {
                domainEventDiscovery.discover(DomainEvent::class) shouldBe null
            }
            it("should return null when an abstract class passed") {
                domainEventDiscovery.discover(AbstractDomainEventDummy::class) shouldBe null
            }
            it("should return null when an anonymous class passed") {
                val anonymousClassObject = object : DomainEvent("id") {
                    override fun eventName(): String {
                        return "anonymousClassEvent"
                    }
                }
                domainEventDiscovery.discover(anonymousClassObject::class) shouldBe null
            }
        }
    }
)
