package alas.pages;

import alas.utilities.Driver;
import org.checkerframework.checker.signature.qual.FieldDescriptor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShoppingCartPage {

    public ShoppingCartPage () {

        PageFactory.initElements(Driver.getDriver(),this);

    }

    @FindBy(css = "[class=cart_item]")
    public List<WebElement> listOfItemsInCart;

    @FindBy(xpath = "//div[@class='cart_item_label']//a//div")
    public List<WebElement> inventoryItemName;

    @FindBy(xpath = "(//button[@class='btn btn_secondary btn_small cart_button'])[1]")
    public WebElement removeFirstItemButton;

    @FindBy(css = "[id='checkout']")
    public WebElement checkoutButton;

    @FindBy(css = "[id='first-name']")
    public WebElement firstNameInputBox;

    @FindBy(css = "[id='last-name']")
    public WebElement lastNameInputBox;

    @FindBy(css = "[id='postal-code']")
    public WebElement postalCodeInputBox;

    @FindBy(css = "[id='continue']")
    public WebElement continueButton;

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    public WebElement checkoutItem;

    @FindBy(css = "[class='inventory_item_price']")
    public WebElement itemPrice;

    @FindBy(css = "[class='summary_subtotal_label']")
    public WebElement itemTotalAmount;

    @FindBy(css = "[id='finish']")
    public WebElement finishButton;

    @FindBy(css = "[class='pony_express']")
    public WebElement verificationImage;

    @FindBy(css = "[class='complete-header']")
    public WebElement successfulMessage;

    @FindBy(css = "[class='complete-text']")
    public WebElement successfulMessage2;



}
