package Modules;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;

public class CreateModule extends BaseClass {
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[1]/div/div[1]/span[2]/button")
    static WebElement controlModuleBTN;
    @FindBy(xpath = "//div[2]/div/div[2]/div/div[1]/div[2]/div[1]/span[1]/button")
    static WebElement addModuleBTN;
    @FindBy(xpath = "//div[1]/div[2]/div/span/input")
    static WebElement keyNameModuleINPT;
    @FindBy(xpath = "//div[2]/div[2]/div/input")
    static WebElement nameModuleINPT;
    @FindBy(xpath = "//div[3]/div[2]/div/span/input")
    static WebElement addressINPT;
    @FindBy(xpath = "//div[3]/div/div[2]/div/div[1]/div[3]/div/button[2]")
    static WebElement saveModuleBTN;
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[1]/ul/li[last()]")
    static WebElement lastMenuItem;


    @Test
    public void createModule(){
        login();
        goToPolicyPage();
       // clickToAddBtn( );
        String moduleName = "testModule" + LocalDateTime.now( ).toString( ).replace( ":", "_" );
        /*setKeyName( "testModuleKey" );
        setName( moduleName );
        setAddress("testAddress" );
        clickToSaveBtn( );*/
        wd.navigate().refresh();
        assertEquals(lastMenuItem.getText(), moduleName);


    }

    public void clickToSaveBtn( ) {
        saveModuleBTN.click();
    }

    public void setAddress( String address ) {
        addressINPT.click();
        addressINPT.clear();
        addressINPT.sendKeys( address );
    }

    public void setName( String name ) {
        nameModuleINPT.click();
        nameModuleINPT.clear();
        nameModuleINPT.sendKeys( name );
    }

    public void setKeyName( String keyName ) {
        keyNameModuleINPT.click();
        keyNameModuleINPT.clear();
        keyNameModuleINPT.sendKeys( keyName );
    }

    public void clickToAddBtn( ) {
        clickControlBtn( );
        addModuleBTN.click();
    }

    public void clickControlBtn( ) {
        controlModuleBTN.click();
    }
}
