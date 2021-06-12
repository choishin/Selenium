package Study;

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
		base_url = "http://192.168.23.98:8081/Record/TableUpdate.jsp";

		while (true) {

			driver.get(base_url);
			Thread.sleep(2000);
			// �������� �Է�â�� Ű���� �ֱ�
			driver.findElement(By.xpath("/html/body/form/input[1]")).click();
			driver.findElement(By.xpath("/html/body/form/input[1]")).sendKeys("�̸�");
			Thread.sleep(1000);
		
			// �������� �Է�â�� Ű���� �ֱ�
			driver.findElement(By.xpath("/html/body/form/input[2]]")).click();
			driver.findElement(By.xpath("/html/body/form/input[2]")).sendKeys(" ");
			Thread.sleep(1000);
			// �������� �Է�â�� Ű���� �ֱ�
			driver.findElement(By.xpath("/html/body/form/input[3]]")).click();
			driver.findElement(By.xpath("/html/body/form/input[3]")).sendKeys(" ");
			Thread.sleep(1000);
			// �������� �Է�â�� Ű���� �ֱ�
			driver.findElement(By.xpath("/html/body/form/input[4]]")).click();
			driver.findElement(By.xpath("/html/body/form/input[4]")).sendKeys(" ");
			Thread.sleep(1000);
			// �������� �Է�â�� Ű���� �ֱ�
			driver.findElement(By.xpath("/html/body/form/input[5]]")).click();
			driver.findElement(By.xpath("/html/body/form/input[5]")).sendKeys(" ");
			// �� ����
			driver.close();

			// 3�� �Ŀ� WebDriver ����
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				// WebDriver ����
				driver.quit();
			}
		}
	}
}
