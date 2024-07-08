package org.example.uteis;

import org.example.HelperImagens;
import org.example.objeto.Pixel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Funcoes {

  private static final int corVerdeMarcadora = new Color(75, 100, 44).getRGB();

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
    BufferedImage imgSaida = HelperImagens.criaImagemNova(imgEntrada.getWidth(), imgEntrada.getHeight());
    for(int altura = 0; altura < imgEntrada.getHeight(); ++altura) {
      for(int largura = 0; largura < imgEntrada.getWidth(); ++largura) {
        int rgb = imgEntrada.getRGB(largura, altura);
        Color cor = new Color(rgb);
        int media = (cor.getRed() + cor.getGreen() + cor.getBlue()) / 3;
        int novoValor = media > limiar ? 255 : 0;
        Color novaCor = new Color(novoValor, novoValor, novoValor);
        imgSaida.setRGB(largura, altura, novaCor.getRGB());
      }
    }
    return imgSaida;
  }

  public static Pixel[][] definePixelsParede(Pixel[][] listaPixels, int rgbParede) {
    for (int i = 0; i < listaPixels.length; i++)
      for (int j = 0; j < listaPixels[i].length; j++)
        if (listaPixels[i][j].getRgb() == rgbParede)
          listaPixels[i][j].setEhParede(true);
    return listaPixels;
  }

  public static int retornaCorParede(BufferedImage img) {
    for(int i = 0; i < img.getHeight(); ++i)
      for (int j = 0; j < img.getWidth(); ++j)
        if (img.getRGB(j, i) != img.getRGB(0, 0))
          return img.getRGB(j, i);
    return 0;
  }

  public static BufferedImage geraImagemAPartirDeMatriz(Pixel[][] matrizDePixels, BufferedImage img) {
    BufferedImage imgSaida = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
    for(int y = 0; y < img.getHeight(); ++y) {
      for(int x = 0; x < img.getWidth(); ++x) {
        Pixel pixelAtual = matrizDePixels[y][x];
        imgSaida.setRGB(y, x, pixelAtual.getRgb());
      }
    }
    return imgSaida;
  }

  public static Pixel[][] geraMatrizAPartirDaImagem(BufferedImage img) {
    Pixel[][] matrizDePixels = new Pixel[img.getWidth()][img.getHeight()];
    for(int i = 0; i < img.getWidth(); ++i) {
      for(int j = 0; j < img.getHeight(); ++j) {
        Pixel pixelAGravar = new Pixel();
        pixelAGravar.setHeight(j);
        pixelAGravar.setWidth(i);
        pixelAGravar.setRgb(img.getRGB(i, j));
        matrizDePixels[i][j] = pixelAGravar;
      }
    }
    return matrizDePixels;
  }

}

//  public static List<Pixel> procuraEntradaLabirinto(Pixel[][] matrizPixel) throws Exception {
//    Pixel posicaoInicialLabirinto = null;
//    for (int y = 0; y < matrizPixel.length; y++) {
//      for (int x = 0; x < matrizPixel[y].length; x++) {
//        if (matrizPixel[x][y].getRgb() != matrizPixel[0][0].getRgb()) {
//          posicaoInicialLabirinto = matrizPixel[x][y];
//          break;
//        }
//      }
//      if (posicaoInicialLabirinto != null)
//        break;
//    }
//    procuraEntradaEmAltura(matrizPixel, posicaoInicialLabirinto);
//  }
//  private static Pixel[] procuraEntradaEmAltura(Pixel[][] matrizPixel, Pixel pixelInicial) {
//    for (int x = pixelInicial.getHeight() + 1; x < matrizPixel[].length; x++) {
//      if (!matrizPixel[x][pixelInicial.getWidth()].isEhParede()) {
//        matrizPixel[pixelInicial.getWidth()][y].setEhpixelEntrada(true);
//        completaPixelsEntradaLabirintoEmAltura(matrizPixel, matrizPixel[pixelInicial.getWidth()][y]);
//      }
//    }
//    return (Pixel[]) pixelsIniciaisEFinaisDeEntradaLabirinto.toArray();
//  }
//  private static Pixel[][] completaPixelsEntradaLabirintoEmAltura(Pixel[][] matrizPixel, Pixel pixelInicial) {
//    for (int x = pixelInicial.getWidth(); y < matrizPixel[pixelInicial.getHeight()].length; y++) {
//      if (matrizPixel[][].isEhParede())
//        matrizPixel[x + 1][pixelInicial.getHeight()].setEhpixelEntrada(true);
//    }
//  }
//  public static int buscaCorParede(BufferedImage img) {
//    int corUm = 0;
//    int corDois = 0;
//    int height = 0;
//    int largura = 0;
//    for(height = 0; height < img.getHeight(); ++height) {
//      for (largura = 0; largura < img.getWidth(); ++largura) {
//        if (largura == 0)
//          corUm = img.getRGB(largura, height);
//        else {
//          if (corUm != img.getRGB(largura, height)) {
//            corDois = img.getRGB(largura, height);
//            break;
//          }
//        }
//      }
//    }
//    return corDois;
//  }
//  public static BufferedImage ehCorCorretaParede(BufferedImage img, int corRgb) {
//    BufferedImage imgSaida = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
//    for(int height = 0; height < img.getHeight(); ++height) {
//      for(int width = 0; width < img.getWidth(); ++width) {
//        if (corRgb == img.getRGB(width, height))
//          imgSaida.setRGB(width, height, corVerdeMarcadora);
//        else
//          imgSaida.setRGB(width, height, img.getRGB(width, height));
//      }
//    }
//    return imgSaida;
//  }
//  public static BufferedImage insereImagem() {
//    try {
//      return ImageIO.read(new File("labirinto_01.png"));
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//  }
//  private static List<Pixel> percorreEmAltura(Pixel[][] matrizPixel, Pixel pixelInicial) {
//    List<Pixel> retornaPixelInicialEFinal = new ArrayList<Pixel>();
//    int qtdePixelsPorLinha = listaPixels.size() / img.getHeight();
//    while (indice <= listaPixels.size()) {
//      if (!listaPixels.get(indice).isEhParede()) {
//        retornaPixelInicialEFinal.add(listaPixels.get(indice));
//        indice += qtdePixelsPorLinha;
//        while (indice <= listaPixels.size()){
//          if (listaPixels.get(indice).isEhParede()) {
//            retornaPixelInicialEFinal.add(listaPixels.get(indice));
//            return retornaPixelInicialEFinal;
//          }
//          indice += qtdePixelsPorLinha;
//        }
//        break;
//      }
//    }
//    return new ArrayList<>();
//  }
