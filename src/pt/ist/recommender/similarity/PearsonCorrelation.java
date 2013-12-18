package pt.ist.recommender.similarity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PearsonCorrelation implements Score {

    /**
     * Put a number on how close two things are based on how they correlate.
     *
     * @param preferences a list of scores
     * @param firstId     the id of the first thing to compare
     * @param secondId    the id of the second thing to compare
     * @return a number from -1 to 1, where 0 is no correlation.
     */
    @Override
    public double getScore(ArrayList<HashMap<Integer, Integer>> preferences,
                           int firstId, int secondId) {
        HashMap<Integer, Boolean> bothRated = new HashMap<Integer, Boolean>();
        double sum1 = 0, sum2 = 0, sqSum1 = 0, sqSum2 = 0, prodSum = 0,
               nom, den;
        int lengthBothRated;
        for (Map.Entry<Integer, Integer> thing :
                preferences.get(firstId).entrySet()) {
            if (preferences.get(secondId).containsKey(thing.getKey())) {
                bothRated.put(thing.getKey(), true);
            }
        }
        lengthBothRated = bothRated.size();

        if (lengthBothRated == 0) return 0;

        for (Map.Entry<Integer, Boolean> thingInCommon : bothRated.entrySet()) {
            sum1 += preferences.get(firstId).get(thingInCommon.getKey());
            sum2 += preferences.get(secondId).get(thingInCommon.getKey());
            sqSum1 += Math.pow(preferences.get(firstId).get(
                        thingInCommon.getKey()), 2);
            sqSum2 += Math.pow(preferences.get(secondId).get(
                        thingInCommon.getKey()), 2);
            prodSum += preferences.get(firstId).get(thingInCommon.getKey()) *
                       preferences.get(secondId).get(thingInCommon.getKey());
        }
        nom = prodSum - ((sum1 * sum2) / lengthBothRated);
        den = Math.sqrt((sqSum1 - Math.pow(sum1, 2) / lengthBothRated) *
                        (sqSum2 - Math.pow(sum2, 2) / lengthBothRated));
        return den != 0 ? nom/den : 0;
    }
}