package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordForUser extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void changePasswordForUser() throws IOException, MessagingException {

        String newPassword = "Newpassword";
        UserData user = null;
        Users users = app.db().users();
        for (UserData choiceUser: users) {
            if (choiceUser.getId() == 1) {
            } else {
                user = choiceUser;
                break;
            }
        }
        app.registration().loginAsAdmin();
        app.registration().choiceUserForChangePassword(user.getId());
        List<MailMessage> mailMessages = app.mail().waitForMail(1,10000);
        String confirmationLink = app.registration().findConfirmationLink(mailMessages, user.getEmail());
        app.registration().changePassword(confirmationLink,newPassword);
        assertTrue(app.newSession().login(user.getUsername(),newPassword));
    }


    @AfterMethod(alwaysRun = true)
    public void afterMailServer() {
        app.mail().stop();
    }


}
