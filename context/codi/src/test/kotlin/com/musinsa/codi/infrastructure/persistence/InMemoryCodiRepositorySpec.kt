package com.musinsa.codi.infrastructure.persistence

import com.musinsa.codi.domain.entity.Codi
import com.musinsa.codi.domain.valueobject.CodiId
import com.musinsa.codi.faker.codi
import com.musinsa.shared.domain.criteria.Pageable
import com.musinsa.shared.infrastructure.criteria.InMemorySpecification
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.util.UUID

class InMemoryCodiRepositorySpec : DescribeSpec(
    {
        val repository = InMemoryCodiRepository()
        val records = Array(100) { codi() }

        records.forEach {
            repository.save(it) shouldBe it
        }

        describe("find()") {
            it("should return a codi when an id passed") {
                val codi = records[0]
                repository.find(codi.id) shouldBe codi
            }

            it("should return null when a not exist id passed") {
                val notExistId = CodiId(UUID.randomUUID().toString())
                repository.find(notExistId) shouldBe null
            }
        }

        describe("findAll()") {
            it("should return a list of codi when a spec and pageable passed") {
                val recordsGroupBy = records.groupBy(Codi::type)
                val codiType = recordsGroupBy.keys.first()
                val targetRecords = recordsGroupBy[codiType]!!
                val size = if (targetRecords.size > 1) targetRecords.size - 1 else targetRecords.size
                val spec = mockk<InMemorySpecification<Codi>>()
                every { spec.toPredicate() } returns { it.type == codiType }
                val pageable = mockk<Pageable>()
                every { pageable.page } returns 1
                every { pageable.size } returns size

                repository.findAll(spec, pageable) shouldBe targetRecords.take(size)
            }
        }
    }
)
