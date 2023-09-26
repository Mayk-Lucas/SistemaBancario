package com.wolkspace.banco.app;

import com.wolkspace.banco.modelo.*;
import com.wolkspace.banco.modelo.atm.CaixaEletronico;
import com.wolkspace.banco.modelo.excecao.SaldoInsuficienteException;
import com.wolkspace.banco.modelo.pagamento.Boleto;
import com.wolkspace.banco.modelo.pagamento.Holerite;

public class Principal {

    public static void main(String[] args) {
        Pessoa titular1 = new Pessoa();
        titular1.setNome("Mayk Lucas");
        titular1.setDocumento("24342323");

        Pessoa titular2 = new Pessoa();
        titular2.setNome("Maria Carine");
        titular2.setDocumento("475938763");

        CaixaEletronico caixaEletronico = new CaixaEletronico();

        ContaInvestimento minhaConta = new ContaInvestimento(titular1, 123, 234);
        ContaEspecial suaConta = new ContaEspecial(titular2, 456, 978, 1000);

        try {
            minhaConta.depositar(20_000);
            minhaConta.sacar(10_000);
            minhaConta.creditarRendimentos(0.8);
            minhaConta.debitarTarifaMensal();

            suaConta.depositar(30_000);
            suaConta.sacar(10_500);
            suaConta.debitarTarifaMensal();

            Boleto boletoEscola = new Boleto(titular1, 30_000);
            Holerite salarioFuncionario = new Holerite(titular2, 100, 2);

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
