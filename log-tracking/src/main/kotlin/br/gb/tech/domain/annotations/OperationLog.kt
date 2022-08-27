package br.gb.tech.domain.annotations

import br.gb.tech.domain.log.OmsObjectType
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FUNCTION

@Retention(RUNTIME)
@Target(FUNCTION)
annotation class OperationLog(
    val processType: String,
    val messageSuccess: String,
    val logType: OmsObjectType
)
