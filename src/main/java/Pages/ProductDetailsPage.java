package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends BasePage {
    /**
     * Instantiates a new base page.
     *
     * @param driver the driver
     */
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath= "//a[@id='atcBtn_btn_1']")
    private WebElement clickAddToCart;

    @FindBy(className = "gh-cart__icon")
    private WebElement addToCartlOGO;





    public void ClickOnaddcartButton()
    {
        clickAddToCart.click();
    }
    public String GetAddToCartCount()
    {
        return   addToCartlOGO.getAttribute("aria-label");

    }


}
