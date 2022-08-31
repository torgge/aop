package br.gb.tech.domain.log

import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class OmsProcessType(value: String) {
    PEDIDO_ABASTECIMENTO("Pedido Abastecimento"),
    PEDIDO_VAREJO("Pedido Varejo"),
    PEDIDO_RESELLER("Pedido Reseller"),
}
