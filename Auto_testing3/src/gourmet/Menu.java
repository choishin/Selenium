package gourmet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Menu {

	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";
	public static String base_url;
	public static BufferedWriter bfw;
	public static boolean isFileExist;
	static int rowsCnt;

	public static void main(String[] args) throws InterruptedException, IOException {

		fileMake();
		headWrite();
		ReadData();
		fileClose();

	}

	static void ReadData() throws IOException {

		try {

			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized"); // ��üȭ������ ����
			options.addArguments("--disable-popup-blocking"); // �˾� ����
			options.addArguments("--disable-default-apps"); // �⺻�� ������

			ChromeDriver driver = new ChromeDriver(options);
			base_url = "https://m.map.naver.com/";
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

			// �ళ�� ��������
			WebElement body = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul"));
			List<WebElement> rows = body.findElements(By.xpath("/html/body/div[4]/div[2]/ul/li"));
			rowsCnt = rows.size();

			// ���� ������ŭ ��ũ�� �ϳ��� ������
			for (int i = 1; i <= rowsCnt; i++) {
				System.out.println(i);
				try {
					Thread.sleep(5000);
					if (driver.findElements(By.xpath("/html/body/div[4]/div[2]/ul/li[" + i + "]/div[1]/a[2]"))
							.size() > 0) {
						driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li[" + i + "]/div[1]/a[2]")).click();
					} else if (driver.findElements(By.xpath("/html/body/div[4]/div[2]/ul/li[" + i + "]/div[1]/a"))
							.size() > 0) {
						Thread.sleep(5000);
						driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li[" + i + "]/div[1]/a")).click();
					}
				} catch (NoSuchElementException e) {
					System.out.println(e);
					continue;
				} catch (StaleElementReferenceException se) {
					System.out.println(i + "��° ����");
					System.out.println(se);
					continue;
				}
				// ��ũ����
				Thread.sleep(3000);
				String currentURL = driver.getCurrentUrl();
				driver.get(currentURL);
				Thread.sleep(3000);
				WebElement menuBar = null;
				int menuCnt = 0;
				String menuTable = null;
				try {
					// �޴����������� ���� �ܾ����
					if (driver.findElements(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div/div/div/div"))
							.size() > 0) {
						menuBar = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div/div/div/div"));
						if (menuBar.findElements(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div/div/div/div/a"))
								.size() > 0) {
							menuCnt = menuBar
									.findElements(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div/div/div/div/a"))
									.size();
							for (int iCnt = 1; iCnt <= menuCnt; iCnt++) {
								String menu = menuBar
										.findElement(By.xpath(
												"/html/body/div[3]/div/div[2]/div[3]/div/div/div/div/a[" + iCnt + "]"))
										.getText();
								if (menu.contains("�޴�")) {
									menuBar.findElement(By.xpath(
											"/html/body/div[3]/div/div[2]/div[3]/div/div/div/div/a[" + iCnt + "]"))
											.click();
								}
							}
						}
					}
				} catch (StaleElementReferenceException se) {
					System.out.println(se);
					continue;
				}
				Thread.sleep(3000);
				try {

					if (driver.findElements(By.xpath("/html/body/div[3]/div/div[2]/div[5]/div/div[1]/div/ul"))
							.size() > 0) {
						menuTable = driver
								.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[5]/div/div[1]/div/ul"))
								.getText();
					} else if (driver.findElements(By.xpath("/html/body/div[3]/div/div[2]/div[5]/div/div[1]/div[1]/ul"))
							.size() > 0) {
						menuTable = driver
								.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[5]/div/div[1]/div[1]/ul"))
								.getText();
					} else {

					}

				} catch (StaleElementReferenceException se) {
					System.out.println(i + "��° ����");
					System.out.println(se);
					continue;
				}

				catch (Exception e) {
					System.out.println(e);
					continue;
				}
				System.out.println(menuTable);
				driver.navigate().back();
				Thread.sleep(1000);
				driver.navigate().back();
			} // for

			driver.close();
			Thread.sleep(2000);
			driver.quit();

		} catch (NoSuchElementException ne) {
			System.out.println(ne);
			System.out.println("�����Ͱ� �������� ����");
		} catch (InterruptedException e) {
			System.out.println(e);
		} finally {
			System.out.println("Done!");
		}
	}

	static void fileMake() {
		try {
			File file = new File("gourmet_main.csv");
			if (file.exists() == false) {
				isFileExist = false;
			} else {
				isFileExist = true;
			}
			bfw = new BufferedWriter(new FileWriter(file, true));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

	static void headWrite() throws IOException {

		if (isFileExist == false) {
			String head = "�ε���," + "�Ĵ��̸�," + "����," + "�湮�ڸ����," + "��ΰŸ����," + "�ּ�," + "�����ð�," + "����," + "����," + "Ȩ������,"
					+ "��Ÿ," + "\n";
			bfw.write(head);
		}
	}

	static void fileClose() {
		try {
			bfw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
