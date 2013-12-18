package pt.ist.recommender.filtering;

import pt.ist.recommender.similarity.Score;

import java.util.*;

public class UserBased extends SimilarityBased implements Filter {

    /**
     * Gets recommendations for a user by using a weighted average of the
     * scores given by other users.
     *
     *
     * @param preferences a list of scores
     * @param userId      the user for which to get the recommendations
     * @param s           the score strategy to use, e.g. Euclidean Distance
     * @return a collection of items scored with how much the user might like
     */
    @Override
    public List<Map.Entry<Integer,Double>> getRecommendations(
            ArrayList<HashMap<Integer, Integer>> preferences,
            int userId, Score s) {
        HashMap<Integer, Double> scores =
                new HashMap<Integer, Double>();
        HashMap<Integer, Double> sumSims =
                new HashMap<Integer, Double>();
        List<Map.Entry<Integer, Double>> recommendations =
                new LinkedList<Map.Entry<Integer, Double>>();

        for (int i = 0; i < preferences.size(); ++i) {
            if (i == userId) continue;
            double sim = s.getScore(preferences, userId, i);
            if (sim <= 0) continue;

            for (Map.Entry<Integer, Integer> movie :
                    preferences.get(i).entrySet()) {
                if (!preferences.get(userId).containsKey(movie.getKey())) {
                    if (scores.containsKey(movie.getKey())) {
                        scores.put(movie.getKey(),
                                scores.get(movie.getKey()) +
                                        movie.getValue() * sim);
                    } else {
                        scores.put(movie.getKey(), movie.getValue() * sim);
                    }

                    if (sumSims.containsKey(movie.getKey())) {
                        sumSims.put(movie.getKey(),
                                sumSims.get(movie.getKey()) + sim);
                    } else {
                        sumSims.put(movie.getKey(), sim);
                    }
                }
            }
        }

        for (Map.Entry<Integer, Double> movie : scores.entrySet()) {
            recommendations.add(
                    new AbstractMap.SimpleImmutableEntry<Integer, Double>(
                            movie.getKey(),
                            movie.getValue()/sumSims.get(movie.getKey())));
        }

        Collections.sort(recommendations, new ScoreComparator());
        Collections.reverse(recommendations);
        return recommendations;
    }
}
