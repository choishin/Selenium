package Study;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
 
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
 
public class App {
	
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";
    
    public static void main(String[] args) {
        
        // ���� package�� workspace ���, Windows�� [ chromedriver.exe ]
       
        
        // WebDriver ��� ����
    	System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        
        // WebDriver �ɼ� ����
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");            // ��üȭ������ ����
        options.addArguments("--disable-popup-blocking");    // �˾� ����
        options.addArguments("--disable-default-apps");     // �⺻�� ������
        
        // WebDriver ��ü ����
        ChromeDriver driver = new ChromeDriver( options );
        
        // �� �� ����
        driver.executeScript("window.open('about:blank','_blank');");
        
        // �� ��� ��������
        List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        
        
        
        // ù��° ������ ��ȯ
        driver.switchTo().window(tabs.get(0));
        
        // �������� ��û
        driver.get("https://heodolf.tistory.com/101");
        
        // ������������ ������ ��������
        WebElement page1_title = driver.findElementByXPath("//*[@id=\"content\"]/div[1]/div[1]/div/h1");
        if( page1_title != null  ) {
            System.out.println( page1_title.getText() );            
        }
        // �������� �ҽ� ���
        //System.out.println( driver.getPageSource() );
        
        // �� ����
        driver.close();
        
        
        
        // �ι�° ������ ��ȯ
        driver.switchTo().window(tabs.get(1));
        
        // �������� ��û
        driver.get("https://heodolf.tistory.com/102");
        
        // ������������ ������ ��������
        WebElement page2_title = driver.findElementByXPath("//*[@id=\"content\"]/div[1]/div[1]/div/h1");
        if( page1_title != null  ) {
            System.out.println( page2_title.getText() );            
        }
        
        // �������� �ҽ� ���
        //System.out.println( driver.getPageSource() );
        
        // �� ����
        driver.close();
        
        
        
        // 5�� �Ŀ� WebDriver ����
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // WebDriver ����
            driver.quit();
        }
    }
}

