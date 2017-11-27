package Politics;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.List;

import static org.testng.Assert.assertEquals;

/*Проверку удаления завязываю на отображение количества записей напроитв Всего, рядом с пагинацией.
Т.о. проверим заодно верно ли работает отображение количества записей в таблице.
Проверка получается не очень атомарная, но если она свалится, то в любом случае придется проходить все руками и смотреть,
что свалилось отображение или удаление*/
public class DeletePolitics extends BaseClass {
    @FindBy(xpath = "//table/tbody/tr")
    static List<WebElement> table;
    @FindBy(xpath = "//div/div[2]/div[1]/div/div[1]/span[5]/button")
    static public WebElement deleteBTN;
    @FindBy(xpath = "//div[@class='ant-modal-footer']//button[2]")
    static public WebElement confirmDeletionBTN;

    @Test
    @Title("Удаление политики")
    public void deletePolitics( ) {
        login( );
        goToPolicyPage( );
        System.out.println(pagginationArrow.getText());
        int countBeforeFromEachPage = goToLastPage( );
        //Количество элементов напротив "Всего"
        int countBefore = Integer.parseInt( countRowsText.getText( ).substring( 7 ) );
        //Определить есть ли элементы для удаления
        boolean thereIsSomethingForDeletion = chosePolicy( "testPolitics", "editPolitics" );
        //Удалить если есть
        clickToDeleteBtn( thereIsSomethingForDeletion );
        //Посчитать сколько элементов после удаления
        int countAfter = Integer.parseInt( countRowsText.getText( ).substring( 7 ) );
        int countAfterFromEachPage = goToLastPage( );
        //Проверка по счетчику
        softAssert.assertEquals( countAfter, countBefore - 1, "Проверка счетчика провалилась." );
        //Проверка по физическому наличию в таблице
        System.out.println(countAfterFromEachPage +" "+countBeforeFromEachPage);
        softAssert.assertEquals( countAfterFromEachPage, countBeforeFromEachPage - 1, "Проверка физического наличия политик в таблице провалилась." );
        softAssert.assertAll();
    }

    @Step("3. Удалить и подтвердить удаление")
    public void clickToDeleteBtn( boolean flag ) {
        if (flag) {
            waitUntilElementBeClickable( deleteBTN );
            deleteBTN.click( );
            waitUntilElementBeClickable( confirmDeletionBTN );
            confirmDeletionBTN.click( );
        }
        wd.navigate( ).refresh( );
    }
}
