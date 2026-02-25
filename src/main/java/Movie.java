package mogodbDemo.atlas;

import org.bson.Document;

import java.util.List;
import java.util.Objects;

public class Movie {

    private final String id;
    private final String title;
    private final int year;
    private final int runtime;
    private final List<String> genres;
    private final String director;
    private final List<String> cast;
    private final double imdbRating;
    private final List<String> languages;

    public Movie(String id,
                 String title,
                 int year,
                 List<String> genres,
                 String director,
                 List<String> cast,
                 double imdbRating,
                 List<String> languages,
                 int runtime) {

        this.id = id;
        this.title = title;
        this.year = year;
        this.genres = List.copyOf(genres);
        this.director = director;
        this.cast = List.copyOf(cast);
        this.imdbRating = imdbRating;
        this.languages = List.copyOf(languages);
        this.runtime = runtime;
    }

    public static Movie fromDocument(Document doc) {

        Document imdbDoc = doc.get("imdb", Document.class);

        double rating = (imdbDoc != null && imdbDoc.getDouble("rating") != null)
                ? imdbDoc.getDouble("rating")
                : 0.0;

        List<String> genres    = Objects.requireNonNullElse(doc.getList("genres",    String.class), List.of());
        List<String> cast      = Objects.requireNonNullElse(doc.getList("cast",      String.class), List.of());
        List<String> languages = Objects.requireNonNullElse(doc.getList("languages", String.class), List.of());

        return new Movie(
                doc.getObjectId("_id").toString(),
                doc.getString("title"),
                doc.getInteger("year", 0),
                genres,
                doc.getString("director"),
                cast,
                rating,
                languages,
                doc.getInteger("runtime", 0)
        );
    }

    public String getId()              { return id; }
    public String getTitle()           { return title; }
    public int getYear()               { return year; }
    public int getRuntime()            { return runtime; }
    public List<String> getGenres()    { return genres; }
    public String getDirector()        { return director; }
    public List<String> getCast()      { return cast; }
    public double getImdbRating()      { return imdbRating; }
    public List<String> getLanguages() { return languages; }

    @Override
    public String toString() {
        return "Movie{" +
                "id='"          + id          + '\'' +
                ", title='"     + title        + '\'' +
                ", year="       + year         +
                ", runtime="    + runtime      +
                ", genres="     + genres       +
                ", director='"  + director     + '\'' +
                ", cast="       + cast         +
                ", imdbRating=" + imdbRating   +
                ", languages="  + languages    +
                '}';
    }
}