package hr.algebra.parsers.rss;

import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.model.Movie;
import hr.algebra.model.Person;
import hr.algebra.utils.FileUtils;
import hr.algebra.utils.MessageUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Leo
 */
public class MovieParser {

    private static final String RSS_URL = "https://web.archive.org/web/20210228003317if_/https://www.blitz-cinestar.hr/rss.aspx?najava=1";
    private static final String DIR = "assets";
    private static final String EXT = ".jpg";

    public static List<Movie> parse() throws IOException, XMLStreamException {
        List<Movie> movies = new ArrayList<>();
        HttpURLConnection connection = UrlConnectionFactory.getHttpUrlConnection(RSS_URL);

        try ( InputStream inputStream = connection.getInputStream()) {
            XMLEventReader reader = ParserFactory.createStaxParser(inputStream);

            Optional<TagType> tagType = Optional.empty();
            Movie movie = null;
            StartElement startElement = null;

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:

                        startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        tagType = TagType.from(qName);

                        if (tagType.isPresent() && tagType.get().equals(TagType.ITEM)) {
                            movie = new Movie();
                            movies.add(movie);
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (tagType.isPresent() && movie != null) {
                            Characters chars = event.asCharacters();
                            String data = chars.getData().trim();

                            switch (tagType.get()) {
                                case ORIGINAL_NAME:
                                    if (!data.isEmpty()) {
                                        movie.setOriginalName(data);
                                    }
                                    break;
                                case DIRECTOR:
                                    if (!data.isEmpty()) {
                                        if (!data.contains(",")) {
                                            movie.setDirector(new Person().parsePersonFromFullName(data));
                                        }
                                        else {
                                            movie.setDirector(new Person().parsePersonFromFullName(splitTheString(data, ",", 2, 0)));
                                        }
                                    }
                                    break;
                                case ACTORS:
                                    if (!data.isEmpty()) {
                                        List<Person> actors = new ArrayList<>();
                                        String[] fullNames = data.split(",");
                                        
                                        for (String fullName : fullNames) {
                                           Person actor = new Person().parsePersonFromFullName(fullName);
                                           actors.add(actor);
                                        }
                                        movie.setActors(actors);
                                    }
                                    break;
                                case MOVIE_DESCRIPTION:
                                    if (!data.isEmpty()) {
                                        //Picture handling
                                        if (startElement != null && movie.getImagePath() == null) {
                                            String extractedImageURL = extractImageUrl(data);
                                            String imageURL = splitTheString(extractedImageURL, "&", 2, 0);
                                            if (imageURL != null) {
                                                handlePicture(movie, imageURL);
                                            }
                                        }
                                        //Description handling
                                        String removeImageFromDescription = splitTheString(data, ">", 2, 1);
                                        String finalDescription = splitTheString(removeImageFromDescription, "<", 2, 0);
                                        movie.setDescription(finalDescription);

                                    }
                                    break;
                            }
                        }
                        break;
                }
            }
        }

        return movies;
    }

    private static void handlePicture(Movie movie, String pictureUrl) {
        try {
            String ext = pictureUrl.substring(pictureUrl.lastIndexOf("."));
            if (ext.length() > 4) {
                ext = EXT;
            }
            String name = UUID.randomUUID() + ext;
            String localPath = DIR + File.separator + name;
            FileUtils.copyFromUrl(pictureUrl, localPath);
            movie.setImagePath(localPath);
        } catch (IOException ex) {
            Logger.getLogger(MovieParser.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "There was a problem with image handling");
        }
    }

    private static String extractImageUrl(String description) {
        Document document = Jsoup.parse(description);
        Elements imgs = document.select("img");

        for (Element img : imgs) {
            if (img.hasAttr("src")) {
                return img.attr("src");
            }
        }

        // no image URL
        return "";
    }
    
    private static String splitTheString(String string, String regex, int parts, int index) {
        String[] split = string.split(regex, parts);
        return split[index];
    }

    private enum TagType {
        ITEM("item"),
        ORIGINAL_NAME("orignaziv"),
        MOVIE_DESCRIPTION("description"),
        DIRECTOR("redatelj"),
        ACTORS("glumci");

        private final String name;

        private TagType(String name) {
            this.name = name;
        }

        private static Optional<TagType> from(String name) {
            for (TagType value : values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
    }
}
