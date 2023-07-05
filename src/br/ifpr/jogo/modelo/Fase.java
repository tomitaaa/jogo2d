package br.ifpr.jogo.modelo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public abstract class Fase extends JPanel implements KeyListener, ActionListener {
  protected Image fundo;
  protected Personagem personagem;
  protected Timer timer;
  private boolean emJogo = true;

  public abstract void paint(Graphics g);

  public abstract void inicializaInimigos();

  public abstract void keyTyped(KeyEvent e);

  public abstract void keyPressed(KeyEvent e);

  public abstract void keyReleased(KeyEvent e);

  @Override
  public void actionPerformed(ActionEvent e) {
    personagem.atualizar();
    // Recuperar a nossa lista de tiros (getTiros) e atribuímos para uma variável
    // local chamada tiros.
    ArrayList<Tiro> tiros = personagem.getTiros();
    // Criando um laço de repetição (for). Iremos percorrer toda a lista.
    for (int i = 0; i < tiros.size(); i++) {
      // Obter o objeto tiro da posicao i do ArrayList
      Tiro tiro = tiros.get(i);
      // Verificar se (if) a posição do x (tiro.getPosicaoEmX()) é maior do que a
      // largura da nossa janela
      if (tiro.getPosicaoEmX() > LARGURA_DA_JANELA || !tiro.getEhVisivel())
        // Remover da lista se estiver fora do campo de visão (LARGURA_DA_JANELA)
        tiros.remove(tiro);
      else
        // Atualizar a posição do tiro.
        tiro.atualizar();
    }
    // Criando um laço de repetição (for). Iremos percorrer toda a lista.
    for (int i = 0; i < this.inimigos.size(); i++) {
      // Obter o objeto inimigo da posicao i do ArrayList
      Inimigo inimigo = this.inimigos.get(i);
      // Verificar se (if) a posição do x (inimigo.getPosicaoEmX()) é maior do que a
      // largura da nossa janela
      if (inimigo.getPosicaoEmX() < 0 || !inimigo.getEhVisivel())
        // Remover da lista se estiver fora do campo de visão (0)
        inimigos.remove(inimigo);
      else
        // Atualizar a posição do inimigo.
        inimigo.atualizar();
    }
    this.verficarColisoes();
    repaint();
  }

  public abstract void verficarColisoes();
}