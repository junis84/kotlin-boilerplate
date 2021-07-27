package com.musinsa.shared.domain.aggregate

import com.musinsa.shared.test.matchers.string.beValidISODateTimeString
import com.musinsa.shared.test.matchers.string.beValidUUIDString
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.property.checkAll
import io.kotest.property.exhaustive.exhaustive
import java.util.UUID

private class TestDomainEvent(
    aggregateId: String,
    eventId: String? = null,
    occurredOn: String? = null,
) : DomainEvent(aggregateId, eventId, occurredOn) {
    override fun eventName(): String {
        return "TestDomainEvent"
    }
}

private fun createDomainEvent(
    aggregateId: String = UUID.randomUUID().toString(),
    eventId: String? = null,
    occurredOn: String? = null,
): DomainEvent {
    return TestDomainEvent(aggregateId, eventId, occurredOn)
}

private fun DomainEvent.partialChangedEvents(): List<DomainEvent> {
    return listOf<DomainEvent>(
        createDomainEvent(eventId = eventId, occurredOn = occurredOn),
        createDomainEvent(aggregateId = aggregateId, occurredOn = occurredOn),
        createDomainEvent(aggregateId = aggregateId, eventId = eventId)
    )
}

class DomainEventSpec : DescribeSpec(
    {
        val domainEvents = List(100) {
            createDomainEvent()
        }.exhaustive()

        describe("constructor()") {
            it("should initialize an eventId by default value when passed null") {
                domainEvents.checkAll { domainEvent ->
                    domainEvent.eventId should beValidUUIDString<String>()
                }
            }
            it("should initialize an occurredOn by default value when passed null") {
                domainEvents.checkAll { domainEvent ->
                    domainEvent.occurredOn should beValidISODateTimeString<String>()
                }
            }
        }

        describe("equals()") {
            it("should be able equivalence comparisons") {
                domainEvents.checkAll { domainEvent ->
                    val nullObject = null
                    (domainEvent == domainEvent) shouldBe true
                    (domainEvent == nullObject) shouldBe false
                    (domainEvent.equals(object {})) shouldBe false

                    with(domainEvent) {
                        val clone = createDomainEvent(aggregateId, eventId, occurredOn)
                        (this === clone) shouldBe false
                        (this == clone) shouldBe true

                        partialChangedEvents().forAll { other ->
                            (this == other) shouldBe false
                        }
                    }
                }
            }
        }

        describe("hashCode()") {
            it("should return same value if some objects are checked for equality") {
                domainEvents.checkAll { domainEvent ->
                    with(domainEvent) {
                        val clone = createDomainEvent(aggregateId, eventId, occurredOn)
                        (this == clone) shouldBe true
                        hashCode() shouldBe clone.hashCode()
                        partialChangedEvents().forAll { other ->
                            hashCode() shouldNotBe other.hashCode()
                        }
                    }
                }
            }
        }

        describe("toString()") {
            it("should return string of properties") {
                domainEvents.checkAll { domainEvent ->
                    with(domainEvent) {
                        val expected =
                            "TestDomainEvent(aggregateId=$aggregateId, eventId=$eventId, occurredOn=$occurredOn)"
                        toString() shouldBe expected
                    }
                }
            }
        }
    }
)
