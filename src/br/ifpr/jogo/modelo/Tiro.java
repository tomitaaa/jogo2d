package br.ifpr.jogo.modelo;

import javax.swing.ImageIcon;

public class Tiro extends ElementoGrafico {

    private static int VELOCIDADE = 2;

    public Tiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY) {
        setPosicaoEmX(posicaoPersonagemEmX);
        setPosicaoEmY(posicaoPersonagemEmY);
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\tiro.jpg");
        setImagem(carregando.getImage());
        setAlturaImagem(carregando.getImage().getHeight(null));
        setLarguraImagem(carregando.getImage().getWidth(null));
    }

    @Override
    public void atualizar() {
        setPosicaoEmX(getPosicaoEmX() + VELOCIDADE);
    }

}