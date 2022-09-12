package br.gb.tech.domain.annotations

import br.gb.tech.domain.log.*
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FUNCTION

@Target(FUNCTION)
@Retention(RUNTIME)
annotation class LogTracking(
    val processType: OmsProcessType,
    val processingNode: OmsProcessingNode,
    val processingAction: OmsProcessingAction,
    val logType: OmsObjectType,
    val typeName: String,
    val message: String
)
