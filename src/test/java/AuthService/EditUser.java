package AuthService;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;

public class EditUser extends CreateUser {
    @FindBy(xpath = "//table/tbody/tr[last()]/td")
    static List<WebElement> tableColums;

    @FindBy(xpath = "//table/tbody/tr[last()]/td[2]")
    static WebElement userLoginRow;

    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[1]/div/span[4]/button")
    static WebElement editBTN;

    @FindBy(xpath = "//div[2]/div/div[2]/div/div[1]/div[2]/form/div[2]/div[2]/div/input")
    static WebElement newPasswordTB;

    @Test
    @Title("Редактирование пользователя")
    public void editUser( ) {
        login( );
        String loginName = "editUser" + LocalDateTime.now( ).toString( ).replace( ":", "_" );
        String[] dataForEdite = { loginName,
                "test1",
                "Testovsky",
                "Testky",
                "Testovichky",
                "testky@test.ru",
                "+70000000000",
                "Testerer" };
        editUserToEdited( dataForEdite );
        compareDataFromTable( dataForEdite, loginName );
    }

    @Step("Сравнние введенных данных и тех, что отображаются в таблице")
    public void compareDataFromTable( String[] dataForEdite , String loginName) {
        //Выбрать только то, что отображается в таблице
        String[] dataFromMass = { loginName, dataForEdite[ 2 ], dataForEdite[ 3 ], dataForEdite[ 4 ], dataForEdite[ 5 ], dataForEdite[ 7 ] };
        wd.navigate().refresh();
        //Выбрать из таблицы то, с чем будем сравнивать
        String[] dataFromTable = new String[ tableColums.size( ) - 1 ];
        for (int i = 1; i < tableColums.size( ) ; i++) {
            dataFromTable[ i - 1 ] = tableColums.get( i ).getText( );
        }
        assertEquals( dataFromMass, dataFromTable );
    }

    @Step("Редактирвоание пользователя")
    private void editUserToEdited( String[] dataForEdite ) {
        userLoginRow.click( );
        editBTN.click( );
        setLogin( dataForEdite[ 0 ] );
        setNewPasswordTB( dataForEdite[ 1 ] );
        setSurname( dataForEdite[ 2 ] );
        setFirstName( dataForEdite[ 3 ] );
        setMiddleName( dataForEdite[ 4 ] );
        setEmail( dataForEdite[ 5 ] );
        setPhoneNumber( dataForEdite[ 6 ] );
        setRole( dataForEdite[ 7 ] );
        clickToSave( );
    }

    @Step("2. Ввод пароля {0}")
    public void setNewPasswordTB( String newPassword ) {
        newPasswordTB.click( );
        newPasswordTB.clear( );
        newPasswordTB.sendKeys( newPassword);
    }
}
