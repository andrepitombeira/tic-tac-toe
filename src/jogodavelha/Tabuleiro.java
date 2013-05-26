package jogodavelha;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Tabuleiro extends JPanel{

    //private EventoDeMouse eventoDeMouse;
    private ImageIcon xis = new ImageIcon(this.getClass().getResource("/jogodavelha/xis.png"));
    private ImageIcon bola = new ImageIcon(this.getClass().getResource("/jogodavelha/bola.png"));
    private char[] tabuleiro = new char[9];

    public Tabuleiro(){
        this.setPreferredSize(new Dimension(300, 300));
        this.iniciarTabuleiro();
    }

    public void iniciarTabuleiro(){
        for(int i = 0; i<tabuleiro.length; i++){
            tabuleiro[i] = ' ';
        }
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);

        g.setColor(Color.black);
        g.fillRect(100, 20, 10, 260);

        g.setColor(Color.black);
        g.fillRect(190, 20, 10, 260);

        g.setColor(Color.black);
        g.fillRect(20, 100, 260, 10);

        g.setColor(Color.black);
        g.fillRect(20, 190, 260, 10);

        //Linha 1
        if(tabuleiro[0] == 'X'){
            this.xis.paintIcon(this, g, 20, 20);
        }
        if(tabuleiro[1] == 'X'){
            this.xis.paintIcon(this, g, 110, 20);
        }
        if(tabuleiro[2] == 'X'){
            this.xis.paintIcon(this, g, 200, 20);
        }

        // Linha 2
        if(tabuleiro[3] == 'X'){
            this.xis.paintIcon(this, g, 20, 110);
        }
        if(tabuleiro[4] == 'X'){
            this.xis.paintIcon(this, g, 110, 110);
        }
        if(tabuleiro[5] == 'X'){
            this.xis.paintIcon(this, g, 200, 110);
        }

        // Linha 3
        if(tabuleiro[6] == 'X'){
            this.xis.paintIcon(this, g, 20, 200);
        }
        if(tabuleiro[7] == 'X'){
            this.xis.paintIcon(this, g, 110, 200);
        }
        if(tabuleiro[8] == 'X'){
            this.xis.paintIcon(this, g, 200, 200);
        }

        //Linha 1
        if(tabuleiro[0] == 'O'){
            this.bola.paintIcon(this, g, 20, 20);
        }
        if(tabuleiro[1] == 'O'){
            this.bola.paintIcon(this, g, 110, 20);
        }
        if(tabuleiro[2] == 'O'){
            this.bola.paintIcon(this, g, 200, 20);
        }

        // Linha 2
        if(tabuleiro[3] == 'O'){
            this.bola.paintIcon(this, g, 20, 110);
        }
        if(tabuleiro[4] == 'O'){
            this.bola.paintIcon(this, g, 110, 110);
        }
        if(tabuleiro[5] == 'O'){
            this.bola.paintIcon(this, g, 200, 110);
        }

        // Linha 3
        if(tabuleiro[6] == 'O'){
            this.bola.paintIcon(this, g, 20, 200);
        }
        if(tabuleiro[7] == 'O'){
            this.bola.paintIcon(this, g, 110, 200);
        }
        if(tabuleiro[8] == 'O'){
            this.bola.paintIcon(this, g, 200, 200);
        }

    }

    public void marcarXis(int i){
        tabuleiro[i] = 'X';
        
        Graphics g = this.getGraphics();

        //Linha 1
        if(i == 0){
            this.xis.paintIcon(this, g, 20, 20);
        }
        else if(i == 1){
            this.xis.paintIcon(this, g, 110, 20);
        }
        else if(i == 2){
            this.xis.paintIcon(this, g, 200, 20);
        }

        // Linha 2
        else if(i == 3){
            this.xis.paintIcon(this, g, 20, 110);
        }
        else if(i == 4){
            this.xis.paintIcon(this, g, 110, 110);
        }
        else if(i == 5){
            this.xis.paintIcon(this, g, 200, 110);
        }

        // Linha 3
        else if(i == 6){
            this.xis.paintIcon(this, g, 20, 200);
        }
        else if(i == 7){
            this.xis.paintIcon(this, g, 110, 200);
        }
        else if(i == 8){
            this.xis.paintIcon(this, g, 200, 200);
        }
    }

    public void marcarBola(int i){
        tabuleiro[i] = 'O';

        Graphics g = this.getGraphics();

        //Linha 1
        if(i == 0){
            this.bola.paintIcon(this, g, 20, 20);
        }
        else if(i == 1){
            this.bola.paintIcon(this, g, 110, 20);
        }
        else if(i == 2){
            this.bola.paintIcon(this, g, 200, 20);
        }

        // Linha 2
        else if(i == 3){
            this.bola.paintIcon(this, g, 20, 110);
        }
        else if(i == 4){
            this.bola.paintIcon(this, g, 110, 110);
        }
        else if(i == 5){
            this.bola.paintIcon(this, g, 200, 110);
        }

        // Linha 3
        else if(i == 6){
            this.bola.paintIcon(this, g, 20, 200);
        }
        else if(i == 7){
            this.bola.paintIcon(this, g, 110, 200);
        }
        else if(i == 8){
            this.bola.paintIcon(this, g, 200, 200);
        }
    }

}
