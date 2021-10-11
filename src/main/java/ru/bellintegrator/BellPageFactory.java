package ru.bellintegrator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class BellPageFactory {
    private WebDriver chromeDriver;
    private String url="https://bellintegrator.ru/index.php?route=product/search";

    @FindBy(how= How.ID, id="input-search")
    WebElement searchField;

    @FindBy(how= How.ID, id="button-search")
    WebElement searchButton;

    @FindBy(how= How.XPATH, using = "//*[@class=\"product-layout product-list col-xs-12\"]//*[@class=\"short__desc\"]")
    List<WebElement> allElements;

    public BellPageFactory(WebDriver chromeDriver){
        this.chromeDriver=chromeDriver;
        chromeDriver.get(url);
    }

    public  void find (String keysFind){
        searchField.click();
        searchField.sendKeys(keysFind);
        searchButton.click();
    }

    public List<WebElement> getAllElements() {
        return allElements;
    }
}
