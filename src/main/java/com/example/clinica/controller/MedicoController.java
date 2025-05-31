package com.example.clinica.controller;

import com.example.clinica.entities.Medico;
import com.example.clinica.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<Medico> criar(@RequestBody Medico medico) {
        Medico novoMedico = medicoRepository.save(medico);
        return ResponseEntity.ok(novoMedico);
    }

    @GetMapping
    public ResponseEntity<List<Medico>> listarTodos() {
        List<Medico> medicos = medicoRepository.findAll();
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarPorId(@PathVariable Long id) {
        return medicoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizar(@PathVariable Long id, @RequestBody Medico medicoAtualizado) {
        return medicoRepository.findById(id)
                .map(medico -> {
                    medico.setNome(medicoAtualizado.getNome());
                    medico.setCrm(medicoAtualizado.getCrm());
                    medico.setTelefone(medicoAtualizado.getTelefone());
                    medico.setEmail(medicoAtualizado.getEmail());
                    medico.setEspecialidade(medicoAtualizado.getEspecialidade());
                    Medico medicoSalvo = medicoRepository.save(medico);
                    return ResponseEntity.ok(medicoSalvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletar(@PathVariable Long id) {
//        if (medicoRepository.existsById(id)) {
//            medicoRepository.deleteById(id);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
}