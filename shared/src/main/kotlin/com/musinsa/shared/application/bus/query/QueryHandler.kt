package com.musinsa.shared.application.bus.query

interface QueryHandler<Q : Query, R : Response> {
    operator fun invoke(query: Q): R
}
