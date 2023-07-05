package br.ifpr.jogo.modelo;

import javax.swing.ImageIcon;

public class Inimigo extends ElementoGrafico {

    private static int VELOCIDADE = 2;

    public Inimigo(int xAleatorio, int yAleatorio) {
        setPosicaoEmX(xAleatorio);
        setPosicaoEmY(yAleatorio);
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\inimigo.jpg");
        super.setImagem(carregando.getImage());
        setAlturaImagem(carregando.getImage().getHeight(null));
        setLarguraImagem(carregando.getImage().getWidth(null));
    }

    @Override
    public void atualizar() {
        setPosicaoEmX(getPosicaoEmX() - VELOCIDADE);
    }

}