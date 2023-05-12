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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class testposte {

    Logger logger = LoggerFactory.getLogger(testposte.class);

    public void method() throws InterruptedException {
        String post = "test.1";
        String email = null;
        String password = null;

        try {
            File jsonFile = new File("C:\\Users\\johan\\OneDrive\\Skrivbord\\java\\openjfx-20_windows-x64_bin-sdk\\javafx-sdk-20\\lib\\testit\\src\\main\\resources\\JSON2");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonFile);

            email = jsonNode.get("facebookCredentials").get("email").asText();
            password = jsonNode.get("facebookCredentials").get("password").asText();

        } catch (IOException e) {
            logger.error("Failed to read JSON file oh noo");
            e.printStackTrace();
        }

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\johan\\OneDrive\\Skrivbord\\Test\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        driver.get("https://facebook.com");


        WebElement acceptButton = driver.findElement(By.cssSelector("button[data-cookiebanner='accept_button']"));
        acceptButton.click();


        // Enter the username and password and click the login button
        WebElement usernameField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("pass"));
        WebElement loginButton = driver.findElement(By.name("login"));

        usernameField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();

        Thread.sleep(3000);
        WebElement divElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div/div/div/div[1]/div[1]/div/div[2]/div/div/div/div[3]/div/div[2]/div/div/div/div[1]/div/div[1]"));
// Perform actions on the element
        divElement.click();
        Thread.sleep(3000);

        WebElement composepost = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[4]/div/div/div[1]/div/div[2]/div/div/div/form/div/div[1]/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div[1]/p"));
        composepost.sendKeys(post);

        Thread.sleep(3000);

        WebElement postButton = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[4]/div/div/div[1]/div/div[2]/div/div/div/form/div/div[1]/div/div/div/div[3]/div[2]/div/div/div[1]"));
        postButton.click();


        Thread.sleep(3000);

        WebElement latestPost = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div/div/div/div[1]/div[1]/div/div[2]/div/div/div/div[3]/div/div[3]/div/div/div[1]/div/div/div/div/div/div/div/div/div/div/div/div/div[8]/div/div/div[3]/div/div/div/div"));
        String latestPostText = latestPost.getText();

        Thread.sleep(5000);

        if (latestPostText.equals(post)) {
            logger.info("Test of Facebook post is a success: " + latestPostText);

        } else {
            logger.error("Test of Facebook post failed: expected '" + post + "', but got '" + latestPostText + "'");


        }

        driver.quit();
    }
}