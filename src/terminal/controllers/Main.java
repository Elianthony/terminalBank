package terminal.controllers;

import terminal.controllers.*;
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
                controladorDaTransacao.OpcaoDeposito();
            } else {
                if (opcaoEscolhida == 2){
                    controladorDaTransacao.OpcaoSaque();
                } else {
                    if (opcaoEscolhida == 3){
                        System.out.println("Temos o total de "+controladorDaTransacao.OpcaoContarCedulas()+" reais em caixa");
                        System.out.println("==========================================================================");
                        System.out.println("Sendo "+controladorDaTransacao.Deposito.gaveta10.getQntDeCedulas()+" notas de 10");
                        System.out.println("Sendo "+controladorDaTransacao.Deposito.gaveta20.getQntDeCedulas()+" notas de 20");
                        System.out.println("Sendo "+controladorDaTransacao.Deposito.gaveta50.getQntDeCedulas()+" notas de 50");
                        System.out.println("Sendo "+controladorDaTransacao.Deposito.gaveta100.getQntDeCedulas()+" notas de 100");
                        System.out.println("==========================================================================");
                        System.out.println("Pressione <ENTER> para voltar ao menu principal");
                        reader.nextLine();
                    } else {
                        if (opcaoEscolhida == 4){
                            terminalAberto = false;
                        }
                    }
                }
            }
        }
    }

    private static int executaMenuERetornaOpcao(TerminalController controlador) {
        Scanner reader = new Scanner(System.in);
        int opcaoEscolhida = 0;
        System.out.println("===============================================================================================");
        System.out.println("Olá! Seja bem-vindo ao terminal de transacoes do banco VivaLaVida");
        System.out.println("===============================================================================================");
        System.out.println("Por favor, nosso sistema dispóe hoje de " + controlador.Deposito.getValorSaldoDeposito() + " reais");
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

}
