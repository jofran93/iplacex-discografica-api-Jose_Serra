package org.iplacex.proyectos.discografia.Discos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import org.iplacex.proyectos.discografia.Artistas.IArtistaRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DiscoController {

    @Autowired
    private IDiscoRepository discoRepo;

    @Autowired
    private IArtistaRepository artistaRepo;


    @PostMapping(
        value = "/disco",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> HandlePostDiscoRequest(@RequestBody Disco disco) {
        if (disco.idArtista == null || disco.idArtista.isBlank()) {
            return ResponseEntity.badRequest().body("idArtista es requerido.");
        }

        boolean existeArtista =
                artistaRepo.existsById(disco.idArtista) ||
                artistaRepo.findOneByMongoId(disco.idArtista).isPresent();

        if (!existeArtista) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El idArtista no existe en la colección 'artistas'.");
        }

        disco._id = null;
        Disco temp = discoRepo.insert(disco);
        return new ResponseEntity<>(temp, HttpStatus.CREATED);
    }

    @GetMapping(
        value = "/disco/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Disco> HandleGetDiscoRequest(@PathVariable("id") String id) {
        Optional<Disco> temp = discoRepo.findById(id);
        return temp.map(d -> new ResponseEntity<>(d, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
    
    @GetMapping(
        value = "/discos",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Disco>> HandleGetAllDiscosRequest() {
        return new ResponseEntity<>(discoRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping(
        value = "/artista/{id}/discos",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Disco>> HandleGetDiscosByArtistaRequest(@PathVariable("id") String idArtista) {
        boolean existeArtista =
                artistaRepo.existsById(idArtista) ||
                artistaRepo.findOneByMongoId(idArtista).isPresent();

        if (!existeArtista) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        List<Disco> discos = discoRepo.findDiscosByIdArtista(idArtista);
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }
}
