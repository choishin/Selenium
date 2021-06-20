package gourmet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Test {

	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";
	public static String base_url;

	public static void main(String[] args) throws InterruptedException, IOException {

//		fileMake();
//		headWrite();
		ReadData();
//		fileClose();

	}

	static int rowsCnt;
	public static BufferedWriter bfw;
	public static boolean isFileExist;

	static void ReadData() throws IOException {

		try {

			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized"); // ��üȭ������ ����
			options.addArguments("--disable-popup-blocking"); // �˾� ����
			options.addArguments("--disable-default-apps"); // �⺻�� ������

			ChromeDriver driver = new ChromeDriver(options);
			base_url = "https://m.map.naver.com/";
//			 base_url =
//			 "https://m.store.naver.com/restaurants/21025196/tabs/menus/baemin/list?more=false&entry=ple";
			driver.get(base_url);

			// �Է�â�� �˻��� ������� �־ ��� ������ ����
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[2]/header/div[4]/div/div/div/span[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/form/div/div[2]/div/span[1]/input"))
					.sendKeys("������ �ѽ� ����");
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/form/div/div[2]/div/span[1]/input"))
					.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			// ������ ����
			//9��° ������ 
			driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li[9]/div[1]/a[2]")).click();
			String currentURL = driver.getCurrentUrl();
			driver.get(currentURL);
			Thread.sleep(2000);
			WebElement menuBar = null;
			int menuCnt = 0;
			String menuTable = null;
			if (driver.findElements(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div/div/div/div")).size() > 0) {
				menuBar = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div/div/div/div"));
				if (menuBar.findElements(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div/div/div/div/a"))
						.size() > 0) {
					menuCnt = menuBar
							.findElements(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div/div/div/div/a")).size();
					for (int iCnt = 1; iCnt <= menuCnt; iCnt++) {
						String menu = menuBar
								.findElement(By.xpath(
										"/html/body/div[3]/div/div[2]/div[3]/div/div/div/div/a[" + iCnt + "]"))
								.getText();
						if (menu.contains("�޴�")) {
							menuBar.findElement(
									By.xpath("/html/body/div[3]/div/div[2]/div[3]/div/div/div/div/a[" + iCnt + "]"))
									.click();
						}
					}
				}
			}
			Thread.sleep(3000);
			String innerURL = driver.getCurrentUrl();
			driver.get(innerURL);	
			Thread.sleep(3000);
			String context = driver.findElement(By.xpath("/html/body")).getText();
			System.out.println(context);
			Thread.sleep(3000);

			driver.navigate().back();
			driver.navigate().back();
			Thread.sleep(3000);
			driver.close();
			Thread.sleep(2000);
			driver.quit();

		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}
}

//	static void fileMake() {
//		try {
//			File file = new File("vaccine_daily.csv");
//			if (file.exists() == false) {
//				isFileExist = false;
//			} else {
//				isFileExist = true;
//			}
//			bfw = new BufferedWriter(new FileWriter(file, true));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.out.println(e);
//		}
//	}
//
//	static void headWrite() throws IOException {
//
//		if (isFileExist == false) {
//			String head = "����," + "����," + "1ȸ������(���Ͻ���)," + "1ȸ������(���ϴ���)," + "2ȸ������(���Ͻ���)," + "2ȸ������(���ϴ���)," + "\n";
//			bfw.write(head);
//		}
//	}
//
//	static void fileWrite(String str) throws IOException {
//	
//			bfw.append(str);
//	
//	}
//
//	static void fileClose() {
//		try {
//			bfw.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}
//
