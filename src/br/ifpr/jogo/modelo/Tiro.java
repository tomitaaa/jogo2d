package br.ifpr.jogo.modelo;

import javax.swing.ImageIcon;

public class Tiro extends ElementoGrafico {

    private static int VELOCIDADE = 2;

    public Tiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY) {
        this.posicaoEmX = posicaoPersonagemEmX;
        this.posicaoEmY = posicaoPersonagemEmY;
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\tiro.jpg");
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getHeight(null);
        this.larguraImagem = this.imagem.getWidth(null);
    }

    @Override
    public void atualizar() {
        this.posicaoEmX = this.posicaoEmX + VELOCIDADE;
    }

}