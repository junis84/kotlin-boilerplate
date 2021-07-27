package com.musinsa.codi.faker

import com.musinsa.codi.domain.entity.Brand
import com.musinsa.codi.domain.entity.Goods
import com.musinsa.shared.domain.valueobject.GoodsName
import com.musinsa.shared.domain.valueobject.GoodsNo
import com.musinsa.shared.domain.valueobject.GoodsSeqNo
import com.musinsa.shared.domain.valueobject.CreatedAt
import com.musinsa.shared.domain.valueobject.GoodsColor
import com.musinsa.shared.domain.valueobject.GoodsDiscountRate
import com.musinsa.shared.domain.valueobject.GoodsImage
import com.musinsa.shared.domain.valueobject.GoodsItemCategory
import com.musinsa.shared.domain.valueobject.GoodsNetPrice
import com.musinsa.shared.domain.valueobject.GoodsPrice
import com.musinsa.shared.domain.valueobject.GoodsStatus
import io.github.serpro69.kfaker.Faker
import kotlin.math.round

private val faker = Faker()

private var sequenceValue = 0
private val priceValueRange = 1000 until 1000000
private val rateValueRange = 0 until 90
private val colorValueRange = 0 until 128

internal fun Goods.clone(): Goods {
    return Goods(
        no,
        seqNo,
        name,
        brand,
        itemCategory,
        image,
        status,
        price,
        netPrice,
        discountRate,
        color,
        createdAt,
    )
}

internal fun goods(): Goods {
    val netPrice = priceValueRange.random()
    val discountRate = rateValueRange.random().toFloat()
    val price = round(netPrice * discountRate / 100).toInt()

    return faker.randomProvider.randomClassInstance {
        typeGenerator<GoodsNo> {
            GoodsNo(++sequenceValue)
        }
        typeGenerator<GoodsSeqNo> {
            GoodsSeqNo(sequenceValue)
        }
        typeGenerator<GoodsName> {
            GoodsName(faker.book.title())
        }
        typeGenerator<Brand> {
            brand()
        }
        typeGenerator<GoodsItemCategory> {
            GoodsItemCategory(faker.artist.names())
        }
        typeGenerator<GoodsImage> {
            GoodsImage("${faker.game.title()}.${faker.file.extension()}")
        }
        typeGenerator<GoodsStatus> {
            randomEnum<GoodsStatus>()
        }
        typeGenerator<GoodsPrice> {
            GoodsPrice(price)
        }
        typeGenerator<GoodsNetPrice> {
            GoodsNetPrice(netPrice)
        }
        typeGenerator<GoodsDiscountRate> {
            GoodsDiscountRate(discountRate)
        }
        typeGenerator<GoodsColor> {
            GoodsColor(colorValueRange.random())
        }
        typeGenerator<CreatedAt> {
            CreatedAt(randomDateTimeUntilNow())
        }
    }
}
