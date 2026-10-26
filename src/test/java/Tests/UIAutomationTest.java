package Tests;

import Pages.*;
import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class UIAutomationTest extends TestBase {

    @Test
    public void testLogin() {
        System.out.println(page.title());
        LoginPage loginPage = new LoginPage(page, base_url);
        DashBoardPage dashBoardPage = loginPage.loginToApplication();
        AddItemsCart addItemsCart = new AddItemsCart(page);
        addItemsCart.AddItems();

        //Go to Cart
        CartPage cartPage = new CartPage(page);
        cartPage.verifyCartPage();

        //Buy Now
        cartPage.CheckoutButton();


        //Checkout
        CheckOutPage checkOutPage = new CheckOutPage(page);
        checkOutPage.fillCheckOut();

        //View Order Confirmation
        OrderConfirmation confirmOrder = new OrderConfirmation(page);
        confirmOrder.VerifyOrderPage();

    }


}
