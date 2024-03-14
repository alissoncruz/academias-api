package com.core.mensalidade;

import com.core.mensalidade.dto.MensalidadeResponse;
import com.core.model.Mensalidade;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/mensalidade")
public class MensalidadeController {

    private final MensalidadeService service;


    @PreAuthorize("USER")
    @PostMapping
    public void save(@RequestBody Mensalidade mensalidade) {
        service.save(mensalidade);
    }

    @GetMapping("/{id}")
    public Mensalidade findById(@PathVariable String id){
        return service.findById(id);
    }
    @GetMapping
    public Page<MensalidadeResponse> findAllAno(
            @RequestParam(value = "ano", required = false, defaultValue = "0") int ano,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        return service.findAllAno(ano, page, size);
    }

    @GetMapping("/mensal")
    public Page<MensalidadeResponse> findAllByMes(
            @RequestParam(value = "mes", required = false, defaultValue = "0") int mes,
            @RequestParam(value = "ano", required = false, defaultValue = "0") int ano,
            @RequestParam(value = "status", required = false, defaultValue = "EM_ABERTO") Mensalidade.Pagamento.Status status,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        return service.findAllByMes(mes, ano, status, page, size);
    }

    @PatchMapping("/pay/{id}")
    public void pay(@PathVariable String id, @RequestBody String comentario){
        service.pay(id, comentario);
    }
}
