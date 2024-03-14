package com.core.contas.receitas;

import com.core.model.Receita;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("receita")
public class ReceitaController {

    private final ReceitaService service;

    @PostMapping
    public void save(Receita receita){
        service.save(receita);
    }
    @GetMapping
    public void findAll(){
        service.findAll();
    }

    @GetMapping("/mes/{mes}")
    public List<Receita> findByMes(@PathVariable int mes){
        return service.findByMes(mes);
    }
}
