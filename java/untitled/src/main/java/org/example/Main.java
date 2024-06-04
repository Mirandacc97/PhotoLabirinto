package org.example;

import org.example.objeto.Pixel;
import org.example.uteis.Funcoes;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Scanner;

import static org.example.uteis.Funcoes.*;

public class Main {

    private static BufferedImage imagemUtilizada = null;

    public static void main(String[] args) throws Exception {
        imagemUtilizada = insereImagem();
        imagemUtilizada = binarizacao(imagemUtilizada, 15);
        int rbgParede = buscaCorParede(imagemUtilizada);
        //perguntaSeEhParede(rbgParede, imagemUtilizada);

        //Este trecho deveria estar em um if, mas em carater de teste ele só executa
        List<Pixel> listaPixels = Funcoes.geraListaAPartirDaImagem(imagemUtilizada);
        listaPixels = Funcoes.definePixelsParede(listaPixels, rbgParede);
        exibeImagem(geraImagemAPartirDeLista(listaPixels, imagemUtilizada));
        listaPixels = procuraEntradaLabirinto(imagemUtilizada, listaPixels);

        Pixel[][] matrizPixels = Funcoes.geraMatrizAPartirDaImagem(imagemUtilizada);
        exibeImagem(geraImagemAPartirDeMatriz(matrizPixels, imagemUtilizada));

    }

}