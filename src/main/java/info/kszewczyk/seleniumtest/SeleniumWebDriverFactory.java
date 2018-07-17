package info.kszewczyk.seleniumtest;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class SeleniumWebDriverFactory {

    public WebDriver create() {
        String urlOpt = RunOptions.instance().get(RunOptions.url);
        String senvOpt = RunOptions.instance().get(RunOptions.seleniumRunEnvironment);
        Optional<String> capability = Optional.ofNullable(RunOptions.instance().get(RunOptions.capability));
        Optional<Integer> timeout = Optional.ofNullable(RunOptions.instance().get(RunOptions.timeout));
        try {
            URL senv = new URL(senvOpt);
            URL url = new URL(urlOpt);//for checking url format
            WebDriver driver = new RemoteWebDriver(senv, capabilities(capability.orElse("chrome")));
            driver.manage().timeouts().implicitlyWait(timeout.orElse(30), TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.get(url.toString());
            return driver;
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Malformed url", e);
        }
    }

    private MutableCapabilities capabilities(String capability) {
        switch (capability) {
            case "firefox":
                return new FirefoxOptions();
            case "ie":
                return new InternetExplorerOptions();
            case "edge":
                return new EdgeOptions();
            case "opera":
                return new OperaOptions();
            case "safari":
                return new SafariOptions();
            default:
                return new ChromeOptions();
        }
    }

}
