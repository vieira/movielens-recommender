package pt.ist.recommender;

import pt.ist.recommender.filtering.Filter;
import pt.ist.recommender.filtering.ItemBased;
import pt.ist.recommender.filtering.UserBased;
import pt.ist.recommender.io.MovieLensReader;
import pt.ist.recommender.io.Reader;
import pt.ist.recommender.similarity.EuclideanDistance;
import pt.ist.recommender.similarity.PearsonCorrelation;
import pt.ist.recommender.similarity.Score;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        test4();
        test4();
        test5();
        test6();
        test7();
        test8();
        test9();
        test1();
        test2();
        test3();
        test10();
        test11();
        test12();

    }

    public static void test1() {
        Reader r = new MovieLensReader("u1.base");
        Reader t = new MovieLensReader("u1.test");
        long startTime = System.nanoTime();
        try {
            System.out.println("Test 1: U1 - ItemBased - Euclidean");
            Test t1 = new Test(r, t, new ItemBased(
                    r.invertPreferences(r.readUserPreferences()),
                    new EuclideanDistance()),
                    null);

            //t1.runVerboseTest();
            System.out.println("MAE: " + t1.calculateMAE(false));
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        System.out.println("Took " +
                (double)((System.nanoTime() - startTime)/1000000000.0) +
                " seconds.");
    }

    public static void test2() {
        Reader r = new MovieLensReader("u2.base");
        Reader t = new MovieLensReader("u2.test");
        long startTime = System.nanoTime();
        try {
            System.out.println("Test 2: U2 - ItemBased - Euclidean");
            Test t1 = new Test(r, t, new ItemBased(
                    r.invertPreferences(r.readUserPreferences()),
                    new EuclideanDistance()),
                    null);

            //t1.runVerboseTest();
            System.out.println("MAE: " + t1.calculateMAE(false));
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        System.out.println("Took " +
                (double)((System.nanoTime() - startTime)/1000000000.0) +
                " seconds.");
    }

    public static void test3() {
        Reader r = new MovieLensReader("u3.base");
        Reader t = new MovieLensReader("u3.test");
        long startTime = System.nanoTime();
        try {
            System.out.println("Test 3: U3 - ItemBased - Euclidean");
            Test t1 = new Test(r, t, new ItemBased(
                    r.invertPreferences(r.readUserPreferences()),
                    new EuclideanDistance()),
                    null);

            //t1.runVerboseTest();
            System.out.println("MAE: " + t1.calculateMAE(false));
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        System.out.println("Took " +
                (double)((System.nanoTime() - startTime)/1000000000.0) +
                " seconds.");
    }


    public static void test4() {
        Reader r = new MovieLensReader("u1.base");
        Reader t = new MovieLensReader("u1.test");
        long startTime = System.nanoTime();

        System.out.println("Test 4: U1 - UserBased - Euclidean");
        Test t1 = new Test(r, t, new UserBased(), new EuclideanDistance());

        //t1.runVerboseTest();
        System.out.println("MAE: " + t1.calculateMAE(false));
        System.out.println("Took " +
                (double)((System.nanoTime() - startTime)/1000000000.0) +
                " seconds.");
    }

    public static void test5() {
        Reader r = new MovieLensReader("u2.base");
        Reader t = new MovieLensReader("u2.test");
        long startTime = System.nanoTime();

        System.out.println("Test 5: U2 - UserBased - Euclidean");
        Test t1 = new Test(r, t, new UserBased(), new EuclideanDistance());

        //t1.runVerboseTest();
        System.out.println("MAE: " + t1.calculateMAE(false));
        System.out.println("Took " +
                (double)((System.nanoTime() - startTime)/1000000000.0) +
                " seconds.");
    }

    public static void test6() {
        Reader r = new MovieLensReader("u3.base");
        Reader t = new MovieLensReader("u3.test");
        long startTime = System.nanoTime();

        System.out.println("Test 6: U3 - UserBased - Euclidean");
        Test t1 = new Test(r, t, new UserBased(), new EuclideanDistance());

        //t1.runVerboseTest();
        System.out.println("MAE: " + t1.calculateMAE(false));
        System.out.println("Took " +
                (double)((System.nanoTime() - startTime)/1000000000.0) +
                " seconds.");
    }

    public static void test7() {
        Reader r = new MovieLensReader("u1.base");
        Reader t = new MovieLensReader("u1.test");
        long startTime = System.nanoTime();

        System.out.println("Test 7: U1 - UserBased - Pearson");
        Test t1 = new Test(r, t, new UserBased(), new PearsonCorrelation());

        //t1.runVerboseTest();
        System.out.println("MAE: " + t1.calculateMAE(false));
        System.out.println("Took " +
                (double)((System.nanoTime() - startTime)/1000000000.0) +
                " seconds.");
    }

    public static void test8() {
        Reader r = new MovieLensReader("u2.base");
        Reader t = new MovieLensReader("u2.test");
        long startTime = System.nanoTime();

        System.out.println("Test 8: U2 - UserBased - Pearson");
        Test t1 = new Test(r, t, new UserBased(), new PearsonCorrelation());

        //t1.runVerboseTest();
        System.out.println("MAE: " + t1.calculateMAE(false));
        System.out.println("Took " +
                (double)((System.nanoTime() - startTime)/1000000000.0) +
                " seconds.");
    }

    public static void test9() {
        Reader r = new MovieLensReader("u3.base");
        Reader t = new MovieLensReader("u3.test");
        long startTime = System.nanoTime();

        System.out.println("Test 9: U3 - UserBased - Pearson");
        Test t1 = new Test(r, t, new UserBased(), new PearsonCorrelation());

        //t1.runVerboseTest();
        System.out.println("MAE: " + t1.calculateMAE(false));
        System.out.println("Took " +
                (double)((System.nanoTime() - startTime)/1000000000.0) +
                " seconds.");
    }

    public static void test10() {
        Reader r = new MovieLensReader("u1.base");
        Reader t = new MovieLensReader("u1.test");
        long startTime = System.nanoTime();
        try {
            System.out.println("Test 10: U1 - ItemBased - Pearson");
            Test t1 = new Test(r, t, new ItemBased(
                    r.invertPreferences(r.readUserPreferences()),
                    new PearsonCorrelation()),
                    null);

            //t1.runVerboseTest();
            System.out.println("MAE: " + t1.calculateMAE(false));
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        System.out.println("Took " +
                (double)((System.nanoTime() - startTime)/1000000000.0) +
                " seconds.");
    }

    public static void test11() {
        Reader r = new MovieLensReader("u2.base");
        Reader t = new MovieLensReader("u2.test");
        long startTime = System.nanoTime();
        try {
            System.out.println("Test 11: U2 - ItemBased - Pearson");
            Test t1 = new Test(r, t, new ItemBased(
                    r.invertPreferences(r.readUserPreferences()),
                    new PearsonCorrelation()),
                    null);

            //t1.runVerboseTest();
            System.out.println("MAE: " + t1.calculateMAE(false));
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        System.out.println("Took " +
                (double)((System.nanoTime() - startTime)/1000000000.0) +
                " seconds.");
    }

    public static void test12() {
        Reader r = new MovieLensReader("u3.base");
        Reader t = new MovieLensReader("u3.test");
        long startTime = System.nanoTime();
        try {
            System.out.println("Test 12: U3 - ItemBased - Pearson");
            Test t1 = new Test(r, t, new ItemBased(
                    r.invertPreferences(r.readUserPreferences()),
                    new PearsonCorrelation()),
                    null);

            //t1.runVerboseTest();
            System.out.println("MAE: " + t1.calculateMAE(false));
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        System.out.println("Took " +
                (double)((System.nanoTime() - startTime)/1000000000.0) +
                " seconds.");
    }

    public static void DEBUG(String[] args) {
        Reader<String[][], ArrayList<Map.Entry<String, Boolean[]>>> r = new MovieLensReader();
        try {
            ArrayList<HashMap<Integer, Integer>> preferences = r.readUserPreferences();
            HashMap<Integer, Integer> classifiations = preferences.get(86);
            ArrayList<Map.Entry<String, Boolean[]>> movies = r.readItems();
            System.out.println("user-80 reviewed " + classifiations.size() + " movies.");
            for (Map.Entry<Integer, Integer> classification : classifiations.entrySet()) {
                System.out.println(
                        movies.get(classification.getKey()).getKey()
                                + " -> " +
                                classification.getValue());
            }

            /* ------------------ */
            System.out.println("***");
            HashMap<Integer, Integer> classifiations2 = preferences.get(919);
            ArrayList<Map.Entry<String, Boolean[]>> movies2 = r.readItems();
            System.out.println("user-919 reviewed " + classifiations2.size() + " movies.");
            for (Map.Entry<Integer, Integer> classification : classifiations2.entrySet()) {
                System.out.println(
                        movies2.get(classification.getKey()).getKey()
                                + " -> " +
                                classification.getValue());
            }

            /* ------------------ */
            System.out.println("***");
            Score s = new EuclideanDistance();
            System.out.println("Similarity by Euclidean Distance: " + s.getScore(preferences, 86, 919));

            /* ------------------ */
            System.out.println("***");
            System.out.println("Closest Users to user-86:");
            UserBased ur = new UserBased();
            List<Map.Entry<Integer, Double>> closestUsers = ur.topMatches(preferences, 86, s);
            for (Map.Entry<Integer, Double> cu : closestUsers) {
                System.out.println(cu.getKey() + " -> " + cu.getValue());
            }

            /* ------------------ */
            System.out.println("***");
            System.out.println("[UB] Best movies for user-87:");
            List<Map.Entry<Integer, Double>> bestMovies = ur.getRecommendations(preferences, 86, s);
            for (Map.Entry<Integer, Double> movie : bestMovies) {
                System.out.println(movie.getKey() + " -> " + movie.getValue());
            }

            /* ------------------ */
            System.out.println("***");
            System.out.println("[UB-Pearson] Best movies for user-87:");
            List<Map.Entry<Integer, Double>> bestMoviesP = ur.getRecommendations(preferences, 86, new PearsonCorrelation());
            for (Map.Entry<Integer, Double> movie : bestMoviesP) {
                System.out.println(movie.getKey() + " -> " + movie.getValue());
            }

            ArrayList<HashMap<Integer, Integer>> invertedPrefs = r.invertPreferences(preferences);
            /* ------------------
            System.out.println("***");
            System.out.println("Scores for Toy Story");

            for (Map.Entry<Integer, Integer> score: invertedPrefs.get(0).entrySet()) {
                System.out.println(score.getKey() + " -> " + score.getValue());
            }
            */
            /* ------------------
            System.out.println("***");
            System.out.println("Similar Movies");
            ItemBased ir = new ItemBased(invertedPrefs, s);
            HashMap<Integer, List<Map.Entry<Integer,Double>>> similarMovies = ir.buildSimilarItems(invertedPrefs, s);
            for (Map.Entry<Integer, List<Map.Entry<Integer, Double>>> movie : similarMovies.entrySet()) {
                System.out.println("----> Movie: " + movies.get(movie.getKey()).getKey());
                for (Map.Entry<Integer, Double> simMovie : movie.getValue()) {
                    System.out.println("-> " + movies.get(simMovie.getKey()).getKey() + " (" + simMovie.getValue() + ")");
                }
            }
            */

            /* ------------------ */
            System.out.println();
            System.out.println("***");
            System.out.println("[IB] Best movies for user-87:");
            Score g = new EuclideanDistance();
            Filter f = new ItemBased(invertedPrefs, g);
            List<Map.Entry<Integer, Double>> bestMovies2 = f.getRecommendations(preferences, 86, g);
            for (Map.Entry<Integer, Double> movie : bestMovies2) {
                System.out.println(movie.getKey() + " -> " + movie.getValue());
            }

        } catch (IOException ioe) {
            System.err.println("File not found or whatever.");
        }
    }
}