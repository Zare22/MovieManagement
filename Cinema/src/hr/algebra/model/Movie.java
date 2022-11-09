package hr.algebra.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 *
 * @author Leo
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Movie {
    
    @XmlAttribute
    private int id;
    @XmlElement(name = "originalname")
    private String originalName;
    @XmlElement(name = "director")
    private Person director;
    @XmlElement(name = "directorid")
    private int directorID;
    @XmlElement(name = "actor")
    private List<Person> actors;
    @XmlElement(name = "description")
    private String description;
    @XmlElement(name = "imagepath")
    private String imagePath;

    public Movie() {
    }

    public Movie(String originalName, int directorID, String description, String imagePath) {
        this.originalName = originalName;
        this.directorID = directorID;
        this.description = description;
        this.imagePath = imagePath;
    }
    
    public Movie(String originalName, Person director, List<Person> actors, String description, String imagePath) {
        this.originalName = originalName;
        this.director = director;
        this.actors = actors;
        this.description = description;
        this.imagePath = imagePath;
    }

    public Movie(int id, String originalName, Person director, String description, String imagePath, List<Person> actors) {
        this.id = id;
        this.originalName = originalName;
        this.director = director;
        this.description = description;
        this.imagePath = imagePath;
        this.actors = actors;
    }

    public int getDirectorID() {
        return directorID;
    }

    public void setDirectorID(int directorID) {
        this.directorID = directorID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Person getDirector() {
        return director;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    public List<Person> getActors() {
        return actors;
    }

    public void setActors(List<Person> actors) {
        this.actors = actors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return originalName;
    }
    
    
    
}
