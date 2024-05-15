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
      return ImageIO.read(new File("imagens/mamografia.png"));
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

  public static void esclareceImagem(BufferedImage img) {
    int altura = img.getHeight();
    int largura = img.getWidth();

    BufferedImage imgSaida = new BufferedImage(largura, altura, img.getType());

    for (int h = 0; h < altura; h++) {
      for (int w = 0; w < largura; w++) {
        int rgb = img.getRGB(w, h);
        Color cor = new Color(rgb);
        int red = cor.getRed();
        int green = cor.getGreen();
        int blue = cor.getBlue();
        Color novaCor = new Color(255 - red, 255 - green, 255 - blue);
        imgSaida.setRGB(w, h, novaCor.getRGB());
      }
    }
    exibeImagem(imgSaida);
  }

}
