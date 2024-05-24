package org.example.menu;

import java.awt.image.BufferedImage;
import java.util.Scanner;

public class Menu {
    public static int exibeMenu() {
        StringBuilder menu = new StringBuilder()
                .append("//////////////////////////////////////////////////")
                .append("\n")
                .append("///Bem vindo à solução PhotoLabirinto")
                .append("\n")
                .append("///Sua solução definitiva em IA de labirintos")
                .append("\n")
                .append("//////////////////////////////////////////////////")
                .append("\n")
                .append("///MENU///////////////////////////////////////////")
                .append("\n")
                .append("///OPCAO 1 - Inserir imagem")
                .append("\n")
                .append("///OPCAO 2 - Imagem Mama")
                .append("\n")
                .append("///OPCAO 3 - Ressonância craniana");

        Scanner teclado = new Scanner(System.in);
        System.out.println(menu.toString() + teclado.nextInt());
        return teclado.nextInt();
    }
}


