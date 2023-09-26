package com.wolkspace.banco.modelo.pagamento;

import com.wolkspace.banco.modelo.Pessoa;

import java.math.BigDecimal;

public class Boleto implements DocumentoPagavel, DocumentoEstornal {

    private Pessoa beneficiario;
    private BigDecimal valor;
    private boolean pago;

    public Boleto(Pessoa beneficiario, BigDecimal valor) {
        this.beneficiario = beneficiario;
        this.valor = valor;
    }

    @Override
    public BigDecimal getValorTotal() {
        return valor;
    }

    @Override
    public boolean estaPago() {
        return pago;
    }

    @Override
    public void estornarPagamento() {
        pago = false;
    }

    @Override
    public void quitarPagamento() {
        pago = true;
    }
}
