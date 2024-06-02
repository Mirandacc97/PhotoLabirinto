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
          imgSaida.setRGB(width, height, new Color(75, 100, 44).getRGB());
        else
          imgSaida.setRGB(width, height, img.getRGB(width, height));
      }
    }
    return imgSaida;
  }

  public static List<Pixel> procuraEntradaLabirinto(BufferedImage img, List<Pixel> listaPixels) {
    int indice = 0;
    for(int altura = 0; altura <= img.getHeight(); ++altura) {
      for(int width = 0; width <= img.getWidth(); ++width) {
        Pixel pixel = new Pixel();
        pixel.setWidth(width);
        pixel.setHeight(altura);
        if (listaPixels.contains(pixel)) {
          Pixel pixelPrincipal = listaPixels.get(indice);
          pixelPrincipal.setehprimeiropixel(true);
          listaPixels.set(indice, pixelPrincipal);//primeiropixel da esquerda em cima

          ehPecorreEsquerda(img, listaPixels, indice, altura, pixel);
          Pixel esquerda = listaPixels.get(indice);
          ehPecorrebaixo(img,listaPixels,indice,width,pixel);

          

          ehPecorrecima(img,listaPixels,indice,width,pixel);
          Pixel cima = listaPixels.get(indice);
          ehPecorreDireita(img,listaPixels,indice,altura,pixel);
        }
        }

      }
    return listaPixels;
  }

  private static List<Pixel> ehPecorrecima(BufferedImage img, List<Pixel> listaPixels, int indice,int width, Pixel pixel) {
    for(int novowidth = width; novowidth< img.getWidth(); ++width){
      if(!listaPixels.contains(pixel)){
        Pixel pixelASerAlterado = listaPixels.get(indice);
        pixelASerAlterado.setEhParedeentrada(true);
        listaPixels.set(indice, pixelASerAlterado);
        for(int width1 = width; width1< img.getWidth(); ++width1) {
          if(listaPixels.contains(pixel)){
            Pixel pixelASerAlterado2 = listaPixels.get(indice);
            pixelASerAlterado.setehpixelEntradaFinal(true);
            listaPixels.set(indice, pixelASerAlterado2);
            return listaPixels;
          }
          indice++;
        }
      }else {
        Pixel pixelASerAlterado = listaPixels.get(indice);
        pixelASerAlterado.setehultimoPixelcima(true);
        listaPixels.set(indice, pixelASerAlterado);
        return listaPixels;
      }
    }
    return null;
  }

  private static List<Pixel> ehPecorreEsquerda(BufferedImage img, List<Pixel> listaPixels, int indice, int altura, Pixel pixel) {
    for(int altura1 = altura; altura1< img.getWidth(); ++altura1){
      if(!listaPixels.contains(pixel)){
        Pixel pixelASerAlterado = listaPixels.get(indice);
        pixelASerAlterado.setEhParedeentrada(true);
        listaPixels.set(indice, pixelASerAlterado);
        for(int altura2 = altura1; altura2< img.getWidth(); ++altura2) {
          if(listaPixels.contains(pixel)){
            Pixel pixelASerAlterado2 = listaPixels.get(indice);
            pixelASerAlterado2.setehpixelEntradaFinal(true);
            listaPixels.set(indice, pixelASerAlterado2);
            return listaPixels;
          }
          indice++;
        }
        }
      else {
        Pixel pixelASerAlterado = listaPixels.get(indice);
        pixelASerAlterado.setehultimoPixelesquerda(true);
        listaPixels.set(indice, pixelASerAlterado);
        return listaPixels;
      }
      }
    return null;
  }
  private static List<Pixel> ehPecorrebaixo(BufferedImage img, List<Pixel> listaPixels, int indice,int width, Pixel pixel) {
    for(int novowidth = width; novowidth< img.getWidth(); ++width){
      if(!listaPixels.contains(pixel)){
        Pixel pixelASerAlterado = listaPixels.get(indice);
        pixelASerAlterado.setEhParedeentrada(true);
        listaPixels.set(indice, pixelASerAlterado);
        for(int width1 = width; width1< img.getWidth(); ++width1) {
          if(listaPixels.contains(pixel)){
            Pixel pixelASerAlterado2 = listaPixels.get(indice);
            pixelASerAlterado2.setehpixelEntradaFinal(true);
            listaPixels.set(indice, pixelASerAlterado2);
            return listaPixels;

          }
          indice++;
        }
      }
    }
    return null;
  }
  private static List<Pixel> ehPecorreDireita(BufferedImage img, List<Pixel> listaPixels, int indice,int altura, Pixel pixel) {
    for(int altura1 = altura; altura1< img.getWidth(); ++altura1){
      if(!listaPixels.contains(pixel)){
        Pixel pixelASerAlterado = listaPixels.get(indice);
        pixelASerAlterado.setEhParedeentrada(true);
        listaPixels.set(indice, pixelASerAlterado);
        for(int altura2 = altura1; altura2< img.getWidth(); ++altura2) {
          if(listaPixels.contains(pixel)){
            Pixel pixelASerAlterado2 = listaPixels.get(indice);
            pixelASerAlterado2.setehpixelEntradaFinal(true);
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

  public static List<Pixel> alimentaListaPixels(BufferedImage img) {
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

  public static List<Pixel> definePixelsParede(List<Pixel> listaPixels, BufferedImage img) {
    int indice = 0;
    for(int altura = 0; altura < img.getHeight(); ++altura) {
      for(int largura = 0; largura < img.getWidth(); ++largura) {
        Pixel pixel = new Pixel();
        pixel.setWidth(largura);
        pixel.setHeight(altura);
        if (listaPixels.contains(pixel)) {
          Pixel pixelASerAlterado = listaPixels.get(indice);
          pixelASerAlterado.setEhParede(true);
          listaPixels.set(indice, pixelASerAlterado);
        }
        indice += 1;
      }
    }
    return listaPixels;
  }
}
