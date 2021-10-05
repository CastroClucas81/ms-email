package com.ms.email.controllers;

import com.ms.email.dtos.EmailDto;
import com.ms.email.models.EmailModel;
import com.ms.email.services.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmailController {

    //preciso de um ponto de injecao aqui
    @Autowired
    EmailService emailService;

    //CRIANDO O MÉTODO POST
    //sempre que algum servico enviar uma requisicao para sending-email, este método será disparado
    @PostMapping("/sending-email")
    //recebo como parametro o emaildto
    //o valid efetiva as validacoes
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDto emailDto) {
        //transformar o dto em model
        EmailModel emailModel = new EmailModel();
        //converte o dto pra model para salvar no bd
        //eu passo dois parametros: o que eu quero converter, para o que eu quero converter
        BeanUtils.copyProperties(emailDto, emailModel);
        //o salvamento ou email do email
        emailService.sendEmail(emailModel);
        //retornar o model pro cliente e o status
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }
}
