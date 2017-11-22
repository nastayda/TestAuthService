package HelpClass;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    public FirefoxDriver wd;
    WriteReadFromFile readData;

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    @BeforeMethod
    public void setUp() throws Exception {
        //Получить реальный путь к geco
        File gecoFile = new File("src/help-files/geckodriver.exe");
        String pathToGeckoDriver = gecoFile.getAbsolutePath();
        //Подготовка утилиты для работы браузера FF
        System.setProperty("webdriver.gecko.driver", pathToGeckoDriver);
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);

        //Получить путь к тестовым файлам
        File testFile = new File("src/help-files/auth-info.txt");

        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Читаем из файла адрес сервера
        readData= new WriteReadFromFile(testFile.getAbsolutePath());
        wd.get(readData.readFromFile().get(0).substring(1));
    }

    @Step("Ввод логина и пароля")
    protected void login() {
        String elementUserName ="userName";
        String elementPassword ="password";
        String nameLogin = readData.readFromFile().get(1);;
        String  passwordLogin = readData.readFromFile().get(2);
        enterLogin(elementUserName, nameLogin);
        enterPassword(elementPassword, passwordLogin);
        signInClick();
        waitTableAndGoToPage();

    }

    @Step("Ждем пока появится таблица и переходм на основную станицу")
    private void waitTableAndGoToPage() {
        WebDriverWait wait = new WebDriverWait(wd, 5);
        //*[@id="authorization"]/div/div[2]/div[2]/div/div/div/div/div/div/div[1]/table
        wait.until( ExpectedConditions.elementToBeClickable( By.xpath("//*[@id=\"authorization\"]/div/div[2]/div[2]/div/div/div/div/div/div/div[1]/table")));
        //Переход на страницу с делами
        //wd.navigate().to("http://vm-107-stu-dev.ursip.ru/");
        //Клик по левому меню "Обращения"
        //wd.findElement(By.cssSelector("div.departments-tree")).click();
    }

    @Step("Нажать на Войти")
    private void signInClick() {
        wd.findElement(By.xpath("//*[@id=\"authorization\"]/div/form/button/span")).click();
    }

    @Step("Ввод пароля")
    private void enterPassword(String elementPassword, String passwordLogin) {
        //*[@id="password"]
        wd.findElement(By.xpath("//*[@id=\"password\"]")).click();
        wd.findElement(By.xpath("//*[@id=\"password\"]")).clear();
        wd.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(passwordLogin);
    }

    @Step("Ввод логина")
    private void enterLogin(String elementUserName, String nameLogin) {
        ////*[@id="userName"]
        wd.findElement(By.xpath("//*[@id=\"userName\"]")).click();
        wd.findElement(By.xpath("//*[@id=\"userName\"]")).clear();
        wd.findElement(By.xpath("//*[@id=\"userName\"]")).sendKeys(nameLogin);
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }

    public String[] getDataFromFile( ) {
        //Получить путь к тестовым файлам
        File testFile = new File( "src/help-files/data-for-doc.txt" );
        //Читаем из файла по абсолютному пути
        WriteReadFromFile readDataForCompare = new WriteReadFromFile( testFile.getAbsolutePath( ) );
        //Заполнить массив тестовыми данными
        String[] dataFromFile = new String[ readDataForCompare.readFromFile( ).size( ) ];
        for (int i = 0; i < readDataForCompare.readFromFile( ).size( ); i++) {
            dataFromFile[ i ] = readDataForCompare.readFromFile( ).get( i );
        }
        //System.out.println(dataFromFile[1]);
        return dataFromFile;
    }
}
