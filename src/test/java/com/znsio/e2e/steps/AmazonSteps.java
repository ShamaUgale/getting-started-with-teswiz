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

    private static final Logger LOGGER = Logger.getLogger(CalculatorSteps.class.getName());
    private final TestExecutionContext context;
    private final Drivers allDrivers;

    public AmazonSteps() {
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
        LOGGER.info("context: " + context.getTestName());
        allDrivers = (Drivers) context.getTestState(SAMPLE_TEST_CONTEXT.ALL_DRIVERS);
        LOGGER.info("allDrivers: " + (null == allDrivers));
    }

    @Given("Guest user is on amazon home screen")
    public void guestUserIsOnAmazonHomeScreen() {
        LOGGER.info("launch website as a guest user");
        allDrivers.createDriverFor(GUEST_USER_CONTEXT.GUEST_USER, Runner.platform, context);
    }

    @When("user searches for {string}")
    public void userSearchesFor(String product) {
        new AmazonBL().searchForProduct(product);
    }

    @And("opens the product details page")
    public void opensTheProductDetailsPage() {
        new AmazonBL().openTheProductDetailsPage();
    }

    @And("Adds the product to cart")
    public void addsTheProductToCart() {
        new ProductDetailsBL().addTheProductToCart();
    }

    @Then("User should see the product in the cart")
    public void userShouldSeeTheProductInTheCart() {
        new CartBL().productShouldBeAvailableInTheCart();
    }
}
