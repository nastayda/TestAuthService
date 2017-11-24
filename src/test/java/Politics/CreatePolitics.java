package Politics;

import HelpClass.BaseClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;

import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;

public class CreatePolitics extends BaseClass {
    ///html/body/div[2]/div/div[2]/div/div[1]/div[3]/div/button[2]
    @FindBy(xpath = "//div[2]/div/div[2]/div/div[1]/div[3]/div/button[2]")
    static WebElement saveBTN;
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[1]/div/div[1]/span[3]/button")
    static WebElement addBTN;
    @FindBy(xpath = "//ul[@class='ant-select-tree']/li[1]/ul/li[2]/span[2]/span")
    static WebElement expandMenu;
    @FindBy(xpath = "//div[@class='ant-modal-body']/form/div[5]/div/div[2]/div[2]/div/div/div/div")
    static WebElement dropdownMenuSign1;
    @FindBy(xpath = "//div[@class='ant-modal-body']/form/div[4]/div/div[2]/div[2]/div/div/div/div")
    static WebElement dropdownMenuSign;
    @FindBy(xpath = "//div[4]/div/div/div/ul/li[1]")
    static WebElement elementSign;
    @FindBy(xpath = "//div[@class='ant-modal-body']/form/div[4]/div/div[4]/div[2]/div/div/div/span")
    static WebElement dropdownMenuCondition;
    @FindBy(xpath = "//div[6]/div/div/div/ul/li[1]")
    static WebElement elementCondition;
    @FindBy(id = "serviceName")
    static WebElement chooseINPT;
    @FindBy(id = "subjectParameter-0")
    static WebElement parametrINPT0;
    @FindBy(id = "subjectValue-0")
    static WebElement valueINPT0;
    @FindBy(id = "subjectParameter-1")
    static WebElement parametrINPT1;
    @FindBy(id = "subjectValue-1")
    static WebElement valueINPT1;
    @FindBy(id = "policyName")
    static WebElement policyNameINPT;
    @FindBy(id = "policyDescription")
    static WebElement policyDescriptionINPT;
    @FindBy(css = "li.ant-select-dropdown-menu-item-active.ant-select-dropdown-menu-item")
    static WebElement dropdownMenuService;
    @FindBy(css = "ul.ant-select-selection__rendered")
    static WebElement menuItem;
    @FindBy(css = "span.ant-select-tree-switcher.ant-select-tree-switcher_close")
    static WebElement chooseServiceSetting;
    @FindBy(xpath = "//table/tbody/tr[last()]/td[2]")
    static WebElement lastRow;

    @Test
    public void createPolitics( ) {
        login( );
        goToPolicyPage( );
        String politicsName = "testPolitics" + LocalDateTime.now( ).toString( ).replace( ":", "_" );
        createNewPolitics( politicsName, "Фамилия", "123", "test description", "Авторизация" );
        goToLastPage( );
        assertEquals( politicsName, lastRow.getText( ) );
    }

    public void createNewPolitics( String politicsName, String parameter, String meaning, String description, String serviceName ) {
        clickToAddBtn( );
        //Заполнить первую строку
        choseParameter( parameter, parametrINPT0 );
        choseSign( );
        setMeaning( meaning, valueINPT0 );
        /*chooseCondition( );
        //Заполнить вторую строку
        choseParameter( "Фамилия", parametrINPT1 );
        choseSign( );
        setMeaning( "123", valueINPT1 );*/

        setPoliticsName( politicsName );
        setDescription( description );
        setServiceName( serviceName );
        chooseSetting( );
        clickSave( );
    }

    @Step("Сохранить")
    public void clickSave( ) {
        saveBTN.click( );
    }

    @Step("Выбрать настройку {0}")
    public void chooseSetting( ) {
        menuItem.click( );
        chooseServiceSetting.click( );
        expandMenu.click( );
        policyDescriptionINPT.click( );
    }

    @Step("Заполнить название сервиса {0}")
    public void setServiceName( String serviceName ) {
        chooseINPT.click( );
        chooseINPT.clear( );
        chooseINPT.sendKeys( serviceName );
    }

    @Step("Заполнить описание {0}")
    public void setDescription( String decription ) {
        policyDescriptionINPT.click( );
        policyDescriptionINPT.clear( );
        policyDescriptionINPT.sendKeys( decription );
    }

    @Step("Заполнить название политики {0}")
    public void setPoliticsName( String politicsName ) {
        policyNameINPT.click( );
        policyNameINPT.clear( );
        policyNameINPT.sendKeys( politicsName );
    }

    @Step("Условие И/ИЛИ")
    public void chooseCondition( ) {
        //Сделать активным выпадающее меню
        dropdownMenuCondition.click( );
        //Выполнить скрипт
        ( (JavascriptExecutor) wd ).executeScript( "arguments[0].click();", elementCondition );
    }

    @Step("Установить значение {0}")
    public void setMeaning( String meaning, WebElement valueINPT ) {
        valueINPT.click( );
        valueINPT.sendKeys( meaning );
    }

    @Step("Выбрать знак")
    public void choseSign( ) {
        dropdownMenuSign.click( );
        ( (JavascriptExecutor) wd ).executeScript( "arguments[0].click();", elementSign );
    }

    @Step("Выбрать параметр {0}")
    public void choseParameter( String parameter, WebElement parametrINPT ) {
        parametrINPT.click( );
        parametrINPT.clear( );
        parametrINPT.sendKeys( parameter );
    }

    @Step("Клик на кнопку Добавить политику")
    public void clickToAddBtn( ) {
        addBTN.click( );
    }
}
