package sockets.cliente;

import javax.swing.JOptionPane;
import jogodavelha.ReiniciarJogo;
import pattern.SingletonAreaInfo;
import pattern.SingletonConexao;
import pattern.SingletonContadores;
import pattern.SingletonPainelTabuleiro;
import protocolo.Mensagens;

public class GerenciadorDeAcoes {

    public static void executar(String acao, String nome){

        if(acao.trim().equals(Mensagens.REINICIAR_JOGO)){

            SingletonAreaInfo.escrever("COMECANDO UM NOVO JOGO...");
            JOptionPane.showMessageDialog(null, "COMECANDO UM NOVO JOGO!!!");
            SingletonPainelTabuleiro.limparTabuleiro();

        } else if(acao.trim().equals(Mensagens.FINALIZAR_TRANSMISSAO)){

            SingletonAreaInfo.escrever("CONEXÃO ENCERRADA");
            SingletonConexao.finalizar();
            JOptionPane.showMessageDialog(null, "CONEXÃO ENCERRADA!!!");
            SingletonContadores.reiniciarContadores();
            SingletonPainelTabuleiro.limparTabuleiro();

        } else if(acao.trim().equals(Mensagens.AGUARDANDO_OUTRO_JOGADOR)){

            SingletonAreaInfo.escrever("AGUARDANDO OUTRO JOGADOR!!!");
            JOptionPane.showMessageDialog(null, "AGUARDANDO OUTRO JOGADOR!!!");

        } else if(acao.trim().equals(Mensagens.OPONENTE_SAIU)){

            SingletonAreaInfo.escrever("SEU OPONENTE SAIU DO JOGO!!!");
            JOptionPane.showMessageDialog(null, "SEU OPONENTE SAIU DO JOGO!!!");
            SingletonContadores.reiniciarContadores();
            SingletonPainelTabuleiro.limparTabuleiro();

        } else if(acao.trim().equals(Mensagens.NAO_E_SUA_VEZ_DE_JOGAR)){

            SingletonAreaInfo.escrever("NÃO É SUA VEZ DE JOGAR!!!");
            JOptionPane.showMessageDialog(null, "NÃO É SUA VEZ DE JOGAR!!!");

        } else if(acao.trim().equals(Mensagens.EMPATOU)){

            SingletonAreaInfo.escrever("O JOGO EMPATOU!!!");
            JOptionPane.showMessageDialog(null, "O JOGO EMPATOU!!!");
            SingletonContadores.addEmpate();
            if(nome.equals(Mensagens.BOLA)){
                new ReiniciarJogo();
            }

        } else if(acao.trim().equals(Mensagens.XIS_GANHOU)){

            if(nome.equals(Mensagens.XIS)){
                SingletonAreaInfo.escrever("PARABENS VOCE GANHOU!!!");
                JOptionPane.showMessageDialog(null, "PARABENS VOCE GANHOU!!!");
                SingletonContadores.addVitoria();
                //SingletonPainelTabuleiro.limparTabuleiro();
            } else{
                SingletonAreaInfo.escrever("VOCE PERDEU, TENTE NOVAMENTE!!!");
                JOptionPane.showMessageDialog(null, "VOCE PERDEU, TENTE NOVAMENTE!!!");
                SingletonContadores.addDerrota();
                new ReiniciarJogo();
            }

        }  else if(acao.trim().equals(Mensagens.BOLA_GANHOU)){

            if(nome.equals(Mensagens.BOLA)){
                SingletonAreaInfo.escrever("PARABENS VOCE GANHOU!!!");
                JOptionPane.showMessageDialog(null, "PARABENS VOCE GANHOU!!!");
                SingletonContadores.addVitoria();
                new ReiniciarJogo();
                //SingletonPainelTabuleiro.limparTabuleiro();
            } else{
                SingletonAreaInfo.escrever("VOCE PERDEU, TENTE NOVAMENTE!!!");
                JOptionPane.showMessageDialog(null, "VOCE PERDEU, TENTE NOVAMENTE!!!");
                SingletonContadores.addDerrota();
                //SingletonPainelTabuleiro.limparTabuleiro();
            }
            
        } else if(acao.trim().equals(Mensagens.XIS)){
            SingletonAreaInfo.escrever("VOCE É O JOGADOR XIS...\n AGUARDE OUTRO JOGADOR ENTRAR...");
            JOptionPane.showMessageDialog(null, "VOCE É O JOGADOR XIS...\n AGUARDE OUTRO JOGADOR ENTRAR...");
        } else if(acao.trim().equals(Mensagens.BOLA)){
            SingletonAreaInfo.escrever("VOCE É O JOGADOR BOLA...");
            JOptionPane.showMessageDialog(null, "VOCE É O JOGADOR BOLA...");
        } else if(acao.trim().equals(Mensagens.OK)){
            SingletonAreaInfo.escrever("VOCE É O JOGADOR XIS...\n AGUARDE OUTRO JOGADOR ENTRAR...");
            JOptionPane.showMessageDialog(null, "VOCE É O JOGADOR XIS...\n AGUARDE OUTRO JOGADOR ENTRAR...");
        }  else if(acao.trim().equals(Mensagens.SUA_VEZ_DE_JOGAR)){
            SingletonAreaInfo.escrever("SUA VEZ DE JOGAR!!!");
            JOptionPane.showMessageDialog(null, "SUA VEZ DE JOGAR!!!");
        }

        // jogadas para XIS
        else if(acao.trim().equals(Mensagens.XIS_1_1)){
            if(nome.equals(Mensagens.XIS))
                SingletonAreaInfo.escrever("SUA JOGADA FOI LINHA 1 COLUNA 1");
            else
                SingletonAreaInfo.escrever("SEU OPONENTE JOGOU LINHA 1 COLUNA 1");
            SingletonPainelTabuleiro.desenharFigura('X', 0);
        } else if(acao.trim().equals(Mensagens.XIS_1_2)){
            if(nome.equals(Mensagens.XIS))
                SingletonAreaInfo.escrever("SUA JOGADA FOI LINHA 1 COLUNA 2");
            else
                SingletonAreaInfo.escrever("SEU OPONENTE JOGOU LINHA 1 COLUNA 2");
            SingletonPainelTabuleiro.desenharFigura('X', 1);
        } else if(acao.trim().equals(Mensagens.XIS_1_3)){
            if(nome.equals(Mensagens.XIS))
                SingletonAreaInfo.escrever("SUA JOGADA FOI LINHA 1 COLUNA 3");
            else
                SingletonAreaInfo.escrever("SEU OPONENTE JOGOU LINHA 1 COLUNA 3");
            SingletonPainelTabuleiro.desenharFigura('X', 2);
        } else if(acao.trim().equals(Mensagens.XIS_2_1)){
            if(nome.equals(Mensagens.XIS))
                SingletonAreaInfo.escrever("SUA JOGADA FOI LINHA 2 COLUNA 1");
            else
                SingletonAreaInfo.escrever("SEU OPONENTE JOGOU LINHA 2 COLUNA 1");
            SingletonPainelTabuleiro.desenharFigura('X', 3);
        }else if(acao.trim().equals(Mensagens.XIS_2_2)){
            if(nome.equals(Mensagens.XIS))
                SingletonAreaInfo.escrever("SUA JOGADA FOI LINHA 2 COLUNA 2");
            else
                SingletonAreaInfo.escrever("SEU OPONENTE JOGOU LINHA 2 COLUNA 2");
            SingletonPainelTabuleiro.desenharFigura('X', 4);
        }else if(acao.trim().equals(Mensagens.XIS_2_3)){
            if(nome.equals(Mensagens.XIS))
                SingletonAreaInfo.escrever("SUA JOGADA FOI LINHA 2 COLUNA 3");
            else
                SingletonAreaInfo.escrever("SEU OPONENTE JOGOU LINHA 2 COLUNA 3");
            SingletonPainelTabuleiro.desenharFigura('X', 5);
        }else if(acao.trim().equals(Mensagens.XIS_3_1)){
            if(nome.equals(Mensagens.XIS))
                SingletonAreaInfo.escrever("SUA JOGADA FOI LINHA 3 COLUNA 1");
            else
                SingletonAreaInfo.escrever("SEU OPONENTE JOGOU LINHA 3 COLUNA 1");
            SingletonPainelTabuleiro.desenharFigura('X', 6);
        }else if(acao.trim().equals(Mensagens.XIS_3_2)){
            if(nome.equals(Mensagens.XIS))
                SingletonAreaInfo.escrever("SUA JOGADA FOI LINHA 3 COLUNA 2");
            else
                SingletonAreaInfo.escrever("SEU OPONENTE JOGOU LINHA 3 COLUNA 2");
            SingletonPainelTabuleiro.desenharFigura('X', 7);
        }else if(acao.trim().equals(Mensagens.XIS_3_3)){
            if(nome.equals(Mensagens.XIS))
                SingletonAreaInfo.escrever("SUA JOGADA FOI LINHA 3 COLUNA 3");
            else
                SingletonAreaInfo.escrever("SEU OPONENTE JOGOU LINHA 3 COLUNA 3");
            SingletonPainelTabuleiro.desenharFigura('X', 8);
        }
        
        // jogadas para BOLA
        else if(acao.trim().equals(Mensagens.BOLA_1_1)){
            if(nome.equals(Mensagens.BOLA))
                SingletonAreaInfo.escrever("SUA JOGADA FOI LINHA 1 COLUNA 1");
            else
                SingletonAreaInfo.escrever("SEU OPONENTE JOGOU LINHA 1 COLUNA 1");
            SingletonPainelTabuleiro.desenharFigura('O', 0);
        } else if(acao.trim().equals(Mensagens.BOLA_1_2)){
            if(nome.equals(Mensagens.BOLA))
                SingletonAreaInfo.escrever("SUA JOGADA FOI LINHA 1 COLUNA 2");
            else
                SingletonAreaInfo.escrever("SEU OPONENTE JOGOU LINHA 1 COLUNA 2");
            SingletonPainelTabuleiro.desenharFigura('O', 1);
        } else if(acao.trim().equals(Mensagens.BOLA_1_3)){
            if(nome.equals(Mensagens.BOLA))
                SingletonAreaInfo.escrever("SUA JOGADA FOI LINHA 1 COLUNA 3");
            else
                SingletonAreaInfo.escrever("SEU OPONENTE JOGOU LINHA 1 COLUNA 3");
            SingletonPainelTabuleiro.desenharFigura('O', 2);
        } else if(acao.trim().equals(Mensagens.BOLA_2_1)){
            if(nome.equals(Mensagens.BOLA))
                SingletonAreaInfo.escrever("SUA JOGADA FOI LINHA 2 COLUNA 1");
            else
                SingletonAreaInfo.escrever("SEU OPONENTE JOGOU LINHA 2 COLUNA 1");
            SingletonPainelTabuleiro.desenharFigura('O', 3);
        }else if(acao.trim().equals(Mensagens.BOLA_2_2)){
            if(nome.equals(Mensagens.BOLA))
                SingletonAreaInfo.escrever("SUA JOGADA FOI LINHA 2 COLUNA 2");
            else
                SingletonAreaInfo.escrever("SEU OPONENTE JOGOU LINHA 2 COLUNA 2");
            SingletonPainelTabuleiro.desenharFigura('O', 4);
        }else if(acao.trim().equals(Mensagens.BOLA_2_3)){
            if(nome.equals(Mensagens.BOLA))
                SingletonAreaInfo.escrever("SUA JOGADA FOI LINHA 2 COLUNA 3");
            else
                SingletonAreaInfo.escrever("SEU OPONENTE JOGOU LINHA 2 COLUNA 3");
            SingletonPainelTabuleiro.desenharFigura('O', 5);
        }else if(acao.trim().equals(Mensagens.BOLA_3_1)){
            if(nome.equals(Mensagens.BOLA))
                SingletonAreaInfo.escrever("SUA JOGADA FOI LINHA 3 COLUNA 1");
            else
                SingletonAreaInfo.escrever("SEU OPONENTE JOGOU LINHA 3 COLUNA 1");
            SingletonPainelTabuleiro.desenharFigura('O', 6);
        }else if(acao.trim().equals(Mensagens.BOLA_3_2)){
            if(nome.equals(Mensagens.BOLA))
                SingletonAreaInfo.escrever("SUA JOGADA FOI LINHA 3 COLUNA 2");
            else
                SingletonAreaInfo.escrever("SEU OPONENTE JOGOU LINHA 3 COLUNA 2");
            SingletonPainelTabuleiro.desenharFigura('O', 7);
        }else if(acao.trim().equals(Mensagens.BOLA_3_3)){
            if(nome.equals(Mensagens.BOLA))
                SingletonAreaInfo.escrever("SUA JOGADA FOI LINHA 3 COLUNA 3");
            else
                SingletonAreaInfo.escrever("SEU OPONENTE JOGOU LINHA 3 COLUNA 3");
            SingletonPainelTabuleiro.desenharFigura('O', 8);
        }
    }
}
