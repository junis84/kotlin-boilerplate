package com.musinsa.codi.domain.entity

import com.musinsa.shared.domain.entity.Entity
import com.musinsa.shared.domain.valueobject.CreatedAt
import com.musinsa.shared.domain.valueobject.GoodsColor
import com.musinsa.shared.domain.valueobject.GoodsDiscountRate
import com.musinsa.shared.domain.valueobject.GoodsImage
import com.musinsa.shared.domain.valueobject.GoodsItemCategory
import com.musinsa.shared.domain.valueobject.GoodsName
import com.musinsa.shared.domain.valueobject.GoodsNetPrice
import com.musinsa.shared.domain.valueobject.GoodsNo
import com.musinsa.shared.domain.valueobject.GoodsPrice
import com.musinsa.shared.domain.valueobject.GoodsSeqNo
import com.musinsa.shared.domain.valueobject.GoodsStatus

@Suppress("LongParameterList")
class Goods(
    val no: GoodsNo,
    val seqNo: GoodsSeqNo,
    val name: GoodsName,
    val brand: Brand,
    val itemCategory: GoodsItemCategory,
    val image: GoodsImage,
    val status: GoodsStatus,
    val price: GoodsPrice,
    val netPrice: GoodsNetPrice,
    val discountRate: GoodsDiscountRate,
    val color: GoodsColor,
    val createdAt: CreatedAt,
) : Entity<GoodsNo>(no) {
    override fun toString(): String {
        return "Goods(no=$no, seqNo=$seqNo, name=$name, brand=$brand, itemCategory=$itemCategory, " +
            "image=$image, status=$status, price=$price, netPrice=$netPrice, discountRate=$discountRate, " +
            "color=$color, createdAt=$createdAt)"
    }
}
