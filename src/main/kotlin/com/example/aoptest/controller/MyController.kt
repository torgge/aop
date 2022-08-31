package com.example.aoptest.controller

import br.gb.tech.domain.annotations.OperationLog
import br.gb.tech.domain.log.*
import com.example.aoptest.domain.MyClassToLog
import com.example.aoptest.service.MyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/demo")
class MyController(
    @Autowired private val service: MyService
) {

    @PostMapping("/hello")
    @OperationLog(
        OmsProcessType.PEDIDO_ABASTECIMENTO,
        OmsProcessingNode.OMS,
        OmsProcessingAction.METHOD,
        OmsLogLevel.INFO,
        OmsObjectType.IN,
        "com.example.aoptest.domain.MyClassToLog",
        "Log Method IN from CONTROLLER"
    )
    fun sayHello(
        @RequestHeader headers: HttpHeaders?,
        @RequestBody body: MyClassToLog
    ): ResponseEntity<MyClassToLog> {
        val props = mapOf<String, Any>(
            "correlationId" to headers?.get("correlationId").toString(),
            "uuid" to UUID.randomUUID().toString()
        )

        val response = this.service.doAny(value = body, props)

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}
