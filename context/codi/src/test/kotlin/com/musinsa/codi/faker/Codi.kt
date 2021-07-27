package com.musinsa.codi.faker

import com.musinsa.codi.domain.entity.Codi
import com.musinsa.codi.domain.valueobject.CodiId
import com.musinsa.codi.domain.valueobject.CodiLabel
import com.musinsa.codi.domain.valueobject.CodiLabels
import com.musinsa.codi.domain.valueobject.CodiRelatedGoods
import com.musinsa.codi.domain.valueobject.CodiSourceId
import com.musinsa.codi.domain.valueobject.CodiThumbnail
import com.musinsa.codi.domain.valueobject.CodiType
import com.musinsa.shared.domain.valueobject.CommentCount
import com.musinsa.shared.domain.valueobject.CreatedAt
import com.musinsa.shared.domain.valueobject.LastCommentAddedAt
import com.musinsa.shared.domain.valueobject.Sex
import com.musinsa.shared.domain.valueobject.SiteKind
import com.musinsa.shared.domain.valueobject.UpdatedAt
import com.musinsa.shared.domain.valueobject.UseYn
import com.musinsa.shared.domain.valueobject.ViewCount
import io.github.serpro69.kfaker.Faker
import java.util.UUID

private val faker = Faker()

private val bitValueRange = 1 until 32
private val countValueRange = 1 until 1000
private val countValueRangeWithZero = 0 until 1000

internal fun Codi.clone(): Codi {
    return Codi(
        id,
        type,
        sourceId,
        thumbnail,
        viewCount,
        commentCount,
        relatedGoods,
        labels,
        siteKind,
        sex,
        useYn,
        createdAt,
        updatedAt,
        lastCommentAddedAt
    )
}

internal fun codi(): Codi {
    return faker.randomProvider.randomClassInstance {
        typeGenerator<CodiId> {
            CodiId(UUID.randomUUID().toString())
        }
        typeGenerator<CodiType> {
            randomEnum<CodiType>()
        }
        typeGenerator<CodiSourceId> {
            CodiSourceId(countValueRange.random())
        }
        typeGenerator<CodiThumbnail> {
            CodiThumbnail(faker.book.title())
        }
        typeGenerator<ViewCount> {
            ViewCount(countValueRangeWithZero.random())
        }
        typeGenerator<CommentCount> {
            CommentCount(countValueRangeWithZero.random())
        }
        typeGenerator<CodiRelatedGoods> {
            val goods = Array((0..5).random()) {
                goods()
            }
            CodiRelatedGoods(goods.toList())
        }
        typeGenerator<CodiLabels> {
            val codiLabels = Array((0..5).random()) {
                randomEnum<CodiLabel>()
            }
            CodiLabels(codiLabels.toList())
        }
        typeGenerator<SiteKind> {
            randomEnum<SiteKind>()
        }
        typeGenerator<Sex> {
            Sex(bitValueRange.random())
        }
        typeGenerator<UseYn> {
            randomEnum<UseYn>()
        }
        typeGenerator<CreatedAt> {
            CreatedAt(randomDateTimeUntilNow())
        }
        typeGenerator<UpdatedAt> {
            UpdatedAt(randomDateTimeUntilNow())
        }
        typeGenerator<LastCommentAddedAt> {
            LastCommentAddedAt(randomDateTimeUntilNow())
        }
    }
}
