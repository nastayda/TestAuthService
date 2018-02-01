package SettingLogoAndMetaData;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.ArrayList;
import java.util.List;

public class ChangeMetaData extends BaseClass {
    @FindBy(xpath = "//div/div/div[1]/div/div[2]/div/div/div[1]/div/button")
    WebElement settingsBTN;

    @FindBy(xpath = "//div[2]/div/div/ul/li[2]")
    WebElement metaDataBTN;
    //div[3]/div/div[2]/div/div[1]/div[2]/form/div[3]/div[2]/div/input
    @FindBy(xpath = "//div[3]/div/div[2]/div/div[1]/div[2]/form/div/div[2]/div/input")
    List<WebElement> elementsINPT;

    @FindBy(xpath = "//div[3]/div/div[2]/div/div[1]/div[3]/div/button[2]")
    WebElement saveBTN;

    @Test
    @Title("Проверка изменения метаданных")
    public void changeMeta( ) {
        PageFactory.initElements( wd, this );
        clickWithExpects( settingsBTN );
        clickWithExpects( metaDataBTN );
        ArrayList<String> elementsList = new ArrayList<>( );
        for (int i = 0; i < elementsINPT.size( ); i++) {
            //System.out.println( elementsINPT.get( i ).getAttribute( "value" ) );
           /* elementsList.add( elementsINPT.get( i ).getAttribute( "value" ) );
            clickWithExpects( elementsINPT.get( i ) );
            elementsINPT.get( i ).clear( );
            elementsINPT.get( i ).sendKeys( elementsList.get( i )+"Test" );*/
        }
        elementsList.add( elementsINPT.get( 0 ).getAttribute( "value" ) );
        clickWithExpects( elementsINPT.get( 0 ) );
        elementsINPT.get( 0 ).clear( );
        elementsINPT.get( 0 ).sendKeys( elementsList.get( 0 ) + "Test" );
        clickWithExpects( saveBTN );
    }

}
