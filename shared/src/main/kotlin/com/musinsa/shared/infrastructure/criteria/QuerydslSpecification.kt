package com.musinsa.shared.infrastructure.criteria

import com.musinsa.shared.domain.criteria.Specification
import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Predicate

open class QuerydslSpecification<T>(
    private val builder: BooleanBuilder
) : Specification<T> {

    infix fun and(other: QuerydslSpecification<T>): QuerydslSpecification<T> {
        return QuerydslSpecification(
            BooleanBuilder(
                toPredicate()
            ).and(
                other.toPredicate()
            )
        )
    }

    infix fun and(other: BooleanBuilder): QuerydslSpecification<T> {
        return QuerydslSpecification(
            BooleanBuilder(
                toPredicate()
            ).and(
                other
            )
        )
    }

    infix fun or(other: QuerydslSpecification<T>): QuerydslSpecification<T> {
        return QuerydslSpecification(
            BooleanBuilder(
                toPredicate()
            ).or(
                other.toPredicate()
            )
        )
    }

    infix fun or(other: BooleanBuilder): QuerydslSpecification<T> {
        return QuerydslSpecification(
            BooleanBuilder(
                toPredicate()
            ).or(
                other
            )
        )
    }

    fun not(): QuerydslSpecification<T> {
        return QuerydslSpecification(
            BooleanBuilder(
                toPredicate()
            ).not()
        )
    }

    fun toPredicate(): Predicate? {
        return builder.value
    }
}
