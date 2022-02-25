package com.gurkanguldas.personsevice.business.service;

import com.gurkanguldas.personsevice.business.dto.IEntityDto;
import com.gurkanguldas.personsevice.data.IEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface ICrudService <T extends IEntity,D extends IEntityDto, ID>
{
    void save(D entityDto, BindingResult bindingResult);
    void delete(ID entityId);
    D find(ID entityId);
    List<D> findAll();
    List<D> findAll(String sortType, boolean descending);

    boolean isExistEntity(ID entityId);

}
