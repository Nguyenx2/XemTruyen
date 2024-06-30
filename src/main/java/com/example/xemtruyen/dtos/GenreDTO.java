package com.example.xemtruyen.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenreDTO {
    @NotBlank(message = "Genre's name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;
}
