package org.iplacex.proyectos.discografia.Artistas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.List;

@Document("artistas")
public class Artista {
    @Id
    public String _id;

    @Field("nombre")
    public String nombre;

    @Field("estilos")
    public List<String> estilos;

    @Field("anioFundacion")
    public int anioFundacion;

    @Field("estaActivo")
    public boolean estaActivo;
}
