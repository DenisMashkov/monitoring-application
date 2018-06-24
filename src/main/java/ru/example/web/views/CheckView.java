package ru.example.web.views;

import lombok.Data;

import java.util.Collection;
import java.util.Date;

@Data
public class CheckView {
    private Date checkDate;
    private Collection<UrlView> urls;
}
