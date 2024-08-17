package com.videoclub.latestvideoclub.repository;

import com.videoclub.latestvideoclub.entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

}
