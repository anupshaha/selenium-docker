package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FlightConfirmationPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//font[contains(text(),'Flight Confirmation')]")
    private WebElement flightConfirmationId;

    @FindBy(xpath = "//font[contains(text(),'USD')]")
    private List<WebElement> prices;

    @FindBy(id = "sign-on")
    private WebElement signOffLink;

    public FlightConfirmationPage(WebDriver driver){
        this.driver= driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public String getPrice(){
        this.wait.until(ExpectedConditions.visibilityOf(flightConfirmationId));
        String price = this.prices.get(1).getText();
        System.out.println(this.flightConfirmationId.getText());
        System.out.println(price);
        this.signOff();
        return price;
    }

    public void signOff(){
        this.wait.until(ExpectedConditions.elementToBeClickable(signOffLink));
        this.signOffLink.click();
    }

}
