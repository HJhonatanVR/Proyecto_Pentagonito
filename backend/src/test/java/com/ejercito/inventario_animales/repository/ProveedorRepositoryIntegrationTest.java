package com.ejercito.inventario_animales.repository;

import com.ejercito.inventario_animales.model.Proveedor;
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
public class ProveedorRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Commit
    public void whenFindByNombre_thenReturnProveedor() {

        // Generar un nombre único para evitar duplicados
        String uniqueNombre = "Proveedor XYZ " + System.currentTimeMillis();

        // Dado - Configurando un proveedor de prueba con nombre único
        Proveedor proveedor = Proveedor.builder()
                .nombre(uniqueNombre) // Nombre único
                .direccion("Av. Los Andes 123")
                .telefono("987654321")
                .build();

        // Persistimos el proveedor en la base de datos
        entityManager.persist(proveedor);
        entityManager.flush();

        // Cuando - Buscamos el proveedor por nombre
        Proveedor found = proveedorRepository.findByNombre(uniqueNombre);

        // Entonces - Verificamos que el proveedor encontrado sea el esperado
        assertThat(found).isNotNull(); // Verificamos que se encontró un proveedor
        assertThat(found.getNombre()).isEqualTo(proveedor.getNombre()); // Verificamos el nombre
    }
}
