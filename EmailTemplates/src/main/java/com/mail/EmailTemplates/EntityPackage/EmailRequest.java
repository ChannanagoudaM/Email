package com.mail.EmailTemplates.EntityPackage;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailRequest {

    private String to;
    private String subject;
    private String message;
    private String[]too;
}
