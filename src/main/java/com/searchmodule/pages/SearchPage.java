package com.searchmodule.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//input[@id='search_form_input_homepage' or @id='searchbox_input']")
    private WebElement searchBoxTxt;

    @FindBy(xpath = "//input[@id='search_button_homepage'] | //button[@aria-label='Search']")
    private WebElement searchBtn;

    @FindBy(linkText = "Videos")
    private WebElement videosLink;

    @FindBy(xpath = "//div[@class='tile  tile--c--w  tile--vid  has-detail  opt--t-xxs']")
    private List<WebElement> videoList;


    public SearchPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void goToSearchPage(){
        this.driver.get("https://duckduckgo.com/");
    }

    public void doSearch(String keyword){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.searchBoxTxt));
        this.searchBoxTxt.sendKeys(keyword);
        this.searchBtn.click();
    }

    public void goToVideos(){
        this.wait.until(ExpectedConditions.visibilityOf(this.videosLink));
        this.videosLink.click();
    }

    public int getResult(){
        By by = By.xpath("//div[@class='tile  tile--c--w  tile--vid  has-detail  opt--t-xxs']");
        this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 1));
        System.out.println(this.videoList.size());
        return this.videoList.size();
    }

}
