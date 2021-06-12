package Vaccine;

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
			base_url = "https://ncv.kdca.go.kr/mainStatus.es?mid=a11702000000";

			driver.get(base_url);
			Thread.sleep(2000);
			// �������� �Է�â�� Ű���� �ֱ�
			String daily_result = driver.findElement(By.xpath("/html/body/div/div[3]")).getText();
			Thread.sleep(1000);
			System.out.println(daily_result);
			// �� ����
			driver.close();
			// 3�� �Ŀ� WebDriver ����

			Thread.sleep(3000);
			driver.quit();

		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}
}
