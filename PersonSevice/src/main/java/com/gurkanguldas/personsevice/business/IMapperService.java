package com.gurkanguldas.personsevice.business.service;

import com.gurkanguldas.personsevice.business.dto.IEntityDto;
import com.gurkanguldas.personsevice.data.IEntity;

public interface IMapperService <T extends IEntity,D extends IEntityDto>
{
    T dtoToEntity(D entityDto);
    D entitytoDto(T entity);
    String entityClassToString();
}
