package selenide.yandex;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class YandexMarketBase extends BasePage {

    @Step("Выбираем раздел товаров {goodName}")
    public YandexMarketBase goToGoods(String goodName) {
        $x("//div[@data-zone-name = 'category-link']//span[contains(.,'" + goodName + "')]").click();
        return page(YandexMarketBase.class);
    }

    @Step("Выбираем товар {goodName}")
    public YandexMarketSearchSpecify chooseGood(String goodName) {
        $x("//div[@class='_1S8Pr']//ul//a[contains(.,'" + goodName + "')]").click();
        return page(YandexMarketSearchSpecify.class);
    }


}
