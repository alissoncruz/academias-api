package com.core.contas.despesas;


import com.core.model.Despesa;
import com.core.model.Receita;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")@AllArgsConstructor
@RestController
@RequestMapping("despesa")
public class DespesaController {

    private DespesaService service;

    @PostMapping
    public void save(Despesa despesa){
        service.save(despesa);
    }

    public Despesa findById(@PathVariable String id){
        return service.findById(id);
    }

    @GetMapping
    public List<Despesa> getAll(){
        return service.findAll();
    }

    public List<Despesa> findByMes(@PathVariable int mes){
        return service.findByMes(mes);
    }


    //despesas por mes
    //despesas por dia
}
