package com.musinsa.shared.domain.valueobject

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.property.checkAll
import io.kotest.property.exhaustive.exhaustive

private class TestUUIDValueObject(value: String) : UUIDValueObject(value)

private fun createUUIDValueObject(
    value: String = java.util.UUID.randomUUID().toString()
): UUIDValueObject {
    return TestUUIDValueObject(value)
}

class UUIDSpec : DescribeSpec(
    {
        val uuids = List(100) {
            createUUIDValueObject()
        }.exhaustive()

        describe("init") {
            it("should throw an exception when an invalid UUID string") {
                checkAll<String>(100) {
                    shouldThrow<IllegalArgumentException> {
                        TestUUIDValueObject(it)
                    }
                }
            }
        }

        describe("equals()") {
            it("should be able equivalence comparisons") {
                uuids.checkAll { uuid ->
                    val nullObject = null
                    (uuid == uuid) shouldBe true
                    (uuid == nullObject) shouldBe false
                    (uuid.equals(object {})) shouldBe false

                    val clone = createUUIDValueObject(uuid.value)
                    (uuid == clone) shouldBe true

                    val other = createUUIDValueObject()
                    (uuid == other) shouldBe false
                }
            }
        }

        describe("hashCode()") {
            it("should return same value if some objects are checked for equality") {
                uuids.checkAll { uuid ->
                    val clone = createUUIDValueObject(uuid.value)
                    (uuid == clone) shouldBe true
                    uuid.hashCode() shouldBe clone.hashCode()

                    val other = createUUIDValueObject()
                    uuid.hashCode() shouldNotBe other.hashCode()
                }
            }
        }

        describe("toString()") {
            it("should return string of properties") {
                uuids.checkAll { uuid ->
                    with(uuid) {
                        val expected = "TestUUIDValueObject(value=$value)"
                        toString() shouldBe expected
                    }
                }
            }
        }
    }
)
