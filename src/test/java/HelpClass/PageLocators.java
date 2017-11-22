package HelpClass;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageLocators extends BaseClass{



    @FindBy(xpath = "//*[@id=\"authorization\"]/div/form/button/span")
    private WebElement signInButton;
    public WebElement getSignInButton(){
        return signInButton;
    }



    public PageLocators( FirefoxDriver driver) {
        this.wd = driver;
        PageFactory.initElements(driver, this);
    }


}
