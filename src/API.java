public enum API {
    NASA("https://api.nasa.gov/planetary/apod?api_key=erNI1eUCbCOhyvfX462le0yOr3adpKuf23KcDNM8&start_date=2022-06-12&end_date=2022-06-14", new ExtratorDeConteudoNASA()),
    IMDB_TOP_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json", new ExtratorDoConteudoDoIMBD());

    private String url;
    private ExtratorDeConteudo extrator;

    API(String url, ExtratorDeConteudo extrator) {
        this.url = url;
        this.extrator = extrator;
    }
    public String getUrl() {
        return url;
    }

    public ExtratorDeConteudo getExtrator() {
        return extrator;
    }
}

