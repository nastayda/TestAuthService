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
import ru.yandex.qatools.allure.annotations.Title;

import javax.sound.midi.Soundbank;
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
                    //System.out.println( pointMenu );
                    clickToSubMenu( i );
                    System.out.println( getRowsFromDB(" like '"+elementSebMenu.getText()+"%'"," where customParam4 ").size());
                    break;
                case "Название организации":
                    System.out.println( pointMenu );
                    break;
                case "Подразделение":
                    System.out.println( pointMenu );
                    break;
                case "Роль":
                    System.out.println( pointMenu );
                    break;
                case "Тип организации":
                    System.out.println( pointMenu );
                    break;
            }

            clickWithExpects( allOrg );
        }
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
