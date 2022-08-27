package br.gb.tech.application

import br.gb.tech.domain.log.OmsLog
import br.gb.tech.domain.log.OmsLoggerService
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Service

@Service
class OmsLoggerServiceImp : OmsLoggerService {
    private val format = Json { prettyPrint = true }

    override fun printLogSuccess(omsLog: OmsLog) {
        val message = format.encodeToString(omsLog)
        println("\n\nSUCCESS LOG: $message \n")
    }

    override fun printLogError(omsLog: OmsLog) {
        val message = format.encodeToString(omsLog)
        println("\n\nERROR LOG: $message \n")
    }
}
