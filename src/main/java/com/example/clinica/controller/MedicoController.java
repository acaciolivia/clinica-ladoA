package com.example.clinica.controller;

import com.example.clinica.entities.Consulta;
import com.example.clinica.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class MedicoController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @PostMapping
    public ResponseEntity<Consulta> criar(@RequestBody Consulta consulta) {
        Consulta novaConsulta = consultaRepository.save(consulta);
        return ResponseEntity.ok(novaConsulta);
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> listarTodas() {
        List<Consulta> consultas = consultaRepository.findAll();
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable Long id) {
        return consultaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizar(@PathVariable Long id, @RequestBody Consulta consultaAtualizada) {
        return consultaRepository.findById(id)
                .map(consulta -> {
                    consulta.setPaciente(consultaAtualizada.getPaciente());
                    consulta.setMedico(consultaAtualizada.getMedico());
                    consulta.setDataConsulta(consultaAtualizada.getDataConsulta());
                    consulta.setHoraConsulta(consultaAtualizada.getHoraConsulta());
                    consulta.setStatus(consultaAtualizada.getStatus());
                    consulta.setMotivoCancelamento(consultaAtualizada.getMotivoCancelamento());
                    consulta.setObservacoes(consultaAtualizada.getObservacoes());
                    Consulta consultaSalva = consultaRepository.save(consulta);
                    return ResponseEntity.ok(consultaSalva);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (consultaRepository.existsById(id)) {
            consultaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
