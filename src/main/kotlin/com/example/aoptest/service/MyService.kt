package com.example.aoptest.service

import br.gb.tech.domain.annotations.LogTracking
import br.gb.tech.domain.log.*
import com.example.aoptest.domain.MyClassToLog
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class MyService {

    @PostConstruct
    fun init() {
        println("Service was started")
    }

    @LogTracking(
        OmsProcessType.PEDIDO_ABASTECIMENTO,
        OmsProcessingNode.OMS,
        OmsProcessingAction.METHOD,
        OmsObjectType.IN,
        "com.example.aoptest.domain.MyClassToLog",
        "Log Method IN from SERVICE"
    )
    fun doAny(value: MyClassToLog, props: Map<String, Any>): MyClassToLog = value

    @LogTracking(
        OmsProcessType.PEDIDO_ABASTECIMENTO,
        OmsProcessingNode.OMS,
        OmsProcessingAction.METHOD,
        OmsObjectType.IN,
        "com.example.aoptest.domain.MyClassToLog",
        "Must throw this method"
    )
    fun throwAny(value: MyClassToLog, correlationId: String): MyClassToLog {
        throw Exception("Erro@@@@@@@@")
        return value
    }
}
