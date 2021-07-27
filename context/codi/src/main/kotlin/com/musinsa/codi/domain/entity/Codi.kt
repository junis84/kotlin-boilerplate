package com.musinsa.codi.domain.entity

import com.musinsa.codi.domain.valueobject.CodiId
import com.musinsa.codi.domain.valueobject.CodiLabels
import com.musinsa.codi.domain.valueobject.CodiRelatedGoods
import com.musinsa.codi.domain.valueobject.CodiSourceId
import com.musinsa.codi.domain.valueobject.CodiThumbnail
import com.musinsa.codi.domain.valueobject.CodiType
import com.musinsa.shared.domain.entity.Entity
import com.musinsa.shared.domain.valueobject.CommentCount
import com.musinsa.shared.domain.valueobject.CreatedAt
import com.musinsa.shared.domain.valueobject.LastCommentAddedAt
import com.musinsa.shared.domain.valueobject.Sex
import com.musinsa.shared.domain.valueobject.SiteKind
import com.musinsa.shared.domain.valueobject.UpdatedAt
import com.musinsa.shared.domain.valueobject.UseYn
import com.musinsa.shared.domain.valueobject.ViewCount

@Suppress("LongParameterList")
class Codi(
    val id: CodiId,
    val type: CodiType,
    val sourceId: CodiSourceId,
    val thumbnail: CodiThumbnail,
    val viewCount: ViewCount,
    val commentCount: CommentCount,
    val relatedGoods: CodiRelatedGoods,
    val labels: CodiLabels,
    val siteKind: SiteKind,
    val sex: Sex,
    val useYn: UseYn,
    val createdAt: CreatedAt,
    val updatedAt: UpdatedAt,
    val lastCommentAddedAt: LastCommentAddedAt
) : Entity<CodiId>(id) {
    override fun toString(): String {
        return "Codi(id=$id, type=$type, sourceId=$sourceId, thumbnail=$thumbnail, viewCount=$viewCount, " +
            "commentCount=$commentCount, relatedGoods=$relatedGoods, labels=$labels, siteKind=$siteKind, sex=$sex, " +
            "useYn=$useYn, createdAt=$createdAt, updatedAt=$updatedAt, lastCommentAddedAt=$lastCommentAddedAt)"
    }
}
