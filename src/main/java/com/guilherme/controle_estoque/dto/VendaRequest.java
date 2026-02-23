package com.guilherme.controle_estoque.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record VendaRequest(

    @NotNull
    @Min(1)
    Integer quantidade)
{
}
