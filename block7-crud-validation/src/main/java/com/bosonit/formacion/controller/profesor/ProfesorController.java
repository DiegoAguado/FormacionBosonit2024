package com.bosonit.formacion.controller.profesor;

import com.bosonit.formacion.application.profesor.ProfesorService;
import com.bosonit.formacion.controller.profesor.dto.ProfesorInputDto;
import com.bosonit.formacion.controller.profesor.dto.ProfesorOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;
import com.bosonit.formacion.customException.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
    @Autowired
    ProfesorService profesorService;

    @PostMapping
    public ResponseEntity<ProfesorOutputDto> addProfesor(@RequestBody ProfesorInputDto profesor) throws EntityNotFoundException, UnprocessableEntityException {
        return ResponseEntity.ok().body(profesorService.addProfesor(profesor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorOutputDto> getProfesorById(@PathVariable String id) throws EntityNotFoundException{
        return ResponseEntity.ok().body(profesorService.getProfesorById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfesorOutputDto>> getAllProfesors(@RequestParam int pageNumber, @RequestParam int pageSize){
        return ResponseEntity.ok().body(profesorService.getAllProfesores(pageNumber, pageSize));
    }

    @PutMapping
    public ResponseEntity<ProfesorOutputDto> updateProfesor(@RequestBody ProfesorInputDto profesor) throws EntityNotFoundException{
        return ResponseEntity.ok().body(profesorService.updateProfesor(profesor));
    }

    @DeleteMapping("/{id}")
    public void deleteProfesorById(@RequestParam String id) throws EntityNotFoundException{
        profesorService.deleteProfesorById(id);
    }
}
