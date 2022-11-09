package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Movie;
import hr.algebra.model.Person;
import hr.algebra.utils.MessageUtils;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leo
 */
public class SQLRepository implements Repository {

    //Movie columns and procedure parameters
    private static final String ID_MOVIE = "IDMovie";
    private static final String MOVIE_ID = "MovieID";
    private static final String ORIGINAL_NAME = "OriginalName";
    private static final String IMAGE_PATH = "ImagePath";
    private static final String MOVIE_DESCRIPTION = "MovieDescription";
    private static final String DIRECTOR_ID = "DirectorID";

    //Person columns and procedure parameters
    private static final String ID_DIRECTOR = "IDDirector";
    private static final String ID_ACTOR = "IDActor";
    private static final String FIRST_NAME = "FirstName";
    private static final String LAST_NAME = "LastName";
    private static final String TYPE = "Type";
    private static final String IDPERSON = "IDPerson";

    //User columns and procedure parameters
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final String IS_ADMIN = "isAdmin";
    private static final String IS_SUCCESS = "isSuccess";
    private static final String RESPONSE = "Response";

    //Movie procedures
    private static final String CREATE_MOVIE_AND_DIRECTOR = "{ CALL createMovieAndDirector (?, ?, ?, ?, ?, ?, ?) }";
    private static final String UPDATE_MOVIE = "{ CALL updateMovie (?, ?, ?, ?, ?) }";
    private static final String DELETE_MOVIE = "{ CALL deleteMovie (?) }";
    private static final String SELECT_MOVIE = "{ CALL selectMovie (?) }";
    private static final String SELECT_MOVIES = "{ CALL selectMovies }";

    //Actor and Director procedures
    private static final String SELECT_MOVIE_ACTORS = "{ CALL selectMovieActors (?) }";
    private static final String SELECT_MOVIE_DIRECTOR = "{ CALL selectMovieDirector (?) }";
    private static final String SELECT_DIRECTORS = "{ CALL selectDirectors }";
    private static final String SELECT_ACTORS = "{ CALL selectActors }";
    private static final String CREATE_ACTORS_FOR_MOVIES = "{ CALL createActorsForMovie (?, ?, ?) }";
    private static final String DELETE_MOVIE_ACTOR = "{ CALL deleteMovieActor (?) }";
    private static final String CREATE_PERSON = "{ CALL createNewPerson (? , ?, ?) }";
    private static final String UPDATE_PERSON = "{ CALL updatePerson (?, ? , ?, ?) }";
    private static final String DELETE_PERSON = "{ CALL deletePerson (?, ?) }";
    private static final String ADD_SINGLE_PERSON_TO_MOVIE = "{ CALL addSinglePersonToMovie (?, ?, ?, ?) }";

    //User managment
    private static final String CREATE_NEW_USER = "{ CALL createNewUser (?, ?, ?) }";
    private static final String CHECK_USER_CREDENTIALS = "{ CALL checkUserCredentials (?, ?, ?, ?, ?) }";

    //Database management
    private static final String DELETE_ALL_DATA = "{ CALL deleteAllData }";

    @Override
    public void createMovieAndDirector(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try ( var con = dataSource.getConnection(); CallableStatement statement = con.prepareCall(CREATE_MOVIE_AND_DIRECTOR)) {

            statement.setString("@" + ORIGINAL_NAME, movie.getOriginalName());
            statement.setString("@" + IMAGE_PATH, movie.getImagePath());
            statement.setString("@" + MOVIE_DESCRIPTION, movie.getDescription());
            statement.setString("@DirectorFirstName", movie.getDirector().getFirstName());
            statement.setString("@DirectorLastName", movie.getDirector().getLastName());

            statement.registerOutParameter("@" + ID_DIRECTOR, Types.INTEGER);
            statement.registerOutParameter("@" + ID_MOVIE, Types.INTEGER);

            statement.executeUpdate();
            
            deleteMovieActor(statement.getInt("@" + ID_MOVIE));
            if (movie.getActors() != null) {
                pairActorMovies(movie);
            }
        }
    }

    @Override
    public void createMovies(List<Movie> movies) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try ( var con = dataSource.getConnection();  CallableStatement statement = con.prepareCall(CREATE_MOVIE_AND_DIRECTOR)) {

            for (Movie movie : movies) {

                statement.setString("@" + ORIGINAL_NAME, movie.getOriginalName());
                statement.setString("@" + IMAGE_PATH, movie.getImagePath());
                statement.setString("@" + MOVIE_DESCRIPTION, movie.getDescription());
                statement.setString("@DirectorFirstName", movie.getDirector().getFirstName());
                statement.setString("@DirectorLastName", movie.getDirector().getLastName());

                statement.registerOutParameter("@" + ID_DIRECTOR, Types.INTEGER);
                statement.registerOutParameter("@" + ID_MOVIE, Types.INTEGER);

                statement.executeUpdate();
                if (movie.getActors() != null) {
                    pairActorMovies(movie);
                }
            }
        }
    }

    @Override
    public void updateMovie(int id, Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try ( var con = dataSource.getConnection();  CallableStatement statement = con.prepareCall(UPDATE_MOVIE)) {

            statement.setInt("@" + ID_MOVIE, id);
            statement.setString("@" + ORIGINAL_NAME, movie.getOriginalName());
            statement.setString("@" + IMAGE_PATH, movie.getImagePath());
            statement.setString("@" + MOVIE_DESCRIPTION, movie.getDescription());
            statement.setInt("@" + DIRECTOR_ID, movie.getDirectorID());

            deleteMovieActor(id);
            pairActorMovies(movie);
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try ( var con = dataSource.getConnection();  CallableStatement statement = con.prepareCall(DELETE_MOVIE)) {

            statement.setInt(ID_MOVIE, id);
            deleteMovieActor(id);

            statement.executeUpdate();
        }
    }

    @Override
    public Optional<Movie> selectMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try ( var con = dataSource.getConnection();  CallableStatement statement = con.prepareCall(SELECT_MOVIE)) {
            statement.setInt("@" + ID_MOVIE, id);
            try ( var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(
                            new Movie(resultSet.getInt(ID_MOVIE),
                                    resultSet.getString(ORIGINAL_NAME),
                                    selectMovieDirector(resultSet.getInt(ID_MOVIE)),
                                    resultSet.getString(MOVIE_DESCRIPTION),
                                    resultSet.getString(IMAGE_PATH),
                                    selectMovieActors(resultSet.getInt(ID_MOVIE)))
                    );
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Movie> selectMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try ( var con = dataSource.getConnection();  CallableStatement statement = con.prepareCall(SELECT_MOVIES);  var resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                movies.add(
                        new Movie(resultSet.getInt(ID_MOVIE),
                                resultSet.getString(ORIGINAL_NAME),
                                selectMovieDirector(resultSet.getInt(ID_MOVIE)),
                                resultSet.getString(MOVIE_DESCRIPTION),
                                resultSet.getString(IMAGE_PATH),
                                selectMovieActors(resultSet.getInt(ID_MOVIE)))
                );
            }
        }
        return movies;
    }

    @Override
    public List<Person> selectMovieActors(int id) throws Exception {
        List<Person> actors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();

        try ( var con = dataSource.getConnection();  CallableStatement statement = con.prepareCall(SELECT_MOVIE_ACTORS)) {
            statement.setInt("@" + ID_MOVIE, id);
            try ( var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    actors.add(new Person(resultSet.getInt(ID_ACTOR), resultSet.getString(FIRST_NAME), resultSet.getString(LAST_NAME)));
                }
            }
        }
        return actors;
    }

    @Override
    public Person selectMovieDirector(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try ( var con = dataSource.getConnection();  CallableStatement statement = con.prepareCall(SELECT_MOVIE_DIRECTOR)) {
            statement.setInt("@" + ID_MOVIE, id);
            try ( var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return (new Person(resultSet.getInt(ID_DIRECTOR), resultSet.getString(FIRST_NAME), resultSet.getString(LAST_NAME)));
                }
            }
        }
        //Refactor
        return new Person("", "");
    }

    @Override
    public List<Person> selectDirectors() throws Exception {
        List<Person> directors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try ( var con = dataSource.getConnection();  CallableStatement statement = con.prepareCall(SELECT_DIRECTORS)) {
            try ( var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    directors.add(new Person(resultSet.getInt(ID_DIRECTOR), resultSet.getString(FIRST_NAME), resultSet.getString(LAST_NAME)));
                }
            }
        }
        return directors;
    }

    @Override
    public void createActorsForMovie(Person actor, String originalName) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try ( var con = dataSource.getConnection();  CallableStatement statement = con.prepareCall(CREATE_ACTORS_FOR_MOVIES)) {
            statement.setString("@" + FIRST_NAME, actor.getFirstName());
            statement.setString("@" + LAST_NAME, actor.getLastName());
            statement.setString("@" + ORIGINAL_NAME, originalName);

            statement.executeUpdate();
        }
    }

    @Override
    public List<Person> selectActors() throws Exception {
        List<Person> actors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try ( var con = dataSource.getConnection();  CallableStatement statement = con.prepareCall(SELECT_ACTORS)) {
            try ( var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    actors.add(new Person(resultSet.getInt(ID_ACTOR), resultSet.getString(FIRST_NAME), resultSet.getString(LAST_NAME)));
                }
            }
        }
        return actors;
    }


    @Override
    public boolean[] checkUserCredentials(String username, String password) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try ( var con = dataSource.getConnection();  CallableStatement statement = con.prepareCall(CHECK_USER_CREDENTIALS)) {
            statement.setString("@" + USERNAME, username);
            statement.setString("@" + PASSWORD, password);
            statement.registerOutParameter(IS_SUCCESS, Types.BIT);
            statement.registerOutParameter(IS_ADMIN, Types.BIT);
            statement.registerOutParameter(RESPONSE, Types.NVARCHAR);

            statement.executeUpdate();
            boolean existsBoolean = statement.getBoolean("@" + IS_SUCCESS);
            boolean adminBoolean = statement.getBoolean("@" + IS_ADMIN);

            boolean[] userInfo = new boolean[2];
            userInfo[0] = existsBoolean;
            userInfo[1] = adminBoolean;

            return userInfo;
        }
    }

    @Override
    public String createNewUser(String username, String password) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try ( var con = dataSource.getConnection();  CallableStatement statement = con.prepareCall(CREATE_NEW_USER)) {
            statement.setString("@" + USERNAME, username);
            statement.setString("@" + PASSWORD, password);
            statement.registerOutParameter(RESPONSE, Types.NVARCHAR);

            statement.executeUpdate();
            return statement.getString(RESPONSE);
        }
    }

    @Override
    public void deleteAllData() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try ( var con = dataSource.getConnection();  CallableStatement statement = con.prepareCall(DELETE_ALL_DATA)) {
            statement.executeUpdate();
        }
    }

    @Override
    public void createNewPerson(String firstName, String lastName, boolean type) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try ( var con = dataSource.getConnection();  CallableStatement statement = con.prepareCall(CREATE_PERSON)) {
            statement.setString("@" + FIRST_NAME, firstName);
            statement.setString("@" + LAST_NAME, lastName);
            statement.setInt("@" + TYPE, type ? 1 : 0);
            statement.executeUpdate();
        }
    }

    @Override
    public void updatePerson(Person actor, boolean type) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try ( var con = dataSource.getConnection();  CallableStatement statement = con.prepareCall(UPDATE_PERSON)) {
            statement.setInt("@" + IDPERSON, actor.getId());
            statement.setString("@" + FIRST_NAME, actor.getFirstName());
            statement.setString("@" + LAST_NAME, actor.getLastName());
            statement.setInt("@" + TYPE, type ? 1 : 0);
            statement.executeUpdate();
        }
    }

    @Override
    public void deletePerson(int personID, boolean type) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try ( var con = dataSource.getConnection();  CallableStatement statement = con.prepareCall(DELETE_PERSON)) {
            statement.setInt("@" + IDPERSON, personID);
            statement.setInt("@" + TYPE, type ? 1 : 0);
            statement.executeUpdate();
        }
    }

    @Override
    public String addSinglePersonToMovie(int personID, int movieID, boolean type) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try ( var con = dataSource.getConnection();  CallableStatement statement = con.prepareCall(ADD_SINGLE_PERSON_TO_MOVIE)) {
            statement.setInt("@" + IDPERSON, personID);
            statement.setInt("@" + MOVIE_ID, movieID);
            statement.setInt("@" + TYPE, type ? 1 : 0);
            
            statement.registerOutParameter("@" + RESPONSE, Types.NVARCHAR);
            statement.executeUpdate();
            return statement.getString(RESPONSE);
        }
    }
    
    
    //Not really the place for this... Check with prof
    private void pairActorMovies(Movie movie) {
        movie.getActors().forEach(a -> {
            try {
                createActorsForMovie(a, movie.getOriginalName());
            } catch (SQLException ex) {
                Logger.getLogger(SQLRepository.class.getName()).log(Level.SEVERE, null, ex);
                MessageUtils.showErrorMessage("Error", "Error when trying to pair actors with selected movie");
            }
        });
    }

    
    private void deleteMovieActor(int movieID) {

        DataSource dataSource = DataSourceSingleton.getInstance();
        try ( var con = dataSource.getConnection();  CallableStatement statement = con.prepareCall(DELETE_MOVIE_ACTOR)) {
            statement.setInt("@MovieID", movieID);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SQLRepository.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Error when trying to delete actors for selected movie");
        }
    }

}
