package pt.ist.recommender.filtering;

import pt.ist.recommender.similarity.Score;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Filter {

    /**
     * Get items recommendations for a given user
     *
     *
     * @param preferences a list of scores
     * @param userId the user for which to get the recommendations
     * @param s the score strategy to use, e.g. Euclidean Distance
     * @return a collection of items scored with how much the user might like
     */
    public List<Map.Entry<Integer,Double>> getRecommendations(
            ArrayList<HashMap<Integer, Integer>> preferences,
            int userId, Score s);
}
