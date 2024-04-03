package task1;

// Import List interface from java.util package to use collections framework
import java.util.List;

// Class representing a Laureate
public class Laureate {

    // Fields to store laureate information
    private String name;
    private List<String> birth_death; // list to store birth and death information
    private List<String> nations; // list to store nations the laureate belongs to
    private List<String> languages; // list to store languages spoken by the laureate
    private List<String> genres; // list to store genres associated with the laureate
    private List<String> citation; // list to store citations received by the laureate

    // Constructor to initialize the laureate object with provided information
    public Laureate(String name, List<String> birth_death, List<String> nations, List<String> languages, List<String> citation, List<String> genres) {
        this.name = name;
        this.birth_death = birth_death;
        this.nations = nations;
        this.languages = languages;
        this.genres = genres;
        this.citation = citation;
    }

    // Getter method to retrieve the laureate's name
    public String getName() {
        return name;
    }

    // Getter method to retrieve the laureate's birth and death information
    public List<String> getBirthDeath() {
        return birth_death;
    }

    // Getter method to retrieve the nations the laureate belongs to
    public List<String> getNations() {
        return nations;
    }

    // Getter method to retrieve the languages spoken by the laureate
    public List<String> getLanguages() {
        return languages;
    }

    // Getter method to retrieve the genres associated with the laureate
    public List<String> getGenres() {
        return genres;
    }

    // Getter method to retrieve the citations received by the laureate
    public List<String> getCitation() {
        return citation;
    }

    // Override toString method to provide a string representation of the laureate object
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Laureate{name=").append(name);
        sb.append(", birth_death=").append(birth_death);
        sb.append(", nations=").append(nations);
        sb.append(", languages=").append(languages);
        sb.append(", citation=").append(citation);
        sb.append(", genres=").append(genres);

        return sb.toString();
    }

}
