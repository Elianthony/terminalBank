package terminal.controllers;

import terminal.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        TerminalController controladorDaTransacao = new TerminalController();
        Scanner reader = new Scanner(System.in);
        boolean opcaoEscolhidaEhValida = false;
        int opcaoEscolhida;
        boolean terminalAberto = true;



        //Começa a operação Avaliando se o usuário quer depositar(1), sacar(2), verificar total de cédulas no terminal(3) ou sair(4)

        while (terminalAberto){
            opcaoEscolhida = executaMenuERetornaOpcao(controladorDaTransacao);
            if(opcaoEscolhida == 1){
                OpcaoDeposito(controladorDaTransacao);
            } else {
                if (opcaoEscolhida == 2){
                    OpcaoSaque(controladorDaTransacao);
                } else {
                    if (opcaoEscolhida == 3){
                        System.out.println("Temos o total de "+OpcaoContarCedulas(controladorDaTransacao)+" reais em caixa");
                        System.out.println("==========================================================================");
                        System.out.println("Sendo "+controladorDaTransacao.Deposito.gaveta10.getQntDeCedulas()+" notas de 10");
                        System.out.println("Sendo "+controladorDaTransacao.Deposito.gaveta20.getQntDeCedulas()+" notas de 20");
                        System.out.println("Sendo "+controladorDaTransacao.Deposito.gaveta50.getQntDeCedulas()+" notas de 50");
                        System.out.println("Sendo "+controladorDaTransacao.Deposito.gaveta100.getQntDeCedulas()+" notas de 100");
                        System.out.println("==========================================================================");
                    } else {
                        if (opcaoEscolhida == 4){
                            terminalAberto = false;
                        }
                    }
                }
            }
        }

    }

    private static int OpcaoContarCedulas(TerminalController controladorDaTransacao) {
        return controladorDaTransacao.Deposito.getValorSaldoDeposito();
    }


    private static int executaMenuERetornaOpcao(TerminalController controlador) {
        Scanner reader = new Scanner(System.in);
        TerminalController controladorDaTransacao = controlador;
        int opcaoEscolhida = 0;
        System.out.println("===============================================================================================");
        System.out.println("Olá! Seja bem-vindo ao terminal de transacoes do banco VivaLaVida");
        System.out.println("===============================================================================================");
        System.out.println("Por favor, nosso sistema dispóe hoje de " + controladorDaTransacao.Deposito.getValorSaldoDeposito() + " reais");
        System.out.println("Gostaria de realizar qual transação?");
        System.out.println("1: Depósito");
        System.out.println("2: Saque");
        System.out.println("3: Verificar saldo de cédulas no terminal");
        System.out.println("4: Sair");

        boolean opcaoEscolhidaEhValida = false;

        do{
            try{
                System.out.println("Entre com a opção:");
                opcaoEscolhida = reader.nextInt();
                switch (opcaoEscolhida){
                    case 1:
                        opcaoEscolhidaEhValida = true;
                        break;
                    case 2:
                        opcaoEscolhidaEhValida = true;
                        break;
                    case 3:
                        opcaoEscolhidaEhValida = true;
                        break;
                    case 4:
                        opcaoEscolhidaEhValida = true;
                        break;
                    default:
                        System.out.println("Por favor selecione uma opção válida!");
                }
            }catch (InputMismatchException e){
                System.out.println("=================================================");
                System.out.println("**Por favor digite apenas opções válidas!**");
                System.out.println("=================================================");
                System.out.println("Gostaria de realizar qual transação?");
                System.out.println("1: Depósito");
                System.out.println("2: Saque");
                System.out.println("3: Verificar saldo de cédulas no terminal");
                reader.nextLine();
            }
        }while(!opcaoEscolhidaEhValida);
        return opcaoEscolhida;
    }

    private static void OpcaoDeposito(TerminalController controlador){
        //Iniciando avaliação de depósito
        System.out.println("=================================================");
        System.out.println("Você selecionou Depósito");
        System.out.println("=================================================");
        System.out.println("Nosso sistema apenas comporta cédulas de 10,20,50 e 100");
        System.out.println("Gostaria de depositar qual cédula?");
        TerminalController controladorDaTransacao = controlador;
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
                        controladorDaTransacao.deposito(valorDaCedula,qtdDeNotas);
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
    }

    private static void OpcaoSaque(TerminalController controlador) {
        System.out.println("=================================================");
        System.out.println("Você selecionou Saque");
        System.out.println("=================================================");
        System.out.println("Nosso sistema apenas comporta cédulas de 10,20,50 e 100");
        System.out.println("Gostaria de sacar qual valor?");
        TerminalController controladorDaTransacao = controlador;
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

        EhPossivelSaque = verificaPossibilidadeDeSaque(valorDoSaque, controladorDaTransacao);

        if(!EhPossivelSaque){
            System.out.println("Não é possível realizar o saque pois não temos saldo no terminal suficiente");
        } else{
            controladorDaTransacao.saque(valorDoSaque);
        }
    }

    private static boolean verificaPossibilidadeDeSaque(int valorDoSaque, TerminalController controlador) {
        boolean boolTemp = false;
        if (valorDoSaque <= controlador.Deposito.getValorSaldoDeposito()){
            boolTemp = true;
            return boolTemp;
        } else {
            return boolTemp;
        }
    }
}
