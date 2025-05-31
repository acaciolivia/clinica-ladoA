package com.example.clinica.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 20, nullable = false, unique = true)
    private String crm;

    @Column(length = 20)
    private String telefone;

    @Column(length = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_especialidade", nullable = false)
    private Especialidade especialidade;

    @CreationTimestamp
    private LocalDateTime criadoEm;

    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas;
}
