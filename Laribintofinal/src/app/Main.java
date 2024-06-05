package app;

import java.awt.image.BufferedImage;


public class Main {
    public static void main(String[] args) {
        BufferedImage imagem = ManipularImagem.abrirImagem("chama.png");
        ManipularImagem.exibirImagem(
                imagem, OperacoesLocais.resolverLabirinto(imagem)
        );
        ManipularImagem.salvarImagem(OperacoesLocais.resolverLabirinto(imagem),"PNG","labirintoresolvido.png");
    }
}