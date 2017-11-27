package Modules;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class DeleteModule extends BaseClass {
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[1]/div/div[1]/span[2]/button")
    static WebElement controlModuleBTN;
    @FindBy(xpath = "//div[@class='ant-modal-body']/div[2]/div/div/div/div/div/table/tbody/tr[last()]/td[1]")
    static WebElement rowNameModuleTD;
    @FindBy(xpath = "//div[2]/div/div[2]/div/div[1]/div[2]/div[1]/span[3]/button")
    static WebElement deleteBTN;
    @FindBy(xpath = "//div[@class='ant-modal-footer']/button[2]")
    static WebElement confirmDeletionBTN;
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[1]/ul/li")
    static List<WebElement> menuItems;

    @Test
    public void deleteModule( ) {
        login( );
        goToPolicyPage( );
        waitSomeMillisec( 1500 );
        int beforeDeletion = menuItems.size( );
        clickControlModule( );
        boolean flag = selectedModule( );
        clickDeleteBtn( flag );
        wd.navigate( ).refresh( );
        waitSomeMillisec( 1500 );
        int afterDeletion = menuItems.size( );
        assertEquals( afterDeletion, beforeDeletion - 1 );
    }

    @Step("Выбрать модуль для удаления")
    public boolean selectedModule( ) {
        if (rowNameModuleTD.getText( ).contains( "testModule" )||rowNameModuleTD.getText( ).contains( "editModule" )) {
            rowNameModuleTD.click( );
            return true;
        } else return false;
    }

    @Step("Удалить модуль")
    public void clickDeleteBtn( boolean flag ) {
        if (flag) {
            deleteBTN.click( );
            confirmDeletionBTN.click( );
        }
    }

    @Step("Нажать на кнопку Управление модулями")
    public void clickControlModule( ) {
        controlModuleBTN.click( );
    }
}
