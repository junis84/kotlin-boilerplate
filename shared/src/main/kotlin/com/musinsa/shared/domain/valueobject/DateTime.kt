package com.musinsa.shared.domain.valueobject

import java.time.LocalDateTime

class CreatedAt(value: LocalDateTime) : ValueObject<LocalDateTime>(value)

class UpdatedAt(value: LocalDateTime) : ValueObject<LocalDateTime>(value)

class LastCommentAddedAt(value: LocalDateTime) : ValueObject<LocalDateTime>(value)
