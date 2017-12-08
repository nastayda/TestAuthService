package SettingLogo;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

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
        wd.navigate().back();
        saveNewUrl(textBefor);
    }

    public void goToLogoPage( ) {
        goToLogo.click();
    }

    public void saveNewUrl( String url ) {
        urlTB.clear();
        urlTB.sendKeys( url );
        saveBTN.click();
    }

    public void goToChangeLogoUrlForm( ) {
        settingBTN.click();
        urlTB.click();
    }
}
