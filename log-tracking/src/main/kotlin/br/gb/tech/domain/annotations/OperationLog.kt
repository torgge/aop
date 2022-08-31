package br.gb.tech.domain.annotations

import br.gb.tech.domain.log.*
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FUNCTION

@Target(FUNCTION)
@Retention(RUNTIME)
annotation class OperationLog(
    val processType: OmsProcessType,
    val processingNode: OmsProcessingNode,
    val processingAction: OmsProcessingAction,
    val logLevel: OmsLogLevel,
    val logType: OmsObjectType,
    val typeName: String,
    val message: String
)
