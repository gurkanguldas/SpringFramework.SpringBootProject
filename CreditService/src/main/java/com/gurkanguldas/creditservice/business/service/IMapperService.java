package com.gurkanguldas.creditservice.business.service;


import com.gurkanguldas.creditservice.business.dto.IEntityDto;
import com.gurkanguldas.creditservice.data.IEntity;

public interface IMapperService <T extends IEntity,D extends IEntityDto>
{
    T dtoToEntity(D entityDto);
    D entitytoDto(T entity);
    String entityClassToString();
}
