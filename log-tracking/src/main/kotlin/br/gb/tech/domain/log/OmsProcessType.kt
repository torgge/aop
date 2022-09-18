package br.gb.tech.domain.log

import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class OmsProcessType(val value: String) {
    PEDIDO_ABASTECIMENTO("Pedido Abastecimento"),
    PEDIDO_VAREJO("Pedido Varejo"),
    PEDIDO_RESELLER("Pedido Reseller"),
    NAO_DEFINIDO("NOT DEFINED")
}
