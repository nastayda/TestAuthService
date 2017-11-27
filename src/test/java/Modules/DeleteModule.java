package Modules;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.List;

//Отказалась от перелистывания открывающейся таблицы в режиме управления модулями, т.к. в левом меню все равно отображаются модули
//Но в перспективе все равно надо будет поправить, т.к. если все модули не будт помещаяться в таблицу на первую страницу, нужно будет перелистыва. Как появится необходимость поправлю.
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
    @FindBy(xpath = "//div[@class='ant-modal-body']/div[2]/div/div[2]/div/div[1]/div[2]/div[2]/div/div/ul/li[last()]")
    //html/body/div[2]/div/div[2]/div/div[1]/div[2]/div[2]/div/div/ul/li[3]
    static WebElement pagginationArrowModule;
    @Test
    @Title("Удаление модуля")
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
        //int countAfterFromEachPage = goToLastPageForModule( );
        int afterDeletion = menuItems.size( );
        softAssert.assertEquals( afterDeletion, beforeDeletion - 1, "Проверка счетчика провалилась.");
        //softAssert.assertEquals( countAfterFromEachPage, countBeforeFromEachPage - 1, "Проверка физического наличия политик в таблице провалилась." );
        softAssert.assertAll();
    }

   /* @Step("Перейти на последнюю страницу")
    protected int goToLastPageForModule( ) {
        int k=countRowsOnEachPage.size();
        //Классно! если только не 100500 страниц надо будет перелистывать....
        if (pagginationArrowModule.getAttribute( "aria-disabled" ) != null) {
            while (pagginationArrowModule.getAttribute( "aria-disabled" ).equals( "false" )) {
                pagginationArrowModule.click( );
                k+=countRowsOnEachPage.size();
            }
        }
        return k;
    }*/

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
