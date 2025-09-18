package org.iplacex.proyectos.discografia.Artistas;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface IArtistaRepository extends MongoRepository<Artista, String> {

    // Búsqueda explícita por el campo _id en Mongo (útil si findById no matchea)
    @Query("{ '_id': ?0 }")
    Optional<Artista> findOneByMongoId(String id);
}
