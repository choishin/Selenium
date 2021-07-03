package Vaccine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

//�õ��� ���������� ������ ��ü DB�� �ֱ�
public class Vaccine_DB {

	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";
	public static final String IP = "192.168.23.87";
//	public static final String IP = "192.168.171.18";
	public static final String filePath = "C:\\Users\\�ֽ�\\Desktop\\vaccine_daily.csv";
	public static String base_url;
	static int rowsCnt;
	public static BufferedWriter bfw;
	public static boolean isFileExist;

	public static void main(String[] args) throws InterruptedException, IOException {
		
		DBMake();
		ReadData();

	}
	

	static void DBMake() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root",
					"2356");
			Statement stmt = conn.createStatement();

			stmt.execute("create table vaccine("
					+ "vaccine_index int auto_increment primary key,"
					+ "vaccine_date varchar(50),"
					+ "city_name varchar(20),"
					+ "first_day_only int,"
					+ "first_day_total int,"
					+ "second_day_only int,"
					+ "second_day_total int "
					+ ");");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

	static void ReadData() throws IOException {

		try {
			String path = filePath;
			String line;
			BufferedReader reader = new BufferedReader(new FileReader(path));
			ArrayList<String> lines = new ArrayList<String>();

			// 1. �ڷ� ���پ� �ް� ArrayList�� �ֱ�
			rowsCnt = 0;
			while ((line = reader.readLine()) != null) {
				String[] column = line.split(",");
				lines.add(line);
				rowsCnt++;
			}
			// 2. ���پ� �Ǿ��ִ� �ڷḦ ,�� �߶� String[][] �� �ֱ�
			// words[][0]: �ε��� words[][1] : ��¥, words[][2] : ������, words[][3] : 1������,
			// words[][4] : 1������(����), words[][5]:2������, wods[][6] : 2������ ����
			String[][] words = new String[lines.size()][];
			for (int i = 1; i < lines.size(); i++) {
				words[i] = lines.get(i).split(",");
				words[i][0] = words[i][0].replace(".", "").replace("24�� ����", "");
				// System.out.println(Arrays.toString(words[i]));
			}

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "2356");
			Statement stmt = conn.createStatement();

			for (int iRow = 1; iRow < rowsCnt; iRow++) {
				stmt.execute(
						"insert into vaccine (vaccine_date,city_name,first_day_only,first_day_total,second_day_only,second_day_total)"
								+ "values('" + words[iRow][0] + "','" + words[iRow][1] + "'," + words[iRow][2] + ","
								+ words[iRow][3] + "," + words[iRow][4] + "," + words[iRow][5] + ");");

				System.out.println(words[iRow][0].replace(".", "").replace("24�� ����", ""));
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
	}

}
