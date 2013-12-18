package pt.ist.recommender.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MovieLensReader implements
        Reader<String[][], ArrayList<Map.Entry<String, Boolean[]>>> {
    private final String DATA_PATH = "data/ml-100k/";
    private final String PREFS_PATH;
    private final String USERS_PATH = DATA_PATH + "u.user";
    private final String ITEMS_PATH = DATA_PATH + "u.item";
    private final int MAX_USERS = 943, MAX_USER_ATTRIBUTES = 3;
    private final int MAX_MOVIES = 1682, MAX_MOVIE_GENRES = 19;

    public MovieLensReader() {
        PREFS_PATH = DATA_PATH + "u1.base";
    }

    public MovieLensReader(String prefs_path) {
        PREFS_PATH = DATA_PATH + prefs_path;
    }

    /**
     * Obtains a array of users and for each user a dictionary of their
     * classified movies.
     *
     * @return [User -> {Movie -> Classification}]
     */
    @Override
    public ArrayList<HashMap<Integer, Integer>> readUserPreferences()
            throws IOException {
        BufferedReader r = new BufferedReader(new FileReader(PREFS_PATH));
        String line;
        ArrayList<HashMap<Integer, Integer>> prefs =
                new ArrayList<HashMap<Integer, Integer>>(
                        Collections.nCopies(MAX_USERS,
                                (HashMap <Integer, Integer>)null));
        while ((line = r.readLine()) != null) {
            String lsa[] = line.split("\t");
            int userId = Integer.parseInt(lsa[0]) - 1;
            int movieId = Integer.parseInt(lsa[1]) - 1;
            int stars = Integer.parseInt(lsa[2]);

            if (prefs.get(userId) == null) {
                HashMap<Integer, Integer> classification =
                        new HashMap<Integer, Integer>();
                classification.put(movieId, stars);
                prefs.set(userId, classification);
            } else {
                prefs.get(userId).put(movieId, stars);
            }
        }
        r.close();
        return prefs;
    }

    /**
     * Obtains a array that in each position has an array of attributes of
     * that user.
     *
     * @return [User -> [attributes]]
     */
    public String[][] readUsers() throws IOException {
        BufferedReader r = new BufferedReader(new FileReader(USERS_PATH));
        String line;
        String[][] users = new String[MAX_USERS][MAX_USER_ATTRIBUTES];

        for (int i = 0; (line = r.readLine()) != null; ++i) {
            String user[] = line.split("\\|");
            users[i][0] = user[1]; // age
            users[i][1] = user[2]; // sex
            users[i][2] = user[3]; // job
        }
        r.close();
        return users;
    }

    /**
     * Obtains a dictionary that for each movieID has a pair consisting of its
     * name and a boolean array of genre.
     *
     * @return [Movie -> {MovieName -> [Genre]}]
     */
    public ArrayList<Map.Entry<String, Boolean[]>> readItems()
            throws IOException {
        BufferedReader r = new BufferedReader(new FileReader(ITEMS_PATH));
        String line;
        ArrayList<Map.Entry<String, Boolean[]>> movies =
                new ArrayList<Map.Entry<String, Boolean[]>>(
                        Collections.nCopies(MAX_MOVIES,
                                (Map.Entry<String, Boolean[]>)null));

        while ((line = r.readLine()) != null) {
            String lsa[] = line.split("\\|");
            int movieId = Integer.parseInt(lsa[0]) - 1;
            String movieName = lsa[1];
            Boolean[] genres = new Boolean[MAX_MOVIE_GENRES];
            for (int i = 0; i < genres.length; ++i) {
                genres[i] = "1".equals(lsa[5 + i]);
            }
            Map.Entry<String, Boolean[]> movie =
                    new AbstractMap.SimpleImmutableEntry<String, Boolean[]>(
                            movieName, genres);
            movies.add(movieId, movie);
        }
        return movies;
    }

    /**
     * Inverts the array of users so that we now have movies and their user
     * score.
     *
     * @param preferences [User -> {Item -> Classification}]
     * @return [Item -> {User -> Classification}]
     */
    public ArrayList<HashMap<Integer, Integer>> invertPreferences(
            ArrayList<HashMap<Integer, Integer>> preferences) {
        ArrayList<HashMap<Integer, Integer>> invPrefs =
                new ArrayList<HashMap<Integer, Integer>>(
                        Collections.nCopies(MAX_MOVIES,
                                new HashMap <Integer, Integer>()));
        for (int user = 0; user < preferences.size(); ++user) {
            HashMap<Integer, Integer> movies = preferences.get(user);
            for (Map.Entry<Integer, Integer> movie : movies.entrySet()) {
                if (invPrefs.get(movie.getKey()).isEmpty()) {
                    HashMap<Integer, Integer> userScores =
                            new HashMap<Integer, Integer>();
                    userScores.put(user, movie.getValue());
                    invPrefs.set(movie.getKey(), userScores);
                } else {
                    invPrefs.get(movie.getKey()).put(user, movie.getValue());
                }
            }
        }
        return invPrefs;
    }
}