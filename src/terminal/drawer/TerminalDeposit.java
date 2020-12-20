package terminal.drawer;

public class TerminalDeposit {

    public TerminalLocker gaveta10 = new TerminalLocker(10);
    public TerminalLocker gaveta20 = new TerminalLocker(20);
    public TerminalLocker gaveta50 = new TerminalLocker(50);
    public TerminalLocker gaveta100 = new TerminalLocker(100);

    public int getValorSaldoDeposito() {
        return gaveta10.getSaldoLocker() + gaveta20.getSaldoLocker() + gaveta50.getSaldoLocker() + gaveta100.getSaldoLocker();
    }
}
