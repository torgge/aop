package com.example.aoptest.service

import br.gb.tech.domain.annotations.OperationLog
import br.gb.tech.domain.log.OmsLog
import br.gb.tech.domain.log.OmsObjectType
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class MyService {

    @PostConstruct
    fun init() {
        println("Service was started")
    }

    @OperationLog("SERVICE", "DEF", OmsObjectType.IN)
    fun doAny(value: OmsLog): OmsLog = value
}
