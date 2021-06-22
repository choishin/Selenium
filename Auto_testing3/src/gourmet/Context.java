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

public class Context {

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
			options.addArguments("--start-maximized"); // 전체화면으로 실행
			options.addArguments("--disable-popup-blocking"); // 팝업 무시
			options.addArguments("--disable-default-apps"); // 기본앱 사용안함

			ChromeDriver driver = new ChromeDriver(options);
			base_url = "https://m.map.naver.com/";
			driver.get(base_url);

			// 입력창에 검색어 순서대로 넣어서 결과 가지고 오기
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[2]/header/div[4]/div/div/div/span[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/form/div/div[2]/div/span[1]/input"))
					.sendKeys("서현역 한식 맛집");
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/form/div/div[2]/div/span[1]/input"))
					.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			// 행개수 가져오기
			WebElement body = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul"));
			List<WebElement> rows = body.findElements(By.xpath("/html/body/div[4]/div[2]/ul/li"));
			rowsCnt = rows.size();
			//행의 개수만큼 링크를 하나씩 누르기
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
			
			// 링크열기
			Thread.sleep(3000);
			String currentURL = driver.getCurrentUrl();
			driver.get(currentURL);
			Thread.sleep(3000);
			
			//정보 한번에 가지고 와서, 자르고 가공하기
			String context = driver.findElement(By.className("_6aUG7")).getText();
			String[] contextArr = context.split("\n");
			System.out.println(i);
			for(int iContext=0; iContext<contextArr.length; iContext++) {
				if(contextArr[iContext].contains("정보 수정") ||contextArr[iContext].contains("펼쳐")
						 ||contextArr[iContext].contains("이 업체의 사장님") ||contextArr[iContext].contains("직접 관리")
						 ||contextArr[iContext].contains("네이버 사업자도구 살펴보기")||contextArr[iContext].contains("스마트스토어")) {
					contextArr[iContext] = "";				
				}
//				if (contextArr[iContext].contains("지번복사지도내비게이션거리뷰")) {
//					contextArr[iContext] = contextArr[iContext].replace("지번복사지도내비게이션거리뷰","");
//				}	
				if (contextArr[iContext].contains("주소")) {			
					System.out.print("주소->"+contextArr[iContext+1].replace("지번복사지도내비게이션거리뷰",""));
					System.out.println(" "+contextArr[iContext+2].replace("지번복사지도내비게이션거리뷰",""));				
				}
				if (contextArr[iContext].contains("영업시간")) {
					System.out.println("영업시간->"+contextArr[iContext+1]);
				}
				if (contextArr[iContext].contains("편의")) {
					System.out.println("편의->"+contextArr[iContext+1]);
				}	
				if (contextArr[iContext].contains("설명")) {
					System.out.println("설명->"+contextArr[iContext+1]);
				}
				
				//System.out.println(iContext+"->"+contextArr[iContext]);
			}

//			String[] contextArr = context.split("\n");
//			System.out.println(contextArr);
//			//8번째 페이지에 대한 예외처리
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
//						if (menu.contains("메뉴")) {
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
//			//9번째 페이지 
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
//						if (menu.contains("메뉴")) {
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
//			String head = "일자," + "구분," + "1회차접종(당일실적)," + "1회차접종(당일누계)," + "2회차접종(당일실적)," + "2회차접종(당일누계)," + "\n";
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