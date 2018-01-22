package Users;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import static org.testng.Assert.assertTrue;

public class TestAuthorization extends BaseClass {
    @FindBy(xpath = "//table")
    static WebElement table;

    @Title("Авторизация пользователя")
    @Test
    @Step("Вызов метода авторизации")
    public void TestAuthorization() {
        //login();
        PageFactory.initElements(wd, this);
        //Завязываемся на отображение таблицы
        assertTrue(table.isEnabled());
    }
}
