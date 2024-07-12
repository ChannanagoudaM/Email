package com.mail.EmailTemplates.ExceptionPackage;

public class MailNotFound extends  RuntimeException{

    public MailNotFound(String message)
    {
        super(message);
    }
}
