package pt.ist.recommender.similarity;

import java.util.ArrayList;
import java.util.HashMap;

public interface Score {

    /**
     * Put a number on how close two things are based on how they score.
     *
     * @param preferences a list of scores
     * @param firstId the id of the first thing to compare
     * @param secondId the id of the second thing to compare
     * @return a number from -1 to 1, where 0 is no correlation.
     */
    public double getScore(ArrayList<HashMap<Integer, Integer>> preferences,
                           int firstId, int secondId);
}