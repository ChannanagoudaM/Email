package com.mail.EmailTemplates.ServicePackage;
import com.mail.EmailTemplates.EntityPackage.Users;
import com.mail.EmailTemplates.RepoPackage.MailInterface;
import com.mail.EmailTemplates.RepoPackage.MailsRepo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class MailsService implements MailInterface {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    TemplateEngine engine;

    @Autowired
    MailsRepo mailsRepo;


    @Value("$(spring.mail.username)")
    String username;


    @Override
    public Users createUser(Users users) {
        return mailsRepo.save(users);
    }


    @Override
    public Users loginUser(String mail, String password) {

        return mailsRepo.findByMailAndPassword(mail, password);
    }

    @Override
    public Users forgotPassword(String mail)
    {
        return mailsRepo.findByMail(mail);
    }


    @Override
    public void sendEmail(String to, String subject, String message) {

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom(username);
        mailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmailToMultiple(String[] to, String subject, String message) {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(username);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        mailSender.send(simpleMailMessage);
    }

    @Override
    public void sendMailWithHtmlFile(String to,String subject , String htmlCode) {

        MimeMessage mimeMessage=mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true,"UTF-8");
            helper.setTo(to);
            helper.setFrom(username);
            helper.setSubject(subject);
            helper.setText(htmlCode,true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMailWithFile(String to, String subject, String message, InputStream is) {


        MimeMessage mimeMessage=mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
            helper.setTo(to);
            helper.setFrom(username);
            helper.setSubject(subject);
            helper.setText(message);
            File file=new File("test.png");
            Files.copy(is,file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fileSystemResource=new FileSystemResource(file);
            helper.addAttachment(fileSystemResource.getFilename(),file);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
