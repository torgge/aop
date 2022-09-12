package com.example.aoptest.controller

import br.gb.tech.domain.annotations.LogTracking
import br.gb.tech.domain.log.*
import com.example.aoptest.domain.MyClassToLog
import com.example.aoptest.service.MyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/demo")
class MyController(
    @Autowired private val service: MyService
) {

    @PostMapping("/hello")
    @LogTracking(
        OmsProcessType.PEDIDO_ABASTECIMENTO,
        OmsProcessingNode.OMS,
        OmsProcessingAction.METHOD,
        OmsObjectType.IN,
        "com.example.aoptest.domain.MyClassToLog",
        "Log Method IN from CONTROLLER"
    )
    fun sayHello(
        @RequestHeader headers: HttpHeaders?,
        @RequestBody body: MyClassToLog
    ): ResponseEntity<MyClassToLog> {
        val props = getHeaders(headers)

        val response = this.service.doAny(value = body, props)

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PostMapping("/throw")
    @LogTracking(
        OmsProcessType.PEDIDO_ABASTECIMENTO,
        OmsProcessingNode.OMS,
        OmsProcessingAction.METHOD,
        OmsObjectType.IN,
        "com.example.aoptest.domain.MyClassToLog",
        "Log Method IN from CONTROLLER"
    )
    fun throwOnHello(
        @RequestHeader headers: HttpHeaders?,
        @RequestBody body: MyClassToLog
    ): ResponseEntity<MyClassToLog> {
        val props = getHeaders(headers)

        val response = this.service.throwAny(value = body, props["correlationId"].toString())

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    private fun getHeaders(headers: HttpHeaders?): Map<String, Any> {
        var props = mapOf<String, Any>()

        if (headers != null) props = mapOf<String, Any>(
            "correlationId" to if (headers.containsKey("correlationId")) headers["correlationId"].toString() else UUID.randomUUID().toString(),
            "instanceId" to if (headers.containsKey("instanceId")) headers["instanceId"].toString() else UUID.randomUUID().toString()
        )

        return props
    }
}
