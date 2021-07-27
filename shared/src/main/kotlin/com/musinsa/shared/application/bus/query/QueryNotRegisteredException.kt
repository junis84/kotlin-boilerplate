package com.musinsa.shared.application.bus.query

import kotlin.reflect.KClass

class QueryNotRegisteredException(query: KClass<out Query>) :
    Exception("The query <$query> hasn't a query handler associated")
