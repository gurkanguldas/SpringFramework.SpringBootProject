package com.gurkanguldas.creditservice.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2
public class CreditDto implements Serializable, IEntityDto
{
    private String creditId;

    @NotNull(message = "Kimlik numarisi bos birakilamaz.")
    @Min(value=0,message = "Kimlik numarisi 0'dan kucuk olamaz.")
    private Long personIdentificationNumber;

    @NotNull(message = "Kredi limiti bos birakilamaz.")
    @Min(value=0,message = "Kredi limit degeri 0'dan kucuk olamaz.")
    private Long creditLimit;

    @NotNull(message = "Kredi durumu bos birakilamaz.")
    private Boolean creditStatus;

}
