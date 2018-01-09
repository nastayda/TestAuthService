package Users;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;

public class CreateUser extends BaseClass {
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div/div[1]/div/div[2]/span[2]/button")
    static WebElement buttonAddBTN;
    @FindBy(id = "password")
    static WebElement passwordTB;
    @FindBy(id = "passwordRepeat")
    static WebElement passwordRepeatTB;
    @FindBy(xpath = "//table/tbody/tr[last()]/td[5]")
    static WebElement nameLoginObject;

    @Title("Создание пользователя")
    @Test
    public void createUser( ) {
        login( );
        String loginName = "testUser" + LocalDateTime.now( ).toString( ).replace( ":", "_" );
        clickAddBtn( );
        createNewUser( loginName, "test", "Testov", "Test", "Testovich",
                "test@test.ru", "+79999999999", "Tester", "TestType", "TestName", "TestSubunit","TestGroup");
        assertEquals( nameLoginObject.getText(), loginName );
        //проверить авторизацию созданным пользователем
    }

    @Step("Создание новго пользователя")
    public void createNewUser( String login, String password, String surname, String firstName, String middleName,
                               String email, String phoneNumber, String role, String orgType, String orgName, String orgSubunit, String orgGroup ) {
        setLogin( login );
        setPassword( password );
        setSurname( surname );
        setFirstName( firstName );
        setMiddleName( middleName );
        setEmail( email );
        setPhoneNumber( phoneNumber );
        setRole( role );
        setOrgType( orgType);
        setOrgName( orgName );
        setOrgSubunit( orgSubunit );
        setOrgGroup( orgGroup );
        clickToSave( );
        wd.navigate( ).refresh( );
    }

    @Step("Ввод пароля {0}")
    public void setPassword( String password ) {
        passwordTB.click( );
        passwordTB.clear( );
        passwordTB.sendKeys( password );
        String Source = wd.getPageSource();
        System.out.println(passwordRepeatTB.getText() );
        if (Source.contains(passwordRepeatTB.getText()))
        {
            passwordRepeatTB.click( );
            passwordRepeatTB.clear( );
            passwordRepeatTB.sendKeys( password );
        }
    }

    public void clickAddBtn( ) {
      buttonAddBTN.click();
    }
}
