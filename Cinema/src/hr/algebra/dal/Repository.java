package hr.algebra.dal;

import hr.algebra.model.Movie;
import hr.algebra.model.Person;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Leo
 */
public interface Repository {
    
    void createMovieAndDirector(Movie movie) throws Exception;
    void createMovies(List<Movie> movies) throws Exception;
    void createActorsForMovie(Person actor, String originalName) throws Exception;
    void updateMovie(int id, Movie movie) throws Exception;
    void deleteMovie(int id) throws Exception;
    void deleteAllData() throws Exception;
    void createNewPerson(String firstName, String lastName, boolean type) throws Exception;
    void updatePerson(Person actor, boolean type) throws Exception;
    void deletePerson(int personID, boolean type) throws Exception;
    String addSinglePersonToMovie(int personID, int movieID, boolean type) throws Exception;
    boolean[] checkUserCredentials(String username, String password) throws Exception;
    String createNewUser(String username, String password) throws Exception;
    Optional<Movie> selectMovie(int id) throws Exception;
    List<Movie> selectMovies() throws Exception;
    List<Person> selectMovieActors(int id) throws Exception;
    Person selectMovieDirector(int id) throws Exception;
    List<Person> selectDirectors() throws Exception;
    List<Person> selectActors() throws Exception;
}
