package com.musinsa.shared.domain.valueobject

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.should
import io.kotest.matchers.types.beInstanceOf
import io.kotest.property.checkAll
import io.kotest.property.exhaustive.exhaustive
import kotlin.random.Random

private class TestListValueObject(value: List<Int>) : ListValueObject<Int>(value)

private fun createListValueObject(
    value: List<Int> = Array((0..5).random()) { Random.nextInt() }.toList()
): ListValueObject<Int> {
    return TestListValueObject(value)
}

class ListValueObjectSpec : DescribeSpec(
    {
        val valueObjects = List(100) {
            createListValueObject()
        }.exhaustive()

        describe("iterator()") {
            it("should return iterator of list") {
                valueObjects.checkAll { valueObject ->
                    valueObject.iterator() should beInstanceOf<Iterator<Int>>()
                    valueObject.map {
                        it should beInstanceOf<Int>()
                    }
                }
            }
        }
    }
)
