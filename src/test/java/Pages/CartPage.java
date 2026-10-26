package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class CartPage {
    private final Page page;

    private final Locator cartNavButton;
    private final Locator checkoutButton;
    private final Locator spinner;

    public CartPage(Page page) {
        this.page = page;
        this.cartNavButton = page.locator("button[routerlink*='cart']");
        this.checkoutButton = page.locator(".totalRow button:has-text('Checkout'), button:has-text('Checkout')");
        this.spinner = page.locator(".ngx-spinner-overlay, ngx-spinner");
    }

    public void verifyCartPage() {
        System.out.println("Handling Cart navigation on Rahul Shetty Client App...");

        // 1. Wait for any active spinner to detach or become invisible
        try {
            spinner.first().waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.DETACHED)
                    .setTimeout(5000));
        } catch (Exception ignored) {
            // Spinner might already be gone
        }

        // 2. Click the Cart button via JS evaluation to bypass any toastr or invisible overlay
        cartNavButton.first().evaluate("el => el.click()");

        // 3. Wait for the cart URL to load
        page.waitForURL("**/dashboard/cart", new Page.WaitForURLOptions().setTimeout(10000));

        // 4. Wait for the checkout button to be attached and visible
        checkoutButton.first().waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(10000));

        System.out.println("Cart page loaded and Checkout button is ready.");
    }

    public void CheckoutButton() {
        System.out.println("Clicking Checkout button...");

        // Wait for any remaining animation and force-click the checkout button
        checkoutButton.first().waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(8000));
        checkoutButton.first().click(new Locator.ClickOptions().setForce(true));

        System.out.println("Checkout clicked successfully.");
    }
}