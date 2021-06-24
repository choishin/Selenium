package headLine;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Test {

	// ���� package�� workspace ���, Windows�� [ chromedriver.exe ]
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";
	private static String base_url;

	public static void main(String[] args) throws InterruptedException {
		
		try {
		// WebDriver ��� ����
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

		// WebDriver �ɼ� ����
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized"); // ��üȭ������ ����
		options.addArguments("--disable-popup-blocking"); // �˾� ����
		options.addArguments("--disable-default-apps"); // �⺻�� ������

		// WebDriver ��ü ����
		ChromeDriver driver = new ChromeDriver(options);

		// �������� ��û
		base_url = "https://news.naver.com/";

			driver.get(base_url);
			Thread.sleep(2000);
			// ����� ������ 5�� ������ ����
			for (int i=1; i<=5; i++) {
				String title = driver.findElement(By.xpath("/html/body/div/div[6]/div[1]/div/div[1]/div[2]/ul/li["+i+"]")).getText();
				Thread.sleep(2000);
				System.out.println(title);
			}
					
//			// �������� �Է�â�� Ű���� �ֱ�
//			driver.findElement(By.xpath("/html/body/form/span/input[1]")).click();
//			driver.findElement(By.xpath("/html/body/form/input[2]")).sendKeys(" ");
//			Thread.sleep(1000);
//			// �������� �Է�â�� Ű���� �ֱ�
//			driver.findElement(By.xpath("/html/body/form/input[3]]")).click();
//			driver.findElement(By.xpath("/html/body/form/input[3]")).sendKeys(" ");
//			Thread.sleep(1000);
//			// �������� �Է�â�� Ű���� �ֱ�
//			driver.findElement(By.xpath("/html/body/form/input[4]]")).click();
//			driver.findElement(By.xpath("/html/body/form/input[4]")).sendKeys(" ");
//			Thread.sleep(1000);
//			// �������� �Է�â�� Ű���� �ֱ�
//			driver.findElement(By.xpath("/html/body/form/input[5]]")).click();
//			driver.findElement(By.xpath("/html/body/form/input[5]")).sendKeys(" ");
//			// �� ����
			driver.close();
			driver.quit();

			// 3�� �Ŀ� WebDriver ����

			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
	}
}
	

