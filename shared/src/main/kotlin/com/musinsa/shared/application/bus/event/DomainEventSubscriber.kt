package com.musinsa.shared.application.bus.event

import com.musinsa.shared.domain.aggregate.DomainEvent
import java.lang.annotation.Inherited
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@Inherited
annotation class DomainEventSubscriber(vararg val value: KClass<out DomainEvent>)
