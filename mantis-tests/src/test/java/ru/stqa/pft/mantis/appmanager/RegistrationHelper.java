package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import java.util.List;

public class RegistrationHelper extends HelperBase{

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }


    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));

    }

    public void loginAsAdmin() {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), app.getProperty("web.login"));
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), app.getProperty("web.password"));
        click(By.cssSelector("input[type='submit']"));
    }

    public void choiceUserForChangePassword(int userId) {
        click(By.cssSelector("a[href='/mantisbt-2.25.2/manage_overview_page.php']"));
        click(By.cssSelector("a[href='/mantisbt-2.25.2/manage_user_page.php']"));
        click(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", userId)));
        click(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div[4]/div[2]/form[1]/fieldset/span/input"));

    }

    public void changePassword(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }

    public void finish(String username,String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("realname"), username);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }


    public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) ->m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
