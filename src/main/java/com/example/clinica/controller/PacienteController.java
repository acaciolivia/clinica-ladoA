package com.example.clinica.controller;

import com.example.clinica.dto.PacienteDTO;
import com.example.clinica.entities.Paciente;
import com.example.clinica.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    //private long idCounter = 1;
    private final PacienteRepository pacienteRepository;

    @PostMapping
    public String criarPaciente(@RequestBody Paciente paciente) {
        Paciente salvo = pacienteRepository.save(paciente);
        return "Paciente criado com sucesso! ID: " + salvo.getId();
    }

    @GetMapping("/{id}")
    public Object buscarPaciente(@PathVariable Long id) {
        return pacienteRepository.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizarPaciente(@PathVariable Long id, @RequestBody Paciente dto) {
        Optional<Paciente> userExist = pacienteRepository.findById(id);

        if (userExist.isPresent()) {
            Paciente paciente = userExist.get();

            paciente.setNomeCompleto(dto.getNomeCompleto());
            paciente.setEmail(dto.getEmail());
            paciente.setTelefone(dto.getTelefone());

            Paciente atualizado = pacienteRepository.save(paciente);

            return ResponseEntity.ok(atualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}