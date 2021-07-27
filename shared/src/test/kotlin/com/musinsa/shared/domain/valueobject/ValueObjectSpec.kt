package com.musinsa.shared.domain.valueobject

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.property.checkAll
import io.kotest.property.exhaustive.exhaustive
import java.util.UUID

private class TestValueObject(value: String) : ValueObject<String>(value)

private fun createValueObject(
    value: String = UUID.randomUUID().toString()
): ValueObject<String> {
    return TestValueObject(value)
}

class ValueObjectSpec : DescribeSpec(
    {
        val valueObjects = List(100) {
            createValueObject()
        }.exhaustive()

        describe("equals()") {
            it("should be able equivalence comparisons") {
                valueObjects.checkAll { valueObject ->
                    val nullObject = null
                    (valueObject == valueObject) shouldBe true
                    (valueObject == nullObject) shouldBe false
                    (valueObject.equals(object {})) shouldBe false

                    val clone = createValueObject(valueObject.value)
                    (valueObject == clone) shouldBe true

                    val other = createValueObject()
                    (valueObject == other) shouldBe false
                }
            }
        }

        describe("hashCode()") {
            it("should return same value if some objects are checked for equality") {
                valueObjects.checkAll { valueObject ->
                    val clone = createValueObject(valueObject.value)
                    (valueObject == clone) shouldBe true
                    valueObject.hashCode() shouldBe clone.hashCode()

                    val other = createValueObject()
                    valueObject.hashCode() shouldNotBe other.hashCode()
                }
            }
        }

        describe("toString()") {
            it("should return string of properties") {
                valueObjects.checkAll { valueObject ->
                    with(valueObject) {
                        val expected = "TestValueObject(value=$value)"
                        toString() shouldBe expected
                    }
                }
            }
        }
    }
)
