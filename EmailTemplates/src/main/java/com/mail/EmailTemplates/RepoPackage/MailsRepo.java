package com.mail.EmailTemplates.RepoPackage;
import com.mail.EmailTemplates.EntityPackage.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailsRepo extends JpaRepository<Users,Integer> {

    Users findByMail(String mail);
    Users findByMailAndPassword(String mail,String password);
}
