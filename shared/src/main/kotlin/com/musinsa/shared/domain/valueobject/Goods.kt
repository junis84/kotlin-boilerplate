package com.musinsa.shared.domain.valueobject

import java.rmi.UnexpectedException

class GoodsNo(value: Int) : NumberValueObject<Int>(value)

class GoodsSeqNo(value: Int) : NumberValueObject<Int>(value)

class GoodsName(value: String) : ValueObject<String>(value)

enum class GoodsStatus(val value: Int) {
    PENDING_REGISTRATION(value = 5),
    PENDING_SALE(value = 10),
    DISCONTINUED(value = -10),
    SOLD_OUT_MANUALLY(value = 20),
    SOLD_OUT(value = 30),
    ON_SALE(value = 40);

    override fun toString(): String {
        return "GoodsStatus(value=$value)"
    }

    companion object {
        fun of(value: Int): GoodsStatus {
            return values().firstOrNull { it.value == value }
                ?: throw UnexpectedException("Value '$value' is not part of the enum ${GoodsStatus::class.java}")
        }
    }
}

class GoodsImage(value: String) : ValueObject<String>(value)

class GoodsDiscountRate(value: Float) : NumberValueObject<Float>(value)

class GoodsPrice(value: Int) : NumberValueObject<Int>(value)

class GoodsNetPrice(value: Int) : NumberValueObject<Int>(value)

class GoodsColor(value: Int) : NumberValueObject<Int>(value)

class GoodsItemCategory(value: String) : ValueObject<String>(value)
