package com.ejercito.inventario_animales.repository;

import com.ejercito.inventario_animales.model.Insumo;
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


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class InsumoRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private InsumoRepository insumoRepository;

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Commit
    public void whenFindByTipo_thenReturnInsumo() {

        // Dado - Configurando un insumo de prueba
        Insumo insumo = Insumo.builder()
                .nombre("Ivermectina")
                .tipo(Insumo.Tipo.MEDICAMENTO)
                .cantidadDisponible(100)
                .fechaVencimiento(java.time.LocalDate.now().plusYears(1))
                .build();

        // Persistimos el insumo en la base de datos
        entityManager.persist(insumo);
        entityManager.flush();

        // Cuando - Buscamos el insumo por tipo
        Insumo found = insumoRepository.findByTipo(Insumo.Tipo.MEDICAMENTO).stream().findFirst().orElse(null);

        // Entonces - Verificamos que el insumo encontrado sea el esperado
        assertThat(found).isNotNull(); // Verificamos que se encontró un insumo
        assertThat(found.getNombre()).isEqualTo(insumo.getNombre()); // Verificamos el nombre
        assertThat(found.getTipo()).isEqualTo(insumo.getTipo()); // Verificamos el tipo
    }
}
