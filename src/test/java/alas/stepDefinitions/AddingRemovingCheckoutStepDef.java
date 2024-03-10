package alas.stepDefinitions;

import alas.pages.InventoryPage;
import alas.pages.LoginPage;
import alas.pages.ShoppingCartPage;
import alas.utilities.ConfigurationPropertiesReader;
import alas.utilities.Driver;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class AddingRemovingCheckoutStepDef {

    LoginPage loginPage = new LoginPage();

    InventoryPage inventoryPage = new InventoryPage();

    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

    String itemOneName;

    String itemTwoName;

    Faker faker = new Faker();

    int randomNumber;

    int randomNumber2;

    @When("user is on the login page")
    public void user_is_on_the_login_page() {

        Driver.getDriver().get("https://www.saucedemo.com/");

    }
    @When("user enters valid username")
    public void user_enters_valid_username() {

        loginPage.userNameInputBox.sendKeys(ConfigurationPropertiesReader.getProperty("username"));

    }
    @When("user enters valid password")
    public void user_enters_valid_password() {

        loginPage.passwordInputBox.sendKeys(ConfigurationPropertiesReader.getProperty("password"));

    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {

        loginPage.loginButton.click();

    }
    @Then("verify that user is successfully logged in")
    public void verify_that_user_is_successfully_logged_in() {

        Assert.assertEquals("Swag Labs" , Driver.getDriver().getTitle());


    }
    @When("user adds and item from the list to the cart")
    public void user_adds_and_item_from_the_list_to_the_cart() {

        randomNumber = faker.number().numberBetween(3,inventoryPage.addToCartButton.size());

        inventoryPage.addToCartButton.get(randomNumber).click();

        itemOneName = inventoryPage.inventoryItemName.get(randomNumber).getText();

    }
    @Then("verify that cart badge is updated correctly")
    public void verify_that_cart_badge_is_updated_correctly() {

        Assert.assertTrue(inventoryPage.shoppingCartBadge.isDisplayed());

    }
    @Then("user open another item's details page")
    public void user_open_another_item_s_details_page() {

        randomNumber2 = faker.number().numberBetween(0,2);

        inventoryPage.inventoryItemName.get(randomNumber2).click();

        itemTwoName = inventoryPage.inventoryDetailsItemName.getText();

    }
    @Then("user adds the item to the cart")
    public void user_adds_the_item_to_the_cart() {

        inventoryPage.addToCartDetailsPageButton.click();

        Assert.assertEquals("2", inventoryPage.shoppingCartBadge.getText());

    }
    @Then("user opens the cart")
    public void user_opens_the_cart() {

        inventoryPage.shoppingCartButton.click();

    }
    @Then("verify that the correct items are present")
    public void verify_that_the_correct_items_are_present() {

        Assert.assertEquals(2, shoppingCartPage.listOfItemsInCart.size());

        Assert.assertEquals(itemOneName, shoppingCartPage.inventoryItemName.get(0).getText());

        System.out.println(itemOneName);

        Assert.assertEquals(itemTwoName, shoppingCartPage.inventoryItemName.get(1).getText());

        System.out.println(itemTwoName);

    }

    @Then("user removes first item from the shopping cart")
    public void user_removes_first_item_from_the_shopping_cart() {

        shoppingCartPage.removeFirstItemButton.click();

        Assert.assertEquals("1", inventoryPage.shoppingCartBadge.getText());

    }
    @Then("verify that 2nd added item is present in the shopping cart")
    public void verify_that_2nd_added_item_is_present_in_the_shopping_cart() {

        Assert.assertEquals(shoppingCartPage.inventoryItemName.get(0).getText(), itemTwoName);

    }

    @Then("user clicks checkout button")
    public void user_clicks_checkout_button() {

        shoppingCartPage.checkoutButton.click();

    }
    @Then("user enters first name")
    public void user_enters_first_name() {

        shoppingCartPage.firstNameInputBox.sendKeys(faker.name().firstName());

    }
    @Then("user enters last name")
    public void user_enters_last_name() {

        shoppingCartPage.lastNameInputBox.sendKeys(faker.name().lastName());

    }
    @Then("user enters postal code")
    public void user_enters_postal_code() {

        shoppingCartPage.postalCodeInputBox.sendKeys(faker.address().zipCode());

    }
    @Then("user clicks on continue button")
    public void user_clicks_on_continue_button() {

        shoppingCartPage.continueButton.click();

        Assert.assertEquals(itemTwoName, shoppingCartPage.checkoutItem.getText());

        Assert.assertEquals(shoppingCartPage.itemPrice.getText(), shoppingCartPage.itemTotalAmount.getText().substring(12));

    }
    @Then("user clicks on finish button")
    public void user_clicks_on_finish_button() {

        shoppingCartPage.finishButton.click();

    }
    @Then("verify that order is completed successfully and message is displayed correctly")
    public void verify_that_order_is_completed_successfully_and_message_is_displayed_correctly() {

        Assert.assertTrue(shoppingCartPage.verificationImage.isDisplayed());

        Assert.assertEquals("Thank you for your order!", shoppingCartPage.successfulMessage.getText());

        Assert.assertEquals("Your order has been dispatched, and will arrive just as fast as the pony can get there!", shoppingCartPage.successfulMessage2.getText());

    }

}
