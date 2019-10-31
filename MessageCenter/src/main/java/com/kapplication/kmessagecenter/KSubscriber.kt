package com.kapplication.kmessagecenter


@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@kotlin.annotation.Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class KSubscriber(
    val tag: Int = -1, // message tag, same as the action of broadcast
    val mode: KThreadMode = KThreadMode.MAIN // message execution thread, default execution on main thread
)
