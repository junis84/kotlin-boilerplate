package com.musinsa.shared.infrastructure.criteria

import com.musinsa.shared.domain.criteria.Specification

private typealias Predicate<T> = (T) -> Boolean

open class InMemorySpecification<T>(
    private val predicate: Predicate<T>
) : Specification<T> {
    infix fun and(spec: InMemorySpecification<T>): InMemorySpecification<T> {
        val other = spec.toPredicate()
        return InMemorySpecification { predicate(it) && other(it) }
    }

    infix fun and(other: Predicate<T>): InMemorySpecification<T> {
        return InMemorySpecification { predicate(it) && other(it) }
    }

    infix fun or(spec: InMemorySpecification<T>): InMemorySpecification<T> {
        val other = spec.toPredicate()
        return InMemorySpecification { predicate(it) || other(it) }
    }

    infix fun or(other: Predicate<T>): InMemorySpecification<T> {
        return InMemorySpecification { predicate(it) || other(it) }
    }

    fun not(): InMemorySpecification<T> {
        return InMemorySpecification { !predicate(it) }
    }

    fun toPredicate(): Predicate<T> {
        return predicate
    }
}
