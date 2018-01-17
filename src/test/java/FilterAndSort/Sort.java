package FilterAndSort;

import HelpClass.BaseClass;
import com.google.common.collect.Lists;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.jvnet.jaxb2_commons.lang.StringUtils.isEmpty;
import static org.testng.AssertJUnit.assertEquals;

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
        softAssert.assertAll();

    }

    public static void sortList( ArrayList<String> aItems ) {
        Collections.sort( aItems, String.CASE_INSENSITIVE_ORDER );
    }

    public void sortByAlphabet( ) {
        for (int i = 0; i < downArrow.size( ); i++) {
            //По убыванию
            downArrow.get( i ).click( );
            waitSomeMillisec( 1000 );
            //с какой колонки начинаем
            for (int k = i + 1; k < i + 2; k++) {
                List<String> sortedByInterface = createSortedByInterfaceList( k );
                ArrayList<String> sortedByRule = new ArrayList<>( sortedByInterface );
                //сортировка по алфавиту
                sortList( sortedByRule );
                //реверсия т.к. тут нажат спан по убыванию
                List<String> reverseOrder = Lists.reverse( sortedByRule );
                softAssert.assertEquals( reverseOrder, sortedByInterface, "Проверка по убыванию провалилась." );
            }
            //По возрастанию
            upArrow.get( i ).click( );
            for (int k = i + 1; k < i + 2; k++) {
                List<String> sortedByInterface = createSortedByInterfaceList( k );
                ArrayList<String> sortedByRule = new ArrayList<>( sortedByInterface );
                //сортировка по алфавиту
                sortList( sortedByRule );
                softAssert.assertEquals( sortedByRule, sortedByInterface, "Проверка по возрастанию провалилась." );
            }
        }
    }

    public List<String> createSortedByInterfaceList( int k ) {
        List<String> sortedByInterface = new ArrayList<>( );
        //какой конкретно эелемент брать
        for (int j = k; j < tableCol.size( ); j = j + tableCol.size( ) / tableRow.size( )) {
            if (!isEmpty( tableCol.get( j ).getText( ) )) {
                sortedByInterface.add( tableCol.get( j ).getText( ) );
            }
        }
        return sortedByInterface;
    }
}
