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
  boolean ehpixelEntradaFinal;

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

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

  public boolean ehultimoPixelcima() {
    return ehUltimoPixelCima;
  }

  public void setehultimoPixelcima(boolean ehultimoPixelcima) {
    this.ehUltimoPixelCima = ehultimoPixelcima;
  }
  public boolean isEhParedeentrada() {
    return ehParedeEntrada;
  }

  public void setEhParedeentrada(boolean ehParede) {
    this.ehParedeEntrada = ehParedeEntrada;
  }
  public boolean ehprimeiropixel() {
    return ehPrimeiroPixel;
  }

  public void setehultimoPixelesquerda(boolean ehultimoPixelesquerda) {
    this.ehUltimoPixelEsquerda = ehultimoPixelesquerda;
  }
  
  public boolean ehultimoPixelesquerda() {
    return ehUltimoPixelEsquerda;
  }
  public boolean isEhCaminhoLabirinto() {
    return ehCaminhoLabirinto;
  }

  public void setEhCaminhoLabirinto(boolean ehCaminhoLabirinto) {
    this.ehCaminhoLabirinto = ehCaminhoLabirinto;
  }

  public void setehprimeiropixel(boolean ehParede) {
    this.ehPrimeiroPixel = ehPrimeiroPixel;
  }

  public void setehpixelEntradaFinal(boolean ehpixelEntradaFinal) {
    this.ehpixelEntradaFinal = ehpixelEntradaFinal;
  }

  public boolean ehpixelEntradaFinal() {
    return ehpixelEntradaFinal();
  }
}



