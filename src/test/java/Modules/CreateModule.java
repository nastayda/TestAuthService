package Modules;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

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
    @Title("Создание модуля")
    public void createModule( ) {
        login( );
        goToPolicyPage( );
        String moduleName = "testModule" + LocalDateTime.now( ).toString( ).replace( ":", "_" );
        String moduleKeyName = "testModuleKey" + LocalDateTime.now( ).toString( ).replace( ":", "_" );
        String moduleAddress = "testAddress" + LocalDateTime.now( ).toString( ).replace( ":", "_" );
        clickToAddBtn( );
        setKeyName( moduleKeyName );
        setName( moduleName );
        setAddress( moduleAddress );
        clickToSaveBtn( );
        wd.navigate( ).refresh( );
        waitSomeMillisec( 500 );
        assertEquals( lastMenuItem.getText( ), moduleName );


    }

    @Step(" Нажать на сохранение")
    public void clickToSaveBtn( ) {
        saveModuleBTN.click( );
    }

    @Step(" Ввод адреса {0}")
    public void setAddress( String address ) {
        addressINPT.click( );
        addressINPT.clear( );
        addressINPT.sendKeys( address );
    }

    @Step(" Ввод названия {0}")
    public void setName( String name ) {
        nameModuleINPT.click( );
        nameModuleINPT.clear( );
        nameModuleINPT.sendKeys( name );
    }

    @Step(" Ввод ключевого названия {0}")
    public void setKeyName( String keyName ) {
        keyNameModuleINPT.click( );
        keyNameModuleINPT.clear( );
        keyNameModuleINPT.sendKeys( keyName );
    }

    @Step(" Нажатие на кнокпу добавления модуля")
    public void clickToAddBtn( ) {
        clickControlBtn( );
        addModuleBTN.click( );
    }

    public void clickControlBtn( ) {
        controlModuleBTN.click( );
    }
}
