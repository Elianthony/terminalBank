package terminal.controllers;

import terminal.drawer.TerminalDeposit;
import terminal.drawer.TerminalLocker;

public class TerminalOperator {
    /*
        Métodos responsáveis pelo depósito em cada gaveta(Locker) de acordo com o valor da cédula que se deve mexer
     */

    protected void deposita10(TerminalDeposit Deposito, int qntDeNotas){
        Deposito.gaveta10.setQntDeCedulas(Deposito.gaveta10.getQntDeCedulas()+qntDeNotas);
    }

    protected void deposita20(TerminalDeposit Deposito, int qntDeNotas){
        Deposito.gaveta20.setQntDeCedulas(Deposito.gaveta20.getQntDeCedulas()+qntDeNotas);
    }

    protected void deposita50(TerminalDeposit Deposito, int qntDeNotas){
        Deposito.gaveta50.setQntDeCedulas(Deposito.gaveta50.getQntDeCedulas()+qntDeNotas);
    }

    protected void deposita100(TerminalDeposit Deposito, int qntDeNotas){
        Deposito.gaveta100.setQntDeCedulas(Deposito.gaveta100.getQntDeCedulas()+qntDeNotas);
    }

    /*
        Métodos responsáveis por selecionar as cédulas de saque
     */

    protected int fatoraQtdDeNotas(int valorDeSaque, int valorDaCedula) {
        int qtdDeNotas = 0;
        switch (valorDaCedula){
            case 100:
                qtdDeNotas = getQtdDeNotas100ParaSaque(valorDeSaque);
                break;
            case 50:
                qtdDeNotas = getQtdDeNotas50ParaSaque(valorDeSaque);
                break;
            case 20:
                qtdDeNotas = getQtdDeNotas20ParaSaque(valorDeSaque);
                break;
            case 10:
                qtdDeNotas = getQtdDeNotas10ParaSaque(valorDeSaque);
                break;
        }
        return qtdDeNotas;
    }

    //Opera a subtração das cédulas já processadas
    protected int subtraiCedulasJaProcessadas(int qtdDeNotas, int valorDaCedula, int valorParaSerSacado){
        int valorJaContado = qtdDeNotas * valorDaCedula;
        return valorParaSerSacado - valorJaContado;
    }

    /*
        Métodos privados responsáveis pela avaliação em cada gaveta(Locker) de acordo com o valor preterido pelo saque
    */

    private int getQtdDeNotas10ParaSaque(int valor){
        return valor / 10;
    }

    private int getQtdDeNotas20ParaSaque(int valor){
        return valor / 20;
    }

    private int getQtdDeNotas50ParaSaque(int valor){
        return valor / 50;
    }

    private int getQtdDeNotas100ParaSaque(int valor){
        return valor / 100;
    }

    /*
        Método OperaSaque que recebe um depósito e avalia qual nota ele vai tirar
     */

    protected void operaSaque(TerminalDeposit Deposito, int valorDaCedula, int qtdDeNotas){
        switch(valorDaCedula){
            case 10:
                saca10(Deposito, qtdDeNotas);
                break;
            case 20:
                saca20(Deposito, qtdDeNotas);
                break;
            case 50:
                saca50(Deposito, qtdDeNotas);
                break;
            case 100:
                saca100(Deposito, qtdDeNotas);
                break;
        }
    }

    /*
        Métodos Saca responsáveis por subtraírem as cédulas do depósito de acordo com a gaveta(Locker)
     */

    private void saca10(TerminalDeposit Deposito, int qtdDeNotas) {
        Deposito.gaveta10.setQntDeCedulas(Deposito.gaveta10.getQntDeCedulas() - qtdDeNotas);
    }

    private void saca20(TerminalDeposit Deposito, int qtdDeNotas) {
        Deposito.gaveta20.setQntDeCedulas(Deposito.gaveta20.getQntDeCedulas() - qtdDeNotas);
    }

    private void saca50(TerminalDeposit Deposito, int qtdDeNotas) {
        Deposito.gaveta50.setQntDeCedulas(Deposito.gaveta50.getQntDeCedulas() - qtdDeNotas);
    }

    private void saca100(TerminalDeposit Deposito, int qtdDeNotas) {
        Deposito.gaveta100.setQntDeCedulas(Deposito.gaveta100.getQntDeCedulas() - qtdDeNotas);
    }


}
