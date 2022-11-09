package hr.algebra.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 *
 * @author Leo
 */
@XmlRootElement(name = "moviearchive")
@XmlAccessorType (XmlAccessType.FIELD)
public class MovieArchive {
    @XmlElementWrapper
    @XmlElement(name = "movie")
    private List<Movie> movies;

    public MovieArchive() {
    }

    public MovieArchive(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }
    
    
}
