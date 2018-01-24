package HelpClass;

import Politics.DeletePolitics;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Listeners(MyTestListener.class)
public class BaseClass {

    ////div[2]/div/div[2]/div/div[1]/div[2]/form/div[4]/h2
    @FindBy(xpath = "//div[2]/div/div[2]/div/div[1]/div[2]/form/div[4]/h2")
    public static WebElement placeInForm;
    public SoftAssert softAssert = new SoftAssert( );
    //Всего элементов в таблице
    @FindBy(xpath = "//div/div[2]/div[2]/div/div/div/ul/li[1]")
    static public WebElement countRowsText;
    @FindBy(xpath = "//table/tbody/tr")
    public static List<WebElement> countRowsOnEachPage;
    ////*[@id="root"]/div/div[2]/div[2]/div/div/div/ul/li[4]
    //*[@id="authorization"]/div/div[2]/div[2]/div/div/div/ul/li[5]
    @FindBy(xpath = "//div/div[2]/div[2]/div/div/div/ul/li[last()-1]")
    public static WebElement pagginationArrow;
    @FindBy(css = "input.ant-input.ant-select-search__field")
    static WebElement loginNameTB;
    @FindBy(id = "nameLast")
    static WebElement lastNameTB;
    @FindBy(id = "customParam1")
    static WebElement orgTypeTB;
    @FindBy(id = "customParam2")
    static WebElement orgNameTB;
    @FindBy(id = "customParam3")
    static WebElement orgSubunitTB;
    @FindBy(id = "customParam4")
    static WebElement orgGroupTB;
    @FindBy(id = "nameFirst")
    static WebElement firstNameTB;
    @FindBy(id = "nameMiddle")
    static WebElement middleNameTB;
    @FindBy(id = "email")
    static WebElement emailTB;
    @FindBy(id = "phone")
    static WebElement phoneNumberTB;
    @FindBy(id = "role")
    static WebElement roleTB;
    @FindBy(xpath = "//div[2]/div/div[2]/div/div[1]/div[3]/div/button[2]")
    static WebElement saveBTN;
    @FindBy(xpath = "//div[2]/div/div[2]/div/div[1]/div[3]/div/button[2]")
    static WebElement savePoliticsBTN;
    //ul[@class='ant-select-tree']/li[1]/ul/li[3]/ul/li[1]/span[2]/span
    //ul[@class='ant-select-tree']/li[1]/ul/li[3]/ul/li[1]/span[2]/span
    @FindBy(xpath = "//ul[@class='ant-select-tree']/li[1]/ul/li[2]/span[2]/span")
    static WebElement expandMenu;
    //*[@id="2"]/div[1]/div[2]/div/div/div/div
    @FindBy(xpath = "//*[@id=\"2\"]/div[1]/div[2]/div/div/div/div")
    static WebElement dropdownMenuSign;
    //div[5]/div/div[2]/div[2]/div/div/div/ul/li[1]
    @FindBy(xpath = "//div[5]/div/div[2]/div[2]/div/div/div/ul/li[1]")
    static WebElement elementSign;
    @FindBy(id = "serviceName")
    static WebElement serviceNameINPT;
    @FindBy(css = "li.ant-select-dropdown-menu-item-active.ant-select-dropdown-menu-item")
    static WebElement modulDropMenu;
    @FindBy(id = "policyName")
    static WebElement policyNameINPT;
    @FindBy(id = "policyDescription")
    static WebElement policyDescriptionINPT;
    @FindBy(css = "ul.ant-select-selection__rendered")
    static WebElement menuItem;
    @FindBy(css = "span.ant-select-tree-switcher.ant-select-tree-switcher_close")
    static WebElement chooseServiceSetting;
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[2]/div[1]/div/div[1]/span[2]/button")
    static WebElement controlModuleBTN;
    @FindBy(xpath = "//div[1]/div[2]/div/span/input")
    static WebElement keyNameModuleINPT;
    @FindBy(xpath = "//div[2]/div[2]/div/input")
    static WebElement nameModuleINPT;
    @FindBy(xpath = "//div[3]/div[2]/div/span/input")
    static WebElement addressINPT;
    @FindBy(xpath = "//div[3]/div/div[2]/div/div[1]/div[3]/div/button[2]")
    static WebElement saveModuleBTN;
    @FindBy(xpath = "//*[@id=\"authorization\"]/div/div[1]/ul/li[last()]")
    public static WebElement lastMenuItem;


    @FindBy(xpath = "//*[@id=\"root\"]/div/form/button")
    static WebElement buttonSignin;
    @FindBy(id = "password")
    static WebElement passwordTB;
    @FindBy(id = "userName")
    static WebElement userNameTB;
    @FindBy(xpath = "//table")
    static WebElement table;

    @FindBy(xpath = "//table/tbody/tr[last()]/td[1]")
    static public WebElement checkB;
    @FindBy(xpath = "//table/tbody/tr[last()]/td[2]")
    static public WebElement lastRowText;

    public static boolean isAlertPresent( FirefoxDriver wd ) {
        try {
            wd.switchTo( ).alert( );
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    @Step(" Ввод логина {0}")
    public static void setLogin( String login ) {
        loginNameTB.clear( );
        loginNameTB.clear( );
        loginNameTB.sendKeys( login );
    }


    public static FirefoxDriver wd;
    public static WriteReadFromFile readData;

    @BeforeSuite
    public void setUp( ITestContext context ) throws Exception {
        //Получить реальный путь к geco
        File gecoFile = new File( "src/help-files/geckodriver.exe" );
        String pathToGeckoDriver = gecoFile.getAbsolutePath( );
        //Подготовка утилиты для работы браузера FF
        System.setProperty( "webdriver.gecko.driver", pathToGeckoDriver );
        DesiredCapabilities capabilities = DesiredCapabilities.firefox( );
        capabilities.setCapability( "marionette", true );
        //Получить путь к тестовым файлам
        goToAuthPage( );
        login( );
        context.setAttribute( "app", BaseClass.this);
    }

    public void goToAuthPage( ) {
        File testFile = new File( "src/help-files/auth-info.txt" );
        wd = new FirefoxDriver( );
        wd.manage( ).timeouts( ).implicitlyWait( 10, TimeUnit.SECONDS );
        //C:\Users\danilkinaas\Documents\GitHub\TestAuthServiceNew\src\help-files\geckodriver.exe
        //Читаем из файла адрес сервера
        readData = new WriteReadFromFile( testFile.getAbsolutePath( ) );
        wd.get( readData.readFromFile( ).get( 0 ).substring( 1 ) );
    }

    @Step("Ввод логина и пароля")
    public void login( ) {
        PageFactory.initElements( wd, this );
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
    private static void waitTable( ) {
        WebDriverWait wait = new WebDriverWait( wd, 5 );
        // wait.until( ExpectedConditions.elementToBeClickable( By.xpath( "//*[@id=\"authorization\"]/div/div[2]/div[2]/div/div/div/div/div/div/div[1]/table" ) ) );
        wait.until( ExpectedConditions.elementToBeClickable( table ) );
    }

    @Step("Нажать на Войти")
    private void signInClick( ) {
        // PageLocators pL = new PageLocators( wd );
        //pL.getSignInButton( ).click( );
        buttonSignin.click( );
        //wd.findElement(By.xpath("//*[@id=\"authorization\"]/div/form/button/span")).click();
    }

    @Step("Ввод пароля")
    private void enterPassword( String elementPassword, String passwordLogin ) {
        //*[@id="password"]
        /*wd.findElement( By.xpath( "//*[@id=\"password\"]" ) ).click( );
        wd.findElement( By.xpath( "//*[@id=\"password\"]" ) ).clear( );
        wd.findElement( By.xpath( "//*[@id=\"password\"]" ) ).sendKeys( passwordLogin );*/
        passwordTB.click( );
        ;
        passwordTB.clear( );
        passwordTB.sendKeys( passwordLogin );
    }

    @Step("Ввод логина")
    private void enterLogin( String elementUserName, String nameLogin ) {
        ////*[@id="userName"]
        /*wd.findElement( By.xpath( "//*[@id=\"userName\"]" ) ).click( );
        wd.findElement( By.xpath( "//*[@id=\"userName\"]" ) ).clear( );
        wd.findElement( By.xpath( "//*[@id=\"userName\"]" ) ).sendKeys( nameLogin );*/
        userNameTB.click( );
        userNameTB.clear( );
        userNameTB.sendKeys( nameLogin );
    }

    @AfterSuite
    public void tearDown( ) {
        wd.quit( );
    }

    @Step("Перейти на последнюю страницу")
    protected int goToLastPage( ) {
        int k = countRowsOnEachPage.size( );
        //Классно! если только не 100500 страниц надо будет перелистывать....
        if (pagginationArrow.getAttribute( "aria-disabled" ) != null) {
            while (pagginationArrow.getAttribute( "aria-disabled" ).equals( "false" )) {
                pagginationArrow.click( );
                k += countRowsOnEachPage.size( );
            }
        }
        return k;
    }

    @Step("Получить название политики")
    public boolean chosePolicy( String testPoliticsName, String editPoliticsName ) {
        if (!DeletePolitics.checkB.isSelected( ) & ( lastRowText.getText( ).contains( testPoliticsName + LocalDateTime.now( ).getYear( ) ) ||
                lastRowText.getText( ).contains( editPoliticsName + LocalDateTime.now( ).getYear( ) ) )) {
            checkB.click( );
            return true;
        } else return false;
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

    public void goToPolicyPage( ) {
        wd.navigate( ).to( getDataFromFile( "src/help-files/auth-info.txt" )[ 3 ] );
    }

    public void waitSomeMillisec( int millisec ) {
        //Подождать пока прогрузится меню! Иначе он берет первый попавшися элемент списка
        try {
            Thread.sleep( millisec );
        } catch (InterruptedException e) {
            e.printStackTrace( );
        }
    }

    public void waitUntilElementBeClickable( WebElement clickableElement ) {
        WebDriverWait wait = new WebDriverWait( wd, 5 );
        WebElement element = wait.until( ExpectedConditions.elementToBeClickable( clickableElement ) );
    }

    //Edit, create users methods
    @Step(" Сохранине")
    public void clickToSave( ) {
        waitUntilElementBeClickable( saveBTN );
        saveBTN.click( );
    }

    @Step(" Ввод роли {0}")
    public void setRole( String role ) {
        roleTB.click( );
        //roleTB.clear( );
        roleTB.sendKeys( Keys.chord( Keys.CONTROL, "a" ), "" );
        roleTB.sendKeys( role );
    }

    @Step(" Ввод номера телефона {0}")
    public void setPhoneNumber( String phoneNumber ) {
        phoneNumberTB.click( );
        //phoneNumberTB.clear( );
        phoneNumberTB.sendKeys( Keys.chord( Keys.CONTROL, "a" ), "" );
        phoneNumberTB.sendKeys( phoneNumber );
    }

    @Step(" Ввод email {0}")
    public void setEmail( String email ) {
        emailTB.click( );
        //emailTB.clear( );
        emailTB.sendKeys( Keys.chord( Keys.CONTROL, "a" ), "" );
        emailTB.sendKeys( email );
    }

    @Step(" Ввод отчества {0}")
    public void setMiddleName( String middleName ) {
        middleNameTB.click( );
        //middleNameTB.clear( );
        middleNameTB.sendKeys( Keys.chord( Keys.CONTROL, "a" ), "" );
        middleNameTB.sendKeys( middleName );
    }

    @Step(" Ввод имени {0}")
    public void setFirstName( String firstName ) {
        firstNameTB.click( );
        //firstNameTB.clear( );
        firstNameTB.sendKeys( Keys.chord( Keys.CONTROL, "a" ), "" );
        firstNameTB.sendKeys( firstName );
    }

    @Step(" Ввод фамилии {0}")
    public void setSurname( String surname ) {
        lastNameTB.click( );
        lastNameTB.sendKeys( Keys.chord( Keys.CONTROL, "a" ), "" );
        //lastNameTB.clear( );
        lastNameTB.sendKeys( surname );
    }

    @Step(" Ввод типа организации {0}")
    public void setOrgType( String orgType ) {
        orgTypeTB.click( );
        orgTypeTB.sendKeys( Keys.chord( Keys.CONTROL, "a" ), "" );
        //lastNameTB.clear( );
        orgTypeTB.sendKeys( orgType );
    }

    @Step(" Ввод названия организации {0}")
    public void setOrgName( String orgName ) {
        orgNameTB.click( );
        orgNameTB.sendKeys( Keys.chord( Keys.CONTROL, "a" ), "" );
        //lastNameTB.clear( );
        orgNameTB.sendKeys( orgName );
    }

    @Step(" Ввод подразделения организации {0}")
    public void setOrgSubunit( String orgSubunit ) {
        orgSubunitTB.click( );
        orgSubunitTB.sendKeys( Keys.chord( Keys.CONTROL, "a" ), "" );
        //lastNameTB.clear( );
        orgSubunitTB.sendKeys( orgSubunit );
    }

    @Step(" Ввод группы {0}")
    public void setOrgGroup( String orgGroup ) {
        orgGroupTB.click( );
        orgGroupTB.sendKeys( Keys.chord( Keys.CONTROL, "a" ), "" );
        //lastNameTB.clear( );
        orgGroupTB.sendKeys( orgGroup );
    }

    //Edit, create politics methods
    @Step("Сохранить")
    public void clickSave( ) {
        savePoliticsBTN.click( );
    }

    @Step("Выбрать настройку")
    public void chooseSetting( ) {
        menuItem.click( );
        //waitSomeMillisec( 1000 );
        chooseServiceSetting.click( );
        waitSomeMillisec( 1000 );
        expandMenu.click( );
        waitSomeMillisec( 1000 );
        policyDescriptionINPT.click( );
    }

    @Step("Заполнить название сервиса")
    public void setServiceName( ) {
        //заполнить модуль
        serviceNameINPT.click( );
        modulDropMenu.click( );

        //заполнить объект
        menuItem.click( );
        waitSomeMillisec( 1000 );
        chooseServiceSetting.click( );
        waitSomeMillisec( 1000 );
        expandMenu.click( );
        waitSomeMillisec( 1000 );
        placeInForm.click( );
        menuItem.click( );
        // waitSomeMillisec( 1000 );
        //serviceNameINPT.clear( );
        //serviceNameINPT.sendKeys( serviceName );

        //wd.findElement(By.id("serviceName")).click();
        // wd.findElement(By.cssSelector("li.ant-select-dropdown-menu-item-active.ant-select-dropdown-menu-item")).click();

        //wd.findElement(By.cssSelector("ul.ant-select-selection__rendered")).click();
        //wd.findElement(By.cssSelector("span.ant-select-tree-switcher.ant-select-tree-switcher_close")).click();
        //wd.findElement(By.xpath("//ul[@class='ant-select-tree']/li[1]/ul/li[2]/span[2]/span")).click();
    }

    @Step("Заполнить описание {0}")
    public void setDescription( String decription ) {
        policyDescriptionINPT.click( );
        policyDescriptionINPT.clear( );
        policyDescriptionINPT.sendKeys( decription );
    }

    @Step("Заполнить название политики {0}")
    public void setPoliticsName( String politicsName ) {
        policyNameINPT.click( );
        policyNameINPT.clear( );
        policyNameINPT.sendKeys( politicsName );
    }

    @Step("Установить значение {0}")
    public void setMeaning( String meaning, WebElement valueINPT ) {
        valueINPT.click( );
        valueINPT.sendKeys( meaning );
    }

    @Step("Выбрать знак")
    public void choseSign( ) {
        dropdownMenuSign.click( );
        ( (JavascriptExecutor) wd ).executeScript( "arguments[0].click();", elementSign );
    }

    @Step("Выбрать параметр {0}")
    public void choseParameter( String parameter, WebElement parametrINPT ) {
        parametrINPT.click( );
        parametrINPT.clear( );
        parametrINPT.sendKeys( parameter );
    }

    //Edit, create modules methods
    @Step(" Нажать на сохранение")
    public void clickToSaveBtn( ) {
        saveModuleBTN.click( );
    }

    @Step(" Ввод адреса {0}")
    public void setAddress( String address ) {
        addressINPT.click( );
        addressINPT.clear( );
        addressINPT.sendKeys( address );
    }

    @Step(" Ввод названия {0}")
    public void setName( String name ) {
        nameModuleINPT.click( );
        nameModuleINPT.clear( );
        nameModuleINPT.sendKeys( name );
    }

    @Step(" Ввод ключевого названия {0}")
    public void setKeyName( String keyName ) {
        keyNameModuleINPT.click( );
        keyNameModuleINPT.clear( );
        keyNameModuleINPT.sendKeys( keyName );
    }

    public void clickControlBtn( ) {
        controlModuleBTN.click( );
    }

    public void resizeWindow( int width, int height ) {
        // Развернуть окно до размеров 1920 х 1080, чтобы добраться до последнего элемента
        wd.manage( ).window( ).setSize( new Dimension( width, height ) );
    }

    protected void resizeAreaWithTable( String xpathExpression ) {
        WebElement target = wd.findElement( By.xpath( xpathExpression ) );
        ( (JavascriptExecutor) wd ).executeScript( "arguments[0].setAttribute('style', 'max-height: 4205px;')", target );
        //Нажать на выбранный пункт
        target.click( );
    }

    public byte[] createSkreenshot( ) throws IOException {
      return  ((TakesScreenshot)wd).getScreenshotAs( OutputType.BYTES);
       // File scrFile = ((TakesScreenshot)wd).getScreenshotAs( OutputType.FILE);
// Now you can do whatever you need to do with it, for example copy somewhere
       // FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot.png"));

    }
}
