package com.ejercito.inventario_animales.repository;

import com.ejercito.inventario_animales.model.Animal;
import com.ejercito.inventario_animales.model.Usuario;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;



import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class AnimalRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;  // Inyectamos TestEntityManager

    @Autowired
    private AnimalRepository animalRepository;


    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Commit
    public void whenFindByEspecie_thenReturnAnimal() {

        // Crear un responsable con un correo único
        Usuario responsable = Usuario.builder()
            .nombre("Juan Pérez")
            .correo("usuario" + System.currentTimeMillis() + "@example.com")
            .rol(Usuario.Rol.ENCARGADO)
            .contraseña("password123")
            .build();

        // Persistimos el responsable
        testEntityManager.persist(responsable);
        testEntityManager.flush();

        // Crear un animal y asignarle el responsable y raza
        Animal animal = Animal.builder()
                .nombre("Rayo")
                .especie(Animal.Especie.CABALLO)
                .raza("Pura Sangre")  // Asignar un valor a la raza
                .edad(5)
                .estado(Animal.Estado.ACTIVO)
                .responsable(responsable) // Relación de clave foránea
                .build();

        // Persistimos el animal con la relación de clave foránea
        testEntityManager.persist(animal);
        testEntityManager.flush();
        testEntityManager.clear();  // Limpia el contexto de persistencia

        // Cuando - Ejecutamos la consulta para encontrar el animal por especie
        List<Animal> found = animalRepository.findByEspecie(Animal.Especie.CABALLO);

        // Entonces - Verificamos que el animal encontrado sea el esperado
        assertThat(found).isNotNull(); // Verificamos que se encontró un animal
        assertThat(found.size()).isGreaterThan(0); // Aseguramos que la lista no esté vacía
        assertThat(found.get(0).getNombre()).isEqualTo(animal.getNombre()); // Verificamos el nombre
        assertThat(found.get(0).getEspecie()).isEqualTo(animal.getEspecie()); // Verificamos la especie
        assertThat(found.get(0).getRaza()).isEqualTo(animal.getRaza()); // Verificamos la raza
        assertThat(found.get(0).getResponsable()).isNotNull(); // Verificamos que el responsable no es null
        assertThat(found.get(0).getResponsable().getNombre()).isEqualTo("Juan Pérez"); // Verificamos el nombre del responsable
    }
}
