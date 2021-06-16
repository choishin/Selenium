package gourmet;

	import java.io.BufferedWriter;
	import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.util.List;
	import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;

	public class Main {

		public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
		public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";
		public static String base_url;

		public static void main(String[] args) throws InterruptedException, IOException {

//			fileMake();
//			headWrite();
			ReadData();
//			fileClose();

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
				base_url = "https://map.naver.com/v5/?c=14150420.2196216,4492985.1129456,15,0,0,0,dh";
				driver.get(base_url);

				//입력창에 검색어 순서대로 넣어서 결과 가지고 오기
				Thread.sleep(2000);
				driver.findElement(By.xpath("/html/body/app/layout/div[3]/div[2]/shrinkable-layout/div/app-base/search-input-box/div/div[1]/div/input")).sendKeys("서현역 한식 맛집");
				Thread.sleep(2000);
				driver.findElement(By.xpath("/html/body/app/layout/div[3]/div[2]/shrinkable-layout/div/app-base/search-input-box/div/div[1]/div/input")).sendKeys(Keys.ENTER);
				Thread.sleep(2000);
				//iframe이 끼어있다면 한번 switch를 해줘야 함
				driver.switchTo().frame("searchIframe");			
				List<WebElement> rows = driver.findElements(By.xpath("/html/body/div[3]/div/div[2]/div[1]/ul/li"));
				rowsCnt = rows.size();				

				for (int i=1; i<=rowsCnt; i++) {
					
					String temp =  driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/ul/li["+i+"]")).getText();
					System.out.println(temp);
				}
				
				
//				String first = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/ul/li[1]/div[1]/a[1]/div[1]/div/span")).getText();
//				System.out.println(first);
				driver.close();
				Thread.sleep(2000);
				driver.quit();

			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
//		static void fileMake() {
//			try {
//				File file = new File("vaccine_daily.csv");
//				if (file.exists() == false) {
//					isFileExist = false;
//				} else {
//					isFileExist = true;
//				}
//				bfw = new BufferedWriter(new FileWriter(file, true));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				System.out.println(e);
//			}
//		}
//
//		static void headWrite() throws IOException {
//
//			if (isFileExist == false) {
//				String head = "일자," + "구분," + "1회차접종(당일실적)," + "1회차접종(당일누계)," + "2회차접종(당일실적)," + "2회차접종(당일누계)," + "\n";
//				bfw.write(head);
//			}
//		}
//
//		static void fileWrite(String str) throws IOException {
//		
//				bfw.append(str);
//		
//		}
//
//		static void fileClose() {
//			try {
//				bfw.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//	}
//
