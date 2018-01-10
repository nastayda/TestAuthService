package Search;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class SearchPolitics extends SearchBase {

    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[1]/div/div[1]/span[1]/span/span/span")
    public WebElement menu;
    @FindBy(xpath = "//ul[@class='ant-select-dropdown-menu ant-select-dropdown-menu-vertical  ant-select-dropdown-menu-root']/li")
    public List<WebElement> menuPoint;
    @FindBy(xpath = "//*[@id=\"authorization\"]//table/thead/tr/th")
    public List<WebElement> tableHeader;
    @FindBy(xpath = "//*[@id=\"authorization\"]//table/tbody/tr")
    public List<WebElement> tableRow;
    @FindBy(xpath = "//*[@id=\"authorization\"]//table/tbody/tr/td")
    public List<WebElement> tableCol;
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[1]/div/div[1]/span[1]/span/span/input")
    public WebElement searchAreaTB;

    public void searchPolitic(){
        login();
        goToPolicyPage();
        getCriteriaFromMenu( );
        softAssert.assertAll( );
    }
}
