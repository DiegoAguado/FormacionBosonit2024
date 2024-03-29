package com.bosonit.formacion.application.profesor;

import com.bosonit.formacion.controller.profesor.dto.ProfesorInputDto;
import com.bosonit.formacion.controller.profesor.dto.ProfesorOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;
import com.bosonit.formacion.customException.UnprocessableEntityException;

import java.util.List;

public interface ProfesorService {
    ProfesorOutputDto addProfesor(ProfesorInputDto profesor) throws EntityNotFoundException, UnprocessableEntityException;
    ProfesorOutputDto getProfesorById(String id) throws EntityNotFoundException;
    List<ProfesorOutputDto> getAllProfesores(int pageNumber, int pageSize);
    ProfesorOutputDto updateProfesor(ProfesorInputDto profesor) throws EntityNotFoundException;
    void deleteProfesorById(String id) throws EntityNotFoundException;
}
