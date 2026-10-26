    package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AddItemsCart {
    Page page;

    public AddItemsCart(Page page) {
        this.page = page;
    }

    public void AddItems() {
        page.locator(".card")
                .filter(new Locator.FilterOptions().setHasText("ADIDAS ORIGINAL"))
                .locator(".fa.fa-eye").first()
                .click();

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add To Cart")).click();
        System.out.println("Add to cart button clicked!");
    }
}
