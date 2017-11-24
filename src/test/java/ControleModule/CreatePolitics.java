package ControleModule;

import HelpClass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class CreatePolitics extends BaseClass {
    @FindBy(xpath = "//div[@class='ant-modal-footer']//button[2]")
    static WebElement saveBTN;
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[1]/div/div[1]/span[3]/button")
    static WebElement addBTN;
    @FindBy(id = "serviceName")
    static WebElement chooseINPT;
    @FindBy(id = "subjectParameter-0")
    static WebElement parametrINPT0;
    @FindBy(id = "subjectValue-0")
    static WebElement valueINPT0;
    @FindBy(id = "policyName")
    static WebElement policyNameINPT;
    @FindBy(id = "policyDescription")
    static WebElement policyDescriptionINPT;
    @FindBy(css = "li.ant-select-dropdown-menu-item-active.ant-select-dropdown-menu-item")
    static WebElement dropdownMenuService;
    @FindBy(css = "ul.ant-select-selection__rendered")
    static WebElement menuItem;
    

    @Test
    public void createPolitics( ) {
        login( );
        wd.navigate( ).to( getDataFromFile( "src/help-files/auth-info.txt" )[ 3 ] );
        wd.get( "http://vm-auth-dev.ursip.ru/policy-manager/" );
        //"Клик на кнопку Добавить политику"
        addBTN.click( );
        //"Заполнить название политики"
        policyNameINPT.click( );
        policyNameINPT.clear( );
        policyNameINPT.sendKeys( "testPoliticy2017" );
        //"Заполнить описание"
        policyDescriptionINPT.click( );
        policyDescriptionINPT.clear( );
        policyDescriptionINPT.sendKeys( "test description" );

        //"Choese service"
        chooseINPT.click();
        dropdownMenuService.click( );
        menuItem.click( );

        //Выбрать настройку
        wd.findElement( By.cssSelector( "span.ant-select-tree-switcher.ant-select-tree-switcher_close" ) ).click( );
        wd.findElement( By.xpath( "//ul[@class='ant-select-tree']/li[1]/ul/li[2]/span[2]/span" ) ).click( );
        wd.findElement( By.cssSelector( "ul.ant-select-selection__rendered" ) ).click( );

        //Выбрать параметр
        parametrINPT0.click();
        parametrINPT0.sendKeys( "Фамилия" );

        //Знак
        wd.findElement( By.xpath( "//div[@class='ant-modal-body']/form/div[4]/div/div[2]/div[2]/div/div/div/div" ) ).click();
         WebElement elementSign = wd.findElement(By.xpath("//div[4]/div/div/div/ul/li[1]"));
        ((JavascriptExecutor)wd).executeScript("arguments[0].click();", elementSign);

        //Значение
        //input[@id='subjectValue-0']
        valueINPT0.click();
        valueINPT0.sendKeys( "123" );
       
        //+
        wd.findElement( By.xpath( "//div[@class='ant-modal-body']/form/div[4]/div/div[4]/div[2]/div/div/div/span" ) ).click( );
        WebElement elementCondition = wd.findElement( By.xpath( "//div[6]/div/div/div/ul/li[1]" ) );
        ((JavascriptExecutor)wd).executeScript("arguments[0].click();", elementCondition);

        //Параметр
        wd.findElement( By.id( "subjectParameter-1" ) ).click( );
        wd.findElement( By.cssSelector( "li.ant-select-dropdown-menu-item-active.ant-select-dropdown-menu-item" ) ).click( );
        //Знак
        wd.findElement( By.xpath( "//div[@class='ant-modal-body']/form/div[5]/div/div[2]/div[2]/div/div/div/div" ) ).click( );
        wd.findElement( By.cssSelector( "li.ant-select-dropdown-menu-item-active.ant-select-dropdown-menu-item" ) ).click( );
        //Значение
        wd.findElement( By.id( "subjectValue-1" ) ).click( );
        wd.findElement( By.cssSelector( "li.ant-select-dropdown-menu-item-active.ant-select-dropdown-menu-item" ) ).click( );

        saveBTN.click( );
    }
}
