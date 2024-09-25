package com.ejercito.inventario_animales.repository;

import com.ejercito.inventario_animales.model.Animal;
import com.ejercito.inventario_animales.model.HistorialMedico;
import com.ejercito.inventario_animales.model.Usuario;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class HistorialMedicoRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private HistorialMedicoRepository historialMedicoRepository;

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Commit
    public void whenFindByAnimalId_thenReturnHistorialMedico() {

        // Generamos un correo único para el usuario
        String uniqueEmail = "usuario" + System.currentTimeMillis() + "@example.com";

        // Crear un veterinario con un correo único
        Usuario veterinario = Usuario.builder()
            .nombre("Juan Pérez")
            .correo(uniqueEmail)
            .rol(Usuario.Rol.VETERINARIO)  // Cambié el rol a VETERINARIO
            .contraseña("password123")
            .build();

        // Persistimos el veterinario
        entityManager.persist(veterinario);
        entityManager.flush();  // Aseguramos que el veterinario está guardado

        // Dado - Configuramos un animal y su historial médico
        Animal animal = Animal.builder()
                .nombre("Bolt " + System.currentTimeMillis())  // Generamos un nombre único
                .especie(Animal.Especie.PERRO)
                .raza("Doberman")  // Asignar un valor a la raza
                .edad(2)
                .estado(Animal.Estado.ACTIVO)
                .responsable(veterinario) // Relación de clave foránea
                .build();
        entityManager.persist(animal);
        entityManager.flush(); // Persistimos el animal antes de persistir el historial

        // Creamos y persistimos el historial médico
        HistorialMedico historial = HistorialMedico.builder()
                .animal(animal)
                .diagnostico("Infección leve")
                .tratamiento("Antibióticos")
                .fechaAtencion(LocalDateTime.now()) // Asignamos la fecha de atención
                .veterinario(veterinario) // Aseguramos la relación con el veterinario
                .build();
        entityManager.persist(historial);
        entityManager.flush();  // Aseguramos que el historial se haya guardado correctamente

        // Cuando - Buscamos el historial médico por ID de animal
        HistorialMedico found = historialMedicoRepository.findByAnimal_IdAnimal(animal.getIdAnimal()).stream().findFirst().orElse(null);

        // Entonces - Verificamos que el historial encontrado sea el esperado
        assertThat(found).isNotNull(); // Verificamos que se encontró un historial
        assertThat(found.getDiagnostico()).isEqualTo(historial.getDiagnostico()); // Verificamos el diagnóstico
        assertThat(found.getTratamiento()).isEqualTo(historial.getTratamiento()); // Verificamos el tratamiento
        assertThat(found.getVeterinario()).isNotNull();  // Verificamos que se guardó el veterinario
        assertThat(found.getVeterinario().getNombre()).isEqualTo("Juan Pérez"); // Verificamos el nombre del veterinario
    }
}
