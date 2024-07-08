package org.example;

import java.awt.image.BufferedImage;

/*
* Classe criada para auxiliar na manipulação de imagens
* */
public class HelperImagens {

  public static BufferedImage criaImagemNova(int comprimento, int altura) {
    return criaImagemNova(comprimento, altura, 13);
  }

  public static BufferedImage criaImagemNova(int comprimento, int altura, int tipoImagem) {
    return new BufferedImage(comprimento, altura, tipoImagem);
  }

}
