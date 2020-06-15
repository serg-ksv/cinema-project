package mate.academy.cinema.model.dto;

import javax.validation.constraints.NotBlank;

public class MovieRequestDto {
    @NotBlank(message = "Movie title can't be empty!")
    private String title;
    @NotBlank(message = "Movie description can't be empty!")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
