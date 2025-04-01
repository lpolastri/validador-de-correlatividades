package domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Materia {
    private String nombre;
    private Set<Materia> correlativas;

    public Materia(String nombre) {
        this.nombre = nombre;
        this.correlativas = Set.of();
    }

    public Boolean cumpleCorrelativas(Alumno alumno) {
        if (correlativas.isEmpty())
            return true;
        return correlativas.stream().allMatch(alumno::estaAprobada);
    }
}
