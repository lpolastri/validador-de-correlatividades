package domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

public class Alumno {
    private String nombre;
    private String apellido;
    private String legajo;
    private Set<Materia> materiasAprobadas;

    public Alumno(String nombre, String apellido, String legajo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.materiasAprobadas = Set.of();
    }

    public Boolean estaAprobada(Materia materia) {
        return materiasAprobadas.contains(materia);
    }
}
