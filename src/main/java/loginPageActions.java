import com.microsoft.playwright.*;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;

public class loginPageActions {

    Playwright playwright = Playwright.create();
    BrowserType browserType = playwright.chromium();
    Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(200));
   // Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
    BrowserContext context = browser.newContext();
    Page page = context.newPage();

    public void setUp() {
        page.navigate("https://app.ninjarmm.com/auth/#/login");
        String title = page.title();
        assertThat(page).hasTitle("NinjaOne");
       assertThat(page.getByAltText("logo")).isVisible();
    }

    public void tearDown() {
       // page.close();
    }

    public void exit() {
       // browser.close();
       // playwright.close();
    }

    public void enterEmail(String username) {
        assertThat(page.getByText("Email")).isVisible();
        page.locator("xpath=//*[@id=\"email\"]").fill(username);
    }

    public void enterPassword(String password) {
       assertThat(page.getByText("Password").nth(1)).isVisible();
        page.locator("xpath=//*[@id=\"password\"]").fill(password);

    }

    public void clickOnSignInButton() {
        assertThat(page.getByText("Sign in")).isVisible();
        page.getByText("Sign in").click();
    }

    public void verifySuccessfulLoginLandingScreen() {
        assertThat(page.getByText("MFA Setup")).isVisible();
        assertThat(page.getByText("Your account requires you to configure at least one form of MFA. Please select a PRIMARY MFA method below.")).isVisible();
        assertThat(page.getByText("By creating your account you are accepting the")).isVisible();
        assertThat(page.getByText("You can select a SECONDARY method within the User editor once logged in.")).isVisible();
    }

    public void verifyInvalidCredentialsLandingScreen() {
        assertThat(page.getByText("Invalid username/password. Please contact your system administrator for assistance.")).isVisible();
    }

    public void verifyKeepMeSignedInCheckBoxIsFunctional() {
        assertThat(page.getByText("Keep me signed in")).isVisible();
        Assert.assertFalse((page.getByText("Keep me signed in")).isChecked());
        page.getByText("Keep me signed in").click();
        Assert.assertTrue((page.getByText("Keep me signed in")).isChecked());
        page.getByText("Keep me signed in").click();
        Assert.assertFalse((page.getByText("Keep me signed in")).isChecked());
    }

    public void clickOnForgotYourPasswordButton() {
        assertThat(page.getByText("Forgot your password?")).isVisible();
        page.getByText("Forgot your password?").click();
        assertThat(page.getByText("Verify Identity By")).isVisible();
    }

    public void verifyForgetYourPasswordLinkIsFunctional() {
        //Note: Developer error - stack='Error: Error: Dropdown Element is not a <select> element
        //page.locator("xpath=//*[@id=\"root\"]/div/div/div/form/div[1]/div/div/div[1]").selectOption("Email");
        page.locator("xpath=//*[@id=\"root\"]/div/div/div/form/div[1]/div/div/div[1]").click();
        page.locator("xpath=//*[@id=\"root\"]/div/div/div/form/div[2]/input").fill("botesting2025@gmail.com");
        page.getByText("Send").click();
        assertThat(page.getByText("Password recovery email sent")).isVisible();
    }

    public void clickOnDoNotHaveAnAccountButton() {
        assertThat(page.getByText("Do not have an account?")).isVisible();
        page.getByText("Do not have an account?").click();
    }

    public void verifyDoNotHaveAnAccountLink() {
        assertThat(page.getByText("Registration")).isVisible();
    }

    public void enterOrganizationForRegistration(String organization) {
        assertThat(page.getByText("Organization")).isVisible();
        page.locator("xpath=//*[@id=\"root\"]/div/div/div/form/div[1]/input").fill(organization);
    }

    public void enterFirstNameForRegistration(String firstName) {
        assertThat(page.getByText("First Name")).isVisible();
        page.locator("xpath=//*[@id=\"root\"]/div/div/div/form/div[2]/div[1]/input").fill(firstName);
    }

    public void enterLastNameForRegistration(String lastName) {
        assertThat(page.getByText("Last Name")).isVisible();
        page.locator("xpath=//*[@id=\"root\"]/div/div/div/form/div[2]/div[2]/input").fill(lastName);
    }

    public void enterEmailForRegistration(String email) {
        assertThat(page.getByText("Email").nth(0)).isVisible();
        page.locator("xpath=//*[@id=\"root\"]/div/div/div/form/div[3]/input").fill(email);
    }

    public void enterPasswordForRegistration(String password) {
        assertThat(page.getByText("Password").nth(0)).isVisible();
        page.locator("xpath=//*[@id=\"root\"]/div/div/div/form/div[4]/input").fill(password);
    }

    public void enterVerifyPasswordForRegistration(String verifyPassword) {
        assertThat(page.getByText("Verify Password")).isVisible();
        page.locator("xpath=//*[@id=\"root\"]/div/div/div/form/div[5]/input").fill(verifyPassword);
    }

    public void enterCellPhoneForRegistration(String cellPhone) {
        assertThat(page.getByText("Cell Phone")).isVisible();
        page.locator("xpath=//*[@id=\"root\"]/div/div/div/form/div[6]/div/div/div/input").fill(cellPhone);
    }

    public void enterLanguageForRegistration(String language) {
       // Dev DOM error - dropdown not a select class option
        assertThat(page.getByText("Languagee")).isVisible();
    }

    public void clickRegisterButtonForRegistration() {
        assertThat(page.getByText("Register")).isVisible();
        page.locator("xpath=//*[@id=\"root\"]/div/div/div/form/button").click();
    }

    public void verifySuccessfulRegistration() {
        assertThat(page.getByText("Account successfully created. Please check your email to activate your account.")).isVisible();
    }

    public void fillUpRegistrationForm(String organization, String firstName, String lastName, String email, String password, String verifyPassword, String cellPhone, String language) {
        enterOrganizationForRegistration(organization);
        enterFirstNameForRegistration(firstName);
        enterLastNameForRegistration(lastName);
        enterEmailForRegistration(email);
        enterPasswordForRegistration(password);
        enterVerifyPasswordForRegistration(verifyPassword);
        enterCellPhoneForRegistration(cellPhone);
        enterLanguageForRegistration(language);
    }

    public void clickRegistrationRegisterButton() {
        clickRegisterButtonForRegistration();
        verifySuccessfulRegistration();
    }

    public int generateRandomNumbers() {
        Random random = new Random();
        int randomNumber = random.nextInt(100000) + 1;
        System.out.println(randomNumber);

        return randomNumber;
    }

    public void verifyPasswordCharacterLimitErrorMessageForRegistration() {
        assertThat(page.getByText("Password must be between (8) and (72) " +
                "characters and meet 3 of the following criteria: lower case letter, upper case letter, special character, number.")).isVisible();
    }

    public void verifyPasswordDoNotMatchErrorMessageForRegistration() {
        assertThat(page.getByText("Passwords do not match")).isVisible();
    }

    public void clickTab() {
        page.keyboard().press("Tab");
    }

    public void verifyPDuplicateEmailErrorForRegistration() {
        assertThat(page.getByText("Email Address has already been registered.")).isVisible();
    }

    public void verifyInvalidPhoneNumberForRegistrationError() {
        assertThat(page.getByText("Invalid phone number")).isVisible();
    }

    public void verifyRequiredMessageForBlankEntryForRegistrationError() {
        assertThat(page.getByText("Required")).isVisible();
    }
}
