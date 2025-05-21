package app_tests; // Declares the package for this test class

// Import required Selenium and JUnit classes
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.List;

// Specifies the order in which test methods should run
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SeleniumTests {
    private WebDriver driver; // WebDriver to control the browser
    private WebDriverWait wait; // WebDriverWait for explicit waits
    private Actions actions; // Actions class for simulating complex user gestures

    private static String testUsername; // Shared test username between tests

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver(); // Initializes the ChromeDriver
        driver.manage().window().maximize(); // Maximizes the browser window
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Sets up explicit wait
        actions = new Actions(driver); // Initializes actions for user interactions

        // Navigate to the OrangeHRM login page
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Login to the application
        wait.until(ExpectedConditions.elementToBeClickable(By.name("username"))).sendKeys("Admin"); // Enter username
        driver.findElement(By.name("password")).sendKeys("admin123"); // Enter password
        driver.findElement(By.cssSelector("button[type='submit']")).click(); // Click login button

        // Navigate to the Admin section after login
        WebElement admin_page = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Admin']")));
        admin_page.click(); // Click on the Admin page

        // Wait for the Add button to be visible (Admin user page loaded)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Add')]")));
    }

    // Helper method to create a new user
    private String createUser(String desiredUsername) {
        driver.findElement(By.xpath("//button[contains(.,'Add')]")).click(); // Click on the Add button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[normalize-space()='Add User']"))); // Wait for the Add User form

        // Select User Role
        WebElement role = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[contains(@class,'oxd-select-text--active')])[1]")));
        role.click(); // Click to open dropdown
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option']//span[normalize-space()='ESS']"))).click(); // Select 'ESS'

        // Select Status
        WebElement status = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[contains(@class,'oxd-select-text--active')])[2]")));
        status.click(); // Click to open dropdown
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option']//span[normalize-space()='Enabled']"))).click(); // Select 'Enabled'

        // Input employee name (partial, to get suggestions)
        WebElement employee_name = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@placeholder='Type for hints...']")));
        employee_name.clear(); // Clear input
        employee_name.sendKeys("S"); // Enter a letter to generate hints

        // Wait for suggestions and select the first one
        By suggestions_locator = By.xpath("//div[@role='listbox']//div[@role='option']//span");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(suggestions_locator, 0));
        List<WebElement> suggestions = driver.findElements(suggestions_locator);
        actions.moveToElement(suggestions.get(0)).click().perform(); // Select first suggestion

        // Enter Username
        driver.findElement(By.xpath("//label[normalize-space()='Username']/../following-sibling::div//input"))
                .sendKeys(desiredUsername);

        // Enter Password
        driver.findElement(By.xpath("//label[normalize-space()='Password']/../following-sibling::div//input"))
                .sendKeys("Ivan223260");

        // Confirm Password
        driver.findElement(By.xpath("//label[normalize-space()='Confirm Password']/../following-sibling::div//input"))
                .sendKeys("Ivan223260");

        // Click Save
        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();

        // Wait for success toast
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".oxd-toast.oxd-toast--success")));

        // Verify success toast appears
        Assertions.assertTrue(toast.getText().contains("Success"), "User was not added successfully!");

        return desiredUsername; // Return created username
    }

    @Test
    @Order(1)
    public void AddUser() {
        testUsername = createUser("IvanPupin"); // Create a user and store username
    }

    @Test
    @Order(2)
    public void DeleteUser() {
        Assertions.assertNotNull(testUsername, "Test username was not set from AddUser test!"); // Ensure username was set

        driver.navigate().refresh(); // Refresh the page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='table']"))); // Wait for table to load

        WebElement target_row = null; // Placeholder for the row of the user to delete

        // Get all user rows
        List<WebElement> rows = driver.findElements(By.xpath("//div[@role='row'][.//div[@role='cell'][2]]"));

        // Search for the row with the matching username
        for (WebElement row : rows) {
            String username = row.findElement(By.xpath(".//div[@role='cell'][2]//div")).getText().trim();
            if (testUsername.equals(username)) {
                target_row = row;
                break;
            }
        }

        Assertions.assertNotNull(target_row, "User to delete not found: " + testUsername); // Ensure the user was found

        // Click delete button
        WebElement delete_button = target_row.findElement(By.xpath(".//button[.//i[contains(@class,'bi-trash')]]"));
        delete_button.click();

        // Confirm delete action
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Yes, Delete']"))).click();

        // Wait for success message
        WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".oxd-toast.oxd-toast--success")));

        // Assert the delete operation was successful
        Assertions.assertTrue(successToast.getText().toLowerCase().contains("success"),
                "Expected a success toast after deleting user " + testUsername);

        // Ensure user no longer appears in the list
        List<WebElement> remaining = driver.findElements(By.xpath(
                "//div[@role='row']//div[@role='cell'][2]//div[normalize-space()='" + testUsername + "']"));
        Assertions.assertTrue(remaining.isEmpty(),
                "User " + testUsername + " still appears in the list after deletion");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Quit the browser after each test
        }
    }
}
