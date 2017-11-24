package Politics;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;

import java.time.LocalDateTime;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class EditPolitics extends CreatePolitics {
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[1]/div/div[1]/span[4]/button")
    static WebElement editBTN;
    @FindBy(xpath = "//table/tbody/tr[last()]/td[2]")
    static WebElement politicsNameRow;
    @FindBy(id = "subjectParameter-0")
    static WebElement parametrINPT0;
    @FindBy(id = "subjectValue-0")
    static WebElement valueINPT0;
    @FindBy(xpath = "//table/tbody/tr[last()]/td")
    static List<WebElement> tableColums;

    @Test
    public void editPolitics( ) {
        login( );
        goToPolicyPage( );
        goToLastPage( );
        String politicsName = "editPolitics" + LocalDateTime.now( ).toString( ).replace( ":", "_" );
        String[] massData = { "Имя", "Алексей", politicsName, "edited description", "Управление политиками"};
        //Заполнить первую строку
        editNewPolitics( massData );
        compareDataFromTable(massData,politicsName);
    }
    @Step("Сравнние введенных данных и тех, что отображаются в таблице")
    public void compareDataFromTable( String[] dataForEdite , String politicsName) {
        goToLastPage( );
        //Выбрать только то, что отображается в таблице
        String[] dataFromMass = { politicsName, dataForEdite[ 3 ], dataForEdite[ 4 ] };
        //Выбрать из таблицы то, с чем будем сравнивать
        String[] dataFromTable = new String[ tableColums.size( ) - 3 ];
        for (int i = 1; i < tableColums.size( ) - 2 ; i++) {
            dataFromTable[ i - 1 ] = tableColums.get( i ).getText( );
        }
        assertEquals( dataFromMass, dataFromTable );
    }

    @Step("Редактирование политики")
    public void editNewPolitics( String[] massData ) {
        politicsNameRow.click( );
        editBTN.click( );
        choseParameter( massData[0], parametrINPT0 );
        choseSign( );
        setMeaning( massData[1], valueINPT0 );
        setPoliticsName( massData[2] );
        setDescription( massData[3] );
        setServiceName( massData[4] );
        chooseSetting( );
        clickSave( );
    }
}
