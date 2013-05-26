package sockets.servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import protocolo.Mensagens;
//import pattern.SingletonTextArea;

public class ServidorDeJogo extends Thread{

    private  static final int porta = 12100;

    @Override
    public void run(){
        try {

            ServerSocket servidor = new ServerSocket(porta);
            
            PrintStream saida;

            // Jogador X
            Socket jogadorXis = servidor.accept();
            saida = new PrintStream(jogadorXis.getOutputStream());
            saida.println(Mensagens.XIS);
            GerenciadorDeJogo.inicializarXis(jogadorXis);
            Thread t1 = new GerenciadorDeJogadas(jogadorXis, Mensagens.XIS);
            t1.start();

            // Jogadar O
            Socket jogadorBola = servidor.accept();
            saida = new PrintStream(jogadorBola.getOutputStream());
            saida.println(Mensagens.BOLA);
            Thread t2 = new GerenciadorDeJogadas(jogadorBola, Mensagens.BOLA);
            t2.start();
            
            GerenciadorDeJogo.inicializar(jogadorXis, jogadorBola);

            servidor.close();


        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(ServidorDeJogo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
