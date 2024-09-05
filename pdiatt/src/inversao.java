package com.example;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class inversao {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Por favor, forneça o caminho da imagem.");
            return;
        }

        String caminhoImagem = args[0];

        try {
            // Ler a imagem
            BufferedImage imagemOriginal = ImageIO.read(new File(caminhoImagem));

            // Processamento da imagem
            BufferedImage imagemProcessada = processarImagem(imagemOriginal);
            String caminhoImagemProcessada = caminhoImagem.replace(".jpg", "_invertida.png");
            ImageIO.write(imagemProcessada, "png", new File(caminhoImagemProcessada));
            System.out.println("Imagem processada e salva com sucesso.");

        } catch (IOException e) {
            System.out.println("Erro ao ler ou salvar a imagem: " + e.getMessage());
        }
    }

    // Método para processar a inversão da imagem
    private static BufferedImage processarImagem(BufferedImage imagemOriginal) {
        int larguraImagem = imagemOriginal.getWidth();
        int alturaImagem = imagemOriginal.getHeight();
        BufferedImage imagemProcessada = new BufferedImage(larguraImagem, alturaImagem, BufferedImage.TYPE_INT_ARGB);

        int limiteInversao = 128;

        for (int x = 0; x < larguraImagem; x++) {
            for (int y = 0; y < alturaImagem; y++) {
                int corPixelOriginal = imagemOriginal.getRGB(x, y);
                Color corOriginal = new Color(corPixelOriginal);
                int mediaCores = (corOriginal.getRed() + corOriginal.getGreen() + corOriginal.getBlue()) / 3;

                if (mediaCores < limiteInversao) {
                    int corInversaR = 255 - corOriginal.getRed();
                    int corInversaG = 255 - corOriginal.getGreen();
                    int corInversaB = 255 - corOriginal.getBlue();
                    Color corInvertida = new Color(corInversaR, corInversaG, corInversaB);
                    imagemProcessada.setRGB(x, y, corInvertida.getRGB());
                } else {
                    imagemProcessada.setRGB(x, y, corPixelOriginal);
                }
            }
        }

        return imagemProcessada;
    }
}
