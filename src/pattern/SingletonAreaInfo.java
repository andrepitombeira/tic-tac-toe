package pattern;

import javax.swing.JTextArea;

public class SingletonAreaInfo {

    private static JTextArea info;

    public static void inicializar(JTextArea info){
        SingletonAreaInfo.info = info;
    }

    public static void escrever(String msg){
        SingletonAreaInfo.info.append("\n"+msg);
    }

    public static void limpar(){
        SingletonAreaInfo.info.setText("");
    }

}
