package org.example.oficina.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Oficina {
    private Long id;

    @NotNull(message = "Informe uma categoria.")
    private CategoriaOficina categoriaOficina;

    private SituacaoOficina situacaoOficina;

    public Oficina() {
        situacaoOficina = SituacaoOficina.RASCUNHO;
    }

    public void publicar() {
        if (situacaoOficina == SituacaoOficina.RASCUNHO){
            situacaoOficina = SituacaoOficina.PUBLICADA;
        }
    }

    public void encerrar() {
        if (situacaoOficina == SituacaoOficina.PUBLICADA){
            situacaoOficina = SituacaoOficina.ENCERRADA;
        }
    }

    public void cancelar() {
        if (situacaoOficina == SituacaoOficina.RASCUNHO || situacaoOficina == SituacaoOficina.PUBLICADA){
            situacaoOficina = SituacaoOficina.CANCELADA;
        }
    }

    @NotBlank(message = "Informe o tema.")
    @Size(min = 8, max = 90, message = "O tema deve conter entre 8 e 90 caracteres.")
    private String tema;

    @NotBlank(message = "Informe um instrutor.")
    @Size(min = 4, message = "O instrutor deve conter no mínimo 4 caracteres")
    private String instrutor;

    @NotBlank(message = "Informe um E-mail")
    @Email(message = "E-mail inválido")
    private String emailInstrutor;

    @Min(value = 2, message = "A carga horária deve conter entre 2 e 40 horas")
    @Max(value = 40, message = "A carga horária deve conter entre 2 e 40 horas")
    private int cargaHoraria;

    private boolean gratuita;

    @Min(value = 4, message = "O número mínimo de vagas é 4")
    @Max(value = 25, message = "O número máximo de vagas é 25")
    private int vagas;

    public int inscritos;

    public boolean possuiVagas() {
        return inscritos < vagas;
    }

    public void inscrever() {
        if (situacaoOficina == SituacaoOficina.PUBLICADA){
            inscritos ++;
            vagas --;
        }
    }


}
