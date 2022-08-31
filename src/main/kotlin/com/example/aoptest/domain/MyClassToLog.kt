package com.example.aoptest.domain

import kotlinx.serialization.Serializable

@Serializable
data class MyClassToLog(
    val sku: String,
    val orderNumber: String,
    val userId: Long
) : java.io.Serializable
