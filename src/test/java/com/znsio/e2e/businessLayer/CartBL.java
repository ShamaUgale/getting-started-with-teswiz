package com.znsio.e2e.businessLayer;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.CartScreen;
import com.znsio.e2e.screen.ProductScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class CartBL {

    private static final Logger LOGGER = Logger.getLogger(com.znsio.e2e.businessLayer.CartBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;


    public CartBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
    }

    public CartBL productShouldBeVisibleInTheCart() {
        LOGGER.info("navigating to cart page");
        ProductScreen.get().goToCart();
        LOGGER.info("checking the total count of items in the cart");
        assertThat(CartScreen.get().getTotalItemsInCart())
                .isEqualTo(context.getTestState("TOTAL_ITEMS_IN_CART"));
        LOGGER.info("verifying the product name in the cart");
        assertThat(CartScreen.get().getItemNameInCart()).
                containsIgnoringCase(context.getTestStateAsString("PRODUCT_NAME"));
        LOGGER.info("validating the product quantity in the cart");
        assertThat(CartScreen.get().getItemQuantityInCart())
                .isEqualTo(context.getTestState("PRODUCT_QUANTITY").toString());
        LOGGER.info("all validations are completed in the cart page");
        LOGGER.info("The product is available in the cart");
        return this;
    }
}