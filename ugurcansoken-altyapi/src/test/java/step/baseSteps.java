package step;

import base.baseTest;
import com.thoughtworks.gauge.Step;
import model.ElementInfo;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class baseSteps extends baseTest {

    public static int DEFAULT_MAX_ITERATION_COUNT = 150;
    public static int DEFAULT_MILLISECOND_WAIT_AMOUNT = 100;
    protected static String SAVED_ATTRIBUTE = null;
    protected static String SAVED_USERNAME = null;
    protected static Integer Index = null;
    private String compareText;
    boolean firstFrame = true;
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    WebDriverWait wait = new WebDriverWait(driver, 180);
    WebDriverWait webDriverWait = new WebDriverWait(driver, 20);

    public baseSteps() {
        initMap(getFileList());
    }

    WebElement findElement(String key) {
        By infoParam = getElementInfoToBy(findElementInfoByKey(key));
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        WebElement webElement = webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(infoParam));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return webElement;
    }

    WebElement findElementWithoutWait(String key) {
        By infoParam = getElementInfoToBy(findElementInfoByKey(key));
        WebElement webElement = driver.findElement(infoParam);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return webElement;
    }

    List<WebElement> findElements(String key) {
        return driver.findElements(getElementInfoToBy(findElementInfoByKey(key)));
    }

    public By getElementInfoToBy(ElementInfo elementInfo) {
        By by = null;
        if (elementInfo.getType().equals("css")) {
            by = By.cssSelector(elementInfo.getValue());
        } else if (elementInfo.getType().equals(("name"))) {
            by = By.name(elementInfo.getValue());
        } else if (elementInfo.getType().equals("id")) {
            by = By.id(elementInfo.getValue());
        } else if (elementInfo.getType().equals("xpath")) {
            by = By.xpath(elementInfo.getValue());
        } else if (elementInfo.getType().equals("linkText")) {
            by = By.linkText(elementInfo.getValue());
        } else if (elementInfo.getType().equals(("partialLinkText"))) {
            by = By.partialLinkText(elementInfo.getValue());
        }
        return by;
    }

    private void clickElement(WebElement element) {
        element.click();
    }

    public void javaScriptClicker(WebDriver driver, WebElement element) {

        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("var evt = document.createEvent('MouseEvents');"
                + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
                + "arguments[0].dispatchEvent(evt);", element);
    }
    private void scrollTo(int x, int y) {
        String script = String.format("window.scrollTo(%d, %d);", x, y);
        executeJS(script, true);
    }

    private Object executeJS(String script, boolean wait) {
        return wait ? getJSExecutor().executeScript(script, "") : getJSExecutor().executeAsyncScript(script, "");
    }

    private JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) driver;
    }

    public void javaScriptClickWithoutWait(String key) {
        WebElement element = findElementWithoutWait(key);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        logger.info(key + " elementi tiklandi.");
    }


    @Step({"Go to <url> address",
            "<url> adresine git"})
    public void goToUrl(String url) {
        driver.get(url);
        logger.info(url + " adresine gidiliyor.");
    }

    @Step({"Wait <value> seconds",
            "<int> saniye bekle"})
    public void waitBySeconds(int seconds) {
        try {
            logger.info(seconds + " saniye bekleniyor.");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step({"Wait <value> milliseconds",
            "<long> milisaniye bekle"})
    public void waitByMilliSeconds(long milliseconds) {
        try {
            logger.info(milliseconds + " milisaniye bekleniyor.");
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Step({"Click to element <key>",
            "Elementine tıkla <key>"})
    public void clickElement(String key) {
        clickElement(findElement(key));
        logger.info(key + " elementine tıklandı.");
    }


    public void javascriptclicker(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    @Step("Suanki URL <url> degerini iceriyor mu kontrol et")
    public void checkURLContainsRepeat(String expectedURL) {
        int loopCount = 0;
        String actualURL = "";
        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            actualURL = driver.getCurrentUrl();

            if (actualURL != null && actualURL.contains(expectedURL)) {
                logger.info("Suanki URL" + expectedURL + " degerini iceriyor.");
                return;
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        fail(
                "Actual URL doesn't match the expected." + "Expected: " + expectedURL + ", Actual: "
                        + actualURL);
    }

    @Step("<key> elementi <text> degerini iceriyor mu kontrol et")
    public void checkElementContainsText(String key, String expectedText) {
        WebElement element = findElement(key);
        String actualText = "";
        int loopCount = 0;
        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            actualText = element.getText();
            if (actualText.contains(expectedText)) {
                logger.info(key + " elementi " + expectedText + " degerini iceriyor.");
                return;
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        fail("Actual value doesn't match the expected. Expected: " + expectedText + ", Actual: " + actualText);
    }


    @Step("<key> listesindeki elementlerden rastgele biri tiklanir")
    public void randommPick(String key) {
        List<WebElement> elements = findElements(key);
        Random random = new Random();
        int index = random.nextInt(elements.size());
        Index = index;
        elements.get(index).click();
        logger.info("rastgele " + (index + 1) + " .elemente tiklandi");

    }


    @Step({"<key> elementine <text> degeri yazilir"})
    public void sendKeyWith(String key, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(getElementInfoToBy(findElementInfoByKey(key))));
        WebElement element = findElementWithoutWait(key);
        element.clear();
        element.sendKeys(text);
        logger.info(key + " elementine" + text + "değeri yazıldı.");
    }

    @Step({"<key> elementinin gorulmesi beklenir"})
    public void checkElementWithDynamic(String key) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getElementInfoToBy(findElementInfoByKey(key))));
        logger.info(key + " elementi goruldu");
    }


    @Step({"<key> Elementine kadar kaydır"})
    public WebElement scrollToElementToBeVisible(String key) {
        WebElement element = findElement(key);
        if (element != null) {
            scrollTo(element.getLocation().getX(), element.getLocation().getY() - 100);
        }
        return element;
    }



    @Step("Urunun <fiyat> fiyat bilgisini <fiyatBilgisi> olarak," +
            " <modelAdi> model adini <modelBilgisi> olarak," +
            "<CPU> CPU modelini <CPUBilgisi> olarak kaydet ve bilgiyi iceren yeni bir dosya olustur")

    public void dosyaOlusturma(String fiyat, String fiyatBilgisi, String modelAdi, String modelBilgisi, String CPU, String CPUBilgisi) throws IOException {
        String saveValueText = findElement(fiyat).getText();
        saveValue(fiyatBilgisi, saveValueText);
        String saveValueTextModel = findElement(modelAdi).getText();
        saveValue(modelBilgisi, saveValueTextModel);
        String saveValueTextCPU = findElement(CPU).getText();
        saveValue(CPUBilgisi, saveValueTextCPU);

        logger.info(saveValueText);
        logger.info(saveValueTextModel);
        logger.info(saveValueTextCPU);

        File file = new File("paytr.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fWriter);
        bWriter.write("Fiyat: " + getValue(fiyatBilgisi) + ", ");
        bWriter.write(" Model: " + getValue(modelBilgisi) + ", ");
        bWriter.write(" CPU: " + getValue(CPUBilgisi));
        bWriter.close();
    }

    @Step("<key> elementi varsa tıkla")
    public void clickif(String key) {
        WebElement element = findElement(key);
        if (element.isDisplayed()) {
            element.click();
            logger.info(element + "elementine tiklandi");
        } else {
            logger.info("Element bulunamadı");
        }

    }


}

