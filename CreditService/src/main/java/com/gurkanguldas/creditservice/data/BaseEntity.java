package com.gurkanguldas.creditservice.data;

import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
public abstract class BaseEntity implements IEntity
{
    @Field(name = "created_by")
    @CreatedBy
    private String createdBy;

    @Field(name = "created_date")
    @CreatedDate
    private Date createdDate;

    @Field(name = "update_by")
    @LastModifiedBy
    private String updateBy;

    @Field(name = "update_date")
    @LastModifiedDate
    private Date updateDate;
}
