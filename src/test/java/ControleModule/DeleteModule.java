package ControleModule;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;

import java.time.LocalDateTime;
/*Проверку удаления завязываю на отображение количества записей напроитв Всего рядом с пагинацией
Т.о. проверим заодно верно ли работает отображение количества записей в таблице */
public class DeleteModule extends BaseClass {
    @FindBy(xpath = "//ul/li[5]")
    static WebElement pagginationArrow;
    @FindBy(xpath = "//table/tbody/tr[last()]/td[2]")
    static WebElement lastRowText;
    @FindBy(xpath = "//table/tbody/tr[last()]/td[1]")
    static WebElement checkB;
    @FindBy(xpath = "//div/div[2]/div[1]/div/div[1]/span[5]/button")
    static WebElement deleteBTN;
    //div[@class='ant-modal-footer']//button[.='OK']
    @FindBy(xpath = "//div[@class='ant-modal-footer']//button[2]")
    static WebElement confirmDeletionBTN;

    @Test
    public void deleteModule( ) {
        login( );
        wd.navigate( ).to( getDataFromFile( "src/help-files/auth-info.txt" )[ 3 ] );
        goToLastPage( );
        boolean thereIsSomethingForDeletion = choseUserForDeletion( );
        clickToDeleteBtn( thereIsSomethingForDeletion );

    }

    @Step("3. Удалить и подтвердить удалене")
    private void clickToDeleteBtn( boolean flag ) {
        if (flag) {
            waitUntilElementBeClickable( deleteBTN );
            deleteBTN.click( );
            waitUntilElementBeClickable( confirmDeletionBTN );
            confirmDeletionBTN.click( );
        }
    }

    @Step("2. Получить название политики")
    public boolean choseUserForDeletion( ) {
        if (!checkB.isSelected( ) & ( lastRowText.getText( ).contains( "testPolitics" + LocalDateTime.now( ).getYear( ) ) ||
                lastRowText.getText( ).contains( "editPolitics" + LocalDateTime.now( ).getYear( ) ) )) {
            checkB.click( );
            return true;
        } else return false;
    }

    @Step("1. Перейти на последнюю страницу")
    private void goToLastPage( ) {
        //Классно! если только не 100500 страниц надо будет перелистывать....
        if (pagginationArrow.getAttribute( "aria-disabled" ) != null) {
            while (pagginationArrow.getAttribute( "aria-disabled" ).equals( "false" )) {
                pagginationArrow.click( );
            }
        }
    }
}
