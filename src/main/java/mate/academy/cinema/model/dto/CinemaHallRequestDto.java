package mate.academy.cinema.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class CinemaHallRequestDto {
    @Min(50)
    @Max(400)
    private int capacity;
    @NotBlank(message = "Cinema hall description can't be empty!")
    private String description;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
