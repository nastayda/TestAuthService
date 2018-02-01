package FilterAndSort;

import HelpClass.BaseClass;
import HelpClass.ConnectionHB;
import HelpClass.UserTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.ArrayList;
import java.util.List;

//Элементы подменю отсортированы по алфавиту!Что неудобно, т.к. есть 4 кастом_параметра,
// которые если перебирать их в цикле будут не совпадать с теми которые есь в меню.
// Тут очень хочется вставить картинку человека с красными глазами, вопрошающего WHY!?!?!?!?!?
// В итоге буду делать через свич-кейс...пока ничего умнее в голову не приходит :(
public class FilterAuthLefSubMenu extends BaseClass {
    @FindBy(xpath = "//div[@class='ant-menu-submenu-title']")
    WebElement allOrg;

    @FindBy(xpath = "//div/div/div[2]/div[1]/div/div/ul[1]/li/ul/li")
    List<WebElement> subMenu;

    @FindBy(xpath = "//div/div/div[2]/div[1]/div/div/ul[2]/li[last()]")
    WebElement elementSebMenu;

    @FindBy(xpath = "//*[@id=\"root\"]//table/tbody/tr")
    List<WebElement> table;

    @Test
    @Title( "Проверка фильтрации по боковому меню" )
    public void searchAuthLefSubMenu() throws Exception {
        PageFactory.initElements( wd, this );
        clickWithExpects( allOrg );
        for (int i = 1; i < subMenu.size() ; i++) {
            //clickWithExpects( allOrg );
            String pointMenu = subMenu.get( i ).getText();
            switch (pointMenu){
                case "Группа":
                    clickToSubMenu( i );
                    //System.out.println( pointMenu );
                    assertCountFromDBadnTable( elementSebMenu.getText( ), " where customParam4 " );
                    break;
                case "Название организации":
                    clickToSubMenu( i );
                    //System.out.println( pointMenu );
                    assertCountFromDBadnTable( elementSebMenu.getText( ), " where customParam2 " );
                    break;
                case "Подразделение":
                    clickToSubMenu( i );
                    assertCountFromDBadnTable( elementSebMenu.getText( ), " where customParam3 " );
                    break;
                case "Роль":
                    clickToSubMenu( i );
                    assertCountFromDBadnTable( elementSebMenu.getText( ), " where role " );
                    break;
                case "Тип организации":
                    clickToSubMenu( i );
                    assertCountFromDBadnTable( elementSebMenu.getText( ), " where customParam1 " );
                    break;
            }
            clickWithExpects( allOrg );
        }
    }

    @Step("Сортировка по критерию ")
    public void assertCountFromDBadnTable( String elementSebMenuText, String param ) throws Exception {
        //System.out.println( getRowsFromDB(" like '"+elementSebMenu.getText()+"%'"," where customParam4 ").size()+"  "+table.size());
        softAssert.assertEquals( getRowsFromDB(" like '"+ elementSebMenuText +"%'", param ).size(), table.size(),
                                "Сортировка по критерию "+ elementSebMenuText +" провалена. Количество элементов в таблице не совпадает с количеством в БД." );
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


    public void clickToSubMenu( int i ) {
        clickWithExpects( subMenu.get( i ) );
        clickWithExpects( elementSebMenu );
    }
}
