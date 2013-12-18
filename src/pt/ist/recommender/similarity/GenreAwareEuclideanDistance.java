package pt.ist.recommender.similarity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GenreAwareEuclideanDistance extends EuclideanDistance {

    private final ArrayList<Map.Entry<String, Boolean[]>> movies;

    public GenreAwareEuclideanDistance(
            ArrayList<Map.Entry<String, Boolean[]>> movies) {
        this.movies = movies;

    }

    // FIXME: All scores too close, nothing less than 3 or greater than 4
    @Override
    public double getScore(ArrayList<HashMap<Integer, Integer>> preferences,
                           int firstId, int secondId) {
        Boolean[] firstMovieGenres = movies.get(firstId).getValue();
        Boolean[] secondMovieGenres = movies.get(secondId).getValue();
        double genreSum = 0, genreCorrelation, scoreCorrelation;

        for (int i = 0; i < firstMovieGenres.length; ++i) {
            genreSum += firstMovieGenres[i] == secondMovieGenres[i] ? 1 : 0;
        }

        genreCorrelation = genreSum / firstMovieGenres.length;
        scoreCorrelation = super.getScore(preferences, firstId, secondId);

        return genreCorrelation > scoreCorrelation ?
                0.75 * genreCorrelation + 0.25 * scoreCorrelation :
                0.75 * scoreCorrelation + 0.25 * genreCorrelation;
    }
}
