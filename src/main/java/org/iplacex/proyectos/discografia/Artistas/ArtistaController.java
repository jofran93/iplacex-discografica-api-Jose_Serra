package org.iplacex.proyectos.discografia.Artistas;

import java.util.List;
import java.util.Optional;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ArtistaController {

    private final IArtistaRepository artistaRepo;

    public ArtistaController(IArtistaRepository artistaRepo) {
        this.artistaRepo = artistaRepo;
    }

    @PostMapping(
        value = "/artista",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> handleInsertArtistaRequest(@RequestBody Artista artista) {
        artista._id = null;
        Artista creado = artistaRepo.insert(artista);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @GetMapping(
        value = "/artista/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> handleGetArtistaRequest(@PathVariable("id") String id) {
        Optional<Artista> temp = artistaRepo.findById(id);
        return temp.map(a -> new ResponseEntity<>(a, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping(
        value = "/artistas",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Artista>> handleGetAllArtistasRequest() {
        return new ResponseEntity<>(artistaRepo.findAll(), HttpStatus.OK);
    }

    @PutMapping(
        value = "/artista/{id}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> handleUpdateArtistaRequest(
        @PathVariable("id") String id,
        @RequestBody Artista artista
    ) {
        if (!artistaRepo.existsById(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        artista._id = id;
        Artista actualizado = artistaRepo.save(artista);
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @DeleteMapping("/artista/{id}")
    public ResponseEntity<Void> handleDeleteArtistaRequest(@PathVariable("id") String id) {
        if (!artistaRepo.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        artistaRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
