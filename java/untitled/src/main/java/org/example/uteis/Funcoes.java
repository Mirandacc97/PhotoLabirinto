package org.example.uteis;

import org.example.objeto.Pixel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Funcoes {

  private static final int corVerdeMarcadora = new Color(75, 100, 44).getRGB();

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
    BufferedImage imgSaida = new BufferedImage(imgEntrada.getWidth(), imgEntrada.getHeight(), imgEntrada.getType());
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

  public static int buscaCorParede(BufferedImage img) {
    int corUm = 0;
    int corDois = 0;
    int height = 0;
    int largura = 0;
    for(height = 0; height < img.getHeight(); ++height) {
      for (largura = 0; largura < img.getWidth(); ++largura) {
        if (largura == 0)
          corUm = img.getRGB(largura, height);
        else {
          if (corUm != img.getRGB(largura, height)) {
            corDois = img.getRGB(largura, height);
            break;
          }
        }
      }
    }
    return corDois;
  }

  public static BufferedImage ehCorCorretaParede(BufferedImage img, int corRgb) {
    BufferedImage imgSaida = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
    for(int height = 0; height < img.getHeight(); ++height) {
      for(int width = 0; width < img.getWidth(); ++width) {
        if (corRgb == img.getRGB(width, height))
          imgSaida.setRGB(width, height, corVerdeMarcadora);
        else
          imgSaida.setRGB(width, height, img.getRGB(width, height));
      }
    }
    return imgSaida;
  }

  public static List<Pixel> procuraEntradaLabirinto(BufferedImage img, List<Pixel> listaPixels) throws Exception {
    int indice = 0;
    for(int altura = 0; altura <= img.getHeight(); ++altura) {
      for(int comprimento = 0; comprimento <= img.getWidth(); ++comprimento) {
        for (int i = 0; i < listaPixels.size(); i++) {
          if (listaPixels.get(i).getWidth() == comprimento
                  && listaPixels.get(i).getHeight() == altura
                  && listaPixels.get(i).isEhParede()) {

            List<Pixel> pixelsDaEntrada = new ArrayList<Pixel>();
            pixelsDaEntrada.addAll (Objects.requireNonNull(percorreEmComprimento(listaPixels, indice, altura, img)));
            pixelsDaEntrada.addAll(Objects.requireNonNull(percorreEmAltura(listaPixels, indice, img)));

            marcaEntrada(pixelsDaEntrada, listaPixels, img);

            //pecorreEsquerda(img, listaPixels, indice, altura, pixel);
            Pixel pixel = new Pixel();
            pixel.setWidth(comprimento);
            pixel.setHeight(altura);

            Pixel pixelPrincipal = listaPixels.get(indice);
            pixelPrincipal.setEhPrimeiroPixel(true);
            listaPixels.set(indice, pixelPrincipal);//primeiro pixel da esquerda em cima

            pecorreEsquerda(img, listaPixels, indice, altura, pixel);
            Pixel esquerda = listaPixels.get(indice);
            pecorreBaixo(img,listaPixels,indice,comprimento,altura,pixel);
            Pixel baixo = listaPixels.get(indice);
            pecorreCima(img,listaPixels,indice,comprimento,pixel);
            Pixel cima = listaPixels.get(indice);
            pecorreDireita(img,listaPixels,indice,altura,comprimento,pixel);
            Pixel direita = listaPixels.get(indice);

//            if(esquerda != null){
//              break;
//            } else if (baixo != null) {
//              break;
//            } else if (direita != null) {
//              break;
//            } else if (cima != null) {
//              break;
//            }
          }
        }
        indice++;
      }
    }
    return listaPixels;
  }

  private static List<Pixel> pecorreCima(BufferedImage img, List<Pixel> listaPixels, int indice, int width, Pixel pixel) {
    for(int widthCima = width; widthCima< img.getWidth(); ++widthCima) {
      if (!listaPixels.contains(pixel)) {
        Pixel pixelASerAlterado = listaPixels.get(indice);
        pixelASerAlterado.setEhParedeEntrada(true);
        listaPixels.set(indice, pixelASerAlterado);
        for (int widthCimaAchouEntrada = widthCima; widthCimaAchouEntrada < img.getWidth(); ++widthCimaAchouEntrada) {
          if (listaPixels.contains(pixel)) {
            Pixel pixelASerAlterado2 = listaPixels.get(indice);
            pixelASerAlterado.setEhpixelEntradaFinal(true);
            listaPixels.set(indice, pixelASerAlterado2);
            return listaPixels;
          }
          indice++;
        }
      }
    }
    return null;
  }

  private static List<Pixel> pecorreEsquerda(BufferedImage img, List<Pixel> listaPixels, int indice, int altura, Pixel pixel) {
    for(int alturaEsquerda = altura; alturaEsquerda< img.getWidth(); ++alturaEsquerda){
      if(!listaPixels.contains(pixel)){
        Pixel pixelASerAlterado = listaPixels.get(indice);
        pixelASerAlterado.setEhParedeEntrada(true);
        listaPixels.set(indice, pixelASerAlterado);
        for(int alturaEsquerdaAchouEntrada = alturaEsquerda; alturaEsquerdaAchouEntrada< img.getWidth(); ++alturaEsquerdaAchouEntrada) {
          if(listaPixels.contains(pixel)){
            Pixel pixelASerAlterado2 = listaPixels.get(indice);
            pixelASerAlterado2. setEhpixelEntradaFinal(true);
            listaPixels.set(indice, pixelASerAlterado2);
            return listaPixels;
          }
          indice++;
        }
        }
      }
    return null;
  }

  private static List<Pixel> percorreEmComprimento(List<Pixel> listaPixels, int indice, int altura, BufferedImage img) {
    List<Pixel> retornaPixelInicialEFinal = new ArrayList<Pixel>();
    int posicaoFinalLinha = (listaPixels.size() / img.getHeight())*(altura+1);
    for(int j = indice; j < posicaoFinalLinha; j++) {
      if (!listaPixels.get(j).isEhParede()) {
        retornaPixelInicialEFinal.add(listaPixels.get(j));
        for (int k = (j + 1); k < posicaoFinalLinha; k++) {
          if (listaPixels.get(k).isEhParede()) {
            retornaPixelInicialEFinal.add(listaPixels.get(k - 1));
            return retornaPixelInicialEFinal;
          }
        }
      }
    }
      return new ArrayList<>();
  }

  private static List<Pixel> percorreEmAltura(List<Pixel> listaPixels, int indice, BufferedImage img) {
    List<Pixel> retornaPixelInicialEFinal = new ArrayList<Pixel>();
    int qtdePixelsPorLinha = listaPixels.size() / img.getHeight();
    while (indice <= listaPixels.size()) {
      if (!listaPixels.get(indice).isEhParede()) {
        retornaPixelInicialEFinal.add(listaPixels.get(indice));
        indice += qtdePixelsPorLinha;
        while (indice <= listaPixels.size()){
          if (listaPixels.get(indice).isEhParede()) {
            retornaPixelInicialEFinal.add(listaPixels.get(indice));
            return retornaPixelInicialEFinal;
          }
          indice += qtdePixelsPorLinha;
        }
        break;
      }
    }
    return new ArrayList<>();
  }

  private static List<Pixel> pecorreBaixo(BufferedImage img, List<Pixel> listaPixels, int indice, int width, int altura, Pixel pixel) {
    for(int alturaEsquerda = altura; alturaEsquerda< img.getWidth(); ++alturaEsquerda){
      if(!listaPixels.contains(pixel)){
        Pixel pixelASerAlterado = listaPixels.get(indice);
        pixelASerAlterado.setEhParedeEntrada(true);
        listaPixels.set(indice, pixelASerAlterado);
        for(int alturaEsquerdaAchouEntrada = alturaEsquerda; alturaEsquerdaAchouEntrada< img.getWidth(); ++alturaEsquerdaAchouEntrada) {
          if(listaPixels.contains(pixel)){
            Pixel pixelASerAlterado2 = listaPixels.get(indice);
            pixelASerAlterado2.setEhpixelEntradaFinal(true);
            listaPixels.set(indice, pixelASerAlterado2);
            return listaPixels;
          }
          indice++;
        }
      }
    }
      for (int widthAbaixo = width; widthAbaixo < img.getWidth(); ++widthAbaixo) {
        if (!listaPixels.contains(pixel)) {
          Pixel pixelASerAlterado = listaPixels.get(indice);
          pixelASerAlterado.setEhParedeEntrada(true);
          listaPixels.set(indice, pixelASerAlterado);
          for (int widthAbaixoAchouEntrada = widthAbaixo; widthAbaixoAchouEntrada < img.getWidth(); ++widthAbaixoAchouEntrada) {
            if (listaPixels.contains(pixel)) {
              Pixel pixelASerAlterado2 = listaPixels.get(indice);
              pixelASerAlterado2. setEhpixelEntradaFinal(true);
              listaPixels.set(indice, pixelASerAlterado2);
              return listaPixels;
            }
            indice++;
          }
        }
      }
    return null;
  }
  private static List<Pixel> pecorreDireita(BufferedImage img, List<Pixel> listaPixels, int indice, int altura, int width, Pixel pixel) {
    for(int widthCima = width; widthCima< img.getWidth(); ++widthCima) {
      if (!listaPixels.contains(pixel)) {
        Pixel pixelASerAlterado = listaPixels.get(indice);
        pixelASerAlterado.setEhParedeEntrada(true);
        listaPixels.set(indice, pixelASerAlterado);
        for (int widthCimaAchouEntrada = widthCima; widthCimaAchouEntrada < img.getWidth(); ++widthCimaAchouEntrada) {
          if (listaPixels.contains(pixel)) {
            Pixel pixelASerAlterado2 = listaPixels.get(indice);
            pixelASerAlterado. setEhpixelEntradaFinal(true);
            listaPixels.set(indice, pixelASerAlterado2);
            return listaPixels;
          }
          indice++;
        }
      }
    }
        for (int alturadireita = altura; alturadireita < img.getWidth(); ++alturadireita) {
          if (!listaPixels.contains(pixel)) {
            Pixel pixelASerAlterado = listaPixels.get(indice);
            pixelASerAlterado.setEhParedeEntrada(true);
            listaPixels.set(indice, pixelASerAlterado);
            for (int alturadireitaAchouEntrada = alturadireita; alturadireitaAchouEntrada < img.getWidth(); ++alturadireitaAchouEntrada) {
              if (listaPixels.contains(pixel)) {
                Pixel pixelASerAlterado2 = listaPixels.get(indice);
                pixelASerAlterado2.setEhpixelEntradaFinal(true);
                listaPixels.set(indice, pixelASerAlterado2);
                return listaPixels;
              }
              indice++;
            }
          }
        }
    return null;
  }
  public static BufferedImage geraImagemAPartirDeLista(List<Pixel> lista, BufferedImage img) {

    BufferedImage imgSaida = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
    int indice = 0;
    for(int altura = 0; altura < img.getHeight(); ++altura) {
      for(int largura = 0; largura < img.getWidth(); ++largura) {
        imgSaida.setRGB(largura, altura, lista.get(indice).getRgb());
        indice += 1;
      }
    }

    return imgSaida;
  }

  public static List<Pixel> geraListaAPartirDaImagem(BufferedImage img) {
    List<Pixel> listaPixels = new ArrayList<Pixel>();
    for(int altura = 0; altura < img.getHeight(); ++altura) {
      for(int largura = 0; largura < img.getWidth(); ++largura) {
        Pixel pixel = new Pixel();
        pixel.setWidth(largura);
        pixel.setHeight(altura);
        pixel.setRgb(img.getRGB(largura, altura));
        listaPixels.add(pixel);
      }
    }
    return listaPixels;
  }

  public static List<Pixel> definePixelsParede(List<Pixel> listaPixels, int rgbParede) {
    for (int i=0; i < listaPixels.size(); i++)
      if (listaPixels.get(i).getRgb() == rgbParede) {
        Pixel pixelParaSerParede = listaPixels.get(i);
        pixelParaSerParede.setEhParede(true);
        listaPixels.set(i, pixelParaSerParede);
      }
    return listaPixels;
  }

  public static void perguntaSeEhParede(int rbgParede, BufferedImage imagemUtilizada) {
    BufferedImage imagemComCorParede = null;
    imagemComCorParede = ehCorCorretaParede(imagemUtilizada, rbgParede);
    Scanner teclado = new Scanner(System.in);
    System.out.println(
            new StringBuilder()
                    .append("Os pixels em verde correspondem a uma parede?")
                    .append("1 - Sim")
                    .append("2 - Não")
                    .toString()
                    + teclado.nextLine());
  }

  private static void marcaEntrada(List<Pixel> pixelsDaEntrada, List<Pixel> listaPixels, BufferedImage img) {
    Pixel[][] matrizDePixels = new Pixel[img.getWidth()][img.getHeight()];
    for (int i = 0; i < img.getWidth(); i++) {
      for (int j = 0; j < img.getHeight(); j++) {
        Pixel pixelAGravar = new Pixel();
        pixelAGravar.setHeight(j);
        pixelAGravar.setWidth(i);
        pixelAGravar.setRgb(img.getRGB(i, j));
        for(Pixel px: pixelsDaEntrada) {
          if (px.getWidth() == i && px.getHeight() == j)
            pixelAGravar.isEhpixelEntradaFinal();
        }
        matrizDePixels[i][j] = pixelAGravar;
      }
    }
  }

  public static BufferedImage geraImagemAPartirDeMatriz(Pixel[][] matrizDePixels, BufferedImage img) {
    BufferedImage imgSaida = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
    for(int i = 0; i < img.getWidth(); ++i) {
      for(int j = 0; j < img.getHeight(); ++j) {
        Pixel pixelAtual = matrizDePixels[i][j];
        imgSaida.setRGB(i, j, pixelAtual.getRgb());
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
