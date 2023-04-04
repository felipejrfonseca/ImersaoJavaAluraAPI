import java.util.List;
import java.util.Map;

public class ExtratorDoConteudoDoIMBD implements ExtratorDeConteudo{
    public List<Conteudo> extraiConteudo(String json){
        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> ListaDeAtributos = jsonParser.parse(json);

        return ListaDeAtributos.stream().map(atributos ->
                new Conteudo(atributos.get("title"), atributos.get("image"))).toList();

        }
    }
