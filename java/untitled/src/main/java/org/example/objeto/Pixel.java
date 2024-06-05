package org.example.objeto;

public class Pixel {

  private int width;
  private int height;
  private int rgb;
  boolean ehParede;
  boolean ehPrimeiroPixel;
  boolean ehParedeEntrada;
  boolean ehCaminhoLabirinto;
  boolean ehUltimoPixelEsquerda;
  boolean ehUltimoPixelCima;

  boolean ehpixelEntrada;

  boolean ehpixelEntradaFinal;

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getRgb() {
    return rgb;
  }

  public void setRgb(int rgb) {
    this.rgb = rgb;
  }



  public boolean isEhParede() {
    return ehParede;
  }

  public void setEhParede(boolean ehParede) {
    this.ehParede = ehParede;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }
  public boolean isEhPrimeiroPixel() {
    return ehPrimeiroPixel;
  }

  public void setEhPrimeiroPixel(boolean ehPrimeiroPixel) {
    this.ehPrimeiroPixel = ehPrimeiroPixel;
  }


  public boolean isEhParedeEntrada(boolean b) {
    return ehParedeEntrada;
  }

  public void setEhParedeEntrada(boolean ehParedeEntrada) {
    this.ehParedeEntrada = ehParedeEntrada;
  }


  public boolean isEhCaminhoLabirinto() {
    return ehCaminhoLabirinto;
  }

  public void setEhCaminhoLabirinto(boolean ehCaminhoLabirinto) {
    this.ehCaminhoLabirinto = ehCaminhoLabirinto;
  }


  public boolean isEhUltimoPixelEsquerda() {
    return ehUltimoPixelEsquerda;
  }

  public void setEhUltimoPixelEsquerda(boolean ehUltimoPixelEsquerda) {
    this.ehUltimoPixelEsquerda = ehUltimoPixelEsquerda;
  }


  public boolean isEhUltimoPixelCima() {
    return ehUltimoPixelCima;
  }

  public void setEhUltimoPixelCima(boolean ehUltimoPixelCima) {
    this.ehUltimoPixelCima = ehUltimoPixelCima;
  }


  public boolean isEhpixelEntrada() {
    return ehpixelEntrada;
  }

  public void setEhpixelEntrada(boolean ehpixelEntrada) {
    this.ehpixelEntrada = ehpixelEntrada;
  }


  public boolean isEhpixelEntradaFinal() {
    return ehpixelEntradaFinal;
  }

  public void setEhpixelEntradaFinal(boolean ehpixelEntradaFinal) {
    this.ehpixelEntradaFinal = ehpixelEntradaFinal;
  }
}



