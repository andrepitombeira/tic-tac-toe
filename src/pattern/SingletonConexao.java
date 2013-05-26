package pattern;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class SingletonConexao {

    private static Socket conexao = null;
    private static String nome = null;

    public static void finalizar() {
        try {
            conexao.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            conexao = null;
        }
    }

    public static void inicializar(Socket conexao, String nome) {
        SingletonConexao.conexao = conexao;
        SingletonConexao.nome = nome;
    }

    public static String getNome(){
        if(nome == null)
            return "";
        return SingletonConexao.nome;
    }

    public static boolean temConexao(){
        if(conexao == null)
            return false;
        return true;
    }

    public static void enviarMensagem(String msg){
        try {
            
            PrintStream saida = new PrintStream(conexao.getOutputStream());
            saida.println(msg);

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex){
            JOptionPane.showMessageDialog(null, "NÁO HÁ CONEXÃO!!!");
            ex.printStackTrace();
        }
    }
    
}
