package sockets.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import protocolo.Mensagens;
//import pattern.SingletonTextArea;

public class GerenciadorDeJogadas extends Thread {

    private Socket conexao;
    private String meuNome;

    public GerenciadorDeJogadas(Socket conexao, String meuNome) {
        this.conexao = conexao;
        this.meuNome = meuNome;
    }

    @Override
    public void run() {
        try {

            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

            //GerenciadorDeJogo.executarAcao(Mensagens.OK, meuNome);

            String linha = entrada.readLine();
            while (linha != null && !linha.trim().equalsIgnoreCase(Mensagens.FINALIZAR_TRANSMISSAO)) {
                GerenciadorDeJogo.executarAcao(linha, meuNome);
                linha = entrada.readLine();
            }

            GerenciadorDeJogo.executarAcao(Mensagens.FINALIZAR_TRANSMISSAO, meuNome);

            conexao.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
