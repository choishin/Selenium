package Vaccine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//시도별 예방접종자 데이터 전체 DB에 넣기
public class Vaccine_daily_DB {

	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";
	public static final String filePath = "C:\\Users\\최신\\Desktop\\vaccine_daily.csv";
	static final String IP = "192.168.23.87";
//	static final String IP = "192.168.171.18";
	public static String base_url;
	static int rowsCnt;
	static BufferedWriter bfw;
	static boolean isFileExist;

	public static void main(String[] args) throws InterruptedException, IOException {
		ReadData();

	}

	static void ReadData() throws IOException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
			String today = sdf.format(date).replace("0", "");
			int today_parse = Integer.parseInt(today);
			int lastdate = today_parse - 1;

			ResultSet rset = stmt.executeQuery("select * from vaccine where vaccine_date=" + lastdate + ";");
			String lastdate_query = "";
			while (rset.next()) {
				lastdate_query = rset.getString(2);
			}

			// 1. 자료 한줄씩 받고 ArrayList에 넣기
			String path = filePath;
			String line;
			BufferedReader reader = new BufferedReader(new FileReader(path));
			ArrayList<String> lines = new ArrayList<String>();
			rowsCnt = 0;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
				rowsCnt++;
			}
			reader.close();
			
			// 2. 한줄씩 되어있는 자료를 ,로 잘라서 String[][] 에 넣기
			// words[][0] : 날짜, words[][1] : 지역명, words[][2] : 1차접종, words[][3] : 1차접종(누계),
			// words[][4]:2차접종, words[][5] : 2차접종 누계
			String[][] words = new String[lines.size()][];
			for (int i = 1; i < lines.size(); i++) {
				words[i] = lines.get(i).split(",");
				words[i][0] = words[i][0].replace(".", "").replace("24시 기준", "");
				if (!lastdate_query.contains(Integer.toString(lastdate))) {
					if (words[i][0].contains(Integer.toString(lastdate))) {
						stmt.execute(
								"insert into vaccine (vaccine_date,city_name,first_day_only,first_day_total,second_day_only,second_day_total)"
										+ "values('" + words[i][0] + "','" + words[i][1] + "'," + words[i][2] + ","
										+ words[i][3] + "," + words[i][4] + "," + words[i][5] + ");");
					}
				}
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
