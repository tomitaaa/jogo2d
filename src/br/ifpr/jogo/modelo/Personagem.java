package br.ifpr.jogo.modelo;

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class Personagem extends ElementoGrafico {

    private int deslocamentoEmX;
    private int deslocamentoEmY;
    private ArrayList<Tiro> tiros;

    private static final int POSICAO_INICIAL_EM_X = 100;
    private static final int POSICAO_INICIAL_EM_Y = 100;
    public static final int DESLOCAMENTO = 3;

    public Personagem() {
        this.posicaoEmX = POSICAO_INICIAL_EM_X;
        this.posicaoEmY = POSICAO_INICIAL_EM_Y;
        this.tiros = new ArrayList<Tiro>();
    }
    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\espaconave.png");
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }
    @Override
    public void atualizar() {
        this.posicaoEmX = this.posicaoEmX + this.deslocamentoEmX;
        this.posicaoEmY = this.posicaoEmY + this.deslocamentoEmY;
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
        int frenteDaNave = this.posicaoEmX + this.larguraImagem;
        int meioDaNave = this.posicaoEmY + (this.alturaImagem / 2);
        Tiro tiro = new Tiro(frenteDaNave, meioDaNave);
        this.tiros.add(tiro);
    }

    public ArrayList<Tiro> getTiros() {
        return this.tiros;
    }

    public void setTiros(ArrayList<Tiro> tiros) {
        this.tiros = tiros;
    }

}
