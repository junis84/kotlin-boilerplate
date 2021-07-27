package com.musinsa.shared.infrastructure.bus.event

import com.musinsa.shared.application.bus.event.DomainEventSubscriber
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.contain
import io.kotest.matchers.should

@DomainEventSubscriber(DomainEventStub::class)
class DomainEventSubscriberStub

class DomainEventSubscriberDiscoverySpec : DescribeSpec(
    {
        val packageName = DomainEventSubscriberStub::class.java.packageName
        val domainEventSubscriberDiscovery =
            DomainEventSubscriberDiscovery(packageName)

        describe("all()") {
            it("should return a pair of an event subscriber and events that are defined by annotation") {
                domainEventSubscriberDiscovery.all() should contain(
                    DomainEventSubscriberStub::class to listOf(
                        DomainEventStub::class
                    )
                )
            }
        }
    }
)
