package com.alura.forohub.infra.security;

import jakarta.validation.constraints.NotBlank;

public record DataAutenticacionUser (

    @NotBlank
    String login,

    @NotBlank
    String password

){}
