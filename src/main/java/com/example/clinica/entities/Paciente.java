package com.example.clinica.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo", length = 100, nullable = false)
    private String nomeCompleto;

    @Column(length = 14, nullable = false, unique = true)
    private String cpf;

    @Column(length = 20)
    private String telefone;

    @Column(length = 100)
    private String email;

    @Lob
    private String informacoesMedicas;

    @Column(nullable = false)
    private Boolean ativo = true;

    @CreationTimestamp
    private LocalDateTime criadoEm;

    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas;
}