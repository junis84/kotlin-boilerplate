package com.musinsa.shared.infrastructure.criteria

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Expressions
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

private fun <T> QuerydslSpecification<T>.shouldCompoundAndExpressions(vararg expressions: BooleanExpression) {
    toPredicate().toString() shouldBe expressions.joinToString(" && ")
}

private fun <T> QuerydslSpecification<T>.shouldCompoundOrExpressions(vararg expressions: BooleanExpression) {
    toPredicate().toString() shouldBe expressions.joinToString(" || ")
}

class QuerydslSpecificationSpec : DescribeSpec(
    {
        var spec: QuerydslSpecification<String>
        val trueBooleanBuilder = BooleanBuilder(Expressions.TRUE)
        val falseBooleanBuilder = BooleanBuilder(Expressions.FALSE)
        val trueSpec = QuerydslSpecification<String>(trueBooleanBuilder)
        val falseSpec = QuerydslSpecification<String>(falseBooleanBuilder)

        describe("and()") {
            it("should return a compound predicate that satisfies both predicates") {
                spec = trueSpec and falseBooleanBuilder
                spec.shouldCompoundAndExpressions(Expressions.TRUE, Expressions.FALSE)
                spec = trueSpec and trueBooleanBuilder
                spec.shouldCompoundAndExpressions(Expressions.TRUE, Expressions.TRUE)

                spec = trueSpec and falseSpec
                spec.shouldCompoundAndExpressions(Expressions.TRUE, Expressions.FALSE)
                spec = trueSpec and trueSpec
                spec.shouldCompoundAndExpressions(Expressions.TRUE, Expressions.TRUE)

                spec = falseSpec and falseSpec
                spec.shouldCompoundAndExpressions(Expressions.FALSE, Expressions.FALSE)
                spec = falseSpec and falseBooleanBuilder
                spec.shouldCompoundAndExpressions(Expressions.FALSE, Expressions.FALSE)

                spec = falseSpec and trueSpec
                spec.shouldCompoundAndExpressions(Expressions.FALSE, Expressions.TRUE)
                spec = falseSpec and trueBooleanBuilder
                spec.shouldCompoundAndExpressions(Expressions.FALSE, Expressions.TRUE)
            }
        }

        describe("or()") {
            it("should return a compound predicate that satisfies both predicates") {
                spec = trueSpec or falseBooleanBuilder
                spec.shouldCompoundOrExpressions(Expressions.TRUE, Expressions.FALSE)
                spec = trueSpec or trueBooleanBuilder
                spec.shouldCompoundOrExpressions(Expressions.TRUE, Expressions.TRUE)

                spec = trueSpec or falseSpec
                spec.shouldCompoundOrExpressions(Expressions.TRUE, Expressions.FALSE)
                spec = trueSpec or trueSpec
                spec.shouldCompoundOrExpressions(Expressions.TRUE, Expressions.TRUE)

                spec = falseSpec or falseSpec
                spec.shouldCompoundOrExpressions(Expressions.FALSE, Expressions.FALSE)
                spec = falseSpec or falseBooleanBuilder
                spec.shouldCompoundOrExpressions(Expressions.FALSE, Expressions.FALSE)

                spec = falseSpec or trueSpec
                spec.shouldCompoundOrExpressions(Expressions.FALSE, Expressions.TRUE)
                spec = falseSpec or trueBooleanBuilder
                spec.shouldCompoundOrExpressions(Expressions.FALSE, Expressions.TRUE)
            }
        }

        describe("not()") {
            it("should return a negate predicate") {
                spec = trueSpec.not()
                spec.toPredicate().toString() shouldBe "!${Expressions.TRUE}"

                spec = falseSpec.not()
                spec.toPredicate().toString() shouldBe "!${Expressions.FALSE}"
            }
        }
    }
)
