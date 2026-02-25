package mogodbDemo.atlas;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBAtlasDownloadExample {

    SearchMethods sm = new SearchMethods();

    public MongoDBAtlasDownloadExample() {

        String uri = ConfigLoader.get("mongodb.uri");

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> moviesCollection = database.getCollection("movies");

            List<Movie> movieList = new ArrayList<>();

            for (Document doc : moviesCollection.find(new Document("year", 1975))) {
                movieList.add(Movie.fromDocument(doc));
            }

            for (Movie movie : movieList) {
                System.out.println(movie);
            }

            System.out.println("Amount of movies: "                        + sm.howManyMovies(movieList));
            System.out.println("Length of longest movie: "                 + sm.lengthOfLongestMovie(movieList));
            System.out.println("Amount of unique genres: "                 + sm.howManyUniqueGenres(movieList));
            System.out.println("Actors in highest rated movie (HoF): "    + sm.actorsInHighestRatedMovie(movieList));
            System.out.println("Movie with fewest actors: "                + sm.movieWithTheFewestActorsListed(movieList));
            System.out.println("Amount of actors in more than one movie: " + sm.amountOfActorsInMoreThanOneMovie(movieList));
            System.out.println("Actor in the most movies: "                + sm.actorInTheMostMovies(movieList));
            System.out.println("Amount of unique languages: "              + sm.allUniqueLanguages(movieList));
            System.out.println("Has doublettle titles: "                   + sm.anyDoubletteTitles(movieList));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MongoDBAtlasDownloadExample m = new MongoDBAtlasDownloadExample();
    }
}