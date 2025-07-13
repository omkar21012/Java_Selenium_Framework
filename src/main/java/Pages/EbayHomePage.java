package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class EbayHomePage extends BasePage {


    @FindBy(xpath = "//input[@title='Search']")
    private WebElement searchBox;
    @FindBy(css = "[id='gh-search-btn'] ")
    private WebElement searchButton;
    @FindBy(xpath = "//ul[@class='srp-results srp-list clearfix']//div[@class='s-item__title']")
    private List<WebElement> booksResults;

    /**
     * Instantiates a new base page.
     *
     * @param driver the driver
     */
    public EbayHomePage(WebDriver driver) {
        super(driver);


    }

    public void SearchText(String Input) {
        searchBox.click();
        searchBox.sendKeys(Input);

    }
    public boolean isSearchBoxEnabled() {
        searchBox.isDisplayed();
        return false;
    }



    public void ClickOnSearchButton() {
        searchButton.click();
    }

    public void SelectFirstResults(int indexnumber) {

        List<WebElement> results = (List<WebElement>) booksResults;
        WebElement a = results.get(indexnumber);
        a.click();
    }


}
