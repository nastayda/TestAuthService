package Users;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.time.LocalDateTime;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class EditUser extends BaseClass {
    @FindBy(xpath = "//table/tbody/tr[last()]/td")
    static List<WebElement> tableColums;

    //*[@id="root"]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[8]/td[5]
    //*[@id="root"]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr[11]/td[4]
    @FindBy(xpath = "//*[@id=\"root\"]//table/tbody/tr[last()]/td[5]")
    static WebElement userLoginRow;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div/div[1]/div/div[2]/span[3]/button")
    static WebElement editBTN;
    //div[@class='ant-modal-body']/form/div[2]/div[2]/div/input
    ////div[2]/div/div[2]/div/div[1]/div[2]/form/div[2]/div[2]/div/input
    @FindBy(xpath = "//div[@class='ant-modal-body']/form/div[2]/div[2]/div/input")
    static WebElement newPasswordTB;
//*[@id="root"]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]

    @Test
    @Title("Редактирование пользователя")
    public void editUser( ) {
        //login( );
        PageFactory.initElements( wd, this );
        wd.navigate( ).refresh( );
        resizeWindow( 1920, 2000 );
        String loginName = "editUser" + LocalDateTime.now( ).toString( ).replace( ":", "_" );
        String[] dataForEdite = { loginName,
                "test1",
                "Testovsky",
                "Testky",
                "Testovichky",
                "testky@test.ru",
                "+70000000000",
                "Testerer",
                "TestType1",
                "TestName1",
                "TestSubunit1",
                "TestGroup1"
        };
        editUserToEdited( dataForEdite );
        compareDataFromTable( dataForEdite, loginName );
    }

    @Step("Сравнние введенных данных и тех, что отображаются в таблице")
    public void compareDataFromTable( String[] dataForEdit, String loginName ) {
        //Выбрать только то, что отображается в таблице
        String[] dataFromMass = { dataForEdit[ 2 ], dataForEdit[ 3 ], dataForEdit[ 4 ], loginName, dataForEdit[ 6 ], dataForEdit[ 5 ], dataForEdit[ 7 ] };
        wd.navigate( ).refresh( );
        //Выбрать из таблицы то, с чем будем сравнивать
        String[] dataFromTable = new String[ tableColums.size( ) - 1 ];
        for (int i = 1; i < tableColums.size( ); i++) {
            dataFromTable[ i - 1 ] = tableColums.get( i ).getText( );
        }
        assertEquals( dataFromMass, dataFromTable );
    }

    @Step("Редактирвоание пользователя")
    private void editUserToEdited( String[] dataForEdit ) {
        waitSomeMillisec( 1000 );
        resizeAreaWithTable( "//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]" );
        waitSomeMillisec( 1000 );
        userLoginRow.click( );
        System.out.println( userLoginRow.getText( ) );
        waitSomeMillisec( 1000 );
        editBTN.click( );
        setLogin( dataForEdit[ 0 ] );
        setNewPasswordTB( dataForEdit[ 1 ] );
        setSurname( dataForEdit[ 2 ] );
        setFirstName( dataForEdit[ 3 ] );
        setMiddleName( dataForEdit[ 4 ] );
        setEmail( dataForEdit[ 5 ] );
        setPhoneNumber( dataForEdit[ 6 ] );
        setRole( dataForEdit[ 7 ] );
        setOrgType( dataForEdit[ 8 ] );
        setOrgName( dataForEdit[ 9 ] );
        setOrgSubunit( dataForEdit[ 10 ] );
        setOrgGroup( dataForEdit[ 11 ] );
        clickToSave( );
    }

    @Step(" Ввод пароля {0}")
    public void setNewPasswordTB( String newPassword ) {
        newPasswordTB.click( );
        newPasswordTB.clear( );
        newPasswordTB.sendKeys( newPassword );
    }
}
