package pattern;

import javax.swing.JTextField;

public class SingletonContadores {

    private static JTextField vitorias;
    private static JTextField derrotas;
    private static JTextField empates;

    public static void inicializar(JTextField vitorias, JTextField derrotas, JTextField empates){
        SingletonContadores.vitorias = vitorias;
        SingletonContadores.derrotas = derrotas;
        SingletonContadores.empates = empates;
    }

    public static void reiniciarContadores(){
        SingletonContadores.vitorias.setText("0");
        SingletonContadores.derrotas.setText("0");
        SingletonContadores.empates.setText("0");
    }

    public static void addVitoria(){
        int i = Integer.parseInt(SingletonContadores.vitorias.getText());
        i++;
        SingletonContadores.vitorias.setText(""+i);

    }

    public static void addDerrota(){
        int i = Integer.parseInt(SingletonContadores.derrotas.getText());
        i++;
        SingletonContadores.derrotas.setText(""+i);
    }

    public static void addEmpate(){
        int i = Integer.parseInt(SingletonContadores.empates.getText());
        i++;
        SingletonContadores.empates.setText(""+i);
    }

}
