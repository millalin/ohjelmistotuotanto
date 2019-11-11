package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        final String URL = "http://localhost:4567";
        driver.get(URL);
        
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);
        
        Random r = new Random();
    
        element = driver.findElement(By.name("username"));
        element.sendKeys("milla"+r.nextInt(100000));

        //element = driver.findElement(By.name("username"));
        //element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("salainen1");
        element = driver.findElement(By.name("passwordConfirmation"));

        element.sendKeys("salainen1");

        element = driver.findElement(By.name("signup"));
        element.click();
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        element = driver.findElement(By.linkText("logout"));
        
        sleep(2);
        element.click();

        sleep(3);
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
    
    
}
