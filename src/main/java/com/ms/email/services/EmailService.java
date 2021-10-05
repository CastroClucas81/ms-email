package com.ms.email.services;

//camada q fica entre o repository e controller

import com.ms.email.enums.StatusEmail;
import com.ms.email.models.EmailModel;
import com.ms.email.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {
    //vou precisar de um ponto de injecao pra conseguir criar os métodos
    //pra fazer a persistência com o banco
    //usando o ponto de Inj.Depen. do repository
    @Autowired
    EmailRepository emailRepository;

    //segundo ponto de injecao
    @Autowired
    private JavaMailSender emailSender;

    //método de envio de email e salvar na based de dados
    public EmailModel sendEmail(EmailModel emailModel) {
        //setar a data de envio
        emailModel.setSendDateEmail(LocalDateTime.now());

        try {
            //tentar enviar o email

            //istancia para montar o email
            SimpleMailMessage message = new SimpleMailMessage();

            //setando os atributos
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());

            //esse metodo q vai fazer o envio em si
            emailSender.send(message);

            //mandar mensagem de sucesso
            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e) {
            //mandar mensagem de error
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            //salvar com ou sem error
            //pois poderemos implementar uma política de retentativas
            //tratamento de error
            return emailRepository.save(emailModel);
        }
    }
}
