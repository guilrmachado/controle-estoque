package com.guilherme.controle_estoque.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record VendaRequest(

    @NotNull
    @Min(value = 1, message = "Quantidade deve ser maior que zero")
    Integer quantidade)
{
}
