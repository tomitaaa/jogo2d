package br.ifpr.jogo.principal;

import javax.swing.JFrame;
import br.ifpr.jogo.modelo.FaseUm;

public class Principal extends JFrame {
    public static int ALTURA_DA_JANELA = 200;
    public static int LARGURA_DA_JANELA = 200;

    public Principal() {
        FaseUm fase = new FaseUm();
        super.add(fase);
        super.setVisible(true);
        super.setSize(1200, 1200);
        super.setTitle("Meu Jogo");
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Principal principal = new Principal();
    }
}
