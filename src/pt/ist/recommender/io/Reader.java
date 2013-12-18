package pt.ist.recommender.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface Reader<U, I> {
    /**
     * Obtains an array of users and for each user an array of their classified
     * items.
     *
     * @return [User -> {Item -> Classification}]
     */
    public ArrayList<HashMap<Integer, Integer>> readUserPreferences()
            throws IOException;

    /**
     * Obtains a collection or array of users.
     *
     * @return users
     */
    public U readUsers() throws IOException;

    /**
     * Obtains a collection or array of items.
     *
     * @return items
     */
    public I readItems() throws IOException;

    /**
     * Inverts the array of users so that we now have movies and their user
     * score.
     *
     * @param preferences [User -> {Item -> Classification}]
     * @return [Item -> {User -> Classification}]
     */
    public ArrayList<HashMap<Integer, Integer>> invertPreferences(
            ArrayList<HashMap<Integer, Integer>> preferences);
}
