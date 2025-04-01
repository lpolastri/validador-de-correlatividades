package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

class InscripcionTest {
    //Alumno
    Alumno alumno = new Alumno("Joaquin", "Rodrigues", "123456");

    //Materias
    Materia dsi = new Materia("Diseño de Sistemas de Informacion");
    Materia asi = new Materia("Analisis de Sistemas de Informacion");
    Materia pdp = new Materia("Paradigmas de Programcion");
    Materia it1 = new Materia("Ingles I");
    Materia ayed = new Materia("Algoritmos y Estructuras de Datos");
    Materia sypn = new Materia("Sistemas y Procesos de Negocios");
    Materia lyed = new Materia("Logica y Estructuras Discretas");

    @BeforeEach
    void setUp() {
        dsi.setCorrelativas(Set.of(asi, pdp, it1, ayed, sypn));
        asi.setCorrelativas(Set.of(sypn, ayed));
        pdp.setCorrelativas(Set.of(lyed, ayed));
    }

    @Test
    @DisplayName("Un alumno sin materias aprobadas puede inscribirse a materias sin correlativas")
    void AlumnoSinMaterias() {
        Inscripcion inscripcion = new Inscripcion(Set.of(lyed, sypn, ayed, it1), alumno);
        Assertions.assertTrue(inscripcion.aprobada());
    }

    @Test
    @DisplayName("Un alumno sin materias aprobadas no puede inscribirse a materias con correlativas")
    void AlumnoSinMateriasCorrelativas() {
        Inscripcion inscripcion = new Inscripcion(Set.of(pdp, asi), alumno);
        Assertions.assertFalse(inscripcion.aprobada());
    }

    @Test
    @DisplayName("Un alumno con materias aprobadas puede inscribirse a materias sin correlativas")
    void AlumnoConMateriasASinCorrelativas() {
        alumno.setMateriasAprobadas(Set.of(ayed, sypn));
        Inscripcion inscripcion = new Inscripcion(Set.of(it1, lyed), alumno);
        Assertions.assertTrue(inscripcion.aprobada());
    }

    @Test
    @DisplayName("Un alumno sin todas las correlativas aprobadas de una materia no puede inscribirse a esta")
    void AlumnoConMateriasNoCorrelativas() {
        alumno.setMateriasAprobadas(Set.of(lyed, sypn));
        Inscripcion inscripcion = new Inscripcion(Set.of(pdp, asi), alumno);
        Assertions.assertFalse(inscripcion.aprobada());
    }

    @Test
    @DisplayName("Un alumno con todas las correlativas aprobadas de una materia puede inscribirse a esta")
    void AlumnoConMateriasCorrelativas() {
        alumno.setMateriasAprobadas(Set.of(lyed, sypn, ayed, it1, asi, pdp));
        Inscripcion inscripcion = new Inscripcion(Set.of(dsi), alumno);
        Assertions.assertTrue(inscripcion.aprobada());
    }
}