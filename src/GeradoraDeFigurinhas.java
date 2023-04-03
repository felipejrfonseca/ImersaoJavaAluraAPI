import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.FontMetrics;
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
        graphics.setColor(Color.DARK_GRAY);

        //escrever uma frase na nova imagem

        String nomeDoArquivo2 = nomeDoArquivo.replace(".png", "");
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D tamanhoTexto = fontMetrics.getStringBounds(nomeDoArquivo2, graphics);
        int larguraTexto = (int) tamanhoTexto.getWidth();
        int posicaoTextoX = (largura - larguraTexto) / 2;
        /*int alturaTexto = (int) tamanhoTexto.getHeight();
        int posicaoTextoY = (altura - alturaTexto) / 2;*/
        graphics.drawString( nomeDoArquivo2, posicaoTextoX, novaAltura-100);

        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        TextLayout textLayout = new TextLayout(nomeDoArquivo2, fonte, fontRenderContext);
        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(posicaoTextoX, novaAltura-100);
        graphics.setTransform(transform);

        BasicStroke outlineStroke = new BasicStroke(largura * 0.004f);
        graphics.setStroke(outlineStroke);

        graphics.setColor(Color.lightGray);
        graphics.draw(outline);
        graphics.setClip(outline);
        
        //escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("ImersaoJavaAluraAPI/src/saida", nomeDoArquivo));

    }
}
