package com.znsio.e2e.screen.web;

import com.znsio.e2e.screen.ProductScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ProductScreenWeb extends ProductScreen {
    private static final Logger LOGGER = Logger.getLogger(ProductScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = ProductScreenWeb.class.getSimpleName();
    private final By addToCartButton = By.id("add-to-cart-button");
    private final By addCartMessage =
            By.xpath(".//div[@class='a-section a-spacing-none a-padding-none sw-fallback']");
    private final By goToCartButton = By.cssSelector("input[aria-labelledby*='view-cart']");


    public ProductScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Product screen");
    }

    public String addProductToCart(){
        LOGGER.info("adding product to cart");
        driver.waitForClickabilityOf(addToCartButton);
        driver.findElement(addToCartButton).click();
        driver.waitTillElementIsPresent(addCartMessage);
        return driver.findElement(addCartMessage).getText();
    }

    public ProductScreen goToCart(){
        LOGGER.info("clicking on go to cart button");
        driver.waitForClickabilityOf(goToCartButton);
        driver.findElement(goToCartButton).click();
        return this;
    }
}
