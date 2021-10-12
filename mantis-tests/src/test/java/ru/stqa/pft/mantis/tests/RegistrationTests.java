package ru.stqa.pft.mantis.tests;

import com.sun.javafx.binding.StringFormatter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }


    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now  = System.currentTimeMillis();
        String email = String.format( "user%s@localhost.localdomain", now);
        String username = String.format("user%s", now);
        String password = "password";
        app.registration().start(username, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2,10000);
        //List<MailMessage> mailMessages = app.james().waitForMail(username,password, 60000);
        String confirmationLink = app.registration().findConfirmationLink(mailMessages, email);
        app.registration().finish(username,confirmationLink, password);
        assertTrue(app.newSession().login(username,password));
    }
    
    @AfterMethod(alwaysRun = true)
    public void afterMailServer() {
        app.mail().stop();
    }
}
