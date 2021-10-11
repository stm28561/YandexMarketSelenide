package selenide.yandex;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class YandexMarketSearchSpecify extends BasePage {


    @Step("Устанавливаем цену, начальную {startPrice} и конечную {endPrice}")
    public YandexMarketSearchSpecify setPrice(String startPrice, String endPrice) {
        $x("//input[@name='Цена от']").setValue(startPrice);
        $x("//input[@name='Цена до']").setValue(endPrice);
        return page(YandexMarketSearchSpecify.class);
    }

    @Step("Выбираем бренд {brandName}")
    public YandexMarketSearchSpecify chooseBrand(String brandName) {
        $x("//legend[contains(.,'Производитель')]/..//span[contains(.,'" + brandName +"')]/../..").click();
        return page(YandexMarketSearchSpecify.class);
    }

    @Step("Устанавливаем 12 элементов на странице")
    public YandexMarketSearchSpecify setTwelveElements()  {
        $x("//*[@data-apiary-widget-name='@MarketNode/SearchPager']//div[@class='_3JNss _1BSH6 v3cFc']").click();
        $x("//*[@class='_1KpjX _35Paz']").click();
        return page(YandexMarketSearchSpecify.class);
    }

    @Step("Отдельно ищем первый элемент из списка, сравниваем новый первый элемент со старым")
    public YandexMarketSearchSpecify searchFirstElementСompareOldAndNewFirstElement() {
        ElementsCollection elementsOld = $$x("//*[@data-zone-name='SearchResults']//article//h3/*[@title]");
        $x("//div[@class='G4KLq']//*[@placeholder='Искать товары']").setValue(elementsOld.get(0).getText());
        $x("//span[@class='JqPid']").click();
        ElementsCollection elementsNew = $$x("//*[@data-zone-name='SearchResults']//article//h3/*[@title]");
        Assertions.assertTrue(elementsOld.get(0).getText().contains(elementsNew.get(0).getText()), "Старый первый элемент из списка не равен новому");
        return page(YandexMarketSearchSpecify.class);
    }

    @Step()
    public boolean checkThatAllElementsOnPageSameBrand(String brandName) {

        while ($x("//a[@aria-label='Следующая страница']").is(Condition.visible)) {
            ElementsCollection elementsCollections = $$x("//*[@data-zone-name='SearchResults']//article//h3/*[@title]");
            if (!elementsCollections.stream().allMatch(o -> o.getText().contains(brandName))) {
                return false;
            }

            $x("//a[@aria-label='Следующая страница']").click();

        }

        return true;
    }
}
