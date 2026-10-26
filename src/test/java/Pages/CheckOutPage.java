package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckOutPage {
    Page page;

    public CheckOutPage(Page page) {
        this.page = page;
    }

    public  void fillCheckOut() {
        page.locator(".field.small")
                .filter(new Locator.FilterOptions().setHasText("CVV Code "))
                .locator(".input")
                .fill("145 980 453");

        page.locator(".field")
                .filter(new Locator.FilterOptions().setHasText("Name on Card "))
                .locator(".input")
                .fill("Master Card");

        Locator countryInput = page.getByPlaceholder("Select Country");
        countryInput.pressSequentially("India", new Locator.PressSequentiallyOptions().setDelay(100));
        page.waitForTimeout(3000);
        Locator dropdownOptions = page.locator(".ta-results");


        dropdownOptions.locator("button")
                .filter(new Locator.FilterOptions().setHas(page.locator("span").getByText(" India", new Locator.GetByTextOptions().setExact(true))))
                .click();


        page.locator(".btnn.action__submit.ng-star-inserted").click();

    }
}
