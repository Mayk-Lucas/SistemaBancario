package com.wolkspace.banco.app;

import com.wolkspace.banco.modelo.*;
import com.wolkspace.banco.modelo.atm.CaixaEletronico;
import com.wolkspace.banco.modelo.excecao.SaldoInsuficienteException;
import com.wolkspace.banco.modelo.pagamento.Boleto;
import com.wolkspace.banco.modelo.pagamento.Holerite;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Principal {

    public static void main(String[] args) {
        Pessoa titular1 = new Pessoa();
        titular1.setNome("Mayk Lucas");
        titular1.setDocumento("24342323");
        titular1.setRendimentoAnual(new BigDecimal("15000"));
        titular1.setTipo(TipoPessoa.JURIDICA);
        titular1.setDataUltimaAtualizacao(LocalDateTime.parse("2023-09-26T11:42:00"));

        System.out.println(titular1.getDataUltimaAtualizacao());

        Pessoa titular2 = new Pessoa();
        titular2.setNome("Maria Carine");
        titular2.setDocumento("475938763");

        CaixaEletronico caixaEletronico = new CaixaEletronico();

        ContaInvestimento minhaConta = new ContaInvestimento(titular1, 123, 234);
        ContaEspecial suaConta = new ContaEspecial(titular2, 456, 978, new BigDecimal("1000"));

        try {
            minhaConta.depositar(new BigDecimal("20000"));
            minhaConta.sacar(new BigDecimal("10000"));
            minhaConta.creditarRendimentos(new BigDecimal("0.8"));
            minhaConta.debitarTarifaMensal();

            suaConta.depositar(new BigDecimal("30000"));
            suaConta.sacar(new BigDecimal("10500"));
            suaConta.debitarTarifaMensal();

            Boleto boletoEscola = new Boleto(titular1, new BigDecimal("30000"));
            Holerite salarioFuncionario = new Holerite(titular2, new BigDecimal("100"), 2);

            caixaEletronico.pagar(boletoEscola, minhaConta);
            caixaEletronico.pagar(salarioFuncionario, suaConta);

            caixaEletronico.estornarPagamento(boletoEscola, minhaConta);

            boletoEscola.imprimirRecibo();
            salarioFuncionario.imprimirRecibo();
        } catch (SaldoInsuficienteException e) {
            System.out.println("Erro ao exercutar operação na conta: " + e.getMessage());
        }

        caixaEletronico.imprimirSaldo(minhaConta);
        System.out.println();
        caixaEletronico.imprimirSaldo(suaConta);
    }

}
