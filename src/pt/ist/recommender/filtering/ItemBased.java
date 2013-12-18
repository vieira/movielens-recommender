package pt.ist.recommender.filtering;

import pt.ist.recommender.similarity.Score;

import java.util.*;

public class ItemBased extends SimilarityBased implements Filter {
    private HashMap<Integer, List<Map.Entry<Integer,Double>>> similarItems;

    public ItemBased(ArrayList<HashMap<Integer, Integer>> invertedPreferences,
                     Score s) {
        similarItems = buildSimilarItems(invertedPreferences, s);
    }

    /**
     * Get items recommendations for a given user
     *
     * @param preferences a list of scores
     * @param userId      the user for which to get the recommendations
     * @param _           the score strategy to use, e.g. Euclidean Distance
     * @return a collection of items scored with how much the user might like
     */
    @Override
    public List<Map.Entry<Integer, Double>> getRecommendations(
            ArrayList<HashMap<Integer, Integer>> preferences,
            int userId, Score _) {
        List<Map.Entry<Integer, Double>> recommendations =
                new LinkedList<Map.Entry<Integer, Double>>();
        HashMap<Integer, Double> scores =
                new HashMap<Integer, Double>();
        HashMap<Integer, Double> sumSims =
                new HashMap<Integer, Double>();
        HashMap<Integer, Integer> userPrefs = preferences.get(userId);

        for (Map.Entry<Integer, Integer> movie : userPrefs.entrySet()) {
            for (Map.Entry<Integer, Double> similarMovie :
                    similarItems.get(movie.getKey())) {
                if (userPrefs.containsKey(similarMovie.getKey())) continue;
                if (!scores.containsKey(similarMovie.getKey())) {
                    scores.put(similarMovie.getKey(),
                            similarMovie.getValue() * movie.getValue());
                    sumSims.put(similarMovie.getKey(), similarMovie.getValue());
                } else {
                    scores.put(similarMovie.getKey(),
                            scores.get(similarMovie.getKey()) +
                                    similarMovie.getValue() * movie.getValue());
                    sumSims.put(similarMovie.getKey(),
                            sumSims.get(similarMovie.getKey()) +
                                    similarMovie.getValue());
                }
            }
        }

        for (Map.Entry<Integer, Double> item : scores.entrySet()) {
            recommendations.add(
                    new AbstractMap.SimpleImmutableEntry<Integer, Double>(
                            item.getKey(),
                            item.getValue() / sumSims.get(item.getKey())));
        }

        Collections.sort(recommendations, new ScoreComparator());
        Collections.reverse(recommendations);
        return recommendations;
    }

    public HashMap<Integer, List<Map.Entry<Integer,Double>>> buildSimilarItems(
            ArrayList <HashMap<Integer, Integer>> invertedPreferences,
            Score s) {
        HashMap<Integer, List<Map.Entry<Integer,Double>>> similarItems =
                new HashMap<Integer, List<Map.Entry<Integer,Double>>>();

        for (int item = 0; item < invertedPreferences.size(); ++item) {
            List<Map.Entry<Integer,Double>> scores =
                    topMatches(invertedPreferences, item, s);
            similarItems.put(item, scores);
        }
        return similarItems;
    }
}
