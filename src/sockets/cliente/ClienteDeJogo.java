package sockets.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import pattern.SingletonConexao;

public class ClienteDeJogo {

    private static final int porta = 12100;

    public void iniciarConexao(String ip){
        try {

            Socket conexao = new Socket(ip, porta);

            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String nome = entrada.readLine();

            SingletonConexao.inicializar(conexao, nome);
            
            Thread t = new GerenciadorDeMensagensCliente(conexao, nome);
            t.start();

        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
