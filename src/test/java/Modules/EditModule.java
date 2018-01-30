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

public class EditModule extends BaseClass {
    @FindBy(xpath = "//div[@class='ant-modal-body']/div[2]/div/div/div/div/div/table/tbody/tr[last()]/td[1]")
    static WebElement rowNameModuleTD;
    @FindBy(xpath = "//div[2]/div/div[2]/div/div[1]/div[2]/div[1]/span[2]/button")
    static WebElement editModuleBTN;

    @Test
    @Title("Редактирование модуля")
    public void editModule( ) {
        PageFactory.initElements(wd, this);
        //login( );
        goToPolicyPage( );
        clickControlBtn( );
        selectedModule( );
        clickToEditBtn( );
        String moduleName = "editModule" + LocalDateTime.now( ).toString( ).replace( ":", "_" );
        String moduleKeyName = "editModuleKey" + LocalDateTime.now( ).toString( ).replace( ":", "_" );
        String moduleAddress = "editAddress" + LocalDateTime.now( ).toString( ).replace( ":", "_" );
        setKeyName( moduleKeyName );
        setName( moduleName );
        setAddress( moduleAddress );
        clickToSaveBtn( );
        wd.navigate( ).refresh( );
        waitSomeTime( 500 );
        assertEquals( lastMenuItem.getText( ), moduleName );
    }

    @Step("Нажать на кнопку Редактировать")
    public void clickToEditBtn( ) {
        //editModuleBTN.click();
        clickWithExpects( editModuleBTN );
    }

    @Step("Выбрать модуль для редактирования")
    public boolean selectedModule( ) {
        waitSomeTime( 2000 );
        if (rowNameModuleTD.getText( ).contains( "testModule" )) {
            //rowNameModuleTD.click( );
            clickWithExpects( rowNameModuleTD );
            return true;
        } else return false;
    }
}
