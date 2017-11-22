package AuthService;

import HelpClass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
        createUser( loginName );
        //assertEquals( wd.findElement( By.xpath( "//table/tbody/tr[last()]/td[2]" ) ).getText( ), loginName );
        assertEquals( nameLoginObject.getText(), loginName );
    }

    @Step("Создание новго пользователя")
    private void createUser( String login ) {
        setLogin( login );
        setPassword( "test" );
        setSurname( "Testov" );
        setFirstName( "Test" );
        setMiddleName( "Testovich" );
        setEmail( "test@test.ru" );
        setPhoneNumber( "+79999999999" );
        setRole( "Tester" );
        clickToSave( );
        wd.navigate( ).refresh( );
    }

    @Step("9. Сохранине")
    private void clickToSave( ) {
       // wd.findElement( By.xpath( "//div[2]/div/div[2]/div/div[1]/div[3]/div/button[2]" ) ).click( );
        saveBTN.click();
    }

    @Step("8. Ввод роли {0}")
    private void setRole( String role ) {
        /*wd.findElement( By.id("role")).click();
        wd.findElement(By.id("role")).clear();
        wd.findElement(By.id("role")).sendKeys( role );*/
        roleTB.click( );
        roleTB.clear( );
        roleTB.sendKeys( role );
    }

    @Step("7. Ввод номера телефона {0}")
    private void setPhoneNumber( String phoneNumber ) {
        /*wd.findElement( By.xpath("//div[@class='ant-modal-body']/form/div[4]/div[2]/div[2]/div/div/div/div/ul/li/div/input")).click();
        wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[4]/div[2]/div[2]/div/div/div/div/ul/li/div/input")).clear();
        wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[4]/div[2]/div[2]/div/div/div/div/ul/li/div/input")).sendKeys( phoneNumber );*/
        phoneNumberTB.click( );
        phoneNumberTB.clear( );
        phoneNumberTB.sendKeys( phoneNumber );
    }

    @Step("6. Ввод email {0}")
    private void setEmail( String email ) {
       /* wd.findElement( By.xpath("//div[@class='ant-modal-body']/form/div[4]/div[1]/div[2]/div/div[1]/div/div/ul/li/div/input")).click();
        wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[4]/div[1]/div[2]/div/div[1]/div/div/ul/li/div/input")).clear();
        wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[4]/div[1]/div[2]/div/div[1]/div/div/ul/li/div/input")).sendKeys( email );*/
        emailTB.click( );
        emailTB.clear( );
        emailTB.sendKeys( email );
    }

    @Step("5. Ввод отчества {0}")
    private void setMiddleName( String middleName ) {
        /*wd.findElement( By.id("middleName")).click();
        wd.findElement(By.id("middleName")).clear();
        wd.findElement(By.id("middleName")).sendKeys( middleName );*/
        middleNameTB.click( );
        middleNameTB.clear( );
        middleNameTB.sendKeys( middleName );
    }

    @Step("4. Ввод имени {0}")
    private void setFirstName( String firstName ) {
       /* wd.findElement( By.id("firstName")).click();
        wd.findElement(By.id("firstName")).clear();
        wd.findElement(By.id("firstName")).sendKeys( firstName );*/
        firstNameTB.click( );
        firstNameTB.clear( );
        firstNameTB.sendKeys( firstName );
    }

    @Step("3. Ввод фамилии {0}")
    private void setSurname( String surname ) {
        /*wd.findElement( By.id("lastName")).click();
        wd.findElement(By.id("lastName")).clear();
        wd.findElement(By.id("lastName")).sendKeys(surname);*/
        lastNameTB.click( );
        lastNameTB.clear( );
        lastNameTB.sendKeys( surname );
    }

    @Step("2. Ввод пароля {0}")
    private void setPassword( String password ) {
        /*wd.findElement( By.id("password")).click();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys( password );
        wd.findElement(By.id("passwordRepeat")).click();
        wd.findElement(By.id("passwordRepeat")).clear();
        wd.findElement(By.id("passwordRepeat")).sendKeys(password);*/
        passwordTB.click( );
        passwordTB.clear( );
        passwordTB.sendKeys( password );
        passwordRepeatTB.click( );
        passwordRepeatTB.clear( );
        passwordRepeatTB.sendKeys( password );

    }

    @Step("1. Ввод логина {0}")
    private void setLogin( String login ) {
        /*wd.findElement( By.xpath("//*[@id=\"authorization\"]/div/div[2]/div[1]/div/span[3]/button")).click();
        wd.findElement(By.cssSelector("input.ant-input.ant-select-search__field")).click();
        wd.findElement(By.cssSelector("input.ant-input.ant-select-search__field")).clear();
        wd.findElement(By.cssSelector("input.ant-input.ant-select-search__field")).sendKeys( login );*/
        buttonAddBTN.click( );
        loginNameTB.clear( );
        loginNameTB.clear( );
        loginNameTB.sendKeys( login );
    }
}
