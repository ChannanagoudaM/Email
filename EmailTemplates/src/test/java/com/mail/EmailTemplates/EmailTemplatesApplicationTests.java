package com.mail.EmailTemplates;

import com.mail.EmailTemplates.ServicePackage.MailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class EmailTemplatesApplicationTests {

	@Autowired
	MailsService service;
//
//	@Test
//	void emailsendTest() {
//		service.sendEmail("channanagouda.aralahalli@eidiko-india.com","First message","Namaste");
//		System.out.println("email sent");
//	}

//	@Test
//	void sendmail()
//	{
//		String html=" <h1 style='color:red'>Namaste India</h1>";
//		service.sendMail("sankethshinde99@gmail.com","first Message","Namaste India",html);
//	}

//	@Test
//	void sendMailFile() throws FileNotFoundException {
//		File file=new File("C:\\Users\\Sreenivas Bandaru\\Downloads\\mountain1.jpg");
//		InputStream is=new FileInputStream(file);
//		service.sendMailWithFile("sankethshinde99@gmail.com","Fourth message","Namaste India",is);
//	}

}
