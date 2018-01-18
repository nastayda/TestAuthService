package FilterAndSort;

import HelpClass.BaseClass;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.List;

import static org.apache.http.util.TextUtils.isEmpty;

public class Filter extends BaseClass {
    //Проблдема с суммированием количествоа потомков списка, поч после закрытия подменю не чистится страница я хз.
    // Как вариант решения на кажном шаге цикла запоминать количество и затем вычитать его из текущего, но мне не нравится это вариант...
    //*[@id="root"]/div/div[2]/div[2]/div/div[1]/div/div/div/div/div[1]/table/thead/tr/th[3]/span/i
    //*[@id="root"]/div/div[2]/div[2]/div/div[1]/div/div/div/div/div[1]/table/thead/tr/th[4]/span/i
    //*[@id="root"]/div/div[2]/div[2]/div/div[1]/div/div/div/div/div[1]/table/thead/tr/th[8]/span/i
    @FindBy(xpath = "//*[@id=\"root\"]//table/thead/tr/th")
    List<WebElement> headers;
    //*[@id="root"]/div/div[2]/div[2]/div/div[1]/div/div/div/div/div[1]/table/thead/tr/th
    @FindBy(xpath = "//thead[@class='ant-table-thead']/tr/th/span/i")
    List<WebElement> filter;
    //div[@class='ant-table-filter-dropdown']/ul/li[2]/label/span/input
    //*[@id="root"]/div/div[2]/div[2]/div/div[2]/div/div/div/ul/li[1]
    //*[@id="root"]/div/div[2]/div[2]/div/div[3]/div/div/div/ul/li[1]
    //div[@class='ant-table-filter-dropdown']/ul/li[2]/label/span/input
    @FindBy(xpath = "//div[@class='ant-table-filter-dropdown']/ul/li")
    List<WebElement> meaningFilterCHBX;
    @FindBy(xpath = "//div[@id='root']/div/div[2]/div[1]/div")
    WebElement spacePage;
    //*[@id="root"]/div/div[2]/div[2]/div/div[2]/div/div/div/div/a[1]
    //*[@id="root"]/div/div[2]/div[2]/div/div[2]/div/div/div/div/a[1]
    @FindBy(xpath = "//div[@class='ant-table-wrapper']//a[1]")
    WebElement okBTN;
    @FindBy(xpath = "//*[@id=\"root\"]//table/tbody/tr")
    private List<WebElement> tableRow;
    @FindBy(xpath = "//*[@id=\"root\"]//table/tbody/tr/td")
    private List<WebElement> tableCol;

    @Title("Проверка фильтрации")
    @Test
    @Step("Вызов фильтрации")
    public void applyFilterTest( ) {
        login( );
        resizeWindow( 1920, 1080 );
        //System.out.println(filter.size() );
        applyFilter( );
        softAssert.assertAll( );
    }

    int index = 0, indexCur = 0, indexPre = 0;
    public void applyFilter( ) {
        //Внешний цикл по колонкам
        for (int i = 0; i < filter.size( ); i++) {
            filter.get( i ).click( );
            //Внутренний цикл по значениям фильтра
            if (i == 0) {
                index = 0;
                indexPre = meaningFilterCHBX.size( );
                indexCur = indexPre;
            } else {
                indexCur = meaningFilterCHBX.size( );
                index = indexCur - indexPre + 1;
                indexPre = indexCur;
            }
            for (int j = index; j < indexCur; j++) {
                if (!isEmpty( meaningFilterCHBX.get( j ).getText( ) )) {
                    String criterion = meaningFilterCHBX.get( j ).getText( );
                    int countBefore = countByCriterion( criterion, i + 1 );
                    meaningFilterCHBX.get( j ).click( );
                    spacePage.click( );
                    int countAfter = tableRow.size( );
                    checkCountInTAbleAfterAppyFilter( criterion, countBefore, countAfter, headers.get( i + 1 ).getText( ) );
                    break;
                }
            }
        }
    }

    @Step("Проверка количества по параметру {3} и значению {0}")
    public void checkCountInTAbleAfterAppyFilter( String criterion, int countBefore, int countAfter, String filterParam ) {
        softAssert.assertEquals( countBefore, countAfter,
                "По фильтру " + filterParam + " по критерию " + criterion );
    }

    private int countByCriterion( String searchCriterion, int startJ ) {
        int k = 0;
        for (int j = startJ; j < tableCol.size( ); j = j + filter.size( ) + 1) {
            if (tableCol.get( j ).getText( ).contains( searchCriterion )) {
                k++;
            }
        }
        return k;
    }
}
