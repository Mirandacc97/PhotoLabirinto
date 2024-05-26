package org.example.uteis;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Funcoes {

  public static BufferedImage insereImagem() {
    try {
      return ImageIO.read(new File("labirinto_01.png"));
    } catch (IOException e) {
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

  public static BufferedImage binarizacao(BufferedImage imgEntrada, int limiar) {
    int largura = imgEntrada.getWidth();
    int altura = imgEntrada.getHeight();
    BufferedImage imgSaida = new BufferedImage(largura, altura, imgEntrada.getType());

    for(int height = 0; height < altura; ++height) {
      for(int width = 0; width < largura; ++width) {
        int rgb = imgEntrada.getRGB(width, height);
        Color cor = new Color(rgb);
        int media = (cor.getRed() + cor.getGreen() + cor.getBlue()) / 3;
        int novoValor = media > limiar ? 255 : 0;
        Color novaCor = new Color(novoValor, novoValor, novoValor);
        imgSaida.setRGB(width, height, novaCor.getRGB());
      }
    }
    return imgSaida;
  }

  public static int buscaCorIntParede(BufferedImage img) {
    int corUm = 0;
    int corDois = 0;
    for(int height = 0; height < img.getHeight(); ++height) {
      if (possuiPixelsDiferentesNaLinha(img, 1)) {
        for(int width = 0; width < img.getWidth(); ++width) {
          if (width == 0)
            corUm = img.getRGB(width, height);
          else {
            if (corUm != img.getRGB(width, height)) {
              corDois = img.getRGB(width, height);
              break;
            }
          }
        }
      }
    }

    mostraCor(img, corUm);

    return corDois;
  }

  private static boolean possuiPixelsDiferentesNaLinha(BufferedImage img, int altura) {
    for(int width = 1; width < img.getWidth(); ++width) {
      if (img.getRGB((width - 1), altura) != img.getRGB(width, altura))
        return true;
    }
    return false;
  }

  public static boolean ehCorCorretaParede(BufferedImage img, int corRgb) {
    int quantidadePixelsNormais = 0;
    int quantidadePixelsVerdes = 0;
    BufferedImage imgSaida = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
    for(int height = 0; height < img.getHeight(); ++height) {
      for(int width = 0; width < img.getWidth(); ++width) {
        if (corRgb == img.getRGB(width, height)) {
          imgSaida.setRGB(width, height, img.getRGB(width, height));
          quantidadePixelsNormais += 1;
        }
        else {
          imgSaida.setRGB(width, height, new Color(75, 100, 44).getRGB());//img.getRGB(width, height));
          quantidadePixelsVerdes += 1;
        }
      }
    }
    System.out.println(" Quantidade de pixels normais - " + quantidadePixelsNormais);
    System.out.println(" Quantidade de pixels verdes - " + quantidadePixelsVerdes);
    exibeImagem(imgSaida);
    return false;
  }

  public static int pegaAlturaImagem(BufferedImage img) {
    return img.getWidth();
  }
  public static int pegaComprimentoImagem(BufferedImage img) {
    return img.getHeight();
  }

  private static void mostraCor(BufferedImage img, int cor) {
    int largura = img.getWidth();
    int altura = img.getHeight();
    BufferedImage imgSaida = new BufferedImage(largura, altura, img.getType());

    for(int height = 0; height < img.getHeight(); ++height) {
      for(int width = 0; width < img.getWidth(); ++width) {
        imgSaida.setRGB(width, height, cor);//img.getRGB(width, height));
      }
    }
    exibeImagem(imgSaida);
  }

}
