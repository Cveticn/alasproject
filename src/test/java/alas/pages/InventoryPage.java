package alas.pages;

import alas.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryPage {

    public InventoryPage () {

        PageFactory.initElements(Driver.getDriver(),this);

    }

    @FindBy(xpath = "//button[@class='btn btn_primary btn_small btn_inventory ']")
    public List<WebElement> addToCartButton;

    @FindBy(xpath = "//div[@class='inventory_item_label']//a//div")
    public List<WebElement> inventoryItemName;

    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    public WebElement shoppingCartBadge;

    @FindBy(xpath = "//button[@class='btn btn_primary btn_small btn_inventory']")
    public WebElement addToCartDetailsPageButton;

    @FindBy(css = "[class='inventory_details_name large_size']")
    public WebElement inventoryDetailsItemName;

    @FindBy(css = "[class='shopping_cart_link']")
    public WebElement shoppingCartButton;

}
