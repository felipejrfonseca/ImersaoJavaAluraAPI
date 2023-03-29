import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        //Fazer uma conexão HTTP e buscar os top 10 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        System.out.println(body);

        //Extrair só os dados que interessam(titulo, poster e classificação)
        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> ListaDeFilmes = jsonParser.parse(body);

        //exbir e manupular os dados
        for (Map<String, String> filme :ListaDeFilmes) {
            System.out.print("\u001b[1mPosição Rank:\u001b[m " + filme.get("rank") + " - ");

            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int nEstrelas = (int)classificacao;

            for (int i = 1; i <= nEstrelas; i++) {
                System.out.print("⭐");
            }

            System.out.print("\n\u001b[1mTítulo:\u001b[m " + filme.get("title"));
            System.out.print("\n\u001b[1mURL Poster:\u001b[m " + filme.get("image") + "\n\n\n");
            //System.out.println(filme.get("imDbRating"));
        }
    }
}