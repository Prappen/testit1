package org.example;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class testLog {

    public void method() throws InterruptedException, AWTException {


        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\johan\\OneDrive\\Skrivbord\\Test\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Navigate to Facebook
        driver.get("https://www.facebook.com");


        // Accept cookies
        WebElement acceptButton = driver.findElement(By.cssSelector("button[data-cookiebanner='accept_button']"));
        acceptButton.click();

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

        // Click the round profile picture in the right corner

        WebElement profilePic = driver.findElement(By.xpath("//*[@aria-label='Your profile']"));
        profilePic.click();

// Find the logout element and logout

        TimeUnit.SECONDS.sleep(1);
// Click on the "logga ut" button


        WebElement logoutButton = driver.findElement(By.xpath("//span[text()='Log Out']"));
        logoutButton.click();

    }

}