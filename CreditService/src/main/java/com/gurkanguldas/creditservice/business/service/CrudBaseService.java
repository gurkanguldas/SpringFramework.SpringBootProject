package com.gurkanguldas.creditservice.business.service;

import com.gurkanguldas.creditservice.business.dto.IEntityDto;
import com.gurkanguldas.creditservice.business.service.message.LogMessage;
import com.gurkanguldas.creditservice.data.IEntity;
import com.gurkanguldas.creditservice.exception.EntityNotFoundException;
import com.gurkanguldas.creditservice.exception.EntityValidateException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public abstract class CrudBaseService <T extends IEntity,D extends IEntityDto, ID> extends MapperService<T,D> implements ICrudService<T,D,ID>
{
    @Autowired
    private LogMessage logMessage;

    MongoRepository<T,ID> repository;

    public CrudBaseService(MongoRepository<T, ID> repository)
    {
        this.repository = repository;
    }

    // Saves the entity
    @Override
    public void save(D entityDto, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) throw new EntityValidateException(logMessage.validationMessage(bindingResult));
        T entity = dtoToEntity(entityDto);
        repository.save(entity);
    }

    // Deletes the entity
    @Override
    public void delete(ID entityId)
    {
        Optional<T> optional=repository.findById(entityId);
        optional.orElseThrow(() -> new EntityNotFoundException(entityClassToString()));
        repository.deleteById(entityId);
    }

    // Finds the entity
    @Override
    public D find(ID entityId)
    {
        T entity = repository.findById(entityId).orElseThrow(() -> new EntityNotFoundException(entityClassToString()));
        return entitytoDto(entity);
    }

    // Finds all entities
    @Override
    public List<D> findAll()
    {
        List<D> listDto = new ArrayList<>();

        for (T entity : repository.findAll())
        {
            D entitytoDto = entitytoDto(entity);
            listDto.add(entitytoDto);
        }
        return listDto;
    }

    // Finds and sorts all entities
    @Override
    public List<D> findAll(String sortType, boolean descending)
    {
        Sort sort= descending ? Sort.by(sortType).descending() : Sort.by(sortType);
        List<D> listDto = new ArrayList<>();

        for (T entity : repository.findAll(sort))
        {
            D entitytoDto = entitytoDto(entity);
            listDto.add(entitytoDto);
        }
        return listDto;
    }

    // Checks if the entity exists
    @Override
    public boolean isExistEntity(ID entityId) {
        return repository.existsById(entityId);
    }
}
