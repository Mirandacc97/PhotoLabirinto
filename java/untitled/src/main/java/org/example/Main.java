package org.example;

import org.example.menu.Menu;

import java.awt.image.BufferedImage;
import java.util.Scanner;

import static org.example.uteis.Funcoes.*;

public class Main {

    private static BufferedImage imagemUtilizada = null;

    public static void main(String[] args) {
        imagemUtilizada = insereImagem();
        //exibeImagem(imagemUtilizada);
        imagemUtilizada = binarizacao(imagemUtilizada, 15);
        //exibeImagem(imagemUtilizada);
        int rgb = buscaCorIntParede(imagemUtilizada);
        boolean corParede = ehCorCorretaParede(imagemUtilizada, rgb);
    }

    private static void verificaSeDeuFalha(boolean deuFalha) {
        if (deuFalha) {
            Scanner teclado = new Scanner(System.in);
            StringBuilder texto = new StringBuilder()
                    .append("Ultimo comando executado deu erro, gostaria de reiniciar o processo?")
                    .append("1 - Sim")
                    .append("2 - Não");
            System.out.println(texto.toString() + teclado.nextLine());

            if (teclado.nextInt() == 999)
                imagemUtilizada = null;
        }
    }

    private static void lacoMenu() {
        boolean deuFalha = false;
        int opcaoSelecionado = 0;
        while (opcaoSelecionado != 99) {
            verificaSeDeuFalha(deuFalha);
            opcaoSelecionado = Menu.exibeMenu();
            if (opcaoSelecionado == 1) {
                try {
                    imagemUtilizada = insereImagem();
                }
                catch (Exception e) {
                    deuFalha = true;
                    System.out.println(e.toString());
                }
            }
            if (opcaoSelecionado == 3) {
                try {
                    binarizacao(imagemUtilizada, 150);
                }
                catch (Exception e) {
                    deuFalha = true;
                    System.out.println(e.toString());
                }
            }
        }
    }
}