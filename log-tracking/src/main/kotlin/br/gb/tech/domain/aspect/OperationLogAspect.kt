package br.gb.tech.domain.aspect

import br.gb.tech.domain.annotations.OperationLog
import br.gb.tech.domain.log.OmsLog
import br.gb.tech.domain.log.OmsLoggerService
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.reflect.Modifier
import java.util.*

@Aspect
@Component
class OperationLogAspect(
    @Autowired private val omsLoggerService: OmsLoggerService
) {
    val format = Json { encodeDefaults = true; prettyPrint = true }

    @Around("@annotation(operationLog)")
    fun aroundExecute(joinPoint: ProceedingJoinPoint, operationLog: OperationLog): Any? {
        val proceed: Any = joinPoint.proceed()

        // Method Information
        val signature = joinPoint.signature as MethodSignature

        println("full method description: " + signature.method)
        println("method name: " + signature.method.name)
        println("declaring type: " + signature.declaringType)

        // Method args
        println("Method args names:")
        Arrays.stream(signature.parameterNames)
            .forEach { s -> println("arg name: $s") }

        println("Method args types:")
        Arrays.stream(signature.parameterTypes)
            .forEach { s -> println("arg type: $s") }

        println("Method args values:")
        Arrays.stream(joinPoint.args)
            .forEach { o -> System.out.println("arg value: $o") }

        // Additional Information
        println("returning type: " + signature.returnType)
        System.out.println("method modifier: " + Modifier.toString(signature.modifiers))
        Arrays.stream(signature.exceptionTypes)
            .forEach { aClass -> println("exception type: $aClass") }

        // Method annotation
        System.out.println("operationLog annotation: $operationLog")

        // Garante parametro do tipo serializable
        if (signature.parameterTypes[0].typeName.equals(operationLog.typeName)) {
            val type: Class<*> = Class.forName(operationLog.typeName)
            val v = type.cast(joinPoint.args[0])
            val str = format.encodeToString(v)
            val instance: JsonObject = format.decodeFromString(str)
            omsLoggerService.printLogSuccess(
                OmsLog(
                    requestCid = "",
                    processCid = "",
                    processType = operationLog.processType,
                    processingNode = operationLog.processingNode,
                    processingAction = operationLog.processingAction,
                    logLevel = operationLog.logLevel,
                    message = operationLog.message,
                    type = null,
                    instanceHash = null,
                    instance = instance
                )
            )
        }

//        val requestCid: String,
//        val processCid: String,
//        val processType: OmsProcessType,
//        val processingNode: OmsProcessingNode,
//        val processingAction: OmsProcessingAction,
//        val logLevel: OmsLogLevel,
//        val message: String,
//        val dateTime: String = LocalDateTime.now().toString(),
//        val type: String?,
//        val instanceHash: String?,

//        println("===============Around Pointcut start execution......===============")
//        val map: MutableMap<String, Any> = HashMap()
//        map["LogType"] = operationLog
//        map["method"] = joinPoint.signature.name
//        map["JPArgs"] = joinPoint.args[0]
// //        val signature: Signature = joinPoint.signature
//        val returnType: Class<*> = (signature as MethodSignature).returnType
//        map["signature"] = signature
//        map["returnType"] = returnType
//        println("$map")
//
//        val secondParam: Map<String, Any>?
//        val firstParam: OmsLog
//        var cid = ""
//
//        if (joinPoint.args.size > 1) {
//            firstParam = joinPoint.args[0] as OmsLog
//            secondParam = joinPoint.args[1] as Map<String, Any>
//        } else {
//            firstParam = joinPoint.args[0] as OmsLog
//            secondParam = mapOf()
//        }
//
//        if (secondParam.isNotEmpty()) {
//            cid = (if (secondParam.containsKey("cid")) secondParam["cid"].toString() else "")
//        }

//        omsLoggerService.printLogSuccess(
//            OmsLog(
//                cid = cid,
//                status = OmsObjectType.IN,
//                content = Json.decodeFromString(Json.encodeToString(firstParam))
//            )
//        )

        return proceed
    }
}

// println("===============Around Pointcut start execution......===============")
// val map: MutableMap<String, Any> = HashMap()
// map["LogType"] = operationLog.logType.name
// map["method"] =
// joinPoint::getTarget::name.get() + "." + joinPoint.signature.name
// println(map)
// println("---------Around Pre notification execution completed in-------")
// val proceed: Any = joinPoint.proceed()
// println("---------Around Return result of target method in:$proceed")
// println("---------Around Target method execution completed in-------")
// println("This is a post notification...")
// println("---------Around Post notification execution completed-------")
// println("===============Around Pointcut execution is complete.===============")
// return proceed
