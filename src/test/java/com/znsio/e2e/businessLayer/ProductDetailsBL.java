package com.znsio.e2e.businessLayer;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.ProductScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductDetailsBL {

    private static final Logger LOGGER = Logger.getLogger(com.znsio.e2e.businessLayer.ProductDetailsBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;

    public ProductDetailsBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
    }

    public ProductDetailsBL userAddsProductIntoCart() {
        assertThat(ProductScreen.get().addProductToCart().trim()).isEqualToIgnoringCase("added to cart");
        context.addTestState("PRODUCT_QUANTITY", 1);
        context.addTestState("TOTAL_ITEMS_IN_CART", 1);
        LOGGER.info("product added to cart successfully");
        return this;
    }
}