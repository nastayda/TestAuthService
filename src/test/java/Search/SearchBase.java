package Search;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Test
public class SearchBase extends BaseClass {
    @FindBy(xpath = "//div[@class='ant-select-selection\n" +
            "            ant-select-selection--single']")
    WebElement menu;
    @FindBy(xpath = "//ul[@class='ant-select-dropdown-menu ant-select-dropdown-menu-vertical  ant-select-dropdown-menu-root']/li")
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
        //Разбить полученную строку на массив и из полученного массива получить нужный элемент
        // System.out.println( Arrays.asList( tableRow.get( 0 ).getText( ).split( " " ) ).get( 0 ) );
        //menu.click();
        getCriteriaFromMenu( );

        //getCriterianFromForm( );
        //compareData( );
        // menuPoint.get( 6 ).click( );
        // serachAreaTB.click( );
        //serachAreaTB.clear( );
        //serachAreaTB.sendKeys( "test" );
    }

    public void compareData( ) {
        for (int i = 0; i < menuPoint.size( ); i++) {
            for (int j = 0; j < tableHeader.size( ); j++) {
                if (menuPoint.contains( tableHeader.get( j ).getText( ) )) {
                    tableRow.get( j ).getText( );
                }
            }
            //System.out.println( "Text i=" + i + " " + menuPoint.get( i ).getText( ) );
        }
    }

    public void getCriteriaFromMenu( ) {
        menu.click( );
        //Удалить пустые элементы
        menuPoint.removeAll( Collections.singleton( "" ) );
        tableHeader.removeAll( Collections.singleton( "" ) );

        System.out.println("Size table"+ tableRow.size() );
        //Цикл по элементам меню
        for (int i = 0; i < menuPoint.size( ); i++) {
            waitSomeMillisec( 1000 );
            menu.click( );
            //System.out.println("before click menuPoint.get( i ).getText( )=" +menuPoint.get( i ).getText( ));
            //Нажать на выбранный пункт меню
            menuPoint.get( i ).click( );
            //Ощущение что после создания нового объекта я создаю ссылку на него...мэджик вобщем...
            String menuPointText = menuPoint.get( i ).getText( );
            waitSomeMillisec( 500 );
            //System.out.println("after click menuPoint.get( i ).getText( )=" +menuPoint.get( i ).getText( ));
            //Цикл по заголовкам таблицы
            for (int j = 0; j < tableHeader.size( ); j++) {
                //Переменная поиска текста для вставки в строку поиска
                String criteriaText ="";
               System.out.println("menuPoint.get( i ).getText( )=" +menuPointText +
                        "\n criteriaText="+criteriaText+
                        "\n tableHeader.get( j ).getText( ) )="+tableHeader.get( j ).getText( )  );
                //Если есть заголовок меню = заголовку таблицы
                if (!tableHeader.get( j ).getText( ).isEmpty() &
                        menuPointText.equals( tableHeader.get( j ).getText( ) )) {
                    //Ищем текст для вставки в строку поиска
                    //Цикл по всем строкам таблицы
                    for (int k = 1; k < tableRow.size( ); k++) {

                        //Получаем текст
                        criteriaText = Arrays.asList( tableRow.get( k ).getText( ).split( " " ) ).get( j );
                        //Если текст  не пуст
                        if (!criteriaText.isEmpty( )) {

                            //тогда вставляем текст в строку поиска
                            serachAreaTB.click( );
                            serachAreaTB.clear( );
                            serachAreaTB.sendKeys( criteriaText );
                            System.out.println("Size "+ tableRow.size() );
                            waitSomeMillisec( 1000 );
                            serachAreaTB.click( );
                            serachAreaTB.clear( );
                            k=tableRow.size( );
                            //Сюда впихнуть ассерт
                        }
                        //Иначе проверить есть ли пагинация и если есть перейти на вторую страницу и попытаться поискать там
                    }

                }
            }
            //System.out.println( "Text from menu i=" + i + " " + menuPoint.get( i ).getText( ) );
        }
    }

    public void getCriterianFromForm( ) {
        addBTN.click( );
        /*for (int i = 0; i < labelList.size( ); i++) {
            System.out.println( labelList.get( i ).getText( ) );
        }*/
        cancelBTN.click( );
    }
}
