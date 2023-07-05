package br.ifpr.jogo.modelo;

import javax.swing.ImageIcon;

public class Inimigo extends ElementoGrafico {

    private static int VELOCIDADE = 2;

    public Inimigo(int xAleatorio, int yAleatorio) {
        this.posicaoEmX = xAleatorio;
        this.posicaoEmY = yAleatorio;
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\inimigo.jpg");
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getHeight(null);
        this.larguraImagem = this.imagem.getWidth(null);
    }

    @Override
    public void atualizar() {
        this.posicaoEmX = this.posicaoEmX - VELOCIDADE;
    }

}