package FilterAndSort;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.List;

//Элементы подменю отсортированы по алфавиту!Что неудобно, т.к. есть 4 кастом_параметра,
// которые если перебирать их в цикле будут не совпадать с теми которые есь в меню.
// Тут очень хочется вставить картинку человека с красными глазами, вопрошающего WHY!?!?!?!?!?
// В итоге буду делать через кейс...пока ничего умнее в голову не приходит :(
public class FilterAuthLefSubMenu extends BaseClass {
    @FindBy(xpath = "//div[@class='ant-menu-submenu-title']")
    WebElement allOrg;

    @FindBy(xpath = "//div/div/div[2]/div[1]/div/div/ul[1]/li/ul/li")
    List<WebElement> subMenu;

    @FindBy(xpath = "//div/div/div[2]/div[1]/div/div/ul[2]/li[last()]")
    WebElement elementSebMenu;

    @Test
    @Title( "Проверка фильтрации по боковому меню" )
    public void searchAuthLefSubMenu(){
        PageFactory.initElements( wd, this );
        clickWithExpects( allOrg );
        for (int i = 1; i < subMenu.size() ; i++) {
            //clickWithExpects( allOrg );
            clickWithExpects( subMenu.get( i ) );
            clickWithExpects( elementSebMenu );
            clickWithExpects( allOrg );
        }
    }
}
