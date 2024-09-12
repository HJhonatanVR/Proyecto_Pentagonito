package com.ejercito.inventario_animales.repository;

import com.ejercito.inventario_animales.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test") // Usa un perfil de pruebas si es necesario
public class UsuarioRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void whenFindByCorreo_thenReturnUsuario() {
        // Dado - Configurando un usuario de prueba usando el builder
        Usuario usuario = Usuario.builder()
                .nombre("John Doe")
                .correo("john.doe@example.com")
                .contraseña("123456")
                .rol(Usuario.Rol.ADMIN)
                .build();

        entityManager.persist(usuario);
        entityManager.flush();

        // Cuando - Ejecutamos la consulta para encontrar el usuario por correo
        Usuario found = usuarioRepository.findByCorreo(usuario.getCorreo()).orElse(null);

        // Entonces - Verificamos que el usuario encontrado sea el esperado
        assertThat(found).isNotNull(); // Verificamos que se encontró un usuario
        assertThat(found.getCorreo()).isEqualTo(usuario.getCorreo()); // Verificamos el correo
    }
}

