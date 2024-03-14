package com.core.aluno;


import com.core.model.Aluno;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("aluno")
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    public void save(@RequestBody @Valid Aluno aluno){
        alunoService.save(aluno);
    }

    //@PreAuthorize("USER")
    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody @Valid Aluno aluno){
        alunoService.update(id, aluno);
    }

    //@PreAuthorize("ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        alunoService.delete(id);
    }

    @PreAuthorize("USER")
    @GetMapping("/{id}")
    public Aluno findById(@PathVariable String id){
        return alunoService.findById(id);
    }

    @PreAuthorize("USER")
    @GetMapping
    public List<Aluno> findAll(){
        return alunoService.findAll();
    }

    @PreAuthorize("USER")
    @GetMapping("/page")
    public Page<Aluno> findAllPage(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                   @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        return alunoService.findAllPage(page, size);
    }


}
