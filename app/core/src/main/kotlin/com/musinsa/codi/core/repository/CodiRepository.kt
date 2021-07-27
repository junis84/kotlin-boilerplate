package com.musinsa.codi.core.repository

import com.musinsa.codi.core.criteria.PageRequest
import com.musinsa.codi.core.model.CodiModel
import com.musinsa.codi.core.model.QCodiModel.codiModel
import com.musinsa.codi.domain.entity.Codi
import com.musinsa.codi.domain.repository.CodiRepository
import com.musinsa.codi.domain.valueobject.CodiId
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
import com.musinsa.shared.infrastructure.criteria.QuerydslSpecification
import java.time.LocalDateTime
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

fun Codi.toModel(): CodiModel {
    return CodiModel(
        id = id.toUUID(),
        type = type.value,
        sourceId = sourceId.value,
        thumbnail = thumbnail.value,
        viewCount = viewCount.value,
        commentCount = commentCount.value,
        siteKind = siteKind.value,
        sex = sex.value,
        useYn = useYn.value,
        createdAt = createdAt.value,
        updatedAt = updatedAt.value,
        lastCommentAddedAt = lastCommentAddedAt.value
    )
}

fun CodiModel.toEntity(): Codi {
    return Codi(
        id = CodiId(id.toString()),
        type = CodiType.of(type),
        sourceId = CodiSourceId(sourceId),
        thumbnail = CodiThumbnail(thumbnail),
        viewCount = ViewCount(viewCount),
        commentCount = CommentCount(commentCount),
        relatedGoods = CodiRelatedGoods(emptyList()),
        labels = CodiLabels(emptyList()),
        siteKind = SiteKind.MUSINSA,
        sex = Sex(sex),
        useYn = UseYn.YES,
        createdAt = CreatedAt(createdAt!!),
        updatedAt = UpdatedAt(LocalDateTime.now()),
        lastCommentAddedAt = LastCommentAddedAt(LocalDateTime.now())
    )
}

@Repository
class CodiRepository :
    QuerydslRepositorySupport(CodiModel::class.java),
    CodiRepository<QuerydslSpecification<Codi>, PageRequest> {

    override fun save(codi: Codi): Codi {
        val model = codi.toModel()
        entityManager?.persist(model)
        return model.toEntity()
    }

    override fun find(id: CodiId): Codi? {
        return from(codiModel)
            .where(
                codiModel.id.eq(
                    id.toUUID()
                )
            )
            .fetchOne()?.toEntity()
    }

    override fun findAll(
        spec: QuerydslSpecification<Codi>,
        pageable: PageRequest
    ): List<Codi> {
        val query = from(codiModel)
            .where(
                spec.toPredicate()
            )
        val pageRequest = pageable.of()
        val result = query.limit(pageRequest.pageSize.toLong())
            .offset(pageRequest.offset).fetch()
        return result.map { it.toEntity() }
    }
}
