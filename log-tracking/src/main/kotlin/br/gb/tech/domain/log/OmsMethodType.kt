package br.gb.tech.domain.log

enum class OmsMethodType {
    CONTROLLER,
    SERVICE,
    MQTT_CONSUMER,
    MQTT_PRODUCER,
    KAFKA_CONSUMER,
    KAFKA_PRODUCER
}
