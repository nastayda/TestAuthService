package AuthService;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.List;

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
        /*String[] dataForEdite = { userLoginRow.getText( ).replaceAll(userLoginRow.getText( ).substring( 0,4 ), "edit" ),
                "test1",
                "Testovsky",
                "Testky",
                "Testovichky",
                "testky@test.ru",
                "+70000000000",
                "Testerer" };
        editUserToEdited( dataForEdite );*/

    }

    public void compareDataFromTable(String [] dataForEdite){
        //Выбрать только то, что отображается в таблице
        String [] dataFromMass ={dataForEdite[0],dataForEdite[2],dataForEdite[3],dataForEdite[4],dataForEdite[5],dataForEdite[7]};
        String [] dataFromTable = new String[tableColums.size()-1];
        for (int i =1 ; i< tableColums.size()-1; i++) {
            dataFromTable[i-1]= tableColums.get( i-1 ).getText();
        }
        assertEquals(dataFromMass,dataFromTable);
    }

    @Step("Редактирвоание документа")
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
        newPasswordTB.sendKeys( newPassword );
    }
}
