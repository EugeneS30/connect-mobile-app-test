package au.edu.wa.education.connect.mobile.connectnow.test.functional.configuration;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan({"au.edu.wa.education.connect.mobile.connectnow.test.functional"})
@Configuration
@PropertySource({"classpath:connectnow.properties"})
public class AndroidDriverConfiguration {
    
    @Value("${driver.implicitlyWait.time:500}")
    private long implicitWaitTime;
    
    @Bean
    public AndroidDriver driver() {
        AndroidDriver driver = new AndroidDriver(remoteAddress, desiredCapabilities);
    }

    @Before
    public void setup() {

        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "/ConnectNow");
        // folder>/Contact Manager
        // File app = new File(appDir, "android-armv7-debug.apk");
        File app = new File(appDir, "android-x86-debug.apk");
        //

        DesiredCapabilities capabilities = new DesiredCapabilities();

        // capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.VERSION, "4.4.2");
        // capabilities.setCapability(MobileCapabilityType.VERSION, "6.0");
        // capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "079f9c180591eb97");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator_5554");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "3000");
        // capabilities.setCapability(MobileCapabilityType.APP_PACKAGE,
        // "au.edu.wa.education.connect.mobile.connectnow");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, ".MainActivity");
        capabilities.setCapability(MobileCapabilityType.SUPPORTS_NETWORK_CONNECTION, "True");
        capabilities.setCapability(MobileCapabilityType.PROXY, "10.25.7.10");
        // capabilities.setCapability("appPackage", "com.android.chrome");
        // capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        // capabilities.setCapability(MobileCapabilityType.APP_PACKAGE,
        // "com.example.android.contactmanager");
        // capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, ".ContactManager");

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // AndroidDriver driver = new AndroidDriver(remoteAddress, capabilities);

    }

    @Test
    public void test() throws InterruptedException {

        Thread.sleep(10000);
        List<WebElement> buttons = driver.findElementsByClassName("android.widget.Button");

        for (WebElement button : buttons) {
            if (button.getAttribute("name").equals("Next slide")) {
                while (button.isEnabled()) {
                    // driver.tap(1, button.getLocation().x, button.getLocation().y, 1);
                    driver.tap(1, 900, 1596, 1);
                    // button.click();
                    Thread.sleep(1000);
                }

            }

        }

        // WebElement el = driver.findElement(MobileBy.tagName("content-desc"));
        // System.out.println(el.toString());
        // List<WebElement> buttons2 = driver.findElementsByTagName("content-desc");
        // List<WebElement> buttons1 = driver.findElementsById("ext-cbutton-16");
        //
        // System.out.println(el.toString());

        int buttonCount = 0;
        for (WebElement button : buttons) {
            buttonCount++ ;
            System.out.println(button.getLocation());
        }
        System.out.println(String.format("%s buttons found.", buttonCount));

        int buttonCount1 = 0;
        // for (WebElement button : buttons1) {
        // buttonCount1++ ;
        // System.out.println(button.getLocation());
        // }
        System.out.println(String.format("%s buttons found.", buttonCount1));

        // System.out.println(buttons.get(0).getLocation());

        // driver.tap(1, buttons.get(0), 1);
        driver.tap(1, 900, 75, 1);
        driver.tap(1, 900, 75, 1);
        // WebElement skip = driver.findElementByCssSelector("#ext-element-214");
        // skip.click();

        System.out.println("test start!");
        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            System.out.println(contextNames); // prints out something like NATIVE_APP \n WEBVIEW_1
        }

        // buttons.get(0).click();
        // buttons.get(1).click();
        // buttons.get(2).click();

        Thread.sleep(3000);

        List<WebElement> fields = driver.findElementsByClassName("android.widget.EditText");
        fields.get(0).sendKeys("e2023069");
        driver.hideKeyboard();
        fields.get(1).sendKeys("789Learn");
        driver.hideKeyboard();

        List<WebElement> views = driver.findElementsByClassName("android.view.View");
        int i = 0;
        for (WebElement view : views) {
            view.click();
        }

        Thread.sleep(5000);

    }

    // @Test
    public void addContact() throws Exception {
        WebElement addContactButton = driver.findElement(By.name("Add Contact"));
        addContactButton.click();
        List<WebElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
        textFieldsList.get(0).sendKeys("Some Name");
        textFieldsList.get(2).sendKeys("Some@example.com");
        driver.findElementByName("Save").click();
    }

    @After
    public void tearDown() {
        System.out.println("done!!");
        driver.quit();

    }

}
