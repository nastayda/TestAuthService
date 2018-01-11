package Search;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class SearchPolitics extends SearchAuth {
    //Вариантов переопределения xpath нет, либо он оч сложный и малоиспользуемый
    // потом есть две альтернативы:
    // 1) Инициализировать объект класса SearchAuth со всеми веб-элементами, которые он содержит -> создавать несколько классов с одинаковым функционалом, но с разными веб-элементы
    // 2) Создать в классе родителе методы которые определяют веб элементы, а в классе наследнике эти методы переопределить -> придется описывать все через FindBy
    // Поэтому просто копипаст с базового класса поиска
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

    public void searchPolitic( ) {
        login( );
        goToPolicyPage( );
        //getCriteriaFromMenu( );
        softAssert.assertAll( );
    }
}
