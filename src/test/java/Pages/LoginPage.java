package Pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    Page page;
    String base_url;
    String EMAIL_PLACEHOLDER = "email@example.com";
    String PASSWORD_PLACEHOLDER = "enter your passsword";

    public DashBoardPage loginToApplication() {
        page.navigate(base_url);
        System.out.println(page.title());
        page.getByPlaceholder(EMAIL_PLACEHOLDER).fill("Donald@23gmail.com");
        page.getByPlaceholder(PASSWORD_PLACEHOLDER).fill("Donald@23");
        page.getByRole(com.microsoft.playwright.options.AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        return new DashBoardPage(page);
    }

    public LoginPage(Page page, String baseUrl) {
        this.page = page;
        this.base_url = baseUrl;
    }
}
