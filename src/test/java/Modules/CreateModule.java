package Modules;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;

public class CreateModule extends BaseClass {
    @FindBy(xpath = "//div[2]/div/div[2]/div/div[1]/div[2]/div[1]/span[1]/button")
    static WebElement addModuleBTN;

    @Test
    @Title("Создание модуля")
    public void createModule( ) {
        //login( );
        PageFactory.initElements(wd, this);
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
        waitSomeTime( 500 );
        assertEquals( lastMenuItem.getText( ), moduleName );
    }

    @Step(" Нажатие на кнокпу добавления модуля")
    public void clickToAddBtn( ) {
        clickControlBtn( );
        //addModuleBTN.click( );
        clickWithExpects( addModuleBTN );
    }

}
