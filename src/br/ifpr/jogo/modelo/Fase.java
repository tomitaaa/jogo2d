package br.ifpr.jogo.modelo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public abstract class Fase extends JPanel implements KeyListener, ActionListener {
 protected Image fundo;
   protected Personagem personagem;
   protected  Timer timer;

   public abstract void paint(Graphics g);
   public abstract void inicializaInimigos();

}