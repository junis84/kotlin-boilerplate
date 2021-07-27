package com.musinsa.shared.infrastructure.criteria

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class InMemorySpecificationSpec : DescribeSpec(
    {
        var spec: InMemorySpecification<String>
        val truePredicate: (String) -> Boolean = { true }
        val falsePredicate: (String) -> Boolean = { false }
        val trueSpec = InMemorySpecification(truePredicate)
        val falseSpec = InMemorySpecification(falsePredicate)

        describe("and()") {
            it("should return a compound predicate that satisfies both predicates") {
                spec = trueSpec and falsePredicate
                (spec.toPredicate())("") shouldBe false
                spec = trueSpec and truePredicate
                (spec.toPredicate())("") shouldBe true

                spec = trueSpec and falseSpec
                (spec.toPredicate())("") shouldBe false
                spec = trueSpec and trueSpec
                (spec.toPredicate())("") shouldBe true

                spec = falseSpec and falseSpec
                (spec.toPredicate())("") shouldBe false
                spec = falseSpec and falsePredicate
                (spec.toPredicate())("") shouldBe false

                spec = falseSpec and trueSpec
                (spec.toPredicate())("") shouldBe false
                spec = falseSpec and truePredicate
                (spec.toPredicate())("") shouldBe false
            }
        }

        describe("or()") {
            it("should return a compound predicate that satisfies either predicate") {
                spec = trueSpec or falsePredicate
                (spec.toPredicate())("") shouldBe true
                spec = trueSpec or truePredicate
                (spec.toPredicate())("") shouldBe true

                spec = trueSpec or falseSpec
                (spec.toPredicate())("") shouldBe true
                spec = trueSpec or trueSpec
                (spec.toPredicate())("") shouldBe true

                spec = falseSpec or falseSpec
                (spec.toPredicate())("") shouldBe false
                spec = falseSpec or falsePredicate
                (spec.toPredicate())("") shouldBe false

                spec = falseSpec or trueSpec
                (spec.toPredicate())("") shouldBe true
                spec = falseSpec or truePredicate
                (spec.toPredicate())("") shouldBe true
            }
        }

        describe("not()") {
            it("should return a negate predicate") {
                spec = trueSpec.not()
                (spec.toPredicate())("") shouldBe false

                spec = falseSpec.not()
                (spec.toPredicate())("") shouldBe true
            }
        }
    }
)
