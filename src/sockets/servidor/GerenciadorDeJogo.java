package sockets.servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import protocolo.Mensagens;

public class GerenciadorDeJogo {

    private static Socket xis;
    private static Socket bola;
    private static char[] tabuleiro = null;
    private static boolean jogadorXis = false;
    private static boolean pronto = false;

    public static void inicializar(Socket xis, Socket bola) {
        GerenciadorDeJogo.xis = xis;
        GerenciadorDeJogo.bola = bola;
        GerenciadorDeJogo.iniciarTabuleiro();
        GerenciadorDeJogo.mandarMsgXis(Mensagens.SUA_VEZ_DE_JOGAR);
    }

    public static void inicializarXis(Socket xis) {
        GerenciadorDeJogo.xis = xis;
    }

    public static void finalizar(){
        try {
            GerenciadorDeJogo.xis.close();
            GerenciadorDeJogo.xis = null;
            GerenciadorDeJogo.bola.close();
            GerenciadorDeJogo.bola = null;
            GerenciadorDeJogo.tabuleiro = null;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }

    private static void iniciarTabuleiro() {
        tabuleiro = new char[9];
        for (int i = 0; i < tabuleiro.length; i++) {
            tabuleiro[i] = ' ';
        }
        jogadorXis = true;
        pronto = true;
    }

    private static char verificarTabuleiro() {

        // Verifica as linhas
        if (tabuleiro[0] == tabuleiro[1] && tabuleiro[0] == tabuleiro[2]) {
            return tabuleiro[0];
        } else if (tabuleiro[3] == tabuleiro[4] && tabuleiro[3] == tabuleiro[5]) {
            return tabuleiro[3];
        } else if (tabuleiro[6] == tabuleiro[7] && tabuleiro[6] == tabuleiro[8]) {
            return tabuleiro[6];
        } // Verifica as colunas
        else if (tabuleiro[0] == tabuleiro[3] && tabuleiro[0] == tabuleiro[6]) {
            return tabuleiro[0];
        } else if (tabuleiro[1] == tabuleiro[4] && tabuleiro[1] == tabuleiro[7]) {
            return tabuleiro[1];
        } else if (tabuleiro[2] == tabuleiro[5] && tabuleiro[2] == tabuleiro[8]) {
            return tabuleiro[2];
        } // Verifica diagonais
        else if (tabuleiro[0] == tabuleiro[4] && tabuleiro[0] == tabuleiro[8]) {
            return tabuleiro[0];
        } else if (tabuleiro[2] == tabuleiro[4] && tabuleiro[2] == tabuleiro[6]) {
            return tabuleiro[2];
        }

        for (int i = 0; i < tabuleiro.length; i++) {
            if (tabuleiro[i] == ' ') {
                return ' ';
            }
        }

        return 'E';

    }

    private static boolean jogarXis(int i) {
        if(tabuleiro[i] == ' '){
            tabuleiro[i] = 'X';
            return true;
        } else
            return false;
    }

    private static boolean jogarBola(int i) {
        if(tabuleiro[i] == ' '){
            tabuleiro[i] = 'O';
            return true;
        } else
            return false;
    }

    private static void mandarMsgXis(String msg) {
        try {
            PrintStream saida = new PrintStream(xis.getOutputStream());
            saida.println(msg);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }

    private static void mandarMsgBola(String msg) {
        try {
            PrintStream saida = new PrintStream(bola.getOutputStream());
            saida.println(msg);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }

    private static void testarJogo(String nome){
        char t = verificarTabuleiro();
        if (t == 'X') {
            GerenciadorDeJogo.mandarMsgXis(Mensagens.XIS_GANHOU);
            GerenciadorDeJogo.mandarMsgBola(Mensagens.XIS_GANHOU);
            GerenciadorDeJogo.pronto = false;
        } else if (t == 'O') {
            GerenciadorDeJogo.mandarMsgXis(Mensagens.BOLA_GANHOU);
            GerenciadorDeJogo.mandarMsgBola(Mensagens.BOLA_GANHOU);
            GerenciadorDeJogo.pronto = false;
        } else if (t == 'E') {
            GerenciadorDeJogo.mandarMsgXis(Mensagens.EMPATOU);
            GerenciadorDeJogo.mandarMsgBola(Mensagens.EMPATOU);
            GerenciadorDeJogo.pronto = false;
        } else {
            if(nome.trim().equals(Mensagens.BOLA))
                GerenciadorDeJogo.mandarMsgXis(Mensagens.SUA_VEZ_DE_JOGAR);
            else
                GerenciadorDeJogo.mandarMsgBola(Mensagens.SUA_VEZ_DE_JOGAR);
        }
    }

    public static synchronized void executarAcao(String acao, String nome) {

        if (nome.equals(Mensagens.XIS)) {

            if (pronto) {

                if (acao.trim().equals(Mensagens.XIS_1_1)) {

                    if (jogadorXis) {
                        if (jogarXis(0)) {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.XIS_1_1);
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.XIS_1_1);
                            GerenciadorDeJogo.testarJogo(nome);
                            GerenciadorDeJogo.jogadorXis = false;
                        } else {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.ESSA_JOGADA_JA_FOI_FEITA);
                        }
                    } else {
                        GerenciadorDeJogo.mandarMsgXis(Mensagens.NAO_E_SUA_VEZ_DE_JOGAR);
                    }

                } else if (acao.trim().equals(Mensagens.XIS_1_2)) {

                    if (jogadorXis) {
                        if (jogarXis(1)) {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.XIS_1_2);
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.XIS_1_2);
                            GerenciadorDeJogo.testarJogo(nome);
                            GerenciadorDeJogo.jogadorXis = false;
                        } else {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.ESSA_JOGADA_JA_FOI_FEITA);
                        }
                    } else {
                        GerenciadorDeJogo.mandarMsgXis(Mensagens.NAO_E_SUA_VEZ_DE_JOGAR);
                    }

                } else if (acao.trim().equals(Mensagens.XIS_1_3)) {

                    if (jogadorXis) {
                        if (jogarXis(2)) {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.XIS_1_3);
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.XIS_1_3);
                            GerenciadorDeJogo.testarJogo(nome);
                            GerenciadorDeJogo.jogadorXis = false;
                        } else {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.ESSA_JOGADA_JA_FOI_FEITA);
                        }
                    } else {
                        GerenciadorDeJogo.mandarMsgXis(Mensagens.NAO_E_SUA_VEZ_DE_JOGAR);
                    }

                } else if (acao.trim().equals(Mensagens.XIS_2_1)) {

                    if (jogadorXis) {
                        if (jogarXis(3)) {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.XIS_2_1);
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.XIS_2_1);
                            GerenciadorDeJogo.testarJogo(nome);
                            GerenciadorDeJogo.jogadorXis = false;
                        } else {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.ESSA_JOGADA_JA_FOI_FEITA);
                        }
                    } else {
                        GerenciadorDeJogo.mandarMsgXis(Mensagens.NAO_E_SUA_VEZ_DE_JOGAR);
                    }

                } else if (acao.trim().equals(Mensagens.XIS_2_2)) {

                    if (jogadorXis) {
                        if (jogarXis(4)) {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.XIS_2_2);
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.XIS_2_2);
                            GerenciadorDeJogo.testarJogo(nome);
                            GerenciadorDeJogo.jogadorXis = false;
                        } else {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.ESSA_JOGADA_JA_FOI_FEITA);
                        }
                    } else {
                        GerenciadorDeJogo.mandarMsgXis(Mensagens.NAO_E_SUA_VEZ_DE_JOGAR);
                    }

                } else if (acao.trim().equals(Mensagens.XIS_2_3)) {

                    if (jogadorXis) {
                        if (jogarXis(5)) {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.XIS_2_3);
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.XIS_2_3);
                            GerenciadorDeJogo.testarJogo(nome);
                            GerenciadorDeJogo.jogadorXis = false;
                        } else {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.ESSA_JOGADA_JA_FOI_FEITA);
                        }
                    } else {
                        GerenciadorDeJogo.mandarMsgXis(Mensagens.NAO_E_SUA_VEZ_DE_JOGAR);
                    }

                } else if (acao.trim().equals(Mensagens.XIS_3_1)) {

                    if (jogadorXis) {
                        if (jogarXis(6)) {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.XIS_3_1);
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.XIS_3_1);
                            GerenciadorDeJogo.testarJogo(nome);
                            GerenciadorDeJogo.jogadorXis = false;
                        } else {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.ESSA_JOGADA_JA_FOI_FEITA);
                        }
                    } else {
                        GerenciadorDeJogo.mandarMsgXis(Mensagens.NAO_E_SUA_VEZ_DE_JOGAR);
                    }

                } else if (acao.trim().equals(Mensagens.XIS_3_2)) {

                    if (jogadorXis) {
                        if (jogarXis(7)) {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.XIS_3_2);
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.XIS_3_2);
                            GerenciadorDeJogo.testarJogo(nome);
                            GerenciadorDeJogo.jogadorXis = false;
                        } else {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.ESSA_JOGADA_JA_FOI_FEITA);
                        }
                    } else {
                        GerenciadorDeJogo.mandarMsgXis(Mensagens.NAO_E_SUA_VEZ_DE_JOGAR);
                    }

                } else if (acao.trim().equals(Mensagens.XIS_3_3)) {

                    if (jogadorXis) {
                        if (jogarXis(8)) {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.XIS_3_3);
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.XIS_3_3);
                            GerenciadorDeJogo.testarJogo(nome);
                            GerenciadorDeJogo.jogadorXis = false;
                        } else {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.ESSA_JOGADA_JA_FOI_FEITA);
                        }
                    } else {
                        GerenciadorDeJogo.mandarMsgXis(Mensagens.NAO_E_SUA_VEZ_DE_JOGAR);
                    }

                }
            } else {
                if(acao.trim().startsWith("X#"))
                    GerenciadorDeJogo.mandarMsgXis(Mensagens.AGUARDANDO_OUTRO_JOGADOR);
            }
            
            if (acao.trim().equals(Mensagens.FINALIZAR_TRANSMISSAO)) {

                GerenciadorDeJogo.mandarMsgXis(Mensagens.FINALIZAR_TRANSMISSAO);
                GerenciadorDeJogo.mandarMsgBola(Mensagens.OPONENTE_SAIU);
                GerenciadorDeJogo.mandarMsgBola(Mensagens.FINALIZAR_TRANSMISSAO);
                GerenciadorDeJogo.finalizar();

            } else if (acao.trim().equals(Mensagens.REINICIAR_JOGO)) {

                iniciarTabuleiro();
                GerenciadorDeJogo.pronto = true;
                GerenciadorDeJogo.jogadorXis = true;
                GerenciadorDeJogo.mandarMsgXis(Mensagens.REINICIAR_JOGO);
                GerenciadorDeJogo.mandarMsgXis(Mensagens.SUA_VEZ_DE_JOGAR);
                GerenciadorDeJogo.mandarMsgBola(Mensagens.REINICIAR_JOGO);

            } else {

                GerenciadorDeJogo.mandarMsgXis(Mensagens.MSG_NAO_ENTENDIDA);

            }

        } else if (nome.equals(Mensagens.BOLA)) {

            if (pronto) {
                if (acao.trim().equals(Mensagens.BOLA_1_1)) {

                    if (!jogadorXis) {
                        if (jogarBola(0)) {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.BOLA_1_1);
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.BOLA_1_1);
                            GerenciadorDeJogo.testarJogo(nome);
                            GerenciadorDeJogo.jogadorXis = true;
                        } else {
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.ESSA_JOGADA_JA_FOI_FEITA);
                        }
                    } else {
                        GerenciadorDeJogo.mandarMsgBola(Mensagens.NAO_E_SUA_VEZ_DE_JOGAR);
                    }

                } else if (acao.trim().equals(Mensagens.BOLA_1_2)) {

                    if (!jogadorXis) {
                        if (jogarBola(1)) {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.BOLA_1_2);
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.BOLA_1_2);
                            GerenciadorDeJogo.testarJogo(nome);
                            GerenciadorDeJogo.jogadorXis = true;
                        } else {
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.ESSA_JOGADA_JA_FOI_FEITA);
                        }
                    } else {
                        GerenciadorDeJogo.mandarMsgBola(Mensagens.NAO_E_SUA_VEZ_DE_JOGAR);
                    }

                } else if (acao.trim().equals(Mensagens.BOLA_1_3)) {

                    if (!jogadorXis) {
                        if (jogarBola(2)) {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.BOLA_1_3);
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.BOLA_1_3);
                            GerenciadorDeJogo.testarJogo(nome);
                            GerenciadorDeJogo.jogadorXis = true;
                        } else {
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.ESSA_JOGADA_JA_FOI_FEITA);
                        }
                    } else {
                        GerenciadorDeJogo.mandarMsgBola(Mensagens.NAO_E_SUA_VEZ_DE_JOGAR);
                    }

                } else if (acao.trim().equals(Mensagens.BOLA_2_1)) {

                    if (!jogadorXis) {
                        if (jogarBola(3)) {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.BOLA_2_1);
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.BOLA_2_1);
                            GerenciadorDeJogo.testarJogo(nome);
                            GerenciadorDeJogo.jogadorXis = true;
                        } else {
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.ESSA_JOGADA_JA_FOI_FEITA);
                        }
                    } else {
                        GerenciadorDeJogo.mandarMsgBola(Mensagens.NAO_E_SUA_VEZ_DE_JOGAR);
                    }

                } else if (acao.trim().equals(Mensagens.BOLA_2_2)) {

                    if (!jogadorXis) {
                        if (jogarBola(4)) {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.BOLA_2_2);
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.BOLA_2_2);
                            GerenciadorDeJogo.testarJogo(nome);
                            GerenciadorDeJogo.jogadorXis = true;
                        } else {
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.ESSA_JOGADA_JA_FOI_FEITA);
                        }
                    } else {
                        GerenciadorDeJogo.mandarMsgBola(Mensagens.NAO_E_SUA_VEZ_DE_JOGAR);
                    }

                } else if (acao.trim().equals(Mensagens.BOLA_2_3)) {

                    if (!jogadorXis) {
                        if (jogarBola(5)) {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.BOLA_2_3);
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.BOLA_2_3);
                            GerenciadorDeJogo.testarJogo(nome);
                            GerenciadorDeJogo.jogadorXis = true;
                        } else {
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.ESSA_JOGADA_JA_FOI_FEITA);
                        }
                    } else {
                        GerenciadorDeJogo.mandarMsgBola(Mensagens.NAO_E_SUA_VEZ_DE_JOGAR);
                    }

                } else if (acao.trim().equals(Mensagens.BOLA_3_1)) {

                    if (!jogadorXis) {
                        if (jogarBola(6)) {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.BOLA_3_1);
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.BOLA_3_1);
                            GerenciadorDeJogo.testarJogo(nome);
                            GerenciadorDeJogo.jogadorXis = true;
                        } else {
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.ESSA_JOGADA_JA_FOI_FEITA);
                        }
                    } else {
                        GerenciadorDeJogo.mandarMsgBola(Mensagens.NAO_E_SUA_VEZ_DE_JOGAR);
                    }

                } else if (acao.trim().equals(Mensagens.BOLA_3_2)) {

                    if (!jogadorXis) {
                        if (jogarBola(7)) {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.BOLA_3_2);
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.BOLA_3_2);
                            GerenciadorDeJogo.testarJogo(nome);
                            GerenciadorDeJogo.jogadorXis = true;
                        } else {
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.ESSA_JOGADA_JA_FOI_FEITA);
                        }
                    } else {
                        GerenciadorDeJogo.mandarMsgBola(Mensagens.NAO_E_SUA_VEZ_DE_JOGAR);
                    }

                } else if (acao.trim().equals(Mensagens.BOLA_3_3)) {

                    if (!jogadorXis) {
                        if (jogarBola(8)) {
                            GerenciadorDeJogo.mandarMsgXis(Mensagens.BOLA_3_3);
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.BOLA_3_3);
                            GerenciadorDeJogo.testarJogo(nome);
                            GerenciadorDeJogo.jogadorXis = true;
                        } else {
                            GerenciadorDeJogo.mandarMsgBola(Mensagens.ESSA_JOGADA_JA_FOI_FEITA);
                        }
                    } else {
                        GerenciadorDeJogo.mandarMsgBola(Mensagens.NAO_E_SUA_VEZ_DE_JOGAR);
                    }

                }
            } else {
                if(acao.trim().startsWith("O#"))
                    GerenciadorDeJogo.mandarMsgBola(Mensagens.AGUARDANDO_OUTRO_JOGADOR);
            }

            if (acao.trim().equals(Mensagens.FINALIZAR_TRANSMISSAO)) {

                GerenciadorDeJogo.mandarMsgXis(Mensagens.FINALIZAR_TRANSMISSAO);
                GerenciadorDeJogo.mandarMsgBola(Mensagens.FINALIZAR_TRANSMISSAO);
                GerenciadorDeJogo.finalizar();

            } else if (acao.trim().equals(Mensagens.REINICIAR_JOGO)) {

                iniciarTabuleiro();
                GerenciadorDeJogo.pronto = true;
                GerenciadorDeJogo.jogadorXis = true;
                GerenciadorDeJogo.mandarMsgXis(Mensagens.REINICIAR_JOGO);
                GerenciadorDeJogo.mandarMsgXis(Mensagens.SUA_VEZ_DE_JOGAR);
                GerenciadorDeJogo.mandarMsgBola(Mensagens.REINICIAR_JOGO);

            } else {

                GerenciadorDeJogo.mandarMsgBola(Mensagens.MSG_NAO_ENTENDIDA);

            }
        }
    }
}
