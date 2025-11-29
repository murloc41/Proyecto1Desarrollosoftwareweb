package com.instituto.compendium.service;

import com.instituto.compendium.model.Archivo;
import com.instituto.compendium.model.Guia;
import com.instituto.compendium.model.Usuario;
import com.instituto.compendium.repository.GuiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class GuiaService {

    @Autowired
    private GuiaRepository guiaRepository;

    private final Path rootLocation = Paths.get("uploads");

    public GuiaService() {
        try {
            Files.createDirectories(rootLocation);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo inicializar el almacenamiento");
        }
    }

    public Guia crearGuia(Guia guia, MultipartFile imagen, MultipartFile[] archivos) {
        if (imagen != null && !imagen.isEmpty()) {
            String nombreArchivo = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
            try {
                Files.copy(imagen.getInputStream(), rootLocation.resolve(nombreArchivo));
                guia.setImagen("/uploads/" + nombreArchivo);
            } catch (Exception e) {
                throw new RuntimeException("Error al guardar la imagen");
            }
        }

        if (archivos != null) {
            for (MultipartFile archivo : archivos) {
                if (!archivo.isEmpty()) {
                    String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();
                    try {
                        Files.copy(archivo.getInputStream(), rootLocation.resolve(nombreArchivo));
                        Archivo nuevoArchivo = new Archivo();
                        nuevoArchivo.setNombre(archivo.getOriginalFilename());
                        nuevoArchivo.setRuta("/uploads/" + nombreArchivo);
                        nuevoArchivo.setTipo(archivo.getContentType());
                        nuevoArchivo.setTamano(archivo.getSize());
                        nuevoArchivo.setGuia(guia);
                        guia.getArchivos().add(nuevoArchivo);
                    } catch (Exception e) {
                        throw new RuntimeException("Error al guardar el archivo: " + archivo.getOriginalFilename());
                    }
                }
            }
        }

        if (guia.getEstado() == null) {
            guia.setEstado(Guia.EstadoPublicacion.BORRADOR);
        }

        return guiaRepository.save(guia);
    }

    public Page<Guia> obtenerGuiasPublicadas(Pageable pageable) {
        return guiaRepository.findByEstado(Guia.EstadoPublicacion.PUBLICADO, pageable);
    }

    public Page<Guia> obtenerGuiasPorAutor(Usuario autor, Pageable pageable) {
        return guiaRepository.findByAutor(autor, pageable);
    }

    public Page<Guia> buscarGuias(String termino, Pageable pageable) {
        return guiaRepository.buscar(termino, pageable);
    }

    public Guia obtenerGuia(Long id) {
        return guiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guía no encontrada"));
    }

    public void eliminarGuia(Long id, Usuario usuario) {
        Guia guia = obtenerGuia(id);
        if (!guia.getAutor().equals(usuario) && !usuario.hasRole("ADMIN")) {
            throw new RuntimeException("No tienes permiso para eliminar esta guía");
        }
        guiaRepository.delete(guia);
    }

    public Guia actualizarGuia(Long id, Guia guiaActualizada, MultipartFile imagen) {
        Guia guia = obtenerGuia(id);
        
        guia.setTitulo(guiaActualizada.getTitulo());
        guia.setDescripcion(guiaActualizada.getDescripcion());
        guia.setContenido(guiaActualizada.getContenido());
        guia.setCategoria(guiaActualizada.getCategoria());
        guia.setDificultad(guiaActualizada.getDificultad());
        guia.setTags(guiaActualizada.getTags());

        if (imagen != null && !imagen.isEmpty()) {
            String nombreArchivo = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
            try {
                // Eliminar imagen anterior si existe
                if (guia.getImagen() != null) {
                    String imagenAnterior = guia.getImagen().replace("/uploads/", "");
                    Files.deleteIfExists(rootLocation.resolve(imagenAnterior));
                }
                Files.copy(imagen.getInputStream(), rootLocation.resolve(nombreArchivo));
                guia.setImagen("/uploads/" + nombreArchivo);
            } catch (Exception e) {
                throw new RuntimeException("Error al actualizar la imagen");
            }
        }

        return guiaRepository.save(guia);
    }
}