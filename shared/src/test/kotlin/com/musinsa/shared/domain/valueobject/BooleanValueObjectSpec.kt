package com.musinsa.shared.domain.valueobject

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import io.kotest.property.exhaustive.exhaustive
import kotlin.random.Random

private class TestBooleanValueObject(value: Boolean) : ValueObject<Boolean>(value)

private fun createBooleanValueObject(
    value: Boolean = Random.nextBoolean()
): ValueObject<Boolean> {
    return TestBooleanValueObject(value)
}

class BooleanValueObjectSpec : DescribeSpec(
    {
        val valueObjects = listOf(
            createBooleanValueObject(true),
            createBooleanValueObject(false)
        ).exhaustive()

        describe("isTrue()") {
            it("should return true if a value is true") {
                valueObjects.checkAll { valueObject ->
                    with(valueObject) {
                        isTrue() shouldBe value
                    }
                }
            }
        }

        describe("isFalse()") {
            it("should return false if a value is false") {
                valueObjects.checkAll { valueObject ->
                    with(valueObject) {
                        isFalse() shouldBe !value
                    }
                }
            }
        }
    }
)
