package org.example.objeto;

public class Pixel {
  private int width;
  private int height;
  private int rgb;
  boolean ehParede;
  boolean ehCaminhoLabirinto;

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

  public boolean isEhCaminhoLabirinto() {
    return ehCaminhoLabirinto;
  }

  public void setEhCaminhoLabirinto(boolean ehCaminhoLabirinto) {
    this.ehCaminhoLabirinto = ehCaminhoLabirinto;
  }
}
