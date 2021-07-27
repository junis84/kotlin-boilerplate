package com.musinsa.codi.core.config

import com.musinsa.codi.BasePackage
import com.musinsa.codi.core.model.BaseModel
import com.musinsa.shared.application.bus.query.QueryBus
import com.musinsa.shared.application.container.Container
import com.musinsa.shared.infrastructure.bus.query.QueryHandlerDiscovery
import com.musinsa.shared.infrastructure.bus.query.SimpleQueryBus
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EntityScan(
    basePackageClasses = [BaseModel::class]
)
class ApplicationConfig(
    private val context: ApplicationContext
) {

    @Bean
    fun queryBus(): QueryBus {
        return SimpleQueryBus(
            QueryHandlerDiscovery(BasePackage::class),
            context.getBean(Container::class.java)
        )
    }
}
