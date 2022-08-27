package br.gb.tech.domain.log

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class OmsLog(
    val cid: String,
    val status: OmsObjectType,
    val content: JsonObject
)
