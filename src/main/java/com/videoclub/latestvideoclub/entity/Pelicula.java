package com.videoclub.latestvideoclub.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity(name="Peliculas")
public class Pelicula {
    @Id
    private int id;

    private String title;

    @Column(length = 500)
    private String overview;

    private LocalDate release_date;

    private String poster_path;

    private String trailerUrl;
}
