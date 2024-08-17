package com.videoclub.latestvideoclub.controllers;

import com.videoclub.latestvideoclub.entity.Pelicula;
import com.videoclub.latestvideoclub.services.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PeliculaController {


    private final PeliculaService peliculaService;

    @Autowired
    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @PostMapping("/peliculas")
    public Pelicula crearPelicula(@RequestBody Pelicula pelicula) {
        return peliculaService.crearPelicula(pelicula);
    }

    @GetMapping("/peliculas")
    public List<Pelicula> obtenerTodosLosPeliculas() {
        return peliculaService.obtenerTodasLasPeliculas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> obtenerPeliculaPorId(@PathVariable Long id) {
        Optional<Pelicula> Pelicula = peliculaService.obtenerPeliculaPorId(id);
        return Pelicula.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizarPelicula(@PathVariable Long id, @RequestBody Pelicula PeliculaDetalles) {
        Pelicula PeliculaActualizado = peliculaService.actualizarPelicula(id, PeliculaDetalles);
        return ResponseEntity.ok(PeliculaActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPelicula(@PathVariable Long id) {
        peliculaService.eliminarPelicula(id);
        return ResponseEntity.noContent().build();
    }
}
