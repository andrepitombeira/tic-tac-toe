package pattern;

import jogodavelha.Tabuleiro;

public class SingletonPainelTabuleiro {

    private static Tabuleiro tabuleiro;

    public static void inicializar(Tabuleiro tabuleiro){
        SingletonPainelTabuleiro.tabuleiro = tabuleiro;
    }

    public static void desenharFigura(char c, int x){
        if(c == 'X')
            SingletonPainelTabuleiro.tabuleiro.marcarXis(x);
        if(c == 'O')
            SingletonPainelTabuleiro.tabuleiro.marcarBola(x);
    }

    public static void limparTabuleiro() {
        tabuleiro.iniciarTabuleiro();
        tabuleiro.paint(tabuleiro.getGraphics());
    }

}
