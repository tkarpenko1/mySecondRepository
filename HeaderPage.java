package com.dataart.selenium.pages;

import org.openqa.selenium.By;
import java.util.NoSuchElementException;

public class HeaderPage extends BasicPage {

    public String getWelcomeMessage() {
        return driver.findElement(By.xpath("//div[@class='header']/div[@class='welcome']")).getText();
    }

    public String getApplicationMessage() {
        return driver.findElement(By.xpath("//div[@class='content']/h1")).getText();
    }

    public boolean isApplicationPresent(){
        try {
            driver.findElement(By.xpath("//div[@class='apps']"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean isMyApplicationPresent(){
        try {
            driver.findElement(By.xpath("//a[.='My applications']"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
