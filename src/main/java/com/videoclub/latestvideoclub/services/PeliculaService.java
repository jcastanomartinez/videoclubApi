package com.videoclub.latestvideoclub.services;

import com.videoclub.latestvideoclub.entity.Pelicula;
import com.videoclub.latestvideoclub.exceptions.ResourceNotFoundException;
import com.videoclub.latestvideoclub.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    @Autowired
    public PeliculaService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    public Pelicula crearPelicula(Pelicula peliculaInsertada){
        Pelicula nuevaPelicula= new Pelicula();
        nuevaPelicula.setId(peliculaInsertada.getId());
        nuevaPelicula.setOverview(peliculaInsertada.getOverview());
        nuevaPelicula.setTitle(peliculaInsertada.getTitle());
        nuevaPelicula.setRelease_date(peliculaInsertada.getRelease_date());
        nuevaPelicula.setPoster_path(peliculaInsertada.getPoster_path());
        nuevaPelicula.setTrailerUrl(peliculaInsertada.getTrailerUrl());
        return peliculaRepository.save(nuevaPelicula);
    }

    public List<Pelicula> obtenerTodasLasPeliculas(){
        return peliculaRepository.findAll();
    }

    public Optional<Pelicula> obtenerPeliculaPorId(Long id){
        return peliculaRepository.findById(id);
    }

    public Pelicula actualizarPelicula(Long id, Pelicula peliculaDetalles) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pelicula no encontrada"));
        pelicula.setId(peliculaDetalles.getId());
        pelicula.setOverview(peliculaDetalles.getOverview());
        pelicula.setTitle(peliculaDetalles.getTitle());
        pelicula.setRelease_date(peliculaDetalles.getRelease_date());
        pelicula.setPoster_path(peliculaDetalles.getPoster_path());
        pelicula.setTrailerUrl(pelicula.getTrailerUrl());
        return peliculaRepository.save(pelicula);
    }

    public void eliminarPelicula(Long id) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pelicula no encontrado"));
        peliculaRepository.delete(pelicula);
    }
}
