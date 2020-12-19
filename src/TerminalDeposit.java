public class TerminalDeposit {

    TerminalLocker gaveta10 = new TerminalLocker(10);
    TerminalLocker gaveta20 = new TerminalLocker(20);
    TerminalLocker gaveta50 = new TerminalLocker(50);
    TerminalLocker gaveta100 = new TerminalLocker(100);

    protected int getValorSaldoDeposito() {
        return gaveta10.getSaldoLocker() + gaveta20.getSaldoLocker() + gaveta50.getSaldoLocker() + gaveta100.getSaldoLocker();
    }
}
