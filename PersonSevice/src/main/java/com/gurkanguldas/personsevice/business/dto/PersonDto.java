package com.gurkanguldas.personsevice.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2
public class PersonDto implements Serializable, IEntityDto
{
    @NotEmpty(message = "Adiniz bos birakilamaz.")
    private String personName;

    @NotEmpty(message = "Soyadiniz bos birakilamaz.")
    private String personSurname;

    @NotNull(message = "ID degeri bos birakilamaz.")
    @Min(value=0,message = "ID degeri 0'dan kucuk olamaz.")
    private Long personId;

    @NotNull(message = "Kimlik numarisi bos birakilamaz.")
    @Min(value=0,message = "Kimlik numarisi 0'dan kucuk olamaz.")
    private Long personIdentificationNumber;

    @NotNull(message = "Aylik gelir bos birakilamaz.")
    @Min(value=0,message = "Aylik gelir degeri 0'dan kucuk olamaz.")
    private Long personMonthlyIncome;

    @NotNull(message = "Telefon numarasi bos birakilamaz.")
    @Min(value=0,message = "Telefon numarasi 0'dan kucuk olamaz.")
    private Long personPhoneNumber;

    @NotNull(message = "Kredi puani bos birakilamaz.")
    @Min(value=0,message = "Kredi puan degeri 0'dan kucuk olamaz.")
    private Long personCredicScore;

}
