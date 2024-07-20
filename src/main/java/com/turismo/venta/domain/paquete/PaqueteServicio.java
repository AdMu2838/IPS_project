package com.turismo.venta.domain.paquete;

import java.math.BigDecimal;
import java.util.List;

public record PaqueteServicio(
        BigDecimal paqCos,
        Character paqEstReg,
        String paqImg,
        List<Long> serviciosCodigos
) {
}
