package Auto_Insert_Data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {

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
															// WebDriver ��ü ����
			ChromeDriver driver = new ChromeDriver(options);
			base_url = "https://ncv.kdca.go.kr/mainStatus.es?mid=a11702000000";
			driver.get(base_url);

			// �ళ�� ��������
			Thread.sleep(2000);
			WebElement tbody = driver.findElementByXPath("/html/body/div/div[3]/div[1]/div[3]/table/tbody");
			List<WebElement> rows = tbody.findElements(By.xpath("/html/body/div/div[3]/div[1]/div[3]/table/tbody/tr"));
			rowsCnt = rows.size();
			// System.out.println(rowsCnt);

			for (int i = 1; i <= rowsCnt; i++) {
				
				// ���� �������� 
				Thread.sleep(2000);
				String daily_date = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/h4[1]/span")).getText().replace(",","").replace("(","").replace(")","");			
				bfw.append(daily_date+",");
				System.out.println(daily_date);
				//������ ���پ� ��������
				Thread.sleep(2000);
				String row = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/table/tbody/tr[" + i + "]")).getText().replace(",", "");
				Thread.sleep(2000);
				String[] row_arr = row.split(" ");
				for (int j = 0; j < row_arr.length; j++) {
					bfw.append(row_arr[j]+",");
					System.out.println(row_arr[j]);
				}
				bfw.newLine();
			}
			driver.close();
			Thread.sleep(2000);
			driver.quit();

		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}



	static void fileMake() {
		try {
			File file = new File("vaccine_daily.csv");
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
			String head = "����," + "����," + "1ȸ������(���Ͻ���)," + "1ȸ������(���ϴ���)," + "2ȸ������(���Ͻ���)," + "2ȸ������(���ϴ���)," + "\n";
			bfw.write(head);
		}
	}

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
