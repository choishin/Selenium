package Price;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Test {

	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";
	public static String base_url;

	public static void main(String[] args) throws InterruptedException, IOException {

			ReadData();

	}

	static int rowsCnt;
	public static BufferedWriter bfw;
	public static boolean isFileExist;

	static void ReadData() throws IOException {

		try {

			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized"); // 전체화면으로 실행
			options.addArguments("--disable-popup-blocking"); // 팝업 무시
			options.addArguments("--disable-default-apps"); // 기본앱 사용안함

			ChromeDriver driver = new ChromeDriver(options);
			//배달의 민족, 요기요, 네이버 예약 페이지로 이동하는 경우 텍스트가 가져와지는지 확인
			base_url = "https://m.store.naver.com/restaurants/1144449409/tabs/menus/baemin/list?more=false&entry=ple";
			driver.get(base_url);	
			
			String menu="";	
			if (driver.findElements(By.tagName("body")).size() >0) {
				menu = driver.findElement(By.tagName("body"))
					.getText();
			}
			System.out.println(menu);

			driver.close();
			Thread.sleep(2000);
			driver.quit();

		} catch (NoSuchElementException ne) {
			System.out.println(ne);
			System.out.println("데이터가 존재하지 않음");
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

}
