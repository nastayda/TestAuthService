package HelpClass;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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

    @FindBy(xpath = "//*[@id=\"authorization\"]/div/form/button/span")
    static WebElement buttonSignin;
    @FindBy(id = "password")
    static WebElement passwordTB;
    @FindBy(id = "userName")
    static WebElement userNameTB;
    @FindBy(xpath = "//table")
    static WebElement table;


    public static boolean isAlertPresent( FirefoxDriver wd ) {
        try {
            wd.switchTo( ).alert( );
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    @BeforeMethod
    public void setUp( ) throws Exception {
        //Получить реальный путь к geco
        File gecoFile = new File( "src/help-files/geckodriver.exe" );
        String pathToGeckoDriver = gecoFile.getAbsolutePath( );
        //Подготовка утилиты для работы браузера FF
        System.setProperty( "webdriver.gecko.driver", pathToGeckoDriver );
        DesiredCapabilities capabilities = DesiredCapabilities.firefox( );
        capabilities.setCapability( "marionette", true );

        //Получить путь к тестовым файлам
        File testFile = new File( "src/help-files/auth-info.txt" );

        wd = new FirefoxDriver( );
        wd.manage( ).timeouts( ).implicitlyWait( 10, TimeUnit.SECONDS );
        //C:\Users\danilkinaas\Documents\GitHub\TestAuthServiceNew\src\help-files\geckodriver.exe
        //Читаем из файла адрес сервера
        readData = new WriteReadFromFile( testFile.getAbsolutePath( ) );
        wd.get( readData.readFromFile( ).get( 0 ).substring( 1 ) );
    }

    @Step("Ввод логина и пароля")
    protected void login( ) {
        PageFactory.initElements(wd, this);
        String elementUserName = "userName";
        String elementPassword = "password";
        String nameLogin = readData.readFromFile( ).get( 1 );
        String passwordLogin = readData.readFromFile( ).get( 2 );
        enterLogin( elementUserName, nameLogin );
        enterPassword( elementPassword, passwordLogin );
        signInClick( );
        waitTable( );

    }

    @Step("Ждем пока появится таблица")
    private void waitTable( ) {
        WebDriverWait wait = new WebDriverWait( wd, 5 );
       // wait.until( ExpectedConditions.elementToBeClickable( By.xpath( "//*[@id=\"authorization\"]/div/div[2]/div[2]/div/div/div/div/div/div/div[1]/table" ) ) );
        wait.until( ExpectedConditions.elementToBeClickable( table ) );
    }

    @Step("Нажать на Войти")
    private void signInClick( ) {
       // PageLocators pL = new PageLocators( wd );
        //pL.getSignInButton( ).click( );
        buttonSignin.click();
        //wd.findElement(By.xpath("//*[@id=\"authorization\"]/div/form/button/span")).click();
    }

    @Step("Ввод пароля")
    private void enterPassword( String elementPassword, String passwordLogin ) {
        //*[@id="password"]
        /*wd.findElement( By.xpath( "//*[@id=\"password\"]" ) ).click( );
        wd.findElement( By.xpath( "//*[@id=\"password\"]" ) ).clear( );
        wd.findElement( By.xpath( "//*[@id=\"password\"]" ) ).sendKeys( passwordLogin );*/
        passwordTB.click();;
        passwordTB.clear();
        passwordTB.sendKeys( passwordLogin );
    }

    @Step("Ввод логина")
    private void enterLogin( String elementUserName, String nameLogin ) {
        ////*[@id="userName"]
        /*wd.findElement( By.xpath( "//*[@id=\"userName\"]" ) ).click( );
        wd.findElement( By.xpath( "//*[@id=\"userName\"]" ) ).clear( );
        wd.findElement( By.xpath( "//*[@id=\"userName\"]" ) ).sendKeys( nameLogin );*/
        userNameTB.click();
        userNameTB.clear();
        userNameTB.sendKeys( nameLogin );
    }

    @AfterMethod
    public void tearDown( ) {
        wd.quit( );
    }

    //Подождем пока появится элемент
    public void waitUntilElementBeClickable( WebElement clickableElement ) {
        WebDriverWait wait = new WebDriverWait( wd, 5 );
        WebElement element = wait.until( ExpectedConditions.elementToBeClickable( clickableElement ) );
    }

    public String[] getDataFromFile( String pathname ) {
        //Получить путь к тестовым файлам
        File testFile = new File( pathname );
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
