package task1;

import java.util.List;

public class Laureate {

    private String name;
    private List<String> birth_death; // list
    private List<String> nations;
    private List<String> languages;
    private List<String> genres;
    private List<String> citation;

    public Laureate(String name, List<String> birth_death, List<String> nations, List<String> languages, List<String> citation, List<String> genres) {
        this.name = name;
        this.birth_death = birth_death;
        this.nations = nations;
        this.languages = languages;
        this.genres = genres;
        this.citation = citation;
    }

    public String getName() {
        return name;
    }

    public List<String> getBirthDeath() {
        return birth_death;
    }

    public List<String> getNations() {
        return nations;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<String> getCitation() {
        return citation;
    }

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
