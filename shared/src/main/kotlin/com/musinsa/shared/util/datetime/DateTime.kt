package com.musinsa.shared.util.datetime

import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun dateTimeToString(offsetDateTime: OffsetDateTime): String {
    return offsetDateTime
        .format(DateTimeFormatter.ISO_DATE_TIME)
}

fun dateTimeToString(localDateTime: LocalDateTime): String {
    return dateTimeToString(
        localDateTime
            .atZone(ZoneId.systemDefault())
            .toOffsetDateTime()
    )
}

fun timestampToString(timestamp: Timestamp): String {
    return dateTimeToString(timestamp.toLocalDateTime())
}
