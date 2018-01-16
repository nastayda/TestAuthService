package FilterAndSort;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class Sort extends BaseClass {
    @FindBy(xpath = "//thead[@class='ant-table-thead']/tr/th/span/div/span[2]/i")
    //*[@id="root"]/div/div[2]/div[2]/div/div/div/div/div/div/div[1]/table/thead/tr/th[2]/span/div/span[1]/i
            List<WebElement> downArrow;
    @FindBy(xpath = "//*[@id=\"root\"]//table/thead/tr/th/span/div/span[1]/i")
    List<WebElement> upArrow;
    @FindBy(xpath = "//*[@id=\"root\"]//table/tbody/tr/td")
    List<WebElement> tableCol;
    //*[@id="root"]//table/thead/tr/th
    @FindBy(xpath = "//*[@id=\"root\"]//table/thead/tr/th")
    List<WebElement> filter;
    @FindBy(xpath = "//*[@id=\"root\"]//table/tbody/tr")
    List<WebElement> tableRow;

    public void sort( ) {
        login( );
        resizeWindow( 1920, 1080 );
        sortByAlphabet( );
    }

    public void sortByAlphabet( ) {
       /* for (int i = 1; i < downArrow.size( ); i++) {
            downArrow.get( i ).click( );
            waitSomeMillisec( 1000 );
            upArrow.get( i ).click( );
            waitSomeMillisec( 1000 );
        }*/
        for (int i = 0; i < tableCol.size( )/tableRow.size(); i++) {
            for (int j = i; j < tableCol.size( ); j=j+tableCol.size( )/tableRow.size()) {
                System.out.println( tableCol.get( j ).getText( ) );
            }
            System.out.println("---");
        }
    }
}
