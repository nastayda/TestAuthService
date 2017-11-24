package ControleModule;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;

/*Проверку удаления завязываю на отображение количества записей напроитв Всего рядом с пагинацией.
Т.о. проверим заодно верно ли работает отображение количества записей в таблице.
Проверка получается не очень атомарная, но если она свалится, то в любом случае придется проходить все руками и смотреть,
что свалилось отображение или удаление*/
public class DeletePolitics extends BaseClass {
    @FindBy(xpath = "//ul/li[5]")
    static WebElement pagginationArrow;
    @FindBy(xpath = "//table/tbody/tr[last()]/td[2]")
    static WebElement lastRowText;
    @FindBy(xpath = "//table/tbody/tr[last()]/td[1]")
    static WebElement checkB;
    @FindBy(xpath = "//div/div[2]/div[1]/div/div[1]/span[5]/button")
    static WebElement deleteBTN;
    @FindBy(xpath = "//div[@class='ant-modal-footer']//button[2]")
    static WebElement confirmDeletionBTN;
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[2]/div/div/div/ul/li[1]")
    static WebElement countRowsText;

    @Test
    @Title("Удаление политики")
    public void deletePolitics( ) {
        login( );
        wd.navigate( ).to( getDataFromFile( "src/help-files/auth-info.txt" )[ 3 ] );
        goToLastPage( );
        //Количество элементов напротив "Всего"
        int countBefore = Integer.parseInt( countRowsText.getText( ).substring( 7 ) );
        //Определить есть ли элементы для удаления
        boolean thereIsSomethingForDeletion = choseUserForDeletion( );
        //Удалить если есть
        clickToDeleteBtn( thereIsSomethingForDeletion );
        //Посчитать сколько элементов после удаления
        int countAfter = Integer.parseInt( countRowsText.getText( ).substring( 7 ) );
        assertEquals( countAfter, countBefore - 1, "Проверка не прошла. Отображается неравное количество до и после удаления." );
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
