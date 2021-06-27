package gourmet;

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

public class Menu {

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

				// ���� �ѹ��� ������ �ͼ�, �ڸ��� �����ϱ� (��6,12,20,54 ->�����ð�, ���� ���� �ܾ ������ ��������)
				String context = driver.findElement(By.className("_6aUG7")).getText();
				String[] contextArr = context.split("\n");
				String address = "";
				String openHour = "";
				String service = "";
				String info = "";
				System.out.println(i);
				if (!context.contains("�ּ�")) {
					address = "��������";
				}
				if (!context.contains("�����ð�")) {
					openHour = "��������";
				}
				if (!context.contains("����")) {
					service = "��������";
				}
				if (!context.contains("����")) {
					info = "��������";
				}
				for (int iContext = 0; iContext < contextArr.length; iContext++) {
					if (contextArr[iContext].contains("���� ����") || contextArr[iContext].contains("����")
							|| contextArr[iContext].contains("�� ��ü�� �����") || contextArr[iContext].contains("���� ����")
							|| contextArr[iContext].contains("���̹� ����ڵ��� ���캸��")
							|| contextArr[iContext].contains("����Ʈ�����")) {
						contextArr[iContext] = "";
					}

					if (contextArr[iContext].contains("�ּ�")) {
						address = contextArr[iContext + 1].replace("������������������̼ǰŸ���", "").replace(",", "/") + " "
								+ contextArr[iContext + 2].replace("������������������̼ǰŸ���", "").replace(",", "/");

					}

					if (contextArr[iContext].contains("�����ð�")) {
						if (contextArr[iContext].contains("�����ð���") || contextArr[iContext].contains("�����ð���")
								|| contextArr[iContext].contains("�����ð�&")) {
							continue;
						} else {
							openHour = contextArr[iContext + 1].replace(",", "/");
						}
					}

					if (contextArr[iContext].contains("����")) {
						if (contextArr[iContext].contains("���ǽü�")) {
							continue;
						} else {
							service = contextArr[iContext + 1].replace(",", "/");
						}
					}

					if (contextArr[iContext].contains("����")) {
						info = contextArr[iContext + 1].replace(",", "/");
					}

//				System.out.println(iContext+"->"+contextArr[iContext]);

				}

				System.out.println("�ּ�->" + address);
				System.out.println("�����ð�->" + openHour);
				System.out.println("����->" + service);
				System.out.println("����->" + info);

				bfw.append(address+",");
				bfw.append(openHour+",");
				bfw.append(service+",");
				bfw.append(info+",");
				bfw.newLine();

//			String[] contextArr = context.split("\n");
//			System.out.println(contextArr);
//			//8��° �������� ���� ����ó��
//			if (i==8) {
//				Thread.sleep(3000);
//				String innerURL = driver.getCurrentUrl();
//				driver.get(innerURL);
//				Thread.sleep(3000);
//				if(driver.findElements(By.xpath("/html/body/div[1]/div/header/div[2]/div")).size() > 0) {
//					menuBar = driver.findElement(By.xpath("/html/body/div[1]/div/header/div[2]/div"));
//					menuCnt = menuBar.findElements(By.xpath("/html/body/div[1]/div/header/div[2]/div/a")).size();
//					for (int iCnt=1; iCnt <=menuCnt; iCnt++) {
//						String menu = menuBar.findElement(By.xpath("/html/body/div[1]/div/header/div[2]/div/a["+iCnt+"]")).getText();
//						if (menu.contains("�޴�")) {
//							menuBar.findElement(By.xpath("/html/body/div[1]/div/header/div[2]/div/a["+iCnt+"]")).click();
//							innerURL = driver.getCurrentUrl();
//							driver.get(innerURL);
//							Thread.sleep(3000);
//							menuTable = driver.findElement(By.xpath("/html/body")).getText();
//						}
//					}
//				}
//			
//			}
//			
//			//9��° ������ 
//			driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li[9]/div[1]/a[2]")).click();
//			String currentURL = driver.getCurrentUrl();
//			driver.get(currentURL);
//			Thread.sleep(2000);
//			WebElement menuBar = null;
//			int menuCnt = 0;
//			String menuTable = null;
//			if (driver.findElements(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div/div/div/div")).size() > 0) {
//				menuBar = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div/div/div/div"));
//				if (menuBar.findElements(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div/div/div/div/a"))
//						.size() > 0) {
//					menuCnt = menuBar
//							.findElements(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div/div/div/div/a")).size();
//					for (int iCnt = 1; iCnt <= menuCnt; iCnt++) {
//						String menu = menuBar
//								.findElement(By.xpath(
//										"/html/body/div[3]/div/div[2]/div[3]/div/div/div/div/a[" + iCnt + "]"))
//								.getText();
//						if (menu.contains("�޴�")) {
//							menuBar.findElement(
//									By.xpath("/html/body/div[3]/div/div[2]/div[3]/div/div/div/div/a[" + iCnt + "]"))
//									.click();
//						}
//					}
//				}
//			}

				driver.navigate().back();
			}
			Thread.sleep(3000);
			driver.close();
			Thread.sleep(2000);
			driver.quit();

		} catch (InterruptedException e) {
			System.out.println(e);
		} finally {
			System.out.println("Done!");
		}
	}

	static void fileMake() {
		try {
			File file = new File("gourmet_Context.csv");
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
			String head = "�ּ�," + "�����ð�," + "����," + "����," + "\n";
			bfw.write(head);
		}
	}

//	static void fileWrite(String str) throws IOException {
//	
//			bfw.append(str);
//	
//	}

	static void fileClose() {
		try {
			bfw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}