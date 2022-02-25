package com.gurkanguldas.frontendservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditDto
{
    @NotEmpty(message = "Kredi id bos birakilamaz.")
    private String creditId;

    @NotNull(message = "Kimlik numarisi bos birakilamaz.")
    @Min(value=0,message = "Kimlik numarisi 0'dan kucuk olamaz.")
    private Long personIdentificationNumber;

    @NotNull(message = "Kredi limiti bos birakilamaz.")
    @Min(value=0,message = "Kredi limiti 0'dan kucuk olamaz.")
    private Long creditLimit;

    @NotNull(message = "Kredi durumu bos birakilamaz.")
    private Boolean creditStatus;
}
