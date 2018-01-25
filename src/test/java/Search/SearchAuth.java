package Search;

import HelpClass.BaseClass;
import HelpClass.ConnectionHB;
import HelpClass.UserTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.util.ArrayList;
import java.util.List;



public class SearchAuth extends BaseClass {
    @FindBy(xpath = "//div[@class='ant-select-selection\n" +
            "            ant-select-selection--single']")
    static WebElement menu;
    @FindBy(xpath = "//ul[@class='ant-select-dropdown-menu ant-select-dropdown-menu-vertical  ant-select-dropdown-menu-root']/li")
    static List<WebElement> menuPoint;
    @FindBy(css = "input.ant-input.ant-input-lg")
    //*[@id="root"]/div/div[1]/div/div[1]/div/div[2]/span[1]/span/span/span[2]/input
    static WebElement searchAreaTB;
    @FindBy(xpath = "//*[@id=\"root\"]//table/tbody/tr")
    static List<WebElement> tableRow;
    @FindBy(xpath = "//*[@id=\"root\"]//table/tbody/tr/td")
    static List<WebElement> tableCol;
    @FindBy(xpath = "//*[@id=\"root\"]//table/thead/tr/th")
    static List<WebElement> tableHeader;

    @Title("Проверка поиска пользователей")
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Step("Вызов метода поиска")
    public void search( ) throws Exception {
        //login( );
        PageFactory.initElements(wd, this);
        getCriteriaFromMenu( );
        softAssert.assertAll( );
    }

    public List<String> getRowsFromDB( String criterion, String param ) throws Exception {
        ConnectionHB conDB = new ConnectionHB( );
        SessionFactory sessionFactory = conDB.setUp( );

        Session session = sessionFactory.openSession( );
        session.beginTransaction( );

        //List<Integer> idList = numbersFromTable;

        String hql = "from UserTable " + param + criterion;
        List result = session.createQuery( hql ).list( );
        session.getTransaction( ).commit( );
        session.close( );
        List<String> newStringResult = new ArrayList<>( );
        for (UserTable item : (List<UserTable>) result) {
            //System.out.println( item.getNameFirst( ) );
            newStringResult.add( item.getId( ) + item.getEmail( ) + item.getLogin( ) + item.getNameFirst( ) + item.getNameLast( ) + item.getNameMiddle( ) +
                    item.getPhone( ) + item.getRole( ) + item.getParam1( ) + item.getParam2( ) + item.getParam3( ) + item.getParam4( )
            );
        }
        return newStringResult;
    }

    public void getCriteriaFromMenu( ) throws Exception {
        menu.click( );
        //System.out.println(menu.getText() );
        //Цикл по элементам меню
        for (int i = 0; i < menuPoint.size( ); i++) {
            //Ждем появления подменю
            waitSomeTime( 1000 );
            //Если подменю не появилось, т.е. текст пункта пуст, то еще раз нажмем на меню
            if (menuPoint.get( i ).getText( ).isEmpty( )) {
                menu.click( );
            }
            //прокрутить до конца выпадающего меню, чтобы получить самый последний элемент
            WebElement target = menuPoint.get( i );
            ( (JavascriptExecutor) wd ).executeScript( "arguments[0].scrollIntoView(true);", target );
            //Нажать на выбранный пункт меню
            target.click( );
            //Получить название пункта подменю
            String menuPointText = menu.getText( ); //target.getText();//menuPoint.get( i ).getText( );
            //Если пункт меню != шапке таблицы
            if (i < 5) {
                if (i == 0) {
                    int countBeforeSearch = getCountRepeatedElementsFromList( getRowsFromDB( "", "" ) );
                    sendTextToTB( "Test" );
                    int countAfterSearch = tableRow.size();
                    softAssert.assertEquals(  countBeforeSearch, countAfterSearch, "Проверка провалена. По критерию Везде параметру Test");
                    clearTB( );
                    //System.out.println( countBeforeSearch );
                } else {
                    int countBeforeSearch = getRowsFromDB( " like 'Test%'", " where customParam" + i ).size( );
                    sendTextToTB( "Test" );
                    int countAfterSearch = tableRow.size( );
                    softAssert.assertEquals( countBeforeSearch, countAfterSearch, "Проверка провалена. По критерию " + menuPointText + " параметру Test" );
                    clearTB( );
                }
            } else {
                // System.out.println( "i=" + i + "after click menu.getText( )=" + menu.getText( ) );
                //Цикл по заголовкам таблицы
                for (int j = 0; j < tableHeader.size( ); j++) {
                    //Переменная поиска текста для вставки в строку поиска
                    String criteriaText = "";
                    //Если есть заголовок меню = заголовку таблицы
                    if (!tableHeader.get( j ).getText( ).isEmpty( ) & menuPointText.equals( tableHeader.get( j ).getText( ) )) {
                        // System.out.println( "table header " + tableHeader.get( j ).getText( ) );
                        //Ищем текст для вставки в строку поиска
                        criteriaText = getNotNullValueFromColumn( j );
                        int countBeforeSearch = countByCriterion( criteriaText, j );
                        //тогда вставляем текст в строку поиска
                        sendTextToTB( criteriaText );
                       // waitSomeTime( 1500 );
                        int countAfterSearch = tableRow.size( );
                        softAssert.assertEquals( countBeforeSearch, countAfterSearch, "Проверка провалена. По критерию " + menuPointText + " параметру " + criteriaText );
                        clearTB();
                    }
                }
            }
        }
    }

    public void clearTB( ) {
        searchAreaTB.click( );
        searchAreaTB.clear( );
    }

    public void sendTextToTB( String test ) {
        clearTB( );
        searchAreaTB.sendKeys( test );
    }

    private int getCountRepeatedElementsFromList( List<String> rowsFromDB ) {
        int count = 0;
        for (String item : rowsFromDB) {
            if (item.contains( "Test" ) || item.contains( "test" )) {
                count++;
            }
        }
        return count;
    }

    public String getNotNullValueFromColumn( int numElement ) {
        for (int i = numElement; i < tableCol.size( ); i += tableHeader.size( )) {
            //проанализировать массив и взять каждый житый ненулевой элемент
            // System.out.println( "From table " + tableCol.get( i ).getText( ) );
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
        int k = 0;
        for (int j = startJ; j < tableCol.size( ); j = j + tableHeader.size( )) {
            if (tableCol.get( j ).getText( ).contains( searchCriterion )) {
                k++;
            }
        }
        return k;
    }
}
