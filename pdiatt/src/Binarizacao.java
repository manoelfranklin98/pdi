import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Binarizacao {
    public static void main(String[] args) {
        try {
            // Caminho da imagem que será processada
            String caminhoImagem = "C:\\Users\\manoel franklin\\IdeaProjects\\pdiatt\\test\\imagem4.jpg";
            BufferedImage imagemOriginal = ImageIO.read(new File(caminhoImagem));

            // Armazena largura e altura da imagem
            int larguraImagem = imagemOriginal.getWidth();
            int alturaImagem = imagemOriginal.getHeight();
            BufferedImage imagemBinarizada = new BufferedImage(larguraImagem, alturaImagem, BufferedImage.TYPE_BYTE_BINARY);

            // Valor limite para binarização
            int limite = 128;

            // Percorre cada pixel da imagem
            for (int x = 0; x < larguraImagem; x++) {
                for (int y = 0; y < alturaImagem; y++) {
                    // Pega a cor do pixel
                    int rgb = imagemOriginal.getRGB(x, y);
                    Color corOriginal = new Color(rgb);

                    // Calcula o valor de cinza do pixel
                    int cinza = (corOriginal.getRed() + corOriginal.getGreen() + corOriginal.getBlue()) / 3;

                    // Se o valor de cinza for menor que o limite, define o pixel como preto, caso contrário, como branco
                    if (cinza < limite) {
                        imagemBinarizada.setRGB(x, y, Color.BLACK.getRGB());
                    } else {
                        imagemBinarizada.setRGB(x, y, Color.WHITE.getRGB());
                    }
                }
            }

            // Salvar a nova imagem binarizada
            String caminhoImagemBinarizada = "C:\\Users\\manoel franklin\\IdeaProjects\\pdiatt\\test\\imagem4_Binarizada.png";
            ImageIO.write(imagemBinarizada, "png", new File(caminhoImagemBinarizada));
            System.out.println("Imagem binarizada e salva com sucesso.");

        } catch (IOException e) {
            System.out.println("Erro ao ler ou salvar a imagem: " + e.getMessage());
        }
    }
}
