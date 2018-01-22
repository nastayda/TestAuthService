package Politics;

import HelpClass.BaseClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;

public class CreatePolitics extends BaseClass {
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[1]/div/div[1]/span[3]/button")
    static WebElement addBTN;
    @FindBy(xpath = "//*[@id=\"2\"]/div/div[2]/div/div/div")
    static WebElement dropdownMenuCondition;
    @FindBy(xpath = "//div[6]/div/div/div/ul/li[1]")
    static WebElement elementCondition;
    @FindBy(id = "subjectParameter-0")
    static WebElement parametrINPT0;
    @FindBy(id = "subjectValue-0")
    static WebElement valueINPT0;
    @FindBy(xpath = "//table/tbody/tr[last()]/td[2]")
    static WebElement lastRow;
    @FindBy(xpath = "//div[@class='adding-object-block']/i")
    static WebElement plus;

    @Test
    @Title("Создание политики")
    public void createPolitics( ) {
        //login( );
        PageFactory.initElements(wd, this);
        goToPolicyPage( );
        String politicsName = "testPolitics" + LocalDateTime.now( ).toString( ).replace( ":", "_" );
        createNewPolitics( politicsName, "Фамилия", "123", "test description" );
        goToLastPage( );
        assertEquals( politicsName, lastRow.getText( ) );
    }

    public void createNewPolitics( String politicsName, String parameter, String meaning, String description ) {
        clickToAddBtn( );

        plusClick( );
        //Заполнить информацию о субъекте
        choseParameter( parameter, parametrINPT0 );
        choseSign( );
        setMeaning( meaning, valueINPT0 );

        //заполенение информации о модулее
        setServiceName( );

        setPoliticsName( politicsName );
        setDescription( description );

        clickSave( );
    }

    @Step("Добавить субъект")
    public void plusClick( ) {
        plus.click( );
    }

    @Step("Условие И/ИЛИ")
    public void chooseCondition( ) {
        //Сделать активным выпадающее меню
        dropdownMenuCondition.click( );
        //Выполнить скрипт
        ( (JavascriptExecutor) wd ).executeScript( "arguments[0].click();", elementCondition );
    }

    @Step("Клик на кнопку Добавить политику")
    public void clickToAddBtn( ) {
        addBTN.click( );
    }
}
