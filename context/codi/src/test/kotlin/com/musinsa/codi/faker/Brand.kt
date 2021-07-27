package com.musinsa.codi.faker

import com.musinsa.codi.domain.entity.Brand
import com.musinsa.shared.domain.valueobject.BrandCode
import com.musinsa.shared.domain.valueobject.BrandName
import io.github.serpro69.kfaker.Faker

private val faker = Faker()

internal fun Brand.clone(): Brand {
    return Brand(
        code,
        name
    )
}

internal fun brand(): Brand {
    val company = faker.company.unique.name()
    return faker.randomProvider.randomClassInstance {
        typeGenerator<BrandCode> {
            BrandCode(company.lowercase().replace(" ", "_"))
        }
        typeGenerator<BrandName> {
            BrandName(company)
        }
    }
}
