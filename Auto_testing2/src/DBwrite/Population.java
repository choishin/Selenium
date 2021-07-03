package DBwrite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Population {
	
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";
	public static String base_url;
	static int rowsCnt;
	public static BufferedWriter bfw;
	public static boolean isFileExist;
	
	public static void main(String[] args) throws InterruptedException, IOException {

//		fileMake();
//		headWrite();
		ReadData();
//		fileClose();

	}
	
	static void ReadData() throws IOException {
		
		try {

			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized"); // 전체화면으로 실행
			options.addArguments("--disable-popup-blocking"); // 팝업 무시
			options.addArguments("--disable-default-apps"); // 기본앱 사용안함
															// WebDriver 객체 생성
			ChromeDriver driver = new ChromeDriver(options);
			base_url = "https://kosis.kr/statHtml/statHtml.do?orgId=202&tblId=DT_202N_B4&conn_path=I2";
			driver.get(base_url);
				
			// 행개수 가져오기
			Thread.sleep(2000);
			WebElement tbody = driver.findElementByXPath("/html/body/form/div[2]/div[5]/div[4]/div[5]/div[1]/table/tbody");
			List<WebElement> rows = tbody.findElements(By.xpath("/html/body/form/div[2]/div[5]/div[4]/div[5]/div[1]/table/tbody/tr"));
			rowsCnt = rows.size();
			
			Class.forName("com.mysql.cj.jdbc.Driver"); 	
			Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
			Statement stmt = conn.createStatement();  		
			
			for (int iRow = 1; iRow <= rowsCnt; iRow++) {
				
				//한줄씩 읽어오기 (앞에 0,1번 필드 ※만일 남녀, 외국인 등 다른 데이터를 가져오고 싶다면 td자리에 다른 숫자를 넣으면됨)
				Thread.sleep(2000);
				String city_name = driver.findElement(By.xpath("/html/body/form/div[2]/div[5]/div[4]/div[5]/div[1]/table/tbody/tr["+iRow+"]/td[1]")).getText().replace(",","");			
				String city_population = driver.findElement(By.xpath("/html/body/form/div[2]/div[5]/div[4]/div[5]/div[1]/table/tbody/tr["+iRow+"]/td[2]")).getText().replace(",","");			
//				bfw.append(city_name+",");
//				bfw.append(city_totalPeople+",");
//				bfw.newLine();			
			    stmt.execute("insert into population (city_name,city_population)values('"+city_name+"','"+city_population+"');");   
				System.out.println(city_name);
				System.out.println(city_population);
			}
		     stmt.close(); 
		     conn.close(); 
			driver.close();
			Thread.sleep(2000);
			driver.quit();

		} catch (Exception e) {
			System.out.println(e);
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
//	static void fileClose() {
//		try {
//			bfw.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}

