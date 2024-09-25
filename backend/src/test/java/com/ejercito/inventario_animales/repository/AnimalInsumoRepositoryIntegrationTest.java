package com.ejercito.inventario_animales.repository;

import com.ejercito.inventario_animales.model.Animal;
import com.ejercito.inventario_animales.model.AnimalInsumo;
import com.ejercito.inventario_animales.model.AnimalInsumoId;
import com.ejercito.inventario_animales.model.Insumo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class AnimalInsumoRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AnimalInsumoRepository animalInsumoRepository;

    //@BeforeEach
    //public void limpiarBaseDeDatos() {
    //animalInsumoRepository.deleteAll();  // Limpiamos la base de datos antes de cada prueba
    //}


    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Commit
    public void whenFindByAnimalId_thenReturnAnimalInsumo() {

        // Crear un animal de prueba
        Animal animal = Animal.builder()
                .nombre("Spirit")
                .especie(Animal.Especie.CABALLO)
                .raza("Pura Sangre")
                .edad(1)
                .estado(Animal.Estado.ACTIVO)
                .build();
        
        // Persistir el animal
        entityManager.persist(animal);
        entityManager.flush();  // Asegurar que el animal se ha guardado

        // Crear un insumo de prueba
        Insumo insumo = Insumo.builder()
                .nombre("Vacuna Antitetánica")
                .tipo(Insumo.Tipo.MEDICAMENTO)
                .cantidadDisponible(50)
                .fechaVencimiento(java.time.LocalDate.now().plusYears(1))
                .build();
        
        // Persistir el insumo
        entityManager.persist(insumo);
        entityManager.flush();  // Asegurar que el insumo se ha guardado

        // Creamos la relación entre el animal y el insumo
        AnimalInsumoId id = new AnimalInsumoId(animal.getIdAnimal(), insumo.getIdInsumo());
        AnimalInsumo animalInsumo = AnimalInsumo.builder()
                .id(id)
                .animal(animal)
                .insumo(insumo)
                .fechaUso(LocalDate.now())
                .cantidadUsada(10)
                .build();
        entityManager.persist(animalInsumo);
        entityManager.flush();

        // Cuando - Buscamos las relaciones por el ID del animal
        List<AnimalInsumo> relaciones = animalInsumoRepository.findByAnimal_IdAnimal(animal.getIdAnimal());

        // Entonces - Verificamos que la relación fue encontrada y es correcta
        assertThat(relaciones).isNotNull(); // Verificar que se encontró el AnimalInsumo
        assertThat(relaciones).hasSize(1);
        assertThat(relaciones.get(0).getInsumo().getNombre()).isEqualTo("Vacuna Antitetánica");
        assertThat(relaciones.get(0).getCantidadUsada()).isEqualTo(10);
    }
}

  