package com.gurkanguldas.creditservice.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document()
public class Credit extends BaseEntity{
    @Id
    private String creditId;

    @NotNull
    @Field("person_identification_number")
    private Long personIdentificationNumber;

    @NotNull
    @Field("credit_limit")
    private Long creditLimit;

    @NotNull
    @Field("credit_status")
    private Boolean creditStatus;


}
