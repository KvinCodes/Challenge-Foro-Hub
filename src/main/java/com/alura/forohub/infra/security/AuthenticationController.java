package com.alura.forohub.infra.security;

import com.alura.forohub.domain.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public String autenticar(@RequestBody @Valid DataAutenticacionUser datos) {

        Authentication authToken =
                new UsernamePasswordAuthenticationToken(
                        datos.login(),
                        datos.password()
                );

        Authentication authentication =
                authenticationManager.authenticate(authToken);

        Usuario usuario = (Usuario) authentication.getPrincipal();

        return tokenService.generarToken(usuario);
    }
}
