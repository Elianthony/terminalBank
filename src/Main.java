public class Main {

    public static void main(String[] args) {
        TerminalController controladorDaTransacao = new TerminalController();

        System.out.println("Olá! Seja bem-vindo ao terminal de transacoes do banco VivaLaVida");
        System.out.println("Por favor, nosso sistema dispóe hoje de " + controladorDaTransacao.Deposito.getValorSaldoDeposito() + " reais");
        System.out.println("Gostaria de realizar qual transação?");
        System.out.println("1: Depósito");
        System.out.println("1: Saque");
        controladorDaTransacao.deposito(10,5);
        controladorDaTransacao.deposito(10, 3);
        System.out.println(controladorDaTransacao.Deposito.getValorSaldoDeposito());

    }
}
