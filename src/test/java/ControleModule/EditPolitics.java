package ControleModule;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class EditPolitics extends CreatePolitics {
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[1]/div/div[1]/span[4]/button")
    static WebElement editBTN;
    @FindBy(xpath = "//table/tbody/tr[last()]/td[2]")
    static WebElement politicsNameRow;
    @Test
    public void editPolitics( ) {
        login();
        goToPolicyPage( );
        goToLastPage();
        politicsNameRow.click();
        
        editBTN.click();
    }
}
