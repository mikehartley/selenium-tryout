import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/*
 * Copyright (c) 2014. Mike Hartley Solutions Ltd
 * All rights reserved.
 */

public class SignInAndOutIT {

    private WebDriver driver;

    @Before
    public void beforeEveryTest() {
        driver = new FirefoxDriver();
    }

    @After
    public void afterEveryTest() {
        driver.quit();
    }

    @Test
    public void errorMessageAfterFailedLoginAttempt() {
        driver.navigate().to("http://localhost:8080/yottr/");

        final WebElement usernameField = driver.findElement(By.id("username"));
        final WebElement passwordField = driver.findElement(By.id("password"));
        final WebElement signInButton = driver.findElement(By.id("sign-in-button"));

        usernameField.sendKeys("mike");
        passwordField.sendKeys("wrongpassword");

        signInButton.click();

        final WebElement errorMessage = driver.findElement(By.id("error-msg"));

        assertNotNull("looks like the error message didn't happen", errorMessage);
        assertEquals("Invalid username and password.", errorMessage.getText());
    }

    private void sleep() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}