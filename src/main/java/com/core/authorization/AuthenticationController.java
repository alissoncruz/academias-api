package com.core.authorization;

import com.core.exception.BusinessException;
import com.core.infra.security.TokenService;
import com.core.model.AuthTokenDTO;
import com.core.model.AuthenticationDTO;
import com.core.model.LoginResponseDTO;
import com.core.model.RegisterDTO;
import com.core.user.User;
import com.core.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;


    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var authUserDTO = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(authUserDTO);
    }

    @PostMapping("/signout")
    public void signout(@RequestBody @Valid AuthTokenDTO authTokenDTO){
    }

    @PostMapping("/validate")
    public Boolean validate(@RequestBody @Valid AuthTokenDTO authTokenDTO){
        //TODO Validar o token
        /**
         * tempo de expiração
         * user
         */
        return true;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if (this.repository.findByLogin(data.login()) != null){
            throw new BusinessException("Usuário já cadastrado.");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());
        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public List<User> getAll(){
        return repository.findAll();
    }
}
