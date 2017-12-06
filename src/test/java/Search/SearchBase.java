package Search;

import HelpClass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class SearchBase extends BaseClass {
    @FindBy(css = "div.ant-select-selection__rendered")
    WebElement menu;
    @FindBy(xpath = "//div[2]/div/div/div/ul/li")
    List<WebElement> menuPoint;
    @FindBy(xpath = "//span[@class='ant-input-group-wrapper']/span/input")
    WebElement serachAreaTB;
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[2]/div/div/div/div/div/div/div[1]/table/thead/tr/th")
    List<WebElement> tableHeads;

    public void search( ) {
        login( );
        menu.click( );
        waitSomeMillisec(  500 );
        for (int i =0; i < menuPoint.size(); i++){
            System.out.println("Text i="+i+" " + menuPoint.get( i ).getText() );
        }
        menuPoint.get( 6 ).click( );
        serachAreaTB.click( );
        serachAreaTB.clear( );
        serachAreaTB.sendKeys( "test" );
    }

}
