package br.gb.tech.domain.log

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import java.time.LocalDateTime

@Serializable
data class OmsLog(
    val requestCid: String,
    val processCid: String,
    val processType: OmsProcessType,
    val processingNode: OmsProcessingNode,
    val processingAction: OmsProcessingAction,
    val logLevel: OmsLogLevel,
    val message: String,
    val dateTime: String = LocalDateTime.now().toString(),
    val type: String? = null,
    val instanceHash: String? = null,
    val instance: JsonObject?
)
