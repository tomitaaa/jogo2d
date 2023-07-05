package br.ifpr.jogo.modelo;

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

}
