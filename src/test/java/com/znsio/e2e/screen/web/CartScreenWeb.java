package com.znsio.e2e.screen.web;

import com.znsio.e2e.screen.CartScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CartScreenWeb extends CartScreen {
    private static final Logger LOGGER = Logger.getLogger(CartScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = CartScreenWeb.class.getSimpleName();
    private final By totalItems = By.cssSelector("div[data-itemtype='active']");
    private final By itemName = By.xpath(".//span[@class='a-truncate-cut']");
    private final By itemQuantity = By.className("a-dropdown-prompt");


    public CartScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Cart screen");
    }


    public int getTotalItemsInCart(){
        LOGGER.info("getting total items");
        List<WebElement> totalElementsInCart = driver.findElements(totalItems);
        LOGGER.info("total items in cart are : " + totalElementsInCart.size());
        return totalElementsInCart.size();
    }

    public String getItemNameInCart(){
        LOGGER.info("getting the product name");
        String productName = driver.findElement(itemName).getText();
        LOGGER.info("product name is : "+ productName);
        return productName;
    }

    public String getItemQuantityInCart(){
        LOGGER.info("getting the product quantity");
        return driver.findElement(itemQuantity).getText().trim();
    }

}
