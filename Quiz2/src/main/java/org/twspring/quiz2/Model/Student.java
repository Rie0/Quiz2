package org.twspring.quiz2.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    @NotNull(message = "Id cannot be empty")
    @Positive(message = "Id cannot be zero or a negative number")
    private int id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Age cannot be empty")
    @Positive(message = "Age cannot be zero or a negative number")
    private int age;

    @NotEmpty(message = "Major cannot be empty")
    private String major;
}
