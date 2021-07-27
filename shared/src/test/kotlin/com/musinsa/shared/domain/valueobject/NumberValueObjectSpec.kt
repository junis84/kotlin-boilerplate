package com.musinsa.shared.domain.valueobject

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import io.kotest.property.exhaustive.exhaustive
import kotlin.random.Random

private class TestIntValueObject(value: Int) : NumberValueObject<Int>(value)

private fun createIntValueObject(
    value: Int = Random.nextInt()
): NumberValueObject<Int> {
    return TestIntValueObject(value)
}

class IntValueObjectSpec : DescribeSpec(
    {
        val valueObjects = List(100) {
            createIntValueObject()
        }.exhaustive()

        describe("compareTo()") {
            it("should be able to compare values") {
                valueObjects.checkAll { valueObject ->
                    with(valueObject) {
                        val variation = Random.nextInt(1, 10)

                        val greater = createIntValueObject(value + variation)
                        compareTo(greater) shouldBe -1

                        val less = createIntValueObject(value - variation)
                        compareTo(less) shouldBe 1

                        val equal = createIntValueObject(value)
                        compareTo(equal) shouldBe 0
                    }
                }
            }
        }
    }
)
