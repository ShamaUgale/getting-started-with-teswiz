package com.znsio.e2e.screen.web;

import com.znsio.e2e.screen.AmazonHomeScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AmazonHomeScreenWeb extends AmazonHomeScreen {

    private static final Logger LOGGER = Logger.getLogger(AmazonHomeScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private final By searchBar = By.id("twotabsearchtextbox");
    private final By searchSubmit = By.id("nav-search-submit-button");
    private final By searchResultsElement = By.cssSelector("span[class='a-size-medium a-color-base a-text-normal']");
    private final By firstSearchResult = By.xpath("(//span[@data-component-type='s-product-image'])[1]");

    public AmazonHomeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Amazon Home screen");
    }

    public List<String> searchProduct(String product){
        LOGGER.info("trying to search a product : " + product);
        driver.findElement(searchBar).click();
        driver.findElement(searchBar).sendKeys(product);
        driver.findElement(searchSubmit).click();
        LOGGER.info("returning search results");
        List<WebElement> results = driver.findElements(searchResultsElement);
        List<String> searchResults = new ArrayList<>();
        for (WebElement result : results)
            searchResults.add(result.getText());
        return searchResults;
    }

    public String getProductDetails() {
        LOGGER.info("clicking on the first product of the search result");
        driver.findElement(firstSearchResult).click();
        String currentHandle = driver.getInnerDriver().getWindowHandle();
        Set<String> handles = driver.getInnerDriver().getWindowHandles();
        if (handles.size() > 1) {
            LOGGER.info("a new product tab is opened");
            LOGGER.info("switching to the new tab");
            for (String actual : handles) {
                if (!actual.equalsIgnoreCase(currentHandle)) {
                    driver.getInnerDriver().switchTo().window(actual);
                }
            }
            LOGGER.info("in the product page");
            return driver.getInnerDriver().getCurrentUrl();
        }
        else {
            LOGGER.info("product page is not opened");
            return null;
        }
    }


}
