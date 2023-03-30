import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.io.InputStream;
import java.net.URL;


public class GeradoraDeFigurinhas {

    public void criar(InputStream inputStream, String nomeDoArquivo) throws IOException {

        //Leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //criar nova imagem com transparencia e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar a imagem original para novo imagem (em memoria)
        Graphics2D graphics =  (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //Configurar a fonte
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 70);
        graphics.setFont(fonte);
        graphics.setColor(Color.RED);

        //escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", 100, novaAltura-100);

        //escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("ImersaoJavaAluraAPI/src/saida", nomeDoArquivo));

    }
}
