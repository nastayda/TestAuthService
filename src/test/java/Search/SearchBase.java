package Search;

import HelpClass.BaseClass;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
    List<WebElement> tableHeader;
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr")
    List<WebElement> tableRow;

    //Туповатая идея(других пока нет): определить какие есть элементы в таблице(по заголовкам),
    // если такие же элементы есть в списке для поиска, то получаем текст первый не пустой
    // и вставляем его по выбранному критерию поиска в текстовый блок
    // ИДЕЯ 2: открыть форму посмотреть какие есть поля и искать эти поля в списке... - наверное будет круче так сделать!
    public void search( ) {
        login( );
        //Разбить полученную строку на массив и из полученного массива получить нужный элемент
        System.out.println( Arrays.asList( tableRow.get( 0 ).getText( ).split( " " ) ).get( 0 ) );
        /*menu.click( );
        waitSomeMillisec( 500 );
        for (int i = 0; i < menuPoint.size( ); i++) {
            for (int j =0; j< tableHeader.size(); j++){
                if (menuPoint.get( i ).getText().equals( tableHeader.get( j ).getText() )){
                    tableRow.get( j ).getText();
                }
            }
            System.out.println( "Text i=" + i + " " + menuPoint.get( i ).getText( ) );
        }
        menuPoint.get( 6 ).click( );
        serachAreaTB.click( );
        serachAreaTB.clear( );
        serachAreaTB.sendKeys( "test" );*/
    }
}
