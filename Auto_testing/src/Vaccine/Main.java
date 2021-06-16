package Vaccine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {

	// 현재 package의 workspace 경로, Windows는 [ chromedriver.exe ]
	private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	private static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";
	private static String base_url;

	public static void main(String[] args) throws InterruptedException, IOException {
		
		fileMake();
		headWrite();
		ReadData();
		fileWrite();
		fileClose();
			
		
	}
	
	static Data data = new Data();
	static void ReadData() {
	
		
		try {
																				
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);																				
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");							// 전체화면으로 실행
			options.addArguments("--disable-popup-blocking"); 					// 팝업 무시
			options.addArguments("--disable-default-apps"); 					// 기본앱 사용안함					
																				// WebDriver 객체 생성
			ChromeDriver driver = new ChromeDriver(options);																			
			base_url = "https://ncv.kdca.go.kr/mainStatus.es?mid=a11702000000";
			
			driver.get(base_url);
			Thread.sleep(2000);
			String daily_date = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/h4[1]/span")).getText();
			data.items[0] = daily_date;
			System.out.println(daily_date);
			Thread.sleep(2000);
			String daily_total = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/table/tbody/tr[1]")).getText();
			Thread.sleep(2000);
			String[] daily_total_temp =daily_total.split(" ");
			data.items[1] = daily_total;
			
			for(int i=0; i<daily_total_temp.length; i++) {	
			System.out.println(daily_total_temp[i]);
			} 
	
			String daily_seoul = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/table/tbody/tr[2]")).getText();
			Thread.sleep(2000);
			data.items[2] = daily_seoul;
			System.out.println(daily_seoul);
			String daily_busan = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/table/tbody/tr[3]")).getText();
			Thread.sleep(2000);
			data.items[3] = daily_busan;
			System.out.println(daily_busan);
			String daily_daegu = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/table/tbody/tr[4]")).getText();
			Thread.sleep(2000);
			data.items[4] = daily_daegu;
			System.out.println(daily_daegu);
			String daily_incheon = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/table/tbody/tr[5]")).getText();
			Thread.sleep(2000);
			data.items[5] = daily_incheon;
			System.out.println(daily_incheon);
			String daily_gwangju = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/table/tbody/tr[6]")).getText();
			Thread.sleep(2000);
			data.items[6] = daily_gwangju;
			System.out.println(daily_gwangju);
			String daily_daejeon = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/table/tbody/tr[7]")).getText();
			Thread.sleep(2000);
			data.items[7] = daily_daejeon;
			System.out.println(daily_daejeon);
			String daily_ulsan = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/table/tbody/tr[8]")).getText();
			Thread.sleep(2000);
			data.items[8] = daily_ulsan;
			System.out.println(daily_ulsan);
			String daily_sejong = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/table/tbody/tr[9]")).getText();
			Thread.sleep(2000);
			data.items[9] = daily_sejong;
			System.out.println(daily_sejong);
			String daily_gyeonggi = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/table/tbody/tr[10]")).getText();
			Thread.sleep(2000);
			data.items[10] = daily_gyeonggi;
			System.out.println(daily_gyeonggi);
			String daily_gangwon = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/table/tbody/tr[11]")).getText();
			Thread.sleep(2000);
			data.items[11] = daily_gangwon;
			System.out.println(daily_gangwon);
			String daily_chungbuk = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/table/tbody/tr[12]")).getText();
			Thread.sleep(2000);
			data.items[12] = daily_chungbuk;
			System.out.println(daily_chungbuk);
			String daily_chungnam = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/table/tbody/tr[13]")).getText();
			Thread.sleep(2000);
			data.items[13] = daily_chungnam;
			System.out.println(daily_chungnam);
			String daily_jeonbuk = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/table/tbody/tr[14]")).getText();
			Thread.sleep(2000);
			data.items[14] = daily_jeonbuk;
			System.out.println(daily_jeonbuk);
			String daily_jeonnam = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/table/tbody/tr[15]")).getText();
			Thread.sleep(2000);
			data.items[15] = daily_jeonnam;
			System.out.println(daily_jeonnam);
			String daily_gyeongbuk = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/table/tbody/tr[16]")).getText();
			Thread.sleep(2000);
			data.items[16] = daily_gyeongbuk;
			System.out.println(daily_gyeongbuk);
			String daily_gyeongnam = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/table/tbody/tr[17]")).getText();
			Thread.sleep(2000);
			data.items[17] = daily_gyeongnam;
			System.out.println(daily_gyeongnam);
			String daily_jeju = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/table/tbody/tr[18]")).getText();
			data.items[18] = daily_jeju;
			System.out.println(daily_jeju);
			Thread.sleep(1000);
																							
			driver.close();														
			Thread.sleep(2000);
			driver.quit();

		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}
	
	public static  BufferedWriter bfw;
	public static boolean isFileExist;
	
	static void fileMake() {
		try {
			File file = new File("vaccine_daily.csv");
			if(file.exists() == false) {
				isFileExist = false;
			} else {
				isFileExist = true;
			}
			bfw =new BufferedWriter(new FileWriter(file, true));					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	
	static void headWrite() throws IOException {				
		
		if(isFileExist == false) {
			String head = "일자," + "구분," +"1회차접종(당일실적),"+ "1회차접종(당일누계)," + "2회차접종(당일실적)," + "2회차접종(당일누계)," + "\n";
			bfw.write(head);
		}
	}
	
	static void fileWrite() throws IOException {
		
		
		for (int i=0; i<data.items.length; i++) {
			bfw.append(data.items[i]);
		}

		bfw.newLine();

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
