package ControleModule;

import HelpClass.BaseClass;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class CreateModule extends BaseClass {
    @Test
    public void createModule(){
        login();
        wd.navigate().to( getDataFromFile( "src/help-files/auth-info.txt" )[3] );

        //Переход на 2 страницу
        wd.findElement(By.linkText("2")).click();
        wd.findElement(By.xpath("//tbody[@class='ant-table-tbody']//td[.='test desription']")).click();
        wd.findElement(By.xpath("//div[@class='service-controls']//button[.='Редактировать']")).click();
        //Удалить то что было до этого
        wd.findElement(By.cssSelector("span.ant-select-selection__clear")).click();
        //Выбрать сервис
        wd.findElement(By.id("serviceName")).click();
        wd.findElement(By.cssSelector("li.ant-select-dropdown-menu-item-active.ant-select-dropdown-menu-item")).click();
        //Выбрать настройку
        wd.findElement(By.cssSelector("ul.ant-select-selection__rendered")).click();
        wd.findElement(By.cssSelector("span.ant-select-tree-switcher.ant-select-tree-switcher_close")).click();
        wd.findElement(By.xpath("//ul[@class='ant-select-tree']/li[1]/ul/li[1]/span[2]/span")).click();
        //Удалить данные которые были до и выбрать новый параметр настройки
        wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[4]/div/div[1]/div[2]/div/div/div/span[1]")).click();
        wd.findElement(By.id("subjectParameter-0")).click();//непосредственный выбор параметра

        //Выбрать знак
        //wd.findElement(By.cssSelector("li.ant-select-dropdown-menu-item-active.ant-select-dropdown-menu-item")).click();

        //waitUntilElementBeClickable( wd.findElement(By.cssSelector("li.ant-select-dropdown-menu-item-active.ant-select-dropdown-menu-item")));
        //wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[4]/div/div[2]/div[2]/div/div/div/div")).click();
        wd.findElement( By.cssSelector( "span.ant-select-selection__clear" ) ).click();
        wd.findElement( By.xpath( "//*[@id=\"subjectParameter-0\"]" ) ).clear();
        waitUntilElementBeClickable( wd.findElement(By.cssSelector("li.ant-select-dropdown-menu-item-active.ant-select-dropdown-menu-item")));
                wd.findElement(By.cssSelector("li.ant-select-dropdown-menu-item-active.ant-select-dropdown-menu-item")).click();

        //Выбрать значение
        wd.findElement(By.id("subjectValue-0")).click();
        wd.findElement(By.cssSelector("li.ant-select-dropdown-menu-item-active.ant-select-dropdown-menu-item")).click();

        //Выбрать условие
        wd.findElement(By.xpath("//div[@class='ant-modal-body']/form/div[4]/div/div[4]/div[2]/div/div/div/span")).click();
        wd.findElement(By.cssSelector("li.ant-select-dropdown-menu-item-active.ant-select-dropdown-menu-item")).click();

        //Выбрать новый параметр
        wd.findElement(By.id("subjectParameter-1")).click();
        wd.findElement(By.xpath("//div[7]/div/div/div/ul/li")).click();

    }
}
