package com.core.aula;

import com.core.model.Aula;
import com.core.model.Checkin;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("aula")
public class AulaController {

    private AulaService aulaService;

    @PostMapping
    public void criarAula(@RequestBody Aula aula){
        aulaService.criarAula(aula);
    }

    @PostMapping("/checkin")
    public void fazerCheckin(@RequestBody Checkin checkin){
        aulaService.fazerCheckin(checkin);
    }

    @GetMapping
    public List<Aula> getAll(){
        return aulaService.getAll();
    }
}
