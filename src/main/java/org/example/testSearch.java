package org.example;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;

public class testSearch {

    public void method() throws InterruptedException, AWTException {

        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\johan\\OneDrive\\Skrivbord\\Test\\chromedriver.exe");

        // Configure the WebDriver to not accept announcements and popups
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        // Create a new instance of the Chrome driver with the configured options
        WebDriver driver = new ChromeDriver(options);

        // Maximize the window
        driver.manage().window().maximize();

        // Navigate to Facebook
        driver.get("https://www.facebook.com");


        // Accept cookies
        WebElement acceptButton = driver.findElement(By.cssSelector("button[data-cookiebanner='accept_button']"));
        acceptButton.click();;

        TimeUnit.SECONDS.sleep(1);

        File jsonFile = new File("C:\\Users\\johan\\OneDrive\\Skrivbord\\java\\openjfx-20_windows-x64_bin-sdk\\javafx-sdk-20\\lib\\testit\\src\\main\\resources\\JSON2");

        String email = null;
        String password = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonFile);

            email = jsonNode.get("facebookCredentials").get("email").asText();
            password = jsonNode.get("facebookCredentials").get("password").asText();

        } catch (IOException e) {
            e.printStackTrace();
        }


        // Enter the username and password and click the login button
        WebElement usernameField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("pass"));
        WebElement loginButton = driver.findElement(By.name("login"));

        usernameField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();

        TimeUnit.SECONDS.sleep(5);

        WebElement searchBox = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/div[3]/div/div/div[1]/div/div/label/input"));
        searchBox.sendKeys("Java");
        TimeUnit.SECONDS.sleep(1);
        searchBox.sendKeys(Keys.ENTER);

    }
}