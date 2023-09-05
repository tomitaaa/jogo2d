package br.ifpr.jogo.modelo;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.awt.Graphics2D;

public class Personagem extends ElementoGrafico {

    private int deslocamentoEmX;
    private int deslocamentoEmY;
    private ArrayList<Tiro> tiros;

    private static final int POSICAO_INICIAL_EM_X = 100;
    private static final int POSICAO_INICIAL_EM_Y = 100;
    public static final int DESLOCAMENTO = 3;
    private int pontuacao;
    private Personagem personagem;

    public int getDeslocamentoEmX() {
        return this.deslocamentoEmX;
    }

    public void setDeslocamentoEmX(int deslocamentoEmX) {
        this.deslocamentoEmX = deslocamentoEmX;
    }

    public int getDeslocamentoEmY() {
        return this.deslocamentoEmY;
    }

    public void setDeslocamentoEmY(int deslocamentoEmY) {
        this.deslocamentoEmY = deslocamentoEmY;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Personagem() {
        setPosicaoEmX(POSICAO_INICIAL_EM_X);
        setPosicaoEmY(POSICAO_INICIAL_EM_Y);
        this.tiros = new ArrayList<Tiro>();
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\espaconave.png");
        setImagem(carregando.getImage());
        setAlturaImagem(carregando.getImage().getHeight(null));
        setLarguraImagem(carregando.getImage().getWidth(null));
    }

    @Override
    public void atualizar() {
        setPosicaoEmX(getPosicaoEmX() + this.deslocamentoEmX);
        setPosicaoEmY(getPosicaoEmY() + this.deslocamentoEmY);
    }

    public void mover(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                this.deslocamentoEmY = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                this.deslocamentoEmX = DESLOCAMENTO;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                this.deslocamentoEmX = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                this.deslocamentoEmY = DESLOCAMENTO;
                break;
            default:
                break;
        }
    }

    public void parar(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        switch (codigo) {

            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                this.deslocamentoEmY = 0;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                this.deslocamentoEmY = 0;
                break;

            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                this.deslocamentoEmX = 0;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                this.deslocamentoEmX = 0;
                break;
            default:
                break;
        }
    }

    public void atirar() {
        int frenteDaNave = getPosicaoEmX() + getLarguraImagem();
        int meioDaNave = getPosicaoEmY() + (getAlturaImagem() / 2);
        Tiro tiro = new Tiro(frenteDaNave, meioDaNave);
        this.tiros.add(tiro);
    }

    public ArrayList<Tiro> getTiros() {
        return this.tiros;
    }

    public void setTiros(ArrayList<Tiro> tiros) {
        this.tiros = tiros;
    }

    public void desenhaPontuacao(Graphics2D graficos) {

        String textoPontuacao = "PONTOS: " + personagem.getPontuacao();
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22));
        graficos.setColor(new java.awt.Color(255, 255, 255));
        graficos.drawString(textoPontuacao, 20, 25);
    }

}
