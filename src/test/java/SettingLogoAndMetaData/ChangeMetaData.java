package SettingLogoAndMetaData;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ChangeMetaData extends BaseClass {
    @FindBy(xpath = "//div/div/div[1]/div/div[2]/div/div/div[1]/div/button")
    WebElement settingsBTN;
    //li.ant-dropdown-menu-item-active.ant-dropdown-menu-item
    //@FindBy(css = "li.ant-dropdown-menu-item-active.ant-dropdown-menu-item")
    @FindBy(xpath = "//div[2]/div/div/ul/li[2]")
    WebElement metaDataBTN;

    //div[3]/div/div[2]/div/div[1]/div[2]/form/div[3]/div[2]/div/input
    @FindBy(xpath = "//div[3]/div/div[2]/div/div[1]/div[2]/form/div/div[2]/div/input")
    List<WebElement> elementsINPT;

    @FindBy(xpath = "//div[3]/div/div[2]/div/div[1]/div[3]/div/button[2]")
    WebElement saveBTN;

    @FindBy(xpath = "//div/div/div[1]/div/div[1]/div")
    WebElement placeNearBTNSettings;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div/div[1]/div/div[2]/span[1]/span/span/span[1]/div/div/div/div")
    //@FindBy(xpath = "//div[@class='ant-select-selection__rendered']")
            WebElement menu;

    @FindBy(xpath = "//ul[@class='ant-select-dropdown-menu ant-select-dropdown-menu-vertical  ant-select-dropdown-menu-root']/li")
    static List<WebElement> menuPoint;

    //div[3]/div/div[2]/div/div[1]/div[2]/form/div[2]/div[2]/div/div[1]/label
    //div[3]/div/div[2]/div/div[1]/div[2]/form/div[2]/div[1]/div/div[1]/label
    //div[3]/div/div[2]/div/div[1]/div[2]/form/div[3]/div[1]/div/div[1]/label
    //div[3]/div/div[2]/div/div[1]/div[2]/form/div[]/div[]/div/div[1]/label
    @FindBy(xpath = "//label")
    List<WebElement> allLables;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div/div[1]/div/div[2]/span[2]/button")
    WebElement addBTN;

    //div[4]/div/div[2]/div/div[1]/div[3]/div/button[1]
    //@FindBy(xpath = "//div[@class='ant-modal-footer']//button[1]")
    @FindBy(xpath = "//div[4]/div/div[2]/div/div[1]/div[3]/div/button[1]")
    WebElement cancelBTN;

    @FindBy(xpath = "//div[4]/div/div[2]/div/div[1]/div[2]/form/div[1]/div[2]/div/div/div/div/ul/li/div/input")
    WebElement loginINPT;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div/div[1]/div/div[2]/span[1]/span/span/span[2]/input")
    WebElement searchBTN;

    @Test
    @Title("Проверка изменения метаданных")
    public void changeMeta( ) {
        PageFactory.initElements( wd, this );

        //Лист с элементами из до изменения
        ArrayList<String> elementsList = new ArrayList<>( );

        //Нажать на меню вызова окна с метаданными
        clickChangeMetadataAll( );

        //Лист после изменения
        ArrayList<String> afterChangeList = new ArrayList<>( );

        //Добавим все элементы в лист до изменения и после изменения
        for (int i = 0; i < elementsINPT.size( ); i++) {
            elementsList.add( elementsINPT.get( i ).getAttribute( "value" ) );
            afterChangeList.add( elementsINPT.get( i ).getAttribute( "value" ) + "Test" );
        }
        //Изменим метаданные
        changeMetadataText( elementsList, "Test" );

        //Запишем все элементы с формы
        ArrayList<String> elementsFromForm1 = elementsFromForm( );
        //assertTrue( getListOfMenuPoint( ).containsAll( afterChangeList ) );
        //Элементы с формы содержат лист элементов после изменения
        softAssert.assertTrue( elementsFromForm1.containsAll( afterChangeList ) , "Проверка изменения метаданных провалена. Данные на форме изсенены не полностью.");

        //Нажать на меню вызова окна с метаданными
        clickChangeMetadataAll( );
        changeMetadataText( elementsList, "" );
        softAssert.assertAll( );
    }

    @Step("Получить все данные с формы")
    public ArrayList<String> elementsFromForm( ) {
        clickWithExpects( searchBTN );
        ArrayList<String> elementsFromForm = new ArrayList<>( );
        clickWithExpects( addBTN );
        waitSomeTime( 2000 );
        for (WebElement item : allLables) {
            elementsFromForm.add( item.getText( ) );
        }
        //clickWithExpects( loginINPT);
        clickWithExpects( cancelBTN );
        clickWithExpects( searchBTN );
        return elementsFromForm;
    }

    @Step("Изменить метаданные")
    public void changeMetadataText( ArrayList<String> elementsList, String paramForChange ) {
        for (int i = 0; i < elementsINPT.size( ); i++) {
            //Вставка в поле измененного значения
            clickWithExpects( elementsINPT.get( i ) );
            elementsINPT.get( i ).clear( );
            elementsINPT.get( i ).sendKeys( elementsList.get( i ) + paramForChange );
        }
        //Сохранение
        clickWithExpects( saveBTN );
    }

    @Step("Вызвать окно с метаданными")
    public void clickChangeMetadataAll( ) {
        clickWithExpects( placeNearBTNSettings );
        clickWithExpects( settingsBTN );
        clickWithExpects( metaDataBTN );
    }
}
