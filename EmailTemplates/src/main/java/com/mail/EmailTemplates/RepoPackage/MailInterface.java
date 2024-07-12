package com.mail.EmailTemplates.RepoPackage;

import com.mail.EmailTemplates.EntityPackage.Users;

import java.io.File;
import java.io.InputStream;

public interface MailInterface {


    Users loginUser(String mail,String password);

    Users createUser(Users users);

    Users forgotPassword(String mail);

    //send email to a single person

    void sendEmail(String to,String subject,String message);

    //send to multiple persons

    void sendEmailToMultiple(String[]to,String subject,String message);

    //send html file

    void sendMailWithHtmlFile(String to,String subject,String htmlCode);

    //send email with file

    void sendMailWithFile(String to, String subject, String message, InputStream file);
}
