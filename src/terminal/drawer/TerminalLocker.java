package terminal.drawer;

public class TerminalLocker {
    int valorDaCedula;
    int qntDeCedulas = 0;

    TerminalLocker(int valorDaCedula) {
        this.valorDaCedula = valorDaCedula;
    }

    public int getQntDeCedulas() {
        return qntDeCedulas;
    }

    public void setQntDeCedulas(int qntDeCedulas) {
        this.qntDeCedulas = qntDeCedulas;
    }

    protected void setValorDaCedulaNoLocker(int valorDaCedula) {
        this.valorDaCedula = valorDaCedula;
    }

    protected int getValorDaCedulaNoLocker() {
        return valorDaCedula;
    }

    protected int getSaldoLocker() {
        return valorDaCedula * qntDeCedulas;
    }
}
