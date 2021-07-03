package DBwrite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

//시도별 예방접종자 데이터 전체 DB에 넣기
public class Vaccine {
	
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";
	public static String base_url;
	static int rowsCnt;
	public static BufferedWriter bfw;
	public static boolean isFileExist;
	
	public static void main(String[] args) throws InterruptedException, IOException {

		ReadData();

	}
	
	static void ReadData() throws IOException {
		
		try {
			String path = "vaccine_daily.csv";
			String line;
			BufferedReader reader = new BufferedReader(new FileReader(path));
			ArrayList<String> lines = new ArrayList<String>();
			
			//1. 자료 한줄씩 받고 ArrayList에 넣기
			rowsCnt=0;
			while ((line = reader.readLine()) != null) {
				String[] column = line.split(",");
				lines.add(line);
				rowsCnt++;
			}			
			//2. 한줄씩 되어있는 자료를 ,로 잘라서 String[][] 에 넣기
			//words[][0] : 날짜, words[][1] : 지역명, words[][2] : 1차접종, words[][3] : 1차접종(누계), words[][4]:2차접종, wods[][5] : 2차접종 누계
			String[][] words = new String[lines.size()][];
			for (int i = 1; i<lines.size(); i++) {	
				words[i] = lines.get(i).split(",");
				words[i][0] = words[i][0].replace(".","").replace("24시 기준", "");
				//System.out.println(Arrays.toString(words[i]));
			}
									
			Class.forName("com.mysql.cj.jdbc.Driver"); 	
			Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
			Statement stmt = conn.createStatement();  	
			
			for (int iRow = 1; iRow < rowsCnt; iRow++) {
				
			    stmt.execute("insert into vaccine2 (vaccine_date,city_name,first_day_only,first_day_total,second_day_only,second_day_total)"
			    		+ "values('"+words[iRow][0]+"','"+words[iRow][1]+"',"+words[iRow][2]+","+words[iRow][3]+","+words[iRow][4]+","+words[iRow][5]+");");   
				System.out.println(words[iRow][0]);
				System.out.println(words[iRow][1]);
				System.out.println(words[iRow][2]);
				System.out.println(words[iRow][3]);
				System.out.println(words[iRow][4]);
				System.out.println(words[iRow][5]);

			}
		     stmt.close(); 
		     conn.close(); 

		} catch (Exception e) {
			System.out.println(e);
		}
		
		finally {
			System.out.println("Done!");
		}
	}

}

