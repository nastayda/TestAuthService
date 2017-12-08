package SettingLogo;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;

import static org.testng.Assert.assertEquals;

public class SettingLogo extends BaseClass {
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[1]/div/span[1]/button")
    WebElement settingBTN;
    @FindBy(xpath = "//form/div[2]/div/div[2]/div/input")
    WebElement urlTB;
    @FindBy(xpath = "//div[2]/div/div[2]/div/div[1]/div[3]/div/button[2]")
    WebElement saveBTN;
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[1]/div")
    WebElement goToLogo;

    @Test
    public void setLogoUrl(){
        login();
        goToChangeLogoUrlForm( );
        String textBefor = urlTB.getAttribute( "value" );
        String newUrl ="https://ya.ru/";
        saveNewUrl( newUrl );
        goToLogoPage( );
        assertEquals(newUrl, wd.getCurrentUrl().toString());
        //Change to the first state
        wd.navigate().back();
        goToChangeLogoUrlForm( );
        saveNewUrl(textBefor);
    }
    @Step("")
    public void goToLogoPage( ) {
        goToLogo.click();
    }
    @Step("")
    public void saveNewUrl( String url ) {
        urlTB.clear();
        urlTB.sendKeys( url );
        saveBTN.click();
    }
    @Step("")
    public void goToChangeLogoUrlForm( ) {
        settingBTN.click();
        urlTB.click();
    }
}
