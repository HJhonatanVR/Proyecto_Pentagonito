package com.ejercito.inventario_animales.repository;

import com.ejercito.inventario_animales.model.Reporte;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ReporteRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReporteRepository reporteRepository;

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Commit
    public void whenFindByTipo_thenReturnReporte() {
        // Generar un valor de tiempo único para asegurar consistencia
        String uniqueTime = String.valueOf(System.currentTimeMillis());
        String uniqueContent = "Reporte detallado de insumos " + uniqueTime;

        // Dado - Configurando un reporte de prueba
        Reporte reporte = Reporte.builder()
                .tipoReporte(Reporte.TipoReporte.INSUMOS)
                .contenido(uniqueContent) // Usamos el valor generado previamente
                .fechaGeneracion(LocalDateTime.now())
                .build();

        // Persistimos el reporte en la base de datos
        entityManager.persist(reporte);
        entityManager.flush();

        // Cuando - Ejecutamos la consulta para encontrar el reporte por tipo
        List<Reporte> reportes = reporteRepository.findByTipoReporte(Reporte.TipoReporte.INSUMOS);

        // Filtramos el reporte que tiene el contenido generado para esta prueba
        Reporte found = reportes.stream()
                .filter(r -> r.getContenido().equals(uniqueContent))
                .findFirst()
                .orElse(null);

        // Entonces - Verificamos que el reporte encontrado sea el esperado
        assertThat(found).isNotNull(); // Verificamos que se encontró un reporte
        assertThat(found.getContenido()).isEqualTo(uniqueContent); // Verificamos que el contenido es el esperado
        assertThat(found.getTipoReporte()).isEqualTo(Reporte.TipoReporte.INSUMOS); // Verificamos el tipo de reporte
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Commit
    public void whenFindAll_thenReturnAllReportes() {
        // Generamos contenido único para asegurarnos de no repetir datos
        String uniqueContent1 = "Reporte de animales " + System.currentTimeMillis();
        String uniqueContent2 = "Reporte de insumos " + System.currentTimeMillis();

        // Dado - Insertamos múltiples reportes
        Reporte reporte1 = Reporte.builder()
            .tipoReporte(Reporte.TipoReporte.ANIMALES)
            .contenido(uniqueContent1)
            .fechaGeneracion(LocalDateTime.now())
            .build();

        Reporte reporte2 = Reporte.builder()
            .tipoReporte(Reporte.TipoReporte.INSUMOS)
            .contenido(uniqueContent2)
            .fechaGeneracion(LocalDateTime.now())
            .build();

        entityManager.persist(reporte1);
        entityManager.persist(reporte2);
        entityManager.flush();

        // Cuando - Ejecutamos la consulta para obtener todos los reportes de la tabla
        List<Reporte> reportes = reporteRepository.findAll();

        // Filtramos y verificamos que los reportes con los contenidos únicos se han creado correctamente
        assertThat(reportes.stream().anyMatch(r -> r.getContenido().equals(uniqueContent1))).isTrue();
        assertThat(reportes.stream().anyMatch(r -> r.getContenido().equals(uniqueContent2))).isTrue();
    }
}
