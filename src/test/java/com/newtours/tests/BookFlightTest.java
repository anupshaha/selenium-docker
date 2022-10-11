package com.newtours.tests;

import com.newtours.pages.*;
import com.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BookFlightTest extends BaseTest {

    private String noOfPassengers;
    private String expectedPrice;

    @BeforeTest
    @Parameters({"noOfPassengers", "expectedPrice"})
    public void setupParameters(String noOfPassengers, String expectedPrice){
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;
    }

    @Test
    public void registrationPageTest(){
        RegistrationPage registrationPage = new RegistrationPage(this.driver);
        registrationPage.goTo();
        registrationPage.enterUserDetails("Selenium", "Docker");
        registrationPage.enterUserCredentials("Selenium", "Docker");
        registrationPage.submit();
    }

    @Test(dependsOnMethods = "registrationPageTest")
    public void registrationConfirmationPageTest(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(this.driver);
        registrationConfirmationPage.goToFlightsDetailsPage();
    }

    @Test(dependsOnMethods="registrationConfirmationPageTest")
    public void flightDetailsPageTest(){
        FlightDetailsPage flightDetailsPage = new FlightDetailsPage(this.driver);
        flightDetailsPage.selectPassengers(noOfPassengers);
        flightDetailsPage.goToFindFlightsPage();
    }

    @Test(dependsOnMethods = "flightDetailsPageTest")
    public void findFlightsPageTest(){
        FindFlightsPage findFlightsPage = new FindFlightsPage(this.driver);
        findFlightsPage.submitFindFlightsPage();
        findFlightsPage.goToFlightConfirmationPage();
    }


    @Test(dependsOnMethods = "findFlightsPageTest")
    public void flightConfirmationPageTest(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(this.driver);
        String price = flightConfirmationPage.getPrice();
        Assert.assertEquals(price, expectedPrice);
    }
}
