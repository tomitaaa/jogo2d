package br.ifpr.jogo.modelo;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import br.ifpr.jogo.modelo.Fase;
import br.ifpr.jogo.principal.Principal;
// import static br.ifpr.jogo.modelo.Fase.QTDE_DE_ASTEROIDES;
import br.ifpr.jogo.modelo.Asteroide;

public class FaseUm extends Fase {

    private static final int DELAY = 5;
    private static final int LARGURA_DA_JANELA = 1200;
    private ArrayList<Inimigo> inimigos;
    private static final int QTDE_DE_INIMIGOS = 40;
    private static final int QTDE_DE_ASTEROIDES = 20;
    private static final int PONTOS_POR_INIMIGO = 10;
    private ArrayList<Asteroide> asteroides;

    public ArrayList<Inimigo> getInimigos() {
        return this.inimigos;
    }

    public void setInimigos(ArrayList<Inimigo> inimigos) {
        this.inimigos = inimigos;
    }

    public ArrayList<Asteroide> getAsteroides() {
        return this.asteroides;
    }

    public void setAsteroides(ArrayList<Asteroide> asteroides) {
        this.asteroides = asteroides;
    }

    public FaseUm() {
        super();
        this.emJogo = true;
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        ImageIcon carregando = new ImageIcon("recursos\\fundo.jpg");
        this.fundo = carregando.getImage();
        this.personagem = new Personagem();
        personagem.carregar();
        this.inicializaInimigos();
        this.addKeyListener(this);
        this.inicializaElementosGraficosAdicionais();
        this.timer = new Timer(DELAY, this);
        this.timer.start();

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        if (emJogo) {
            graficos.drawImage(fundo, 0, 0, null);

            for (Asteroide asteroide : asteroides) {
                graficos.drawImage(asteroide.getImagem(), asteroide.getPosicaoEmX(), asteroide.getPosicaoEmY(), this);
            }
            graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), this);

            ArrayList<Tiro> tiros = personagem.getTiros();
            for (Tiro tiro : tiros) {
                graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), this);
            }

            for (Inimigo inimigo : inimigos) {
                graficos.drawImage(inimigo.getImagem(), inimigo.getPosicaoEmX(), inimigo.getPosicaoEmY(), this);
            }
            super.desenhaPontuacao(graficos);
        } else {
            ImageIcon fimDeJogo = new ImageIcon("recursos\\fimdejogo.jpg");
            graficos.drawImage(fimDeJogo.getImage(), 0, 0, this);
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
        for (Asteroide asteroide : this.asteroides) {
            asteroide.atualizar();
        }
        ArrayList<Tiro> tiros = personagem.getTiros();

        for (int i = 0; i < tiros.size(); i++) {

            Tiro tiro = tiros.get(i);

            if (tiro.getPosicaoEmX() > LARGURA_DA_JANELA || !tiro.getEhVisivel())

                tiros.remove(tiro);
            else

                tiro.atualizar();
        }

        for (int i = 0; i < this.inimigos.size(); i++) {

            Inimigo inimigo = this.inimigos.get(i);

            if (inimigo.getPosicaoEmX() < 0 || !inimigo.getEhVisivel())

                inimigos.remove(inimigo);
            else

                inimigo.atualizar();
        }
        this.verficarColisoes();
        repaint();
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

    @Override
    public void inicializaElementosGraficosAdicionais() {
        this.asteroides = new ArrayList<Asteroide>();
        for (int i = 0; i < QTDE_DE_ASTEROIDES; i++) {
            int x = (int) (Math.random() * Principal.LARGURA_DA_JANELA);
            int y = (int) (Math.random() * Principal.ALTURA_DA_JANELA);
            Asteroide asteroide = new Asteroide(x, y);
            this.asteroides.add(asteroide);
        }
    }

}