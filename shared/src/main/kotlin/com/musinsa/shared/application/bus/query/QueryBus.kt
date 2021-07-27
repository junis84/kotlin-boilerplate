package com.musinsa.shared.application.bus.query

interface QueryBus {
    @Throws(QueryHandlerExecutionException::class)
    fun <R : Response> ask(query: Query): R?
}
