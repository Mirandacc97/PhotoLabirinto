package app;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class OperacoesLocais {

    // Método principal que resolve o labirinto
    public static BufferedImage resolverLabirinto(BufferedImage imagem) {
        int altura = imagem.getHeight();
        int largura = imagem.getWidth();

        // Cria uma cópia da imagem original
        BufferedImage imagemSaida = new BufferedImage(largura, altura, imagem.getType());
        Graphics g = imagemSaida.getGraphics();
        g.drawImage(imagem, 0, 0, null);
        g.dispose();

        // Cria uma pilha para armazenar os pontos do caminho
        Stack<Point> pilha = new Stack<>();

        // Inicia no meio da imagem
        Point inicio = new Point(largura / 2, altura / 2);
        pilha.push(inicio);

        while (!pilha.isEmpty()) {
            Point atual = pilha.pop();

            // Se o ponto atual já foi visitado, pule para o próximo
            if (getCor(imagemSaida, atual.x, atual.y) == Color.GREEN.getRGB()) {
                continue;
            }

            // Marca o ponto atual como visitado (pintando de verde)
            setCor(imagemSaida, atual.x, atual.y, Color.GREEN.getRGB());

            // Adiciona os vizinhos do ponto atual à pilha
            for (Point vizinho : getVizinhos(atual)) {
                if (isBranco(imagem, vizinho.x, vizinho.y)) {
                    pilha.push(vizinho);
                }
            }

            // Se o ponto atual está na borda da imagem, então encontramos a saída
            if (atual.x == 0 || atual.y == 0 || atual.x == largura - 1 || atual.y == altura - 1) {
                break;
            }
        }

        return imagemSaida;
    }

    // Verifica se um pixel é branco
    private static boolean isBranco(BufferedImage imagem, int x, int y) {
        int rgb = getCor(imagem, x, y);
        return rgb == Color.WHITE.getRGB();
    }

    // Obtém a cor de um pixel
    private static int getCor(BufferedImage imagem, int x, int y) {
        if (x < 0 || x >= imagem.getWidth() || y < 0 || y >= imagem.getHeight()) {
            return Color.BLACK.getRGB();
        }
        return imagem.getRGB(x, y);
    }

    // Define a cor de um pixel
    private static void setCor(BufferedImage imagem, int x, int y, int rgb) {
        if (x < 0 || x >= imagem.getWidth() || y < 0 || y >= imagem.getHeight()) {
            return;
        }
        imagem.setRGB(x, y, rgb);
    }

    // Obtém os vizinhos de um ponto
    private static List<Point> getVizinhos(Point ponto) {
        List<Point> vizinhos = new ArrayList<>();
        vizinhos.add(new Point(ponto.x - 1, ponto.y));
        vizinhos.add(new Point(ponto.x + 1, ponto.y));
        vizinhos.add(new Point(ponto.x, ponto.y - 1));
        vizinhos.add(new Point(ponto.x, ponto.y + 1));
        return vizinhos;
    }

}