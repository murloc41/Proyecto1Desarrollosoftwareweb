package com.instituto.compendium.service;

import com.instituto.compendium.model.Juego;
import com.instituto.compendium.repository.JuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class JuegoService {

    @Autowired
    private JuegoRepository juegoRepository;

    public List<Juego> listarJuegos() {
        return juegoRepository.findAll();
    }

    public Juego obtenerJuego(Long id) {
        return juegoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Juego no encontrado"));
    }

    @Value("${app.upload.dir:uploads}")
    private String uploadDir;

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(uploadDir));
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el directorio de subidas", e);
        }
    }

    public String guardarImagen(MultipartFile imagen) {
        if (imagen != null && !imagen.isEmpty()) {
            try {
                String nombreOriginal = imagen.getOriginalFilename();
                if (nombreOriginal == null) nombreOriginal = "imagen";
                String nombreArchivo = UUID.randomUUID().toString() + "_" + 
                    nombreOriginal.replaceAll("\\s+", "_");
                Path rutaArchivo = Paths.get(uploadDir).resolve(nombreArchivo);
                Files.copy(imagen.getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);
                return "/uploads/" + nombreArchivo;
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar la imagen", e);
            }
        }
        return null;
    }

    public Juego crearJuego(Juego juego, MultipartFile imagen) {
        String rutaImagen = guardarImagen(imagen);
        if (rutaImagen != null) {
            juego.setImagen(rutaImagen);
        }
        return juegoRepository.save(juego);
    }

    public Juego actualizarJuego(Long id, Juego juegoActualizado) {
        Juego juego = obtenerJuego(id);
        
        juego.setNombre(juegoActualizado.getNombre());
        juego.setDescripcion(juegoActualizado.getDescripcion());
        juego.setGenero(juegoActualizado.getGenero());
        juego.setPlataforma(juegoActualizado.getPlataforma());
        juego.setImagen(juegoActualizado.getImagen());
        juego.setRating(juegoActualizado.getRating());
        juego.setTotalValoraciones(juegoActualizado.getTotalValoraciones());

        return juegoRepository.save(juego);
    }

    public void eliminarJuego(Long id) {
        Juego juego = obtenerJuego(id);
        juegoRepository.delete(juego);
    }
}