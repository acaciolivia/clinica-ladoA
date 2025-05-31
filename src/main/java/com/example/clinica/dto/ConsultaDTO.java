package com.example.clinica.dto;

import lombok.Data;

@Data
public class ConsultaDTO {
    private Long id;
    private Long pacienteId;
    private Long medicoId;
    private String dataConsulta;
    private String horaConsulta;
    private StatusConsulta status;
    private String motivoCancelamento;
    private String observacoes;
    private String criadoEm;

    public enum StatusConsulta {
        AGENDADA,
        REALIZADA,
        CANCELADA
    }
}
