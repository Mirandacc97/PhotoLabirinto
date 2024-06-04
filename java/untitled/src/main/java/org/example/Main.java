package org.example;

import org.example.objeto.Pixel;
import org.example.uteis.Funcoes;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Scanner;

import static org.example.uteis.Funcoes.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedImage imagem = manipular_imagem.abrirImagem("labirinto_01.png");

        manipular_imagem.salvarImagem(Funcoes.binarizacao(imagem,15),"PNG","labirinto_01.png");
        manipular_imagem.exibirImagem(
                imagem,
                imagem
        );
        int rbgParede = buscaCorParede(imagem);
        //perguntaSeEhParede(rbgParede, imagemUtilizada);

        //Este trecho deveria estar em um if, mas em carater de teste ele só executa
        List<Pixel> listaPixels = Funcoes.geraListaAPartirDaImagem(imagem);
        listaPixels = Funcoes.definePixelsParede(listaPixels, rbgParede);
//        exibeImagem(geraImagemAPartirDeLista(listaPixels, imagem));
        listaPixels = procuraEntradaLabirinto(imagem, listaPixels);

        Pixel[][] matrizPixels = Funcoes.geraMatrizAPartirDaImagem(imagem);
        exibeImagem(geraImagemAPartirDeMatriz(matrizPixels, imagem));

    }

}