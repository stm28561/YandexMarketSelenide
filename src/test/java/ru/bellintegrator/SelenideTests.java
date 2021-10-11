package ru.bellintegrator;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import selenide.GoogleMainPage;
import selenide.GoogleSearchResult;
import selenide.OpenMainPage;
import selenide.yandex.YandexMainPage;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTests {

    @BeforeEach
    public  void option(){
        Configuration.timeout=6000;
        Configuration.browser="chrome";
        Configuration.startMaximized=true;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;

        /*
        System.setProperty("webdriver.chrome.driver",System.getenv("CHROME_DRIVER"));
        WebDriver driver;
        driver = new ChromeDriver(options);
        setWebDriver(driver);
         */

    }

    @Test
    public void otkrSelenideShort(){
        open("https://www.google.ru/", GoogleMainPage.class)
                .search("Открытие")
                .goLinkByName("Банк Открытие: Частным клиентам", OpenMainPage.class)
                .checkBuySell("USD");
    }

    @Test
    public void otkrSelenide(){
        GoogleMainPage googleMainPage = open("https://www.google.ru/", GoogleMainPage.class);
        GoogleSearchResult googleSearchResult = googleMainPage.search("Открытие");
        OpenMainPage openMainPage = googleSearchResult.goLinkByName("Банк Открытие: Частным клиентам", OpenMainPage.class);
        openMainPage.checkBuySell("USD");
    }

    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска c помощью PO")
    @ParameterizedTest(name="{displayName} {arguments}")
    @CsvSource({"USD"})
    public void otkrSelenideShort2(String typeMoney){
        open("https://www.google.ru/", GoogleMainPage.class)
                .search("Открытие")
                .goLinkByName("Банк Открытие: Частным клиентам", OpenMainPage.class)
                .checkBuySell(typeMoney);
    }

    @Test
    public void yandexMarketSelenide() {
        open("https://yandex.ru/", YandexMainPage.class)
                .goToMarket()
                .goToGoods("Компьютеры")
                .chooseGood("Ноутбуки")
                .setPrice("10000", "30000")
                .chooseBrand("Lenovo")
                .chooseBrand("HP")
                .setTwelveElements()
                .searchFirstElementСompareOldAndNewFirstElement();
    }

    @Feature("Проверка всех элементов на соответствие после выбора бренда")
    @DisplayName("Проверка результатов c помощью PO")
    @ParameterizedTest(name="{displayName} {arguments}")
    @CsvSource({"Apple"})
    public void yandexMarketSelenideSecond(String brandName) {
        open("https://yandex.ru/", YandexMainPage.class)
                .goToMarket()
                .goToGoods("Электроника")
                .chooseGood("Смартфоны")
                .chooseBrand(brandName)
                .setTwelveElements()
                .checkThatAllElementsOnPageSameBrand(brandName);
    }
}
