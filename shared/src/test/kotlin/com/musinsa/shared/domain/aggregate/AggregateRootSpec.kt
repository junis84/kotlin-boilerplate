package com.musinsa.shared.domain.aggregate

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk

private fun createAggregateRoot(): AggregateRoot {
    return object : AggregateRoot() {
    }
}

class AggregateRootSpec : DescribeSpec(
    {
        var aggregateRoot: AggregateRoot = createAggregateRoot()
        beforeEach {
            aggregateRoot = createAggregateRoot()
        }

        describe("recordEvent()") {
            it("should be able to record domain event") {
                val event = mockk<DomainEvent>()
                aggregateRoot.recordEvent(event) shouldBe Unit
            }
        }

        describe("pullEvents()") {
            it("should return and clear domain events") {
                val event1 = mockk<DomainEvent>()
                aggregateRoot.recordEvent(event1)
                val event2 = mockk<DomainEvent>()
                aggregateRoot.recordEvent(event2)
                aggregateRoot.pullEvents() shouldBe listOf(event1, event2)
                aggregateRoot.pullEvents() shouldBe emptyList()
            }
        }
    }
)
