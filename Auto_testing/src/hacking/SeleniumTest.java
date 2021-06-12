
package hacking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTest {

	public static void main(String[] args) {

		try {
			SeleniumTest selTest = new SeleniumTest();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// WebDriver
	private WebDriver driver;

	// Properties
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";

	// 크롤링 할 URL
	private String base_url;

	public SeleniumTest() throws InterruptedException {
		super();

		// System Property SetUp
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

		// Driver SetUp
		driver = new ChromeDriver();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");

		base_url = "https://news.naver.com/main/read.nhn?m_view=1&mode=LSD&mid=shm&sid1=100&oid=005&aid=0001446410";

		while (true) {
			driver.get(base_url);
//			Thread.sleep(3000);
//			driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[1]/div/div[2]/div[2]")).click();
			Thread.sleep(2000);
			String comments = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[1]/div/div[2]/div[2]")).getText();
			System.out.println(comments);
		}

	}

}
