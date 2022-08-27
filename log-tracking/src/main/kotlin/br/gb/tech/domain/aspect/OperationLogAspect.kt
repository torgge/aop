package br.gb.tech.domain.aspect

import br.gb.tech.domain.annotations.OperationLog
import br.gb.tech.domain.log.OmsLoggerService
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Aspect
@Component
class OperationLogAspect(
    @Autowired private val omsLoggerService: OmsLoggerService
) {
    @Around("@annotation(operationLog)")
    fun aroundExecute(joinPoint: ProceedingJoinPoint, operationLog: OperationLog): Any? {
        println("===============Around Pointcut start execution......===============")
        val map: MutableMap<String, Any> = HashMap()
        map["LogType"] = operationLog
        map["method"] = joinPoint.signature.name
        map["JPArgs"] = joinPoint.args[0]
        println(map)
        println("---------Around Pre notification execution completed in-------")
        val proceed: Any = joinPoint.proceed()
        println("---------Around Return result of target method in:$proceed")
        println("---------Around Target method execution completed in-------")
        println("This is a post notification...")
        println("---------Around Post notification execution completed-------")
        println("===============Around Pointcut execution is complete.===============")
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
