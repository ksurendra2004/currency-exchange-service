package com.exchange.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "exchange_value")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ExchangeModel {

    @Id
    @Column(name = "id") private Long id;
    @Column(name = "currency_from")
    private String fromCurrency;
    @Column(name = "currency_to") private String toCurrency;
    @Column(name = "conversion_multiple")
    private BigDecimal conversionMultiple;
    @Column(name = "port") private int port;

    public ExchangeModel(Long id, String fromCurrency, String toCurrency, BigDecimal conversionMultiple) {
        super();
        this.id = id;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.conversionMultiple = conversionMultiple;
    }
}
