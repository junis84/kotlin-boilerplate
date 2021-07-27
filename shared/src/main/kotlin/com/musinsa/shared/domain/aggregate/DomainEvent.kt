package com.musinsa.shared.domain.aggregate

import com.musinsa.shared.util.datetime.dateTimeToString
import java.time.LocalDateTime
import java.util.UUID

abstract class DomainEvent(
    open val aggregateId: String,
    eventId: String? = null,
    occurredOn: String? = null
) {
    open val eventId = eventId ?: UUID.randomUUID().toString()
    open val occurredOn = occurredOn ?: dateTimeToString(LocalDateTime.now())

    abstract fun eventName(): String

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DomainEvent

        if (aggregateId != other.aggregateId) return false
        if (eventId != other.eventId) return false
        if (occurredOn != other.occurredOn) return false

        return true
    }

    override fun hashCode(): Int {
        var result = aggregateId.hashCode()
        result = 31 * result + eventId.hashCode()
        result = 31 * result + occurredOn.hashCode()
        return result
    }

    override fun toString(): String {
        return "${javaClass.simpleName}(aggregateId=$aggregateId, eventId=$eventId, occurredOn=$occurredOn)"
    }
}
