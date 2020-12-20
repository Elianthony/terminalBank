package terminal.controllers;

import terminal.drawer.TerminalDeposit;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TerminalController extends TerminalOperator {
    TerminalDeposit Deposito = new TerminalDeposit();

    /*
        Métodos que refletem a opção do menu na camada de execução
     */

    protected void OpcaoDeposito(){

        //Iniciando avaliação de depósito
        System.out.println("=================================================");
        System.out.println("Você selecionou Depósito");
        System.out.println("=================================================");
        System.out.println("Nosso sistema apenas comporta cédulas de 10,20,50 e 100");
        System.out.println("Gostaria de depositar qual cédula?");
        Scanner reader = new Scanner(System.in);
        boolean opcaoEscolhidaEhValida = false;

        int valorDaCedula = 0;
        int qtdDeNotas = 0;

        //Solicita valor da cédula e verifica se é possível incluí-la no sistema
        do{
            try{
                System.out.println("Entre com o valor da nota:");
                valorDaCedula = reader.nextInt();
                switch(valorDaCedula){
                    case 10:
                        opcaoEscolhidaEhValida = true;
                        break;
                    case 20:
                        opcaoEscolhidaEhValida = true;
                        break;
                    case 50:
                        opcaoEscolhidaEhValida = true;
                        break;
                    case 100:
                        opcaoEscolhidaEhValida = true;
                        break;
                    default:
                        throw new InputMismatchException();
                }

                //Caso haja qualquer erro de mismatch o sistema trata a exceção permitindo que o usuário possa inserir novo valor
            }catch(InputMismatchException e){
                System.out.println("=================================================================");
                System.out.println("**Por favor digite apenas notas que o sistema comporta!**");
                System.out.println("**Nosso sistema apenas comporta cédulas de 10, 20, 50 e 100**");
                System.out.println("=================================================================");
                reader.nextLine();
            }
        }while(!opcaoEscolhidaEhValida);

        //Após salvar o valor da cédula adequadamente o sistema lida com a quantidade de cédulas que o usuário quer depositar

        opcaoEscolhidaEhValida = false;

        do{
            try{
                System.out.println("Entre com a quantidade de notas:");
                qtdDeNotas = reader.nextInt();

                //Naturalmente ao inserir o valor "0"(zero) o sistema não lançaria a exceção permitindo inserir novamente o valor então eu lanço o erro após verificar que o valor entrado é igual a "0"(zero)

                if (qtdDeNotas == 0){
                    throw new InputMismatchException();
                } else {
                    opcaoEscolhidaEhValida = true;
                }

                //Caso haja qualquer erro de Inputmismatch o sistema trata a exceção permitindo que o usuário possa inserir novo valor
            }catch(InputMismatchException e){
                System.out.println("=================================================");
                System.out.println("**Por favor digite apenas valores válidos!**");
                System.out.println("=================================================");
                reader.nextLine();
            }
        }while(!opcaoEscolhidaEhValida);

        //O sistema então confirma com o usuário se a quantidade de cédulas e o valor da nota estão corretos para que possa invocar o deposito()
        opcaoEscolhidaEhValida = false;
        char opc;

        //O usuário deve confirmar a transação com um caractere entre "S"(Sim) ou "N"(Não)
        System.out.println("Você deseja depositar "+qtdDeNotas+" notas de "+valorDaCedula+" reais? [S/N]");
        do{
            try{
                opc = reader.next().charAt(0);
                //O sistema garante que a entrada seja ou "S" ou "N"
                switch (opc){
                    case 'S':
                        deposito(valorDaCedula,qtdDeNotas);
                        opcaoEscolhidaEhValida =true;
                        break;
                    case 'N':
                        opcaoEscolhidaEhValida =true;
                        break;
                    default:
                        throw new InputMismatchException();
                }
                //Caso haja qualquer erro de Inputmismatch o sistema trata a exceção permitindo que o usuário possa inserir novo caractere
            }catch(InputMismatchException e){
                System.out.println("Por favor, digite 'S' ou 'N'");
                reader.nextLine();
            }
        }while(!opcaoEscolhidaEhValida);

        System.out.println("=================================================");
        System.out.println("Transação efetuada com sucesso!");
        System.out.println("=================================================");
        System.out.println("Voltando ao menu principal...");
        reader.nextLine();
    }

    protected void OpcaoSaque() {
        System.out.println("=================================================");
        System.out.println("Você selecionou Saque");
        System.out.println("=================================================");
        System.out.println("Nosso sistema apenas comporta cédulas de 10,20,50 e 100");
        System.out.println("Gostaria de sacar qual valor?");
        boolean valorDeSaqueEhValido = false;
        Scanner reader = new Scanner(System.in);
        int valorDoSaque = 0;

        do{
            try{
                valorDoSaque = reader.nextInt();
                int resto = (valorDoSaque % 10);
                if(resto == 0){
                    valorDeSaqueEhValido = true;
                } else {
                    System.out.println("Somente aceitamos valores que são divisíveis por 10 visto que nosso sistema trabalha somente com as cédulas de 10, 20, 50, 100");
                }
            }catch(InputMismatchException e){
                System.out.println("Por favor, digite um Valor válido!");
                reader.nextLine();
            }
        }while(!valorDeSaqueEhValido);

        boolean EhPossivelSaque;

        EhPossivelSaque = verificaPossibilidadeDeSaque(valorDoSaque);

        if(!EhPossivelSaque){
            System.out.println("Não é possível realizar o saque pois não temos saldo no terminal suficiente");
        } else{
            saque(valorDoSaque);
        }
    }

    /*
        Testadores e avaliadores úteis para as transações do menu
     */

    private boolean verificaPossibilidadeDeSaque(int valorDoSaque) {
        boolean boolTemp = false;
        if (valorDoSaque <= Deposito.getValorSaldoDeposito()){
            boolTemp = true;
            return boolTemp;
        } else {
            return boolTemp;
        }
    }

    protected int OpcaoContarCedulas() {
        return Deposito.getValorSaldoDeposito();
    }

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

        Scanner reader = new Scanner(System.in);

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
        System.out.println("Será necessário um total de:");

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