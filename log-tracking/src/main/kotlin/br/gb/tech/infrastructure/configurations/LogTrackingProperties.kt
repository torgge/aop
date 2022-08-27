package br.gb.tech.infrastructure.configurations

import br.gb.tech.domain.configuration.LogTrackingConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
@ComponentScan(
    basePackages = [
        "br.gb.tech.infrastructure",
        "br.gb.tech.domain",
        "br.gb.tech.application"
    ]
)
@EnableConfigurationProperties
@ConfigurationProperties("oms.demaba.logtracking")
@Primary
class LogTrackingProperties : LogTrackingConfiguration {
    override lateinit var valueStream: String
    override lateinit var applicationOrigin: String
    override lateinit var templateVersion: String
    override var enabled: Boolean = true
}
