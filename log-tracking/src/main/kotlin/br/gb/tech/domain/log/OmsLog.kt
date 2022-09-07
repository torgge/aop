package br.gb.tech.domain.log

import kotlinx.serialization.Serializable
import java.time.LocalDateTime
@Serializable
data class OmsLog(
    val requestId: String,
    val processId: String,
    val processType: OmsProcessType,
    val processingNode: OmsProcessingNode,
    val processingAction: OmsProcessingAction,
    val logLevel: OmsLogLevel,
    val message: String,
    val dateTime: String = LocalDateTime.now().toString(),
    val type: String? = null,
    val instanceHash: String? = null,
    val instance: String?
)
