package pt.ist.recommender;

import pt.ist.recommender.filtering.Filter;
import pt.ist.recommender.io.Reader;
import pt.ist.recommender.similarity.Score;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    private final Reader<String[][], ArrayList<Map.Entry<String, Boolean[]>>> r;
    private final Reader<String[][], ArrayList<Map.Entry<String, Boolean[]>>> t;

    private final Filter f;
    private final Score s;

    public Test(Reader base, Reader test, Filter f, Score s) {
        r = base;
        t = test;
        this.f = f;
        this.s = s;
    }

    public void runVerboseTest() {
        try {
            ArrayList<HashMap<Integer, Integer>> rPrf = r.readUserPreferences();
            ArrayList<HashMap<Integer, Integer>> tPrf = t.readUserPreferences();

            for (int i = 0; i < rPrf.size() && i < tPrf.size(); ++i) {
                if (rPrf.get(i) == null || tPrf.get(i) == null) {
                    continue;
                } else {
                    System.out.println("--> User " + (i + 1));
                    List<Map.Entry<Integer, Double>> userPrefs =
                            f.getRecommendations(rPrf, i, s);
                    for (Map.Entry<Integer, Double> pref : userPrefs) {
                        if (!tPrf.get(i).containsKey(pref.getKey())) continue;
                        System.out.println("-> Movie " + (pref.getKey() + 1));
                        double prefVal = pref.getKey() >= 5 ? 5 :
                                (pref.getKey() <= 1 ? 1 : pref.getKey());
                        System.out.println("Predicted: " + prefVal);
                        System.out.println("Real: " +
                                tPrf.get(i).get(pref.getKey()));
                    }
                }
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

    public double calculateMAE(boolean showPerUser) {
        try {
            ArrayList<HashMap<Integer, Integer>> rPrf = r.readUserPreferences();
            ArrayList<HashMap<Integer, Integer>> tPrf = t.readUserPreferences();
            double sumErrors = 0;
            double sumErrorsUser = 0;
            int n = 0, nUser = 0;

            for (int i = 0; i < rPrf.size() && i < tPrf.size(); ++i) {
                if (rPrf.get(i) == null || tPrf.get(i) == null) {
                    continue;
                } else {
                    List<Map.Entry<Integer, Double>> userPrefs =
                            f.getRecommendations(rPrf, i, s);
                    for (Map.Entry<Integer, Double> pref : userPrefs) {
                        if (!tPrf.get(i).containsKey(pref.getKey())) continue;
                        double prefVal = pref.getKey() >= 5 ? 5.0 :
                                (pref.getKey() <= 1 ? 1.0 : pref.getKey());
                        sumErrorsUser += Math.abs(prefVal -
                                              tPrf.get(i).get(pref.getKey()));
                        ++nUser;
                    }
                    if (showPerUser) {
                    System.out.println("MAE for User-" + (i+1) + ": " +
                            (sumErrorsUser/nUser) + " / Number of Reviews: " +
                            rPrf.get(i).size());
                    }
                    sumErrors += sumErrorsUser; sumErrorsUser = 0;
                    n += nUser; nUser = 0;
                }
            }
            return sumErrors / n;
        } catch (IOException ioe) {
            System.err.println(ioe);
            return Double.NaN;
        }
    }
}
