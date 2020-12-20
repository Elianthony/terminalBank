package terminal.controllers;

import terminal.drawer.TerminalDeposit;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TerminalController extends TerminalOperator {
    TerminalDeposit Deposito = new TerminalDeposit();

    /*
        Método responsável pelo depósito, pela avaliação do depósito e gerenciamento da quantidade de notas finais.
     */

    //Avalia Valor do depósito e quantidade de notas tratando caso a nota seja diferente das que o sistema comporta
    protected void deposito(int valorDeDeposito, int qntDeNotas) {
        switch (valorDeDeposito) {
            case 10:
                deposita10(Deposito, qntDeNotas);
                break;
            case 20:
                deposita20(Deposito, qntDeNotas);
                break;
            case 50:
                deposita50(Deposito, qntDeNotas);
                break;
            case 100:
                deposita100(Deposito, qntDeNotas);
                break;
            default:
                System.out.println("O sistema não trabalha com essa nota");
        }
    }


    /*
        Métodos responsáveis pelo saque, pela avaliação do saque e gerenciamento da quantidade de notas finais.
     */

    //Recebe o valor preterido e começa as tratativas de execução para operar o saque

    protected void saque(int valorDeSaque) {

        int qtdDeNotas10 = 0;
        int qtdDeNotas20 = 0;
        int qtdDeNotas50 = 0;
        int qtdDeNotas100 = 0;
        int valorParaSerSacado = valorDeSaque;

        boolean daPraDividir = true;

        //Fatora procurando qual a quantidade de cada célula para atender ao valor do saque

        while (daPraDividir) {
            if (valorParaSerSacado >= 10) {
                if (valorParaSerSacado >= 100) {
                    qtdDeNotas100 = fatoraQtdDeNotas(valorParaSerSacado, 100);
                    valorParaSerSacado = subtraiCedulasJaProcessadas(qtdDeNotas100, 100, valorParaSerSacado);
                } else {
                    if (valorParaSerSacado >= 50) {
                        qtdDeNotas50 = fatoraQtdDeNotas(valorParaSerSacado, 50);
                        valorParaSerSacado = subtraiCedulasJaProcessadas(qtdDeNotas50, 50, valorParaSerSacado);
                    } else {
                        if (valorParaSerSacado >= 20) {
                            qtdDeNotas20 = fatoraQtdDeNotas(valorParaSerSacado, 20);
                            valorParaSerSacado = subtraiCedulasJaProcessadas(qtdDeNotas20, 20, valorParaSerSacado);
                        } else {
                            qtdDeNotas10 = fatoraQtdDeNotas(valorParaSerSacado, 10);
                            valorParaSerSacado = subtraiCedulasJaProcessadas(qtdDeNotas10, 10, valorParaSerSacado);
                        }
                    }
                }
            } else {
                System.out.println("Infelizmente nosso sistema não comporta saques menores que 10");
                daPraDividir = false;
            }
        }

        //Totaliza o resultado final do saque que foi contabilizado

        int saldoDeSaqueContabilizado = (qtdDeNotas100 * 100) + (qtdDeNotas50 * 50) + (qtdDeNotas20 * 20) + (qtdDeNotas10 * 10);

        //Imprime do valor solicitado quanto pode ser cobrido

        System.out.println("O Saque selecionado foi de " + valorDeSaque + " podemos sacar " + saldoDeSaqueContabilizado);
        System.out.println("Será contabilizado um total de:");

        //imprime quantidade de notas para o usuário

        if (qtdDeNotas10 > 0) {
            System.out.println(qtdDeNotas10 + " notas de 10");
        }
        if (qtdDeNotas20 > 0) {
            System.out.println(qtdDeNotas20 + " notas de 20");
        }
        if (qtdDeNotas50 > 0) {
            System.out.println(qtdDeNotas50 + " notas de 50");
        }
        if (qtdDeNotas100 > 0) {
            System.out.println(qtdDeNotas100 + " notas de 100");
        }

        //Permite a abertura da transação com o depósito do terminal

        Scanner reader = new Scanner(System.in);
        boolean opcaoEscolhidaEhValida = false;
        char opc;

        System.out.println("Deseja concluir a transação? [S/N]");
        do {
            try {
                opc = reader.next().charAt(0);
                //O sistema garante que a entrada seja ou "S" ou "N"
                switch (opc) {
                    case 'S':

                        //Opera a transação se a quantidade de cédulas calculada for maior que "0"(zero)
                        if (qtdDeNotas10 > 0) {
                            operaSaque(Deposito, 10, qtdDeNotas10);
                        }
                        if (qtdDeNotas20 > 0) {
                            operaSaque(Deposito, 20, qtdDeNotas20);
                        }
                        if (qtdDeNotas50 > 0) {
                            operaSaque(Deposito, 50, qtdDeNotas50);
                        }
                        if (qtdDeNotas100 > 0) {
                            operaSaque(Deposito, 100, qtdDeNotas100);
                        }
                        opcaoEscolhidaEhValida = true;
                        break;
                    case 'N':
                        opcaoEscolhidaEhValida = true;
                        break;
                    default:
                        throw new InputMismatchException();
                }

                //Caso haja qualquer erro de Inputmismatch o sistema trata a exceção permitindo que o usuário possa inserir novo caractere
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite 'S' ou 'N'");
                reader.nextLine();
            }
        } while (!opcaoEscolhidaEhValida);
    }
}