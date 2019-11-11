package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }    
    
    @Given("command new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();   
    }
    
    
    @Given("user with username {string} with password {string} is successfully created")
    public void newUserisCreated(String username, String password) {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();
        createUserWith(username, password, password);
    } 
    
    @Given("user with username {string} and password {string} is tried to be created")
    public void newUserTriedToBeCreated(String username, String password) {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();
        createUserWith(username, password, password);
    }
    
    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @When("too short username {string} and valid password {string} is given")
    public void tooShortUsernameAndValidPasswordAreGiven(String username, String password) {
        createUserWith(username, password, password);
    }     
    
    
    @When("too short username {string} and password {string} are given")
    public void tooShortUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    } 
    
    @When("correct username {string} and too short password {string} is given")
    public void correctUsernameAndTooShortPasswordIsGiven(String username, String password) {
        createUserWith(username, password, password);
    }  
     
    @When("valid username {string} and password {string} is given but confirm {string} doesn't match")
    public void validtUsernameButNotMatchingPasswords(String username, String password, String confirm) {
        createUserWith(username, password, confirm);
    }  
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    

    
    @Then("user is not created and error \"username should have at least 3 characters\" is reported")
    public void userIsNotCreatedInAndErrorMessageIsGiven() {
        pageHasContent("username should have at least 3 characters");
    }
    
    @Then("user is not created and error \"password should have at least 8 characters\" is reported")
    public void userIsNotCreatedInAndPasswordErrorMessageIsGiven() {
        pageHasContent("password should have at least 8 characters");
    }
    
    @Then("user is not created and error \"password and password confirmation do not match\" is reported")
    public void userIsNotCreatedInAndPasswordmstchErrorMessageIsGiven() {
        pageHasContent("password and password confirmation do not match");
    }
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @When("nonexistent username {string} and valid password {string} are given")
    public void nonexistentUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    } 
        
    @When("a valid username {string} and password {string} and matching password {string} confirmation are entered")
    public void validUsernameAndPasswordAndPasswordconfirmAreGiven(String username, String password, String confirm) {
        createUserWith(username, password, confirm);
    }
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }    
    
    @When("username {string} and password {string} are given")
    public void usernameAndPasswordAreGiven(String username, String password) throws Throwable {
        logInWith(username, password);
    }   
    
    @Then("system will respond {string}")
    public void systemWillRespond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    
    @Then("a new user is created")
    public void newUserIsCreated() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
    
    private void createUserWith(String username, String password, String confirm) {
        //assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confirm);
        element = driver.findElement(By.name("signup"));
        element.submit();  
    } 
}
