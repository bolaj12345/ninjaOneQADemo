import org.testng.annotations.*;

public class loginPageTest {

    loginPageActions loginPage = new loginPageActions();

    @BeforeMethod()
    public void beforeTest() {
        loginPage.setUp();
    }

    @Test(priority = 0)
    public void loginTest_ValidCredentials() {
        try {
            loginPage.enterEmail("botesting2025@gmail.com");
            loginPage.enterPassword("PASSWORDautomationtest2025");
            loginPage.clickOnSignInButton();
            loginPage.verifySuccessfulLoginLandingScreen();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 1)
    public void loginTest_InvalidCredentials() {
        try {
            loginPage.enterEmail("botesting2025@gmail.com");
            loginPage.enterPassword("invalidPASSWORD");
            loginPage.clickOnSignInButton();
            loginPage.verifyInvalidCredentialsLandingScreen();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 2)
    public void loginTest_verifyKeepMeSignedInCheckboxState() {
        try {
            loginPage.enterEmail("botesting2025@gmail.com");
            loginPage.enterPassword("invalidPASSWORD");
            loginPage.verifyKeepMeSignedInCheckBoxIsFunctional();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 3)
    public void loginTest_forgetYourPasswordLinkVerification() {
        try {
            loginPage.clickOnForgotYourPasswordButton();
            loginPage.verifyForgetYourPasswordLinkIsFunctional();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 4)
    public void loginTest_doNotHaveAnAccountLinkVerification() {
        try {
            loginPage.clickOnDoNotHaveAnAccountButton();
            loginPage.verifyDoNotHaveAnAccountLink();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 5)
    public void loginTest_createNewValidAccount() {
        try {
            loginPage.clickOnDoNotHaveAnAccountButton();
            loginPage.fillUpRegistrationForm("Ninja", "Ninja",
                    "Test", loginPage.generateRandomNumbers() + "ntest+@gmail.com", "PASSword12345*", "PASSword12345*", "1234567890", "English");
            loginPage.clickRegistrationRegisterButton();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 6)
    public void loginTest_verifyRegistrationPasswordCharacterCriteria() {
        try {
            loginPage.clickOnDoNotHaveAnAccountButton();
            loginPage.enterOrganizationForRegistration("organization");
            loginPage.enterFirstNameForRegistration("firstName");
            loginPage.enterLastNameForRegistration("lastName");
            loginPage.enterEmailForRegistration(loginPage.generateRandomNumbers() + "ntest+@gmail.com");
            loginPage.enterPasswordForRegistration("12345");
            loginPage.clickTab();
            loginPage.verifyPasswordCharacterLimitErrorMessageForRegistration();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 7)
    public void loginTest_verifyRegistrationPasswordDoNotMatchError() {
        try {
            loginPage.clickOnDoNotHaveAnAccountButton();
            loginPage.enterOrganizationForRegistration("organization");
            loginPage.enterFirstNameForRegistration("firstName");
            loginPage.enterLastNameForRegistration("lastName");
            loginPage.enterEmailForRegistration(loginPage.generateRandomNumbers() + "ntest+@gmail.com");
            loginPage.enterPasswordForRegistration("PASSword12345*");
            loginPage.enterVerifyPasswordForRegistration("PASSword123");
            loginPage.clickTab();
            loginPage.verifyPasswordDoNotMatchErrorMessageForRegistration();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 8)
    public void loginTest_verifyDuplicateEmailForRegistration() {
        try {
            loginPage.clickOnDoNotHaveAnAccountButton();
            loginPage.fillUpRegistrationForm("Ninja", "Ninja",
                    "Test", "botesting2025@gmail.com", "PASSword12345*", "PASSword12345*", "1234567890", "English");
            loginPage.clickRegisterButtonForRegistration();
            loginPage.verifyPDuplicateEmailErrorForRegistration();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 9)
    public void loginTest_verifyDInvalidPhoneErrorForRegistration() {
        try {
            loginPage.clickOnDoNotHaveAnAccountButton();
            loginPage.fillUpRegistrationForm("Ninja", "Ninja",
                    "Test", "botesting2025@gmail.com", "PASSword12345*", "PASSword12345*", "1234567890234", "English");
            loginPage.clickTab();
            loginPage.verifyInvalidPhoneNumberForRegistrationError();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 10)
    public void loginTest_verifyDRequiredBlankEntryForOrganizationColumnForRegistration() {
        try {
            loginPage.clickOnDoNotHaveAnAccountButton();
            loginPage.fillUpRegistrationForm("", "Ninja",
                    "Test", "botesting2025@gmail.com", "PASSword12345*", "PASSword12345*", "1234567890", "English");
            loginPage.clickRegisterButtonForRegistration();
            loginPage.verifyRequiredMessageForBlankEntryForRegistrationError();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 11)
    public void loginTest_verifyDRequiredBlankEntryForFirstNameColumnForRegistration() {
        try {
            loginPage.clickOnDoNotHaveAnAccountButton();
            loginPage.fillUpRegistrationForm("Organization", "",
                    "Test", "botesting2025@gmail.com", "PASSword12345*", "PASSword12345*", "1234567890", "English");
            loginPage.clickRegisterButtonForRegistration();
            loginPage.verifyRequiredMessageForBlankEntryForRegistrationError();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 12)
    public void loginTest_verifyDRequiredBlankEntryForLastNameColumnForRegistration() {
        try {
            loginPage.clickOnDoNotHaveAnAccountButton();
            loginPage.fillUpRegistrationForm("Organization", "Ninja",
                    "", "botesting2025@gmail.com", "PASSword12345*", "PASSword12345*", "1234567890", "English");
            loginPage.clickRegisterButtonForRegistration();
            loginPage.verifyRequiredMessageForBlankEntryForRegistrationError();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}