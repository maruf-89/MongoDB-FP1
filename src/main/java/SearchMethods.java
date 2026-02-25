package mogodbDemo.atlas;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SearchMethods {

    // Hur många filmer gjordes 1975 (enligt vårt data). Returnerar ett tal.
    public long howManyMovies(List<Movie> movies) {

        return movies.stream()
                .count();
    }

    // Hitta längden på den film som var längst (högst runtime). Returnerar ett tal.
    public int lengthOfLongestMovie(List<Movie> movies) {

        return movies.stream()
                .mapToInt(Movie::getRuntime)
                .max()
                .orElse(0);
    }

    // Hur många UNIKA genrer hade filmerna från 1975. Returnerar ett tal.
    public long howManyUniqueGenres(List<Movie> movies) {

        return movies.stream()
                .flatMap(movie -> movie.getGenres().stream())
                .distinct()
                .count();
    }

    // Vilka skådisar spelade i den film som hade högst imdb-rating. Returnerar en List<String>.
    // Använder den högre ordningens funktion findMovieByComparator.
    public List<String> actorsInHighestRatedMovie(List<Movie> movies) {

        return findMovieByComparator(
                movies,
                Comparator.comparingDouble(Movie::getImdbRating),
                Movie::getCast
        );
    }

    // Vad är titeln på den film som hade minst antal skådisar listade? Returnerar en String.
    public String movieWithTheFewestActorsListed(List<Movie> movies) {

        return movies.stream()
                .min(Comparator.comparingInt(movie -> movie.getCast().size()))
                .map(Movie::getTitle)
                .orElse("");
    }

    // Hur många skådisar var med i mer än 1 film? Returnerar ett tal.
    public long amountOfActorsInMoreThanOneMovie(List<Movie> movies) {

        return movies.stream()
                .flatMap(movie -> movie.getCast().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream()
                .filter(count -> count > 1)
                .count();
    }

    // Vad hette den skådis som var med i flest filmer? Returnerar en String.
    public String actorInTheMostMovies(List<Movie> movies) {

        return movies.stream()
                .flatMap(movie -> movie.getCast().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }

    // Hur många UNIKA språk har filmerna? Returnerar ett tal.
    public long allUniqueLanguages(List<Movie> movies) {

        return movies.stream()
                .flatMap(movie -> movie.getLanguages().stream())
                .distinct()
                .count();
    }

    // Finns det någon titel som mer än en film har? Returnerar en bool.
    public boolean anyDoubletteTitles(List<Movie> movies) {

        return movies.stream()
                .collect(Collectors.groupingBy(Movie::getTitle, Collectors.counting()))
                .values()
                .stream()
                .anyMatch(count -> count > 1);
    }

    // Högre ordningens funktion

    public <T> T findMovieByComparator(List<Movie> movies,
                                       Comparator<Movie> comparator,
                                       Function<Movie, T> mapper) {

        return movies.stream()
                .max(comparator)
                .map(mapper)
                .orElse(null);
    }
}