package br.gb.tech.application

import br.gb.tech.domain.log.OmsLog
import br.gb.tech.domain.log.OmsLoggerService
import br.gb.tech.infrastructure.configurations.LogTrackingProperties
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OmsLoggerServiceImp(
    @Autowired private val properties: LogTrackingProperties
) : OmsLoggerService {
    private val logger = LoggerFactory.getLogger(OmsLoggerService::class.java)
    private val format = Json { prettyPrint = true }
    override fun printLogSuccess(omsLog: OmsLog) = printLog(omsLog)
    override fun printLogError(omsLog: OmsLog) = printLog(omsLog)

    private fun printLog(omsLog: OmsLog) {
//        println("\n>>\n $omsLog  \n>>\n")
        val message = format.encodeToString(omsLog)
        logger.info("\n\n=============================[${properties.valueStream}]=============================")
        logger.info("[ORIGIN] ${properties.applicationOrigin}\n")
        logger.info("[TEMPLATE VERSION] ${properties.templateVersion}\n")
        logger.info("[CONTENT] $message\n")
        logger.info("===[VERSION: ${properties.templateVersion}]=====================================\n\n")
    }

    private fun convertToJsonObject(value: Any): JsonObject = Json.decodeFromString(Json.encodeToString(value))
}
