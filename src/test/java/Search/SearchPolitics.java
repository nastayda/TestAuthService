package Search;

import HelpClass.BaseClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.util.List;


public class SearchPolitics extends BaseClass {
    //Вариантов переопределения xpath нет, либо он оч сложный и малоиспользуемый
    // потом есть две альтернативы:
    // 1) Инициализировать объект класса SearchAuth со всеми веб-элементами, которые он содержит -> создавать несколько классов с одинаковым функционалом, но с разными веб-элементы
    // 2) Создать в классе родителе методы которые определяют веб элементы, а в классе наследнике эти методы переопределить -> придется описывать все через FindBy
    // Поэтому просто копипаст с базового класса поиска
    // ПЕРЕПИСАТЬ ВСЮ ПРОВЕРКУ ПОТОМУ ЧТО МОЖНО СДЕЛТЬ ЭТО ПРОЩЕ:
    // 1) Выгрузить инфу из бд
    // 2) по выббранному критерию найти в выгруженных данных колво
    // 3) сравнить с колвом отобр в табл
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[1]/div/div[1]/span[1]/span/span/span")
    WebElement menu;
    @FindBy(xpath = "//ul[@class='ant-select-dropdown-menu ant-select-dropdown-menu-vertical  ant-select-dropdown-menu-root']/li")
    List<WebElement> menuPoint;
    @FindBy(xpath = "//*[@id=\"authorization\"]//table/thead/tr/th")
    List<WebElement> tableHeader;
    @FindBy(xpath = "//*[@id=\"authorization\"]//table/tbody/tr")
    List<WebElement> tableRow;
    @FindBy(xpath = "//*[@id=\"authorization\"]//table/tbody/tr/td")
    List<WebElement> tableCol;
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[1]/div/div[1]/span[1]/span/span/input")
    WebElement searchAreaTB;

    @Title("Проверка поиска политик")
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Step("Вызов метода поиска ")
    public void searchPolitic( ) throws Exception {
        //login( );
        PageFactory.initElements(wd, this);
        goToPolicyPage( );
        getCriteriaFromMenu( );
        softAssert.assertAll( );
        //getRowsFromDB(new ArrayList<>( Arrays.asList( 193 ) ));
    }

    public void getCriteriaFromMenu( ) {
        menu.click( );
        //System.out.println(menu.getText() );
        //Цикл по элементам меню
        int countMenuPoint = menuPoint.size( );
        for (int i = 0; i < countMenuPoint; i++) {
            wd.navigate().refresh();
            menu.click( );
            //Ждем появления подменю
            //waitSomeTime( 1000 );
            //Если подменю не появилось, т.е. текст пункта пуст, то еще раз нажмем на меню
            if (menuPoint.get( i ).getText( ).isEmpty( )) {
                menu.click( );
                //waitSomeTime( 1000 );
            }
            //прокрутить до конца выпадающего меню, чтобы получить самый последний элемент
            WebElement target = menuPoint.get( i );
            ( (JavascriptExecutor) wd ).executeScript( "arguments[0].scrollIntoView(true);", target );
            //Нажать на выбранный пункт меню
            target.click( );
            //System.out.println( "after click menu.getText( )=" + menu.getText( ) );
            //Получить название пункта подменю
            //String menuPointText = menuPoint.get( i ).getText( );
            String menuPointText = menu.getText( );
            waitSomeTime( 500 );
            //Цикл по заголовкам таблицы
            for (int j = 0; j < tableHeader.size( ); j++) {
                //Переменная поиска текста для вставки в строку поиска
                String criteriaText = "";
                //Если есть заголовок меню = заголовку таблицы
                if (!tableHeader.get( j ).getText( ).isEmpty( ) & menuPointText.equals( tableHeader.get( j ).getText( ) )) {
                    //System.out.println( "table header " + tableHeader.get( j ).getText( ) );
                    //Ищем текст для вставки в строку поиска
                    criteriaText = getNotNullValueFromColumn( j );
                    int countBeforeSearch = countByCriterion( criteriaText, j );
                    //тогда вставляем текст в строку поиска
                    creatTB( );
                    searchAreaTB.sendKeys( criteriaText );
                    //System.out.println( "Size " + tableRow.size( ) );
                    waitSomeTime( 1500 );
                    int countAfterSearch = tableRow.size( );
                    searchWithParameterPolitics( menuPointText, criteriaText, countBeforeSearch, countAfterSearch );
                    creatTB( );
                }
            }
        }
    }

    @Step("Проверка по параметру {0} провалена по критерию {1}")
    public void searchWithParameterPolitics( String menuPointText, String criteriaText, int countBeforeSearch, int countAfterSearch ) {
        softAssert.assertEquals( countAfterSearch, countBeforeSearch,  "Проверка по параметру " + menuPointText + " провалена по критерию " + criteriaText );
    }

    public void creatTB( ) {
        searchAreaTB.click( );
        searchAreaTB.clear( );
    }

    public String getNotNullValueFromColumn( int numElement ) {
        for (int i = numElement; i < tableCol.size( ); i += tableHeader.size( )) {
            //проанализировать массив и взять каждый житый ненулевой элемент
            //System.out.println( "From table " + tableCol.get( i ).getText( ) );
            if (!tableCol.get( i ).getText( ).isEmpty( )) {
                return tableCol.get( i ).getText( );
            }
            //Иначе проверить есть ли пагинация и если есть перейти на вторую страницу и попытаться поискать там
            else {
                if (pagginationArrow.getAttribute( "aria-disabled" ) != null) {
                    while (pagginationArrow.getAttribute( "aria-disabled" ).equals( "false" )) {
                        pagginationArrow.click( );
                        if (!tableCol.get( i ).getText( ).isEmpty( )) {
                            return tableCol.get( i ).getText( );
                        }
                    }
                }
            }
        }
        return "";
    }

    private int countByCriterion( String searchCriterion, int startJ ) {
        int k=0;
        //System.out.println( tableRow.size() );
        //Классно! если только не 100500 страниц надо будет перелистывать....
        if (pagginationArrow.getAttribute( "aria-disabled" ) != null) {
            resizeAreaWithTable( "//*[@id=\"authorization\"]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]" );
            for (int j = startJ; j < tableCol.size( ); j = j + tableHeader.size( )) {
                if (tableCol.get( j ).getText( ).equals( searchCriterion )) {
                    k++;
                }
            }
            while (pagginationArrow.getAttribute( "aria-disabled" ).equals( "false" )) {
                pagginationArrow.click( );
                for (int j = startJ; j < tableCol.size( ); j = j + tableHeader.size( )) {
                    if (tableCol.get( j ).getText( ).equals( searchCriterion )) {
                        k++;
                    }
                }
                waitSomeTime( 1500 );
            }
        }
        else{
            for (int j = startJ; j < tableCol.size( ); j = j + tableHeader.size( )) {
                if (tableCol.get( j ).getText( ).contains( searchCriterion )) {
                    k++;
                }
            }
        }
        return k;
    }
}
