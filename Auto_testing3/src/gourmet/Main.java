package gourmet;

	import java.io.BufferedWriter;
	import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.util.List;
	import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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
				base_url = "https://m.map.naver.com/";
				driver.get(base_url);

				//입력창에 검색어 순서대로 넣어서 결과 가지고 오기
				Thread.sleep(2000);
				driver.findElement(By.xpath("/html/body/div[2]/header/div[4]/div/div/div/span[1]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/form/div/div[2]/div/span[1]/input")).sendKeys("서현역 한식 맛집");
				Thread.sleep(2000);
				driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/form/div/div[2]/div/span[1]/input")).sendKeys(Keys.ENTER);
				Thread.sleep(2000);

				// 행개수 가져오기
				WebElement body = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul"));
				List<WebElement> rows = body.findElements(By.xpath("/html/body/div[4]/div[2]/ul/li"));
				rowsCnt = rows.size();	
				System.out.println(rowsCnt);
														
				for (int i=1; i<=rowsCnt; i++) {
				try  {
					Thread.sleep(5000);
					driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+i+"]/div[1]/a[2]")).click();	
							
				} catch (Exception e) {
					driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+i+"]/div[1]/a")).click();			
					continue;
				}
				
				Thread.sleep(3000);
				String currentURL = driver.getCurrentUrl();
				driver.get(currentURL);
				Thread.sleep(3000);
				String restaurantName = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div/div/div[1]/span/span[1]")).getText();
				Thread.sleep(3000);
				String stars = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div/div/div[1]/div/span[1]/em")).getText();
//				String info;
//				if (driver.findElements(By.xpath("/html/body/div[3]/div/div[2]/div[1]/ul/li["+i+"]/div[1]/a[1]/div[2]/div")).size()>0) {
//					info = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/ul/li["+i+"]/div[1]/a[1]/div[2]/div")).getText();
//				}else {
//					continue;
//				}
//				String media;
//				if (driver.findElements(By.xpath("/html/body/div[3]/div/div[2]/div[1]/ul/li["+i+"]/div[1]/a[1]/div[3]/div")).size() > 0) {
//					media = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/ul/li["+i+"]/div[1]/a[1]/div[3]/div")).getText();
//				}else {
//					continue;
//				}
//			
//				String visitorsReview = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/ul/li["+i+"]/div[1]/a[2]/div/span[2]")).getText();
//				String bloggerReview = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/ul/li["+i+"]/div[1]/a[2]/div/span[3]")).getText();
							
				System.out.println(restaurantName);
				System.out.println(stars);
//				System.out.println(info);
//				System.out.println(media);
//				System.out.println(stars);
//				System.out.println(visitorsReview);
//				System.out.println(bloggerReview);
				driver.navigate().back();
				
				}
								
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
