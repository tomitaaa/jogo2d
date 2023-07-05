package br.ifpr.jogo.modelo;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class FaseUm extends Fase {

    private static final int DELAY = 5;
    private static final int LARGURA_DA_JANELA = 1200;
    private ArrayList<Inimigo> inimigos;
    private static final int QTDE_DE_INIMIGOS = 40;

    public FaseUm() {
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        ImageIcon carregando = new ImageIcon("recursos\\fundo.jpg");
        fundo = carregando.getImage();
        personagem = new Personagem();
        personagem.carregar();
        this.inicializaInimigos();
        this.addKeyListener(this);

        this.timer = new Timer(DELAY, this);
        this.timer.start();

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        boolean emJogo = true;
        if (emJogo) {
            graficos.drawImage(fundo, 0, 0, null);
            graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), this);

            ArrayList<Tiro> tiros = personagem.getTiros();

            for (Tiro tiro : tiros) {

                tiro.carregar();

                graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), this);
            }

            for (Inimigo inimigo : inimigos) {

                inimigo.carregar();

                graficos.drawImage(inimigo.getImagem(), inimigo.getPosicaoEmX(), inimigo.getPosicaoEmY(), this);
            }
        } else {
            ImageIcon fimDeJogo = new ImageIcon("recursos\\fimdejogo.png");
            graficos.drawImage(fimDeJogo.getImage(), 0, 0, null);
        }
        g.dispose();
    }

    @Override
    public void inicializaInimigos() {
        inimigos = new ArrayList<Inimigo>();
        for (int i = 0; i < QTDE_DE_INIMIGOS; i++) {
            int x = (int) (Math.random() * 8000 + 1024);
            int y = (int) (Math.random() * 650 + 30);
            Inimigo inimigo = new Inimigo(x, y);
            inimigos.add(inimigo);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            personagem.atirar();
        else
            personagem.mover(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.personagem.parar(e);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        personagem.atualizar();

        ArrayList<Tiro> tiros = personagem.getTiros();

        for (int i = 0; i < tiros.size(); i++) {

            Tiro tiro = tiros.get(i);

            if (tiro.getPosicaoEmX() > LARGURA_DA_JANELA)

                tiros.remove(tiro);
            else

                tiro.atualizar();
        }

        for (int i = 0; i < this.inimigos.size(); i++) {

            Inimigo inimigo = this.inimigos.get(i);

            if (inimigo.getPosicaoEmX() < 0)
                inimigos.remove(inimigo);
            else
                inimigo.atualizar();
        }

        repaint();
    }

    @Override
    public void ActionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ActionPerformed'");
    }

    @Override
    public void verficarColisoes() {
        Rectangle formaPersonagem = this.personagem.getRectangle();
        for (int i = 0; i < this.inimigos.size(); i++) {
            Inimigo inimigo = inimigos.get(i);
            Rectangle formaInimigo = inimigo.getRectangle();
            if (formaInimigo.intersects(formaPersonagem)) {
                this.personagem.setEhVisivel(false);
                inimigo.setEhVisivel(false);
                emJogo = false;
            }
            ArrayList<Tiro> tiros = this.personagem.getTiros();
            for (int j = 0; j < tiros.size(); j++) {
                Tiro tiro = tiros.get(j);
                Rectangle formaTiro = tiro.getRectangle();
                if (formaInimigo.intersects(formaTiro)) {
                    inimigo.setEhVisivel(false);
                    tiro.setEhVisivel(false);
                }
            }
        }
    }

}