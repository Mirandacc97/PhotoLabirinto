package org.example;

import org.example.menu.Menu;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import static org.example.uteis.Funcoes.exibeImagem;
import static org.example.uteis.Funcoes.insereImagem;

public class Main {

    private static BufferedImage imagemUtilizada = null;

    public static void main(String[] args) {
        int opcaoSelecionado = 0;
        boolean deuFalha = false;
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
            if (opcaoSelecionado == 2) {
                try {
                    exibeImagem(imagemUtilizada);
                }
                catch (Exception e) {
                    deuFalha = true;
                    System.out.println(e.toString());
                }
            }
        }
    }
    private static void verificaSeDeuFalha(boolean deuFalha) {
        if (deuFalha) {
            Scanner teclado = new Scanner(System.in);

            StringBuilder texto = new StringBuilder();
            texto.append("Ultimo comando executado deu erro, gostaria de reiniciar o processo?");
            texto.append("1 - Sim");
            texto.append("2 - Não");
            System.out.println(texto.toString() + teclado.nextLine());

            if (teclado.nextInt() == 1)
                imagemUtilizada = null;
        }
    }
}