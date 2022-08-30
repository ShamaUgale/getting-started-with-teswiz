package com.znsio.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.businessLayer.AmazonBL;
import com.znsio.e2e.businessLayer.CartBL;
import com.znsio.e2e.businessLayer.ProductDetailsBL;
import com.znsio.e2e.entities.GUEST_USER_CONTEXT;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Drivers;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class AmazonSteps {

    private static final Logger LOGGER = Logger.getLogger(AmazonSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public AmazonSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("user searches for {string} product on amazon home page")
    public void userSearchesForProductOnAmazonHomePage(String product) {
        LOGGER.info("launch website as a guest user");
        allDrivers.createDriverFor(GUEST_USER_CONTEXT.GUEST_USER, Runner.platform, context);
        new AmazonBL().userSearchesForProductOnAmazonHomePage(product);
    }

    @When("user selects first product from search results")
    public void userSelectsFirstProductFromSearchResults() {
        new AmazonBL().userSelectsFirstProductFromSearchResults();
    }

    @And("user adds product into cart")
    public void userAddsProductIntoCart() {
        new ProductDetailsBL().userAddsProductIntoCart();
    }

    @Then("added product should be visible into the cart")
    public void addedProductShouldBeVisibleIntoTheCart() {
        new CartBL().productShouldBeVisibleInTheCart();
    }
}
