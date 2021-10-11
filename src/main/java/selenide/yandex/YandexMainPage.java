package selenide.yandex;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenide.GoogleSearchResult;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class YandexMainPage {

    @Step("Захожу в меню Маркет")
    public YandexMarketBase goToMarket(){
        $x("//a[@data-id='market']").click();
        switchTo().window(1);
        return page(YandexMarketBase.class);
    }

}
