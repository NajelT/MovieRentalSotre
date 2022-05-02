package com.example.mrs.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Movie {
    private long id;
    private String name;
    private LocalDate releaseDate;
    private List<String> categories;
    private List<String> actors;
    private List<String> director;
    private List <String> writers;
    private String description;
    private int runtime;

    public Movie(long id, String name, String releaseDate, List<String> categories, List<String> actors, List<String> director, List<String> writers, String description, int runtime) {
        this.id = id;
        this.name = name;
        this.releaseDate = LocalDate.parse(releaseDate, DateTimeFormatter.ISO_LOCAL_DATE);
        this.categories = categories;
        this.actors = actors;
        this.director = director;
        this.writers = writers;
        this.description = description;
        this.runtime = runtime;
    }

    public BigDecimal getPrice(){
        int weeks = (int) ChronoUnit.WEEKS.between(releaseDate, LocalDate.now());
        return getPrice(weeks);
    }

    public BigDecimal getPrice(int weeksFromRelease){
        if (weeksFromRelease < 52) {
            return new BigDecimal(5);
        }else if(weeksFromRelease < 156){
            return BigDecimal.valueOf(3.49);
        }
        return BigDecimal.valueOf(1.99);
    }

    public BigDecimal rentOut(int durationInWeeks){
        BigDecimal totalPrice = new BigDecimal(0);
        int weeks = (int) ChronoUnit.WEEKS.between(releaseDate, LocalDate.now());
        for (int i = 0; i < durationInWeeks; i++) {
            totalPrice = totalPrice.add(this.getPrice(weeks));
            weeks+=1;
        }
        return totalPrice;
    }
}
