package com.task.task.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class Body {
    @NotNull
    @Size(min = 1, message = "Input text shouldn't be empty.")
    private List<String> data;
}
