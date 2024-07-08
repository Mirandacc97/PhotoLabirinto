package org.example;

import org.example.objeto.Pixel;
import org.example.uteis.Funcoes;

import java.awt.image.BufferedImage;

import static org.example.uteis.Funcoes.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedImage imagem = manipular_imagem.abrirImagem("labirinto_01.png");

        manipular_imagem.salvarImagem(Funcoes.binarizacao(imagem,15),"PNG","labirinto_01.png");
        manipular_imagem.exibirImagem(imagem, imagem);

        int rbgParede = retornaCorParede(imagem);
        marcaPixelsComMesmoRGB(imagem, rbgParede);
        Pixel[][] matrizPixels = Funcoes.geraMatrizAPartirDaImagem(imagem);
        matrizPixels = definePixelsParede(matrizPixels, retornaCorParede(imagem));
    }

    private static void marcaPixelsComMesmoRGB(BufferedImage imagemASerAlterada, int rgbASerAlterado) {
        BufferedImage novaImagem = HelperImagens.criaImagemNova(imagemASerAlterada.getWidth(), imagemASerAlterada.getHeight());
        for (int i = 0; i < novaImagem.getWidth(); i++) {
            System.out.println("Posição X - " + i);
            for (int j = 0; j < novaImagem.getHeight(); j++) {
                System.out.println("Posição Y - " + j);
                if (imagemASerAlterada.getRGB(i, j) == rgbASerAlterado) {
                    System.out.println("Encontrei cor diferente!");
                    novaImagem.setRGB(i, j, 150);
                } else {
                    novaImagem.setRGB(i, j, imagemASerAlterada.getRGB(i, j));
                }
            }
        }
        Funcoes.exibeImagem(novaImagem);
    }

}