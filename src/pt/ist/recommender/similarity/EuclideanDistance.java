package pt.ist.recommender.similarity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EuclideanDistance implements Score {

    /**
     * Put a number on how close two things are using euclidean distance.
     *
     * @param preferences a list of scores
     * @param firstId the id of the first thing to compare
     * @param secondId the id of the second thing to compare
     * @return a number from 0 to 1, where 0 is nothing in common
     */
    @Override
    public double getScore(ArrayList<HashMap<Integer, Integer>> preferences,
                           int firstId, int secondId) {
        boolean hasCommon = false;
        double sumOfSquares = 0;

        for (Map.Entry<Integer, Integer> thing :
                preferences.get(firstId).entrySet()) {
            if (preferences.get(secondId).containsKey(thing.getKey())) {
                hasCommon = true;
                break;
            }
        }

        if (!hasCommon) return 0;

        for (Map.Entry<Integer, Integer> thing :
                preferences.get(firstId).entrySet()) {
            if (preferences.get(secondId).containsKey(thing.getKey())) {
                sumOfSquares += Math.pow(
                        preferences.get(firstId).get(thing.getKey()) -
                        preferences.get(secondId).get(thing.getKey()), 2);
            }
        }

        return 1 / (1 + Math.sqrt(sumOfSquares));
    }
}
