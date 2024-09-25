package com.ejercito.inventario_animales.repository;

import com.ejercito.inventario_animales.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class UsuarioRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;


    // Método para generar correos únicos usando UUID
    private String generarCorreoUnico() {
        return "usuario" + UUID.randomUUID() + "@example.com";
    }

    @Test
    @Commit
    public void whenFindByCorreo_thenReturnUsuario() {

        // Generamos un correo único para evitar duplicados
        String uniqueCorreo = generarCorreoUnico();

        // Dado - Configuramos un usuario de prueba con correo único
        Usuario usuario = Usuario.builder()
                .nombre("John Doe")
                .correo(uniqueCorreo)
                .contraseña("123456")
                .rol(Usuario.Rol.ADMIN)
                .build();

        // Persistimos el usuario dentro de la transacción
        entityManager.persist(usuario);
        entityManager.flush();

        // Cuando - Ejecutamos la consulta para encontrar el usuario por correo
        Usuario found = usuarioRepository.findByCorreo(usuario.getCorreo()).orElse(null);

        // Entonces - Verificamos que el usuario encontrado sea el esperado
        assertThat(found).isNotNull(); // Verificamos que se encontró un usuario
        assertThat(found.getCorreo()).isEqualTo(usuario.getCorreo()); // Verificamos el correo
        assertThat(found.getNombre()).isEqualTo("John Doe"); // Verificamos el nombre
        assertThat(found.getRol()).isEqualTo(Usuario.Rol.ADMIN); // Verificamos el rol
    }

    @Test
    @Commit
    public void whenSaveAndDeleteUsuario_thenUsuarioIsDeleted() {

        // Generamos un correo único para el usuario
        String uniqueCorreo = generarCorreoUnico();

        // Dado - Configuramos un usuario de prueba
        Usuario usuario = Usuario.builder()
                .nombre("Jane Doe")
                .correo(uniqueCorreo)
                .contraseña("abcdef")
                .rol(Usuario.Rol.ENCARGADO)
                .build();

        // Persistimos el usuario en la base de datos
        entityManager.persist(usuario);
        entityManager.flush();

        // Verificamos que el usuario fue guardado correctamente
        Usuario found = usuarioRepository.findByCorreo(uniqueCorreo).orElse(null);
        assertThat(found).isNotNull(); // El usuario debería existir

        // Ahora eliminamos el usuario
        usuarioRepository.delete(found);
        entityManager.flush();

        // Verificamos que el usuario fue eliminado
        Optional<Usuario> deletedUser = usuarioRepository.findByCorreo(uniqueCorreo);
        assertThat(deletedUser).isEmpty(); // El usuario ya no debería existir
    }

    @Test
    @Commit
    public void whenFindAll_thenReturnAllUsuarios() {

        // Contamos cuántos usuarios existen antes de agregar nuevos
        int initialSize = usuarioRepository.findAll().size();
    
        // Generamos correos únicos para los nuevos usuarios
        String correoUsuario1 = generarCorreoUnico();
        String correoUsuario2 = generarCorreoUnico();

        // Dado - Insertamos múltiples usuarios con correos únicos
        Usuario usuario1 = Usuario.builder()
                .nombre("Usuario 1")
                .correo(correoUsuario1)
                .contraseña("123")
                .rol(Usuario.Rol.ADMIN)
                .build();

        Usuario usuario2 = Usuario.builder()
                .nombre("Usuario 2")
                .correo(correoUsuario2)
                .contraseña("456")
                .rol(Usuario.Rol.ENCARGADO)
                .build();

        entityManager.persist(usuario1);
        entityManager.persist(usuario2);
        entityManager.flush();

        // Cuando - Ejecutamos la consulta para obtener todos los usuarios
        List<Usuario> usuarios = usuarioRepository.findAll();

        // Entonces - Verificamos que se han agregado los nuevos usuarios
        assertThat(usuarios).hasSize(initialSize + 2); // Verificamos que se han agregado 2 usuarios más
    }
}
