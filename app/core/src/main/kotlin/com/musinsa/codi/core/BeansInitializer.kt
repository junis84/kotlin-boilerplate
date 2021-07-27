package com.musinsa.codi.core

import com.musinsa.codi.application.find.FindCodiQueryHandler
import com.musinsa.codi.application.findAll.FindAllCodiesQueryHandler
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

fun beans() = beans {
    bean {
        FindCodiQueryHandler(
            ref()
        )
    }
    bean {
        FindAllCodiesQueryHandler(
            ref()
        )
    }
}

class BeansInitializer : ApplicationContextInitializer<GenericApplicationContext> {

    override fun initialize(context: GenericApplicationContext) =
        beans().initialize(context)
}
