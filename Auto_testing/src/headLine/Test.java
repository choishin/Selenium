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
		base_url = "https://news.naver.com/";

			driver.get(base_url);
			Thread.sleep(2000);
			// 상단의 헤드라인 5개 가지고 오기
			for (int i=1; i<=5; i++) {
				String title = driver.findElement(By.xpath("/html/body/div/div[6]/div[1]/div/div[1]/div[2]/ul/li["+i+"]")).getText();
				Thread.sleep(2000);
				System.out.println(title);
			}
					
//			// 웹페이지 입력창에 키워드 넣기
//			driver.findElement(By.xpath("/html/body/form/span/input[1]")).click();
//			driver.findElement(By.xpath("/html/body/form/input[2]")).sendKeys(" ");
//			Thread.sleep(1000);
//			// 웹페이지 입력창에 키워드 넣기
//			driver.findElement(By.xpath("/html/body/form/input[3]]")).click();
//			driver.findElement(By.xpath("/html/body/form/input[3]")).sendKeys(" ");
//			Thread.sleep(1000);
//			// 웹페이지 입력창에 키워드 넣기
//			driver.findElement(By.xpath("/html/body/form/input[4]]")).click();
//			driver.findElement(By.xpath("/html/body/form/input[4]")).sendKeys(" ");
//			Thread.sleep(1000);
//			// 웹페이지 입력창에 키워드 넣기
//			driver.findElement(By.xpath("/html/body/form/input[5]]")).click();
//			driver.findElement(By.xpath("/html/body/form/input[5]")).sendKeys(" ");
//			// 탭 종료
			driver.close();
			driver.quit();

			// 3초 후에 WebDriver 종료

			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
	}
}
	

