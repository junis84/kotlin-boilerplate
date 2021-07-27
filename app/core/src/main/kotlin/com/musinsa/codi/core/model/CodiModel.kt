package com.musinsa.codi.core.model

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

@Suppress("LongParameterList")
@Entity
@Table(name = "codi")
class CodiModel(
    @Column(name = "codi_id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
    val id: UUID,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codi_no", nullable = false)
    var no: Int? = null,

    @Column(name = "codi_type", nullable = false)
    var type: String = "",

    @Column(name = "source_id", nullable = false)
    var sourceId: Int = 0,

    @Column(name = "sex", nullable = false)
    var sex: Int = 1,

    @Column(name = "site_kind", nullable = false)
    var siteKind: Int = 2,

    @Column(name = "thumbnail", nullable = false)
    var thumbnail: String = "",

    @Column(name = "is_360_image", nullable = false)
    var is360Image: Int = 0,

    @Column(name = "is_video", nullable = false)
    var isVideo: Int = 0,

    @Column(name = "is_ad", nullable = false)
    var isAd: Int = 0,

    @Column(name = "is_top", nullable = false)
    var isTop: Int = 0,

    @Column(name = "view_cnt", nullable = false)
    var viewCount: Int = 0,

    @Column(name = "comment_cnt", nullable = false)
    var commentCount: Int = 0,

    @Column(name = "hidden_comment_cnt", nullable = false)
    var hiddenCommentCount: Int = 0,

    @Column(name = "last_comment_added_at", nullable = true)
    var lastCommentAddedAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "use_yn", nullable = false)
    var useYn: String = "",

    @Column(name = "displayed_since", nullable = false)
    var displayedSince: LocalDateTime = LocalDateTime.now(),

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DateTime")
    var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "DateTime")
    var updatedAt: LocalDateTime? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CodiModel

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Codi(no=$no, id=$id, type='$type', sourceId=$sourceId, sex=$sex, siteKind=$siteKind, " +
            "thumbnail='$thumbnail', is360Image=$is360Image, isVideo=$isVideo, isAd=$isAd, isTop=$isTop, " +
            "viewCnt=$viewCount, commentCnt=$commentCount, hiddenCommentCnt=$hiddenCommentCount, " +
            "lastCommentAddedAt=$lastCommentAddedAt, useYn='$useYn', displayedSince=$displayedSince, " +
            "createdAt=$createdAt, updatedAt=$updatedAt)"
    }
}
