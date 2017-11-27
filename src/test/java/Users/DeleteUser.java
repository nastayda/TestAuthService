package Users;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.time.LocalDateTime;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class DeleteUser extends BaseClass {
    @FindBy(xpath = "//table/tbody/tr")
    static List<WebElement> table;
    @FindBy(xpath = "//table/tbody/tr[last()]/td[1]")
    static WebElement checkB;
    @FindBy(xpath = "//table/tbody/tr[last()]/td[2]")
    static WebElement userLoginT;
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[1]/div/div/span[1]/button")
    static WebElement deleteBTN;
    @FindBy(xpath = "//div[2]/div/div[2]/div/div[1]/div[3]/button[2]")
    static WebElement confirmDeletionBTN;

    @Test
    @Title("Удалить пользователя")
    public void deleteUser( ) {
        login( );
        int countRowsBefore = getCountRows( );
        choseUserForDeletion( );
        clickToDeleteBtn( );
        assertEquals( getCountRows( ), countRowsBefore - 1 );

    }

    @Step("Удалить и подтвердить удалене")
    public void clickToDeleteBtn( ) {
        waitUntilElementBeClickable( deleteBTN );
        deleteBTN.click( );
        waitUntilElementBeClickable( confirmDeletionBTN );
        confirmDeletionBTN.click( );
        waitUntilElementBeClickable( checkB );

    }

    @Step("Получить имя пользователя")
    public void choseUserForDeletion( ) {
        if (!checkB.isSelected( ) & (userLoginT.getText( ).contains( "testUser" + LocalDateTime.now( ).getYear( ) )||
                                     userLoginT.getText( ).contains( "editUser" + LocalDateTime.now( ).getYear( ) ))) {
            checkB.click( );
        }
    }

    @Step("Получить число элементов со страницы")
    public int getCountRows( ) {
        wd.navigate( ).refresh( );
        //Посчитать число строк в таблице
        return table.size( );
    }

}
