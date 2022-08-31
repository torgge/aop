package com.example.aoptest.service

import br.gb.tech.domain.annotations.OperationLog
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

    @OperationLog(
        OmsProcessType.PEDIDO_ABASTECIMENTO,
        OmsProcessingNode.OMS,
        OmsProcessingAction.METHOD,
        OmsLogLevel.INFO,
        OmsObjectType.IN,
        "com.example.aoptest.domain.MyClassToLog",
        "Log Method IN from SERVICE"
    )
    fun doAny(value: MyClassToLog, props: Map<String, Any>): MyClassToLog = value
}
