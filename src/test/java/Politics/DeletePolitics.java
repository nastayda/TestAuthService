package Politics;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import static org.testng.Assert.assertEquals;

/*Проверку удаления завязываю на отображение количества записей напроитв Всего, рядом с пагинацией.
Т.о. проверим заодно верно ли работает отображение количества записей в таблице.
Проверка получается не очень атомарная, но если она свалится, то в любом случае придется проходить все руками и смотреть,
что свалилось отображение или удаление*/
public class DeletePolitics extends BaseClass {
    @FindBy(xpath = "//div/div[2]/div[1]/div/div[1]/span[5]/button")
    static public WebElement deleteBTN;
    @FindBy(xpath = "//div[@class='ant-modal-footer']//button[2]")
    static public WebElement confirmDeletionBTN;

    @Test
    @Title("Удаление политики")
    public void deletePolitics( ) {
        login( );
        goToPolicyPage( );
        goToLastPage( );
        //Количество элементов напротив "Всего"
        int countBefore = Integer.parseInt( countRowsText.getText( ).substring( 7 ) );
        //Определить есть ли элементы для удаления
        boolean thereIsSomethingForDeletion = chosePolicy( "testPolitics", "editPolitics" );
        //Удалить если есть
        clickToDeleteBtn( thereIsSomethingForDeletion );
        //Посчитать сколько элементов после удаления
        int countAfter = Integer.parseInt( countRowsText.getText( ).substring( 7 ) );
        assertEquals( countAfter, countBefore - 1, "Проверка не прошла. Отображается неравное количество до и после удаления." );
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
