package SettingLogo;

import HelpClass.BaseClass;
//import com.sun.jna.platform.FileUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.File;
import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class SettingLogo extends BaseClass {
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div/div[2]/div/div/div[1]/div/button")
    WebElement settingBTN;
    @FindBy(xpath = "//div[2]/div/div/ul/li[1]")
    WebElement getSettingLogoSubmenuBTN;
    @FindBy(xpath = "//form/div[2]/div/div[2]/div/input")
    WebElement urlTB;
    @FindBy(xpath = "//div[3]/div/div[2]/div/div[1]/div[3]/div/button[2]")
    WebElement saveBTN;
    @FindBy(xpath = "//div[@id='root']/div/div[1]/div/div[1]/div/div[1]/img")
    WebElement goToLogo;
    @FindBy(xpath = "//*[@id=\"root\"]")
    WebElement body;

    @Test
    public void setLogoUrl( ) throws IOException {
        // login();
        PageFactory.initElements( wd, this );
        goToChangeLogoUrlForm( );
        String textBefor = urlTB.getAttribute( "value" );
        String newUrl = "https://ya.ru/";
        saveNewUrl( newUrl );
        waitSomeMillisec( 2000 );
        goToLogoPage( );
        assertEquals( wd.getCurrentUrl( ).toString( ), newUrl, "URL не был изменен. " );
        //Change to the first state
        wd.navigate( ).back( );
        goToChangeLogoUrlForm( );
        saveNewUrl( textBefor );
    }

    @Step("Переход на страницу с логотипом")
    public void goToLogoPage( ) {
        goToLogo.click( );
    }

    @Step("Сохранить новый URL {0}")
    public void saveNewUrl( String url ) throws IOException {
        urlTB.click( );
        urlTB.clear( );
        urlTB.sendKeys( url );
        saveBTN.click( );
        waitSomeMillisec( 2000 );
        createSkreenshot( );
        body.click();
    }



    @Step("Прейти по изменненому URL")
    public void goToChangeLogoUrlForm( ) {
        settingBTN.click( );
        getSettingLogoSubmenuBTN.click( );
    }
}
