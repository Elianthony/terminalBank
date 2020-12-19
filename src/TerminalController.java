public class TerminalController {
    TerminalDeposit Deposito = new TerminalDeposit();

    protected void deposito(int valorDeDeposito, int qntDeNotas){
        switch(valorDeDeposito){
            case 10:
                deposita10(qntDeNotas);
                break;
            case 20:
                deposita20(qntDeNotas);
                break;
            case 50:
                deposita50(qntDeNotas);
                break;
            case 100:
                deposita100(qntDeNotas);
                break;
            default:
                System.out.println("O sistema não trabalha com essa nota");
        }
    }

    private void deposita10(int qntDeNotas){
        Deposito.gaveta10.setQntDeCedulas(Deposito.gaveta10.getQntDeCedulas()+qntDeNotas);
    }

    private void deposita20(int qntDeNotas){
        Deposito.gaveta20.setQntDeCedulas(Deposito.gaveta20.getQntDeCedulas()+qntDeNotas);
    }

    private void deposita50(int qntDeNotas){
        Deposito.gaveta50.setQntDeCedulas(Deposito.gaveta50.getQntDeCedulas()+qntDeNotas);
    }

    private void deposita100(int qntDeNotas){
        Deposito.gaveta100.setQntDeCedulas(Deposito.gaveta100.getQntDeCedulas()+qntDeNotas);
    }

    protected void saque(int valorDeSaque){

    }

}