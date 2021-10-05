package com.ms.email.dtos;

//objeto q vamos receber no método post
//algumas colunas vao ser necessárias

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class EmailDto {
    //usando o validation para validar
    //o validation já faz o tratamento de erros (se o campo tiver vazio e tal)

    @NotBlank
    private String ownerRef;
    @NotBlank
    @Email
    private String emailFrom;
    @NotBlank
    @Email
    private String emailTo;
    @NotBlank
    private String subject;
    @NotBlank
    private String text;
}
