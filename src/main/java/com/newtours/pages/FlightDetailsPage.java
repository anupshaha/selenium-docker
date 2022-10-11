package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FlightDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "passCount")
    private WebElement passengerDrpDown;

    @FindBy(name = "findFlights")
    private WebElement continueBtn;

    public FlightDetailsPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void selectPassengers(String numberOfPassengers){
        this.wait.until(ExpectedConditions.elementToBeClickable(passengerDrpDown));
        Select select = new Select(passengerDrpDown);
        select.selectByValue(numberOfPassengers);
    }

    public void goToFindFlightsPage(){
        this.continueBtn.click();
    }

}
