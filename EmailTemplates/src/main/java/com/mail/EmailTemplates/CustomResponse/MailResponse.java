package com.mail.EmailTemplates.CustomResponse;

import lombok.*;
import org.springframework.http.HttpStatus;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailResponse {

    private String message;

    private HttpStatus status;

    private boolean success=false;
}
