package com.gurkanguldas.creditservice.business.service;

import com.gurkanguldas.creditservice.business.dto.IEntityDto;
import com.gurkanguldas.creditservice.data.IEntity;
import com.gurkanguldas.creditservice.exception.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;

public class MapperService<T extends IEntity,D extends IEntityDto> implements IMapperService<T,D>
{
    //First class of generic class
    private final Class<T> entity = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    //Second class of generic class
    private final Class<D> entityDto = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];

    @Autowired
    private ModelMapper modelMapper;

    //Converts data transfer object to entity.
    @Override
    public T dtoToEntity(D entityDto)
    {
        if(entityDto == null) throw new EntityNotFoundException(entityClassToString());
        return (T) modelMapper.map(entityDto, entity);
    }

    //Converts entity to data transfer object.
    @Override
    public D entitytoDto(T entity)
    {
        if(entity == null) throw new EntityNotFoundException(entityClassToString());
        return (D) modelMapper.map(entity, entityDto);
    }

    //Converts first class to string
    @Override
    public String entityClassToString()
    {
        return entity.getSimpleName();
    }
}
