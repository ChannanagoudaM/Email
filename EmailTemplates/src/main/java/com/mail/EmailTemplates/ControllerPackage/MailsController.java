package com.mail.EmailTemplates.ControllerPackage;


import com.mail.EmailTemplates.CustomResponse.MailResponse;
import com.mail.EmailTemplates.EntityPackage.EmailRequest;
import com.mail.EmailTemplates.EntityPackage.Users;
import com.mail.EmailTemplates.ServicePackage.MailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/email/api")
@SessionAttributes(names = {"", ""})
public class MailsController {

    @Autowired
    private  MailsService mailsService;



    @GetMapping("/register")
    public String registerPage()
    {
        return "user-registration";
    }

    @PostMapping("/process-registration")
    public String createUser(Users users)
    {
        Users savedUser=mailsService.createUser(users);
        if(savedUser!=null)
        {
            return "redirect:/email/api/login";
        }
        return "redirect:/email/api/user-registration";
    }

    @GetMapping("/login")
    public String loginPage()
    {
        return "login";
    }

    @GetMapping("/forgot-password")
    public String otpGeneration(@RequestParam String mail)
    {
        Users user=mailsService.forgotPassword(mail);
        if(user!=null)
        {
            return "redirect:/email/api/forgot-password.html";
        }
        return "OTP sent to your email Id";
    }

    @PostMapping("/process-login")
    public String loginUser(@RequestParam String mail,@RequestParam String password)
    {
        Users users=mailsService.loginUser(mail,password);
        if(users!=null)
        {
            return "redirect:/email/api/home";
        }
      return "redirect:/email/api/login";
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request,Model model)
    {
        model.addAttribute("subject",request.getSubject());
        model.addAttribute("message",request.getMessage());
        model.addAttribute("to",request.getTo());
        mailsService.sendEmail(request.getTo(), request.getSubject(), request.getMessage());
        return ResponseEntity.ok(MailResponse.builder().message("Mail sent successfully").success(true).status(HttpStatus.OK).build());
    }

    @PostMapping("/sendWithHtml")
    public ResponseEntity<?> sendMailWithHttpCode(@RequestBody EmailRequest request, Model model)
    {
        mailsService.sendMailWithHtmlFile(request.getTo(),request.getSubject(),request.getMessage());

        return ResponseEntity.ok(MailResponse.builder().message("Email sent successfully").status(HttpStatus.OK).success(true).build());
    }

    @PostMapping("/sendWithFile")
    public ResponseEntity<?> sendWithFile(@RequestPart EmailRequest request,@RequestPart MultipartFile file) throws IOException {
        mailsService.sendMailWithFile(request.getTo(), request.getSubject(), request.getMessage(),file.getInputStream());
        return ResponseEntity.ok(MailResponse.builder().message("Email sent to multiple users successfully").status(HttpStatus.OK).success(true).build());
    }


    @PostMapping("/sendWithMultiple")
    public ResponseEntity<?> sendEmailToMultiple(@RequestBody EmailRequest request)
    {
        mailsService.sendEmailToMultiple(request.getToo(), request.getSubject(), request.getMessage());
        return ResponseEntity.ok(MailResponse.builder().message("Email sent to multiple users successfully").status(HttpStatus.OK).success(true).build());
    }




}
    