package mogodbDemo.atlas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchMethodsTest {

    private SearchMethods sm;
    private List<Movie> testMovies;

    @BeforeEach
    void setUp() {

        sm = new SearchMethods();

        // Egen testdata – ingen databasåtkomst behövs (används mot alla tester), skapar testdata med 3 filmer (setUP)

        testMovies = List.of(

                new Movie(
                        "1",
                        "Movie1",
                        1975,
                        List.of("Drama"),
                        "Director1",
                        List.of("Actor1", "Actor2"),
                        8.0,
                        List.of("English"),
                        100
                ),

                new Movie(
                        "2",
                        "Movie2",
                        1975,
                        List.of("Action"),
                        "Director2",
                        List.of("Actor1"),
                        9.0,
                        List.of("French"),
                        120
                ),

                new Movie(
                        "3",
                        "Movie3",
                        1975,
                        List.of("Drama"),
                        "Director3",
                        List.of("Actor3"),
                        7.0,
                        List.of("English"),
                        90
                )
        );
    }

    @Test
    void testHowManyMovies() {
        assertEquals(3, sm.howManyMovies(testMovies));
    }

    @Test
    void testLengthOfLongestMovie() {
        assertEquals(120, sm.lengthOfLongestMovie(testMovies));
    }

    @Test
    void testHowManyUniqueGenres() {
        assertEquals(2, sm.howManyUniqueGenres(testMovies));
    }

    @Test
    void testActorsInHighestRatedMovie() {
        assertEquals(List.of("Actor1"), sm.actorsInHighestRatedMovie(testMovies));
    }

    @Test
    void testMovieWithTheFewestActorsListed() {
        assertEquals("Movie2", sm.movieWithTheFewestActorsListed(testMovies));
    }

    @Test
    void testAmountOfActorsInMoreThanOneMovie() {
        assertEquals(1, sm.amountOfActorsInMoreThanOneMovie(testMovies));
    }

    @Test
    void testActorInTheMostMovies() {
        assertEquals("Actor1", sm.actorInTheMostMovies(testMovies));
    }

    @Test
    void testAllUniqueLanguages() {
        assertEquals(2, sm.allUniqueLanguages(testMovies));
    }

    @Test
    void testAnyDoubletteTitles_false() {
        assertFalse(sm.anyDoubletteTitles(testMovies));
    }

    @Test
    void testAnyDoubletteTitles_true() {
        List<Movie> moviesWithDuplicate = new ArrayList<>(testMovies);
        moviesWithDuplicate.add(new Movie(
                "4",
                "Movie1",
                1975,
                List.of("Comedy"),
                "Director4",
                List.of("Actor4"),
                6.0,
                List.of("Spanish"),
                80
        ));
        assertTrue(sm.anyDoubletteTitles(moviesWithDuplicate));
    }

    @Test
    void testFindMovieByComparator_higherOrderFunction() {
        assertEquals(List.of("Actor1"), sm.findMovieByComparator(
                testMovies,
                Comparator.comparingDouble(Movie::getImdbRating),
                Movie::getCast
        ));
    }
}