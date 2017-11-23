package ControleModule;

import HelpClass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import sun.misc.BASE64Decoder;

import java.time.LocalDateTime;

public class DeleteModule extends BaseClass {
    //*[@id="authorization"]/div/div[2]/div[2]/div/div/div/ul/li[5]
    @FindBy(xpath = "//ul/li[5]")
    static WebElement pagginationArrow;
    @FindBy(xpath = "//table/tbody/tr[last()]/td[2]")
    static WebElement lastRowText;
    @FindBy(xpath = "//table/tbody/tr[last()]/td[1]")
    static WebElement checkB;
    @FindBy(xpath = "//div/div[2]/div[1]/div/div[1]/span[5]/button")
    static WebElement deleteBTN;
    @FindBy(xpath = "//div/div[13]/div/div[2]/div/div[1]/div[3]/button[2]/span")
    static WebElement confirmDeletionBTN;

    @Test
    public void deleteModule( ) {
        login( );
        wd.navigate( ).to( getDataFromFile( "src/help-files/auth-info.txt" )[ 3 ] );
        goToLastPage( );
        choseUserForDeletion( );
        waitUntilElementBeClickable( deleteBTN );
        clickToDeleteBtn( );
        //System.out.println(pagginationArrow.getAttribute( "aria-disabled" ));
        //Переход на 2 страницу
        // wd.findElement( By.linkText("2")).click();
        //System.out.println(pagginationArrow.getAttribute( "aria-disabled" ));
        //*[@id="authorization"]/div/div[2]/div[2]/div/div/div/ul/li[5]/a
        // wd.findElement(By.xpath("//tbody[@class='ant-table-tbody']//td[.='test desription']")).click();
        // wd.findElement(By.xpath("//div[@class='service-controls']//button[.='Редактировать']")).click();

    }

    @Step("3. Удалить и подтвердить удалене")
    private void clickToDeleteBtn( ) {
        waitUntilElementBeClickable( deleteBTN );
        deleteBTN.click( );
        waitUntilElementBeClickable( confirmDeletionBTN );
        confirmDeletionBTN.click( );
    }

    @Step("2. Получить название политики")
    public void choseUserForDeletion( ) {
        if (!checkB.isSelected( ) & ( lastRowText.getText( ).contains( "testPolitics" + LocalDateTime.now( ).getYear( ) ) ||
                lastRowText.getText( ).contains( "editPolitics" + LocalDateTime.now( ).getYear( ) ) )) {
            checkB.click( );
        }
    }

    @Step("1. Перейти на последнюю страницу")
    private void goToLastPage( ) {
        //Классно! если только не 100500 страниц надо будет перелистывать....
        while (pagginationArrow.getAttribute( "aria-disabled" ).equals( "false" )) {
            pagginationArrow.click( );
        }
    }
}
