package org.example.uteis;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Funcoes {

  public static BufferedImage insereImagem() {
    try {
      return ImageIO.read(new File("java/untitled/src/main/imagens/labirinto_01.jpg"));
    } catch (IOException e) {
      System.out.println("Erro ao carregar a imagem");
      throw new RuntimeException(e);
    }
  }

  public static void exibeImagem(BufferedImage... img) {
    JFrame frame = new JFrame();
    frame.setTitle("Proc. de Imagens");
    frame.getContentPane().setLayout(new FlowLayout());
    for (BufferedImage imagem : img) {
      JLabel label = new JLabel(new ImageIcon(imagem));
      frame.getContentPane().add(label);
    }
    frame.pack();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

}
