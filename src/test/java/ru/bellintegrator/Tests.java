package ru.bellintegrator;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Tests extends BaseTest {

    @Test
    public void firstUITest(){
    }

    @Test
    public void firstTestTitle(){
        chromeDriver.get("https://bellintegrator.ru/");
        String title = chromeDriver.getTitle();
        System.out.println(title);
        Assertions.assertTrue(title.contains("Bell Integrator"),"Тейтл не корректен");
    }

    @Test
    public void secondTestFind(){
        chromeDriver.get("https://bellintegrator.ru/index.php?route=product/search");
        WebElement searchField = chromeDriver.findElement(By.id("input-search"));
        WebElement searchButton = chromeDriver.findElement(By.id("button-search"));
        searchField.click();
        searchField.sendKeys("RPA");
        searchButton.click();

        List<WebElement> resaultSearch = chromeDriver.findElements(By.xpath("//*[@class='product-layout product-list col-xs-12']//*[@class='short__desc']"));
        System.out.println(resaultSearch.size());
        resaultSearch.stream().forEach(x-> System.out.println(x));
        resaultSearch.stream().forEach(x-> System.out.println(x.getText()));

        Assertions.assertTrue(resaultSearch.stream().anyMatch(x->x.getText().contains("Кирилл Филенков")),"Статьи не найдены");
    }

    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска c помощью PO")
    @ParameterizedTest(name="{displayName} {arguments}")
    @CsvSource({"RPA,Кирилл Филенков","нагрузочное тестирование,Сергей Минаев"})
    public void testPO(String keyWords,String result){
        chromeDriver.get("https://bellintegrator.ru/index.php?route=product/search");
        BellBeforeSearch bellBeforeSearch = new BellBeforeSearch(chromeDriver);
        bellBeforeSearch.find(keyWords);
        BellAfterSearch bellAfterSearch = new BellAfterSearch(chromeDriver);
        Assertions.assertTrue(bellAfterSearch.getResults().stream().anyMatch(x->x.getText().contains(result)),"Статьи не найдены");
    }

    @Feature("Проверка результатов поиска")
    @Test
    public void testPF(){
        BellPageFactory bellPageFactory = PageFactory.initElements(chromeDriver,BellPageFactory.class);
        bellPageFactory.find("RPA");
        System.out.println(bellPageFactory.getAllElements().size());
    }

    @Test
    public void firstTestTitle1() throws InterruptedException {
        chromeDriver.get("https://alfabank.ru/currency/");
        Thread.sleep(10000);
        chromeDriver.findElement(By.xpath("//*[@id=\"alfa\"]/div/div[5]/div/div/div/div/div[1]/div[1]/button[2]"));
    }
}
