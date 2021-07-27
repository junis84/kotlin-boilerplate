package com.musinsa.shared.util.datetime

import com.musinsa.shared.test.matchers.string.beValidISODateTimeString
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.should
import io.kotest.property.checkAll
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId

class DateTimeSpec : DescribeSpec(
    {
        describe("dateTimeToString()") {
            it("should return a string as a datetime format") {
                checkAll<LocalDateTime>(100) {
                    val offsetDateTime = it
                        .atZone(ZoneId.systemDefault())
                        .toOffsetDateTime()
                    dateTimeToString(offsetDateTime) should beValidISODateTimeString<String>()
                    dateTimeToString(it) should beValidISODateTimeString<String>()
                }
            }
        }
        describe("timestampToString()") {
            it("should return a string as a datetime format") {
                checkAll<LocalDateTime>(100) {
                    timestampToString(Timestamp.valueOf(it)) should beValidISODateTimeString<String>()
                }
            }
        }
    }
)
