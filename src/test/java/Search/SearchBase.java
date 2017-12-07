package Search;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class SearchBase extends BaseClass {
    @FindBy(xpath = "//div[@class='ant-select-selection-selected-value']")
    WebElement menu;
    @FindBy(xpath = "//div[3]/descendant::li")
    List<WebElement> menuPoint;
    @FindBy(xpath = "//span[@class='ant-input-group-wrapper']/span/input")
    WebElement serachAreaTB;
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/table/tbody/tr")
    List<WebElement> tableRow;
    @FindBy(xpath = "//div[@class='ant-modal-body']/form/div/descendant::label")
    List<WebElement> labelList;
    @FindBy(xpath = "//*[@id=\"authorization\"]//table/thead/tr/th")
    List<WebElement> tableHeader;
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[1]/div/span[3]/button")
    WebElement addBTN;
    ///html/body/div[3]/div/div[2]/div/div[1]/div[3]/div/button[1]
    @FindBy(xpath = "//div[@class='ant-modal-footer']//button[1]")
    WebElement cancelBTN;
    //Суть проверки по шагам:
    // 1. Открыть форму и посмотреть какие есть критерии посика+
    // 2. Открыть список критериев поиска на странице и посмотреть какие есть критерии там+
    // 3. Получить критерии поиска по заголовкам таблицы+
    // 4. Сравнить массив криетриев со страниц и массив криетриев из заголовoк таблицы
    // 5. Если совпали! классно - берем первый непустой критерий из таблицы и осуществляем поиск
    // 6. Иначе сравниваем список со странице со списком из формы и если совпал список,
    // то придется половину взять из заголовка, а другю половину из форм...поочередно их открывая и ища необходимые даные - что фигово,
    // т.к. долго и надо учесть два момента:
    // 1) определять текстбокс под определенным лейблом - та еще задачка и
    // 2) учесть вариант когда надо осуществлять перход на другую страницу дял поиска
    // 7. Считать всего в таблице - учесть перход на вторую страницу
    // 8. Написать два варианта проверок позитивные тесты и негативные

    public void search( ) {
        login( );
        addBTN.click( );
        for (int i = 0; i < labelList.size( ); i++) {
            System.out.println( labelList.get( i ).getText( ) );
        }
        cancelBTN.click( );
        //Разбить полученную строку на массив и из полученного массива получить нужный элемент
        // System.out.println( Arrays.asList( tableRow.get( 0 ).getText( ).split( " " ) ).get( 0 ) );
        waitSomeMillisec( 500 );
        menu.click( );
        waitSomeMillisec( 500 );
        for (int i = 0; i < menuPoint.size( ); i++) {
            for (int j =0; j< tableHeader.size(); j++){
               /* if (menuPoint.get( i ).getText().equals( tableHeader.get( j ).getText() )){
                    tableRow.get( j ).getText();
                }*/
            }
            //System.out.println( "Text i=" + i + " " + menuPoint.get( i ).getText( ) );
        }
        for (int j =0; j< tableHeader.size(); j++){
               /* if (menuPoint.get( i ).getText().equals( tableHeader.get( j ).getText() )){
                    tableRow.get( j ).getText();
                }*/
            System.out.println( "Text i=" + j + " " + tableHeader.get( j ).getText( ) );
        }
        // menuPoint.get( 6 ).click( );
        serachAreaTB.click( );
        serachAreaTB.clear( );
        serachAreaTB.sendKeys( "test" );
    }
}
