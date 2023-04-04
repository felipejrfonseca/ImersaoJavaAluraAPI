import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        API api = API.IMDB_TOP_MOVIES;
        String url = api.getUrl();
        ExtratorDeConteudo extrator = api.getExtrator();

        var http = new ClientHttp();
        String json = http.buscaDeDados(url);

        //exbir e manupular os dados
        List<Conteudo> conteudos = extrator.extraiConteudo(json);

        for (Conteudo conteudo : conteudos) {
            InputStream inputStream= new  URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = conteudo.titulo() + ".png";

            GeradoraDeFigurinhas.criar(inputStream, nomeArquivo);

            System.out.println(conteudo.titulo());
        }
    }
}
