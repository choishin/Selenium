package Image;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Image {

	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";
	public static String base_url;

	public static void main(String[] args) throws InterruptedException, IOException {

		fileMake();
		headWrite();
		ReadData();
		fileClose();

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
				}
				// ��ũ����
				Thread.sleep(3000);
				String currentURL = driver.getCurrentUrl();
				driver.get(currentURL);
				Thread.sleep(3000);
				// �̸���������
				String restaurantName = driver
						.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div/div/div[1]/span/span[1]"))
						.getText();
				currentURL = currentURL.replace("/home","/photo/#photo");
				System.out.println(restaurantName);
				System.out.println(currentURL);			
				bfw.append(restaurantName + ",");
				bfw.append(currentURL + ",");
				bfw.newLine();
				Thread.sleep(2000);
				driver.navigate().back();
			}

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
			File file = new File("gourmet_image.csv");
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
			String head = "��ȣ��," + "�����ּ�," + "\n";
			bfw.write(head);
		}
	}

	// Ȥ�� �޼ҵ�� ������ ������ ���ΰ���
	static void fileWrite(String str) throws IOException {

		bfw.append(str);

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
