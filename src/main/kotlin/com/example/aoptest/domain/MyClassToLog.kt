package com.example.aoptest.domain

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class MyClassToLog(
    val sku: String,
    val orderNumber: String,
    val userId: Long
) {
    override fun toString(): String {
//        val format = Json { prettyPrint = true }
        return Json.encodeToString(this)
    }
}
