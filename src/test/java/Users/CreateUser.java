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
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[1]/div/span[3]/button")
    static WebElement buttonAddBTN;
    @FindBy(css = "input.ant-input.ant-select-search__field")
    static WebElement loginNameTB;
    @FindBy(id = "password")
    static WebElement passwordTB;
    @FindBy(id = "passwordRepeat")
    static WebElement passwordRepeatTB;
    @FindBy(id = "lastName")
    static WebElement lastNameTB;
    @FindBy(id = "firstName")
    static WebElement firstNameTB;
    @FindBy(id = "middleName")
    static WebElement middleNameTB;
    @FindBy(xpath = "//div[@class='ant-modal-body']/form/div[4]/div[1]/div[2]/div/div[1]/div/div/ul/li/div/input")
    static WebElement emailTB;
    @FindBy(xpath = "//div[@class='ant-modal-body']/form/div[4]/div[2]/div[2]/div/div/div/div/ul/li/div/input")
    static WebElement phoneNumberTB;
    @FindBy(id = "role")
    static WebElement roleTB;
    @FindBy(xpath = "//div[2]/div/div[2]/div/div[1]/div[3]/div/button[2]")
    static WebElement saveBTN;
    @FindBy(xpath = "//table/tbody/tr[last()]/td[2]")
    static WebElement nameLoginObject;

    @Title("Создание пользователя")
    @Test
    public void createUser( ) {
        login( );
        String loginName = "testUser" + LocalDateTime.now( ).toString( ).replace( ":", "_" );
        clickAddBtn( );
        createNewUser( loginName, "test", "Testov", "Test", "Testovich", "test@test.ru", "+79999999999", "Tester" );
        assertEquals( nameLoginObject.getText(), loginName );
        //проверить авторизацию созданным пользователем
    }

    @Step("Создание новго пользователя")
    public void createNewUser( String login, String password, String surname, String firstName, String middleName, String email, String phoneNumber, String role ) {
        setLogin( login );
        setPassword( password );
        setSurname( surname );
        setFirstName( firstName );
        setMiddleName( middleName );
        setEmail( email );
        setPhoneNumber( phoneNumber );
        setRole( role );
        clickToSave( );
        wd.navigate( ).refresh( );
    }

    @Step("9. Сохранине")
    public void clickToSave( ) {
        waitUntilElementBeClickable( saveBTN );
        saveBTN.click();
    }

    @Step("8. Ввод роли {0}")
    public void setRole( String role ) {
        roleTB.click( );
        roleTB.clear( );
        roleTB.sendKeys( role );
    }

    @Step("7. Ввод номера телефона {0}")
    public void setPhoneNumber( String phoneNumber ) {
        phoneNumberTB.click( );
        phoneNumberTB.clear( );
        phoneNumberTB.sendKeys( phoneNumber );
    }

    @Step("6. Ввод email {0}")
    public void setEmail( String email ) {
        emailTB.click( );
        emailTB.clear( );
        emailTB.sendKeys( email );
    }

    @Step("5. Ввод отчества {0}")
    public void setMiddleName( String middleName ) {
        middleNameTB.click( );
        middleNameTB.clear( );
        middleNameTB.sendKeys( middleName );
    }

    @Step("4. Ввод имени {0}")
    public void setFirstName( String firstName ) {
        firstNameTB.click( );
        firstNameTB.clear( );
        firstNameTB.sendKeys( firstName );
    }

    @Step("3. Ввод фамилии {0}")
    public void setSurname( String surname ) {
        lastNameTB.click( );
        lastNameTB.clear( );
        lastNameTB.sendKeys( surname );
    }

    @Step("2. Ввод пароля {0}")
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

    @Step("1. Ввод логина {0}")
    public static void setLogin( String login ) {
        loginNameTB.clear( );
        loginNameTB.clear( );
        loginNameTB.sendKeys( login );
    }

    public void clickAddBtn( ) {
      buttonAddBTN.click();
    }
}
