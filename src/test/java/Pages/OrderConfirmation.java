package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class OrderConfirmation {
    Page page;

    public OrderConfirmation(Page page) {
        this.page = page;
    }

    public void VerifyOrderPage(){
        page.locator("ul").filter(new Locator.FilterOptions().setHasText("  ORDERS")).click();

        String orderId = page.locator("tr.ng-star-inserted")
                .filter(new Locator.FilterOptions().setHasText("ADIDAS ORIGINAL"))
                .first().textContent().replace("|", "").trim();

        System.out.println("Order ID: " + orderId);

        Locator rows = page.locator("tbody tr");
        rows.first().waitFor();

        Locator targetRow = rows.filter(new Locator.FilterOptions().setHasText(orderId));

        assertThat(targetRow).isVisible();

        targetRow.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Delete")).click();

    }
}
