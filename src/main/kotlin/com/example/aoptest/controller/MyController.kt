package com.example.aoptest.controller

import br.gb.tech.domain.annotations.OperationLog
import br.gb.tech.domain.log.OmsLog
import br.gb.tech.domain.log.OmsObjectType
import com.example.aoptest.service.MyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/demo")
class MyController(
    @Autowired private val service: MyService
) {

    @PostMapping("/hello")
    @OperationLog("CONTROLLER", "DEF", OmsObjectType.IN)
    fun sayHello(@RequestBody body: OmsLog): ResponseEntity<OmsLog> {
        val response = this.service.doAny(value = body)

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}
