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

	// 현재 package의 workspace 경로, Windows는 [ chromedriver.exe ]
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";
	private static String base_url;

	public static void main(String[] args) throws InterruptedException {
		try {
			// WebDriver 경로 설정
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

			// WebDriver 옵션 설정
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized"); // 전체화면으로 실행
			options.addArguments("--disable-popup-blocking"); // 팝업 무시
			options.addArguments("--disable-default-apps"); // 기본앱 사용안함

			// WebDriver 객체 생성
			ChromeDriver driver = new ChromeDriver(options);

			// 웹페이지 요청
			base_url = "https://ncv.kdca.go.kr/mainStatus.es?mid=a11702000000";

			driver.get(base_url);
			Thread.sleep(2000);
			// 웹페이지 입력창에 키워드 넣기
			String daily_result = driver.findElement(By.xpath("/html/body/div/div[3]")).getText();
			Thread.sleep(1000);
			System.out.println(daily_result);
			// 탭 종료
			driver.close();
			// 3초 후에 WebDriver 종료

			Thread.sleep(3000);
			driver.quit();

		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}
}
