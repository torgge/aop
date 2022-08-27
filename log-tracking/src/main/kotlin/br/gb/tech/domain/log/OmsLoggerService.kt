package br.gb.tech.domain.log

interface OmsLoggerService {
    fun printLogSuccess(omsLog: OmsLog)
    fun printLogError(omsLog: OmsLog)
}
