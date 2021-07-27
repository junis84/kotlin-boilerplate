package com.musinsa.shared.domain.aggregate

abstract class DomainException(val errorCode: String, errorMessage: String) :
    RuntimeException(errorMessage)
