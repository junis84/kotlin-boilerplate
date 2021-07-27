package com.musinsa.codi.domain.valueobject

import com.musinsa.codi.domain.entity.Codi
import com.musinsa.codi.domain.entity.Goods
import com.musinsa.shared.domain.valueobject.ListValueObject
import com.musinsa.shared.domain.valueobject.NumberValueObject
import com.musinsa.shared.domain.valueobject.UUIDValueObject
import com.musinsa.shared.domain.valueobject.ValueObject
import java.rmi.UnexpectedException

class CodiId(value: String) : UUIDValueObject(value)

class CodiSourceId(value: Int) : NumberValueObject<Int>(value)

class CodiThumbnail(value: String) : ValueObject<String>(value)

enum class CodiLabel(val value: String) {
    IMAGE_360("image360"),
    VIDEO("video"),
    AD("ad"),
    TOP("top");

    override fun toString(): String {
        return "CodiLabel(value=$value)"
    }

    companion object {
        fun of(value: String): CodiLabel {
            return values().firstOrNull { it.value == value }
                ?: throw UnexpectedException("Value '$value' is not part of the enum ${CodiLabel::class.java}")
        }
    }
}

enum class CodiType(val value: String) {
    CODI_SHOP("codishop"),
    CODI_MAP("codimap"),
    BRAND_SNAP("brandsnap"),
    STREET_SNAP("streetsnap");

    override fun toString(): String {
        return "CodiType(value=$value)"
    }

    companion object {
        fun of(value: String): CodiType {
            return values().firstOrNull { it.value == value }
                ?: throw UnexpectedException("Value '$value' is not part of the enum ${CodiType::class.java}")
        }
    }
}

class CodiLabels(value: List<CodiLabel>) : ListValueObject<CodiLabel>(value)

class Codies(value: List<Codi>) : ListValueObject<Codi>(value)

class CodiRelatedGoods(value: List<Goods>) : ListValueObject<Goods>(value)
