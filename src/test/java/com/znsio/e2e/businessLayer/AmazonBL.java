package com.znsio.e2e.businessLayer;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.AmazonHomeScreen;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class AmazonBL {

        private static final Logger LOGGER = Logger.getLogger(com.znsio.e2e.businessLayer.AmazonBL.class.getName());
        private final TestExecutionContext context;
        private final SoftAssertions softly;


    public AmazonBL() {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
    }

    public AmazonBL searchForProduct(String productName) {
        context.addTestState("PRODUCT_NAME", productName);
        for (String searchResult : AmazonHomeScreen.get().searchProduct(productName)) {
            LOGGER.info("comparing the search results" + searchResult + "with the searched product : " + productName);
            softly.assertThat( searchResult.contains(productName.toLowerCase()));
            LOGGER.info("search results are verified");
        }
        return this;
    }

    public AmazonBL openTheProductDetailsPage() {
        String newProductTab = AmazonHomeScreen.get().getProductDetails();
        assertThat(newProductTab).isNotEmpty();
        String[] url = newProductTab.split("\\?");
        assertThat(url[0].replaceAll("-"," ")
                .contains(context.getTestState("PRODUCT_NAME").toString()));
        LOGGER.info("on the product page");
        return this;
    }
}
