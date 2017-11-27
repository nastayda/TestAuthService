package Modules;

import HelpClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class DeleteModule extends BaseClass {
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[1]/div/div[1]/span[2]/button")
    static WebElement controlModuleBTN;
    @FindBy(xpath = "//div[@class='ant-modal-body']/div[2]/div/div/div/div/div/table/tbody/tr[last()]/td[1]")
    static WebElement rowNameModuleTD;
    @FindBy(xpath = "//div[2]/div/div[2]/div/div[1]/div[2]/div[1]/span[3]/button")
    static WebElement deleteBTN;
    @FindBy(xpath = "//div[@class='ant-modal-footer']/button[2]")
    static WebElement confirmDeletionBTN;
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[1]/ul/li")
    static List<WebElement> menuItems;

    @Test
    public void deleteModule( ) {
        login( );
        goToPolicyPage( );
        waitSomeMillisec( 1500 );
        int beforeDeletion = menuItems.size( );
        controlModuleBTN.click( );
        if (rowNameModuleTD.getText( ).contains( "testModule" )) {
            rowNameModuleTD.click( );
            deleteBTN.click( );
            confirmDeletionBTN.click( );
        }
        wd.navigate( ).refresh( );
        waitSomeMillisec( 1500 );
        int afterDeletion = menuItems.size( );
        assertEquals( afterDeletion, beforeDeletion - 1 );
    }
}
