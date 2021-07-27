package com.musinsa.shared.domain.entity

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.property.checkAll
import io.kotest.property.exhaustive.exhaustive
import java.util.UUID

private class TestEntity(value: String) : Entity<String>(value) {
    fun clone(): TestEntity {
        val idField = javaClass.superclass.getDeclaredField("id")
        idField.isAccessible = true
        return TestEntity(idField.get(this) as String)
    }
}

private fun createEntity(value: String = UUID.randomUUID().toString()): TestEntity {
    return TestEntity(value)
}

class EntitySpec : DescribeSpec(
    {
        val entities = List(100) {
            createEntity()
        }.exhaustive()

        describe("equals()") {
            it("should be able equivalence comparisons") {
                entities.checkAll { entity ->
                    val nullObject = null
                    (entity == entity) shouldBe true
                    (entity == nullObject) shouldBe false
                    (entity.equals(object {})) shouldBe false

                    val clone = entity.clone()
                    (entity == clone) shouldBe true

                    val other = createEntity()
                    (entity == other) shouldBe false
                }
            }
        }

        describe("hashCode()") {
            it("should return same value if some objects are checked for equality") {
                entities.checkAll { entity ->
                    val clone = entity.clone()
                    (entity == clone) shouldBe true
                    entity.hashCode() shouldBe clone.hashCode()

                    val other = createEntity()
                    entity.hashCode() shouldNotBe other.hashCode()
                }
            }
        }
    }
)
