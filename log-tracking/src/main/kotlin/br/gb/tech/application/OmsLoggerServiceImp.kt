package br.gb.tech.application

import br.gb.tech.domain.log.OmsLog
import br.gb.tech.domain.log.OmsLogLevel
import br.gb.tech.domain.log.OmsLoggerService
import br.gb.tech.infrastructure.configurations.LogTrackingProperties
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

// import kotlinx.serialization.decodeFromString
// import kotlinx.serialization.json.JsonObject

@Service
class OmsLoggerServiceImp(
    @Autowired private val properties: LogTrackingProperties
) : OmsLoggerService {
    private val logger = LoggerFactory.getLogger(OmsLoggerService::class.java)
    private val format = Json { prettyPrint = true }
    override fun printLogSuccess(omsLog: OmsLog) {
        when (omsLog.logLevel) {
            OmsLogLevel.INFO -> logInfo(omsLog)
            OmsLogLevel.ERROR -> logError(omsLog)
            else -> logInfo(omsLog)
        }
    }
    override fun printLogError(omsLog: OmsLog) = logInfo(omsLog)

    private fun logInfo(omsLog: OmsLog) {
        val content = format.encodeToString(omsLog)
        logger.info("\n\n===[${properties.valueStream}]=============================")
        logger.info("[ORIGIN] ${properties.applicationOrigin}")
        logger.info("[TEMPLATE VERSION] ${properties.templateVersion}")
        logger.info("[CONTENT] \n $content")
        logger.info("[INSTANCE] ${omsLog.instance}")
        logger.info("===[VERSION: ${properties.templateVersion}]=============================\n\n")
    }

    private fun logError(omsLog: OmsLog) {
        val content = format.encodeToString(omsLog)
        logger.error("\n\n===[${properties.valueStream}]=============================")
        logger.error("[ORIGIN] ${properties.applicationOrigin}")
        logger.error("[TEMPLATE VERSION] ${properties.templateVersion}")
        logger.error("[CONTENT] \n $content")
        logger.error("[INSTANCE] ${omsLog.instance}")
        logger.error("===[VERSION: ${properties.templateVersion}]=============================\n\n")
    }

//    private fun convertToJsonObject(value: String): JsonObject = Json.decodeFromString(Json.encodeToString(value))
}
