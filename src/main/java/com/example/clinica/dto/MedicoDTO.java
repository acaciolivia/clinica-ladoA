package com.example.clinica.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MedicoDTO {
    private String nome;

    private String crm;

    private String telefone;

    private String email;

    private EspecialidadeDTO especialidade;

    private List<ConsultaDTO> consultasDTO;
}

