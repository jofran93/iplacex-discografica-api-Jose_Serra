package org.iplacex.proyectos.discografia.Discos;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("discos")
public class Disco {
    @Id
    public String _id;            

    public String idArtista;
    public String nombre;
    public int anioLanzamiento;
    public List<String> canciones;
    public String getIdArtista() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getIdArtista'");
    }
    public Object getNombre() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getNombre'");
    }
    public void setNombre(Object nombre2) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setNombre'");
    }
    public Object getAnioLanzamiento() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getAnioLanzamiento'");
    }
    public void setAnioLanzamiento(Object anioLanzamiento2) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setAnioLanzamiento'");
    }
    public Object getCanciones() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getCanciones'");
    }
    public void setCanciones(Object canciones2) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setCanciones'");
    }
    public void setIdArtista(String idArtista2) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setIdArtista'");
    }
    public void setId(Object object) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }
}
