package sockets.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import protocolo.Mensagens;

public class GerenciadorDeMensagensCliente extends Thread{

    private Socket conexao;
    private String nome;

    public GerenciadorDeMensagensCliente(Socket conexao, String nome) {
        this.conexao = conexao;
        this.nome = nome;
    }

    @Override
    public void run(){
        try {

            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            
            try{
                GerenciadorDeAcoes.executar(nome, nome);
            } catch( NullPointerException ex){
                ex.printStackTrace();
            }

            String linha = entrada.readLine();
            while(linha != null && !linha.trim().equals(Mensagens.FINALIZAR_TRANSMISSAO)){
                GerenciadorDeAcoes.executar(linha, nome);
                linha = entrada.readLine();
            }

            GerenciadorDeAcoes.executar(Mensagens.FINALIZAR_TRANSMISSAO, nome);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
