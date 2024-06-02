package org.example.objeto;

public class Pixel {
  private int width;
  private int height;
  private int rgb;
  boolean ehParede;
  boolean ehultimoPixelcima;
  boolean ehprimeiropixel;
  boolean ehparedeentrada;
  boolean ehCaminhoLabirinto;
  boolean ehultimoPixelesquerda;
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
    return ehultimoPixelcima;
  }

  public void setehultimoPixelcima(boolean ehultimoPixelcima) {
    this.ehultimoPixelcima = ehultimoPixelcima;
  }
  public boolean isEhParedeentrada() {
    return ehparedeentrada;
  }

  public void setEhParedeentrada(boolean ehParede) {
    this.ehparedeentrada = ehparedeentrada;
  }
  public boolean ehprimeiropixel() {
    return ehprimeiropixel;
  }

  public void setehultimoPixelesquerda(boolean ehultimoPixelesquerda) {
    this.ehultimoPixelesquerda= ehultimoPixelesquerda;
  }
  public boolean ehultimoPixelesquerda() {
    return ehultimoPixelesquerda;
  }
  public boolean isEhCaminhoLabirinto() {
    return ehCaminhoLabirinto;
  }

  public void setEhCaminhoLabirinto(boolean ehCaminhoLabirinto) {
    this.ehCaminhoLabirinto = ehCaminhoLabirinto;
  }

  public void setehprimeiropixel(boolean ehParede) {
    this.ehprimeiropixel= ehprimeiropixel;
  }

  public void setehpixelEntradaFinal(boolean ehpixelEntradaFinal) {
    this.ehpixelEntradaFinal = ehpixelEntradaFinal;
  }

  public boolean ehpixelEntradaFinal() {
    return ehpixelEntradaFinal();
  }
}



