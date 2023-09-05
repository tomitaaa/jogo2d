package br.ifpr.jogo.modelo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.Timer;

public abstract class Fase extends JPanel implements KeyListener, ActionListener {
  protected Image fundo;
  protected Personagem personagem;
  protected Timer timer;
  protected boolean emJogo = true;
  private static final int QTDE_DE_ASTEROIDES = 20;
  private ArrayList<Asteroide> asteroides;

  public abstract void paint(Graphics g);

  public abstract void inicializaInimigos();

  public abstract void keyTyped(KeyEvent e);

  public abstract void keyPressed(KeyEvent e);

  public abstract void keyReleased(KeyEvent e);

  public abstract void inicializaElementosGraficosAdicionais();

  @Override
  public abstract void actionPerformed(ActionEvent e);

  public abstract void verficarColisoes();

  public void desenhaPontuacao(Graphics2D graficos) {
    String textoPontuacao = "PONTOS: " + personagem.getPontuacao();
    graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22));
    graficos.setColor(new java.awt.Color(255, 255, 255));
    graficos.drawString(textoPontuacao, 20, 25);
  }

}
