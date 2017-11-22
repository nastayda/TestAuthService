package AuthService;

import HelpClass.BaseClass;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;

import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;

public class CreateUser extends BaseClass{
    @Test
    public void createUser(){
        login();
        String loginName = "testUser"+ LocalDateTime.now().toString().replace(":","_");
        createUser( loginName );
        assertEquals(wd.findElement( By.xpath( "//table/tbody/tr[last()]/td[2]" ) ).getText() , loginName);
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
        wd.navigate().refresh();
    }

    @Step("9. Сохранине")
    private void clickToSave( ) {
        wd.findElement( By.xpath("//div[2]/div/div[2]/div/div[1]/div[3]/div/button[2]")).click();
    }

    @Step("8. Ввод роли {0}")
    private void setRole( String role ) {
        wd.findElement( By.id("role")).click();
        wd.findElement(By.id("role")).clear();
        wd.findElement(By.id("role")).sendKeys( role );
    }

    @Step("7. Ввод номера телефона {0}")
    private void setPhoneNumber( String phoneNumber ) {
        wd.findElement( By.xpath("//div[@class='ant-modal-body']/form/div[4]/div[2]/div[2]/div/div/div/div/ul/li/div/input")).click();
        wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[4]/div[2]/div[2]/div/div/div/div/ul/li/div/input")).clear();
        wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[4]/div[2]/div[2]/div/div/div/div/ul/li/div/input")).sendKeys( phoneNumber );
    }

    @Step("6. Ввод email {0}")
    private void setEmail( String email ) {
        wd.findElement( By.xpath("//div[@class='ant-modal-body']/form/div[4]/div[1]/div[2]/div/div[1]/div/div/ul/li/div/input")).click();
        wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[4]/div[1]/div[2]/div/div[1]/div/div/ul/li/div/input")).clear();
        wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[4]/div[1]/div[2]/div/div[1]/div/div/ul/li/div/input")).sendKeys( email );
    }

    @Step("5. Ввод отчества {0}")
    private void setMiddleName( String middleName ) {
        wd.findElement( By.id("middleName")).click();
        wd.findElement(By.id("middleName")).clear();
        wd.findElement(By.id("middleName")).sendKeys( middleName );
    }

    @Step("4. Ввод имени {0}")
    private void setFirstName( String firstName ) {
        wd.findElement( By.id("firstName")).click();
        wd.findElement(By.id("firstName")).clear();
        wd.findElement(By.id("firstName")).sendKeys( firstName );
    }

    @Step("3. Ввод фамилии {0}")
    private void setSurname( String surname ) {
        wd.findElement( By.id("lastName")).click();
        wd.findElement(By.id("lastName")).clear();
        wd.findElement(By.id("lastName")).sendKeys(surname);
    }

    @Step("2. Ввод пароля {0}")
    private void setPassword( String password ) {
        wd.findElement( By.id("password")).click();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys( password );
        wd.findElement(By.id("passwordRepeat")).click();
        wd.findElement(By.id("passwordRepeat")).clear();
        wd.findElement(By.id("passwordRepeat")).sendKeys(password);
    }

    @Step("1. Ввод логина {0}")
    private void setLogin( String login ) {
        wd.findElement( By.xpath("//*[@id=\"authorization\"]/div/div[2]/div[1]/div/span[3]/button")).click();
        wd.findElement(By.cssSelector("input.ant-input.ant-select-search__field")).click();
        wd.findElement(By.cssSelector("input.ant-input.ant-select-search__field")).clear();
        wd.findElement(By.cssSelector("input.ant-input.ant-select-search__field")).sendKeys( login );
    }
}
