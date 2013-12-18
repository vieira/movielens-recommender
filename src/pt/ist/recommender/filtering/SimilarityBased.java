package pt.ist.recommender.filtering;

import pt.ist.recommender.similarity.Score;

import java.util.*;

public abstract class SimilarityBased {

    protected class ScoreComparator implements
            Comparator<Map.Entry<Integer, Double>> {

        @Override
        public int compare(Map.Entry<Integer, Double> u1,
                           Map.Entry<Integer, Double> u2) {
            return u1.getValue().compareTo(u2.getValue());
        }
    }

    public List<Map.Entry<Integer, Double>> topMatches(
            ArrayList<HashMap<Integer, Integer>> preferences,
            int id, Score s, int limit) {
        List<Map.Entry<Integer, Double>> matches =
                new LinkedList<Map.Entry<Integer, Double>>();

        for (int i = 0; i < preferences.size(); ++i) {
            if (i != id) {
                Map.Entry<Integer, Double> otherUser =
                        new AbstractMap.SimpleImmutableEntry<Integer, Double>(
                                i, s.getScore(preferences, id, i));
                matches.add(otherUser);
            }
        }
        Collections.sort(matches, new ScoreComparator());
        Collections.reverse(matches);
        return matches /*.subList(0, limit)*/;
    }

    public List<Map.Entry<Integer, Double>> topMatches(
            ArrayList <HashMap<Integer, Integer>> preferences,
            int id, Score s) {
        return topMatches(preferences, id, s, 50);
    }
}
