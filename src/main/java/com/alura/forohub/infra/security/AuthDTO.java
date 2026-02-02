package com.alura.forohub.infra.security;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO(

        @NotBlank
        String login,

        @NotBlank
        String password
) {}
