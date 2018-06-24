package ru.example.web.views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlView {
    private String name;
    private Boolean active;
    private Integer statusCode;
}
