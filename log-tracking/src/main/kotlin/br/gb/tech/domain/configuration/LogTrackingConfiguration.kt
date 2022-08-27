package br.gb.tech.domain.configuration

interface LogTrackingConfiguration {
    val valueStream: String
    val applicationOrigin: String
    val templateVersion: String
    val enabled: Boolean
}
