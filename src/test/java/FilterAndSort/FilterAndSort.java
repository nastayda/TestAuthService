package FilterAndSort;

import HelpClass.BaseClass;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import java.util.List;

import static org.apache.http.util.TextUtils.isEmpty;

@Test
public class FilterAndSort extends BaseClass {
    //*[@id="root"]/div/div[2]/div[2]/div/div[1]/div/div/div/div/div[1]/table/thead/tr/th[3]/span/i
    //*[@id="root"]/div/div[2]/div[2]/div/div[1]/div/div/div/div/div[1]/table/thead/tr/th[4]/span/i
    //*[@id="root"]/div/div[2]/div[2]/div/div[1]/div/div/div/div/div[1]/table/thead/tr/th[8]/span/i
    @FindBy(xpath = "//thead[@class='ant-table-thead']/tr/th/span/i")
    List <WebElement> filter;
    //div[@class='ant-table-filter-dropdown']/ul/li[2]/label/span/input
    //*[@id="root"]/div/div[2]/div[2]/div/div[2]/div/div/div/ul/li[1]
    //*[@id="root"]/div/div[2]/div[2]/div/div[3]/div/div/div/ul/li[1]
    //div[@class='ant-table-filter-dropdown']/ul/li[2]/label/span/input
    @FindBy(xpath = "//div[@class='ant-table-filter-dropdown']/ul/li")
    List<WebElement> meaningFilterCHBX;
    //*[@id="root"]/div/div[2]/div[2]/div/div[2]/div/div/div/div/a[1]
    //*[@id="root"]/div/div[2]/div[2]/div/div[2]/div/div/div/div/a[1]
    @FindBy(xpath = "//div[@class='ant-table-wrapper']//a[1]")
    WebElement okBTN;
    @FindBy(xpath = "//*[@id=\"root\"]//table/tbody/tr")
    private List<WebElement> tableRow;
    @FindBy(xpath = "//*[@id=\"root\"]//table/tbody/tr/td")
    private List<WebElement> tableCol;

    public void applyFilterTest( ) {
        login( );
        // Развернуть окно до размеров 1920 х 1080, чтобы добраться до последнего элемента
        wd.manage().window().setSize( new Dimension( 1920, 1080 ) );
        //System.out.println(filter.size() );
        applyFilter( );
    }

    public void applyFilter( ) {
        //Внешний цикл по колонкам
        for (int i = 0; i < filter.size( ); i++) {
            filter.get( i ).click();
            //Внутренний цикл по значениям фильтра
            for (int j = 0; j < meaningFilterCHBX.size( ); j++) {
                if (!isEmpty(meaningFilterCHBX.get( j ).getText())){
                    System.out.println( meaningFilterCHBX.get( j ).getText() );
                    int countBefore = countByCriterion(meaningFilterCHBX.get( j ).getText(), j);
                    meaningFilterCHBX.get( j ).click();
                    waitSomeMillisec( 1000 );
                    okBTN.click();
                    int countAfter = tableRow.size();
                    softAssert.assertEquals( countBefore, countAfter );
                    j=meaningFilterCHBX.size( );
                }
            }
        }
    }

    private int countByCriterion( String searchCriterion, int startJ ) {
        int k = 0;
        for (int j = startJ; j < tableCol.size( ); j = j + filter.size( )+1) {
            if (tableCol.get( j ).getText( ).contains( searchCriterion )) {
                k++;
            }
        }
        return k;
    }
}
