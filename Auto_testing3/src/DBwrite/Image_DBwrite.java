package DBwrite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;


public class Image_DBwrite {

	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";
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
			Connection conn = DriverManager.getConnection("jdbc:mysql://34.83.91.32:3306/kopoctc", "root", "2356");
			Statement stmt = conn.createStatement();

			stmt.execute("create table gourmet_image("
					+ "gourmet_name varchar(20),"
					+ "gourmet_image varchar(200)"
					+ ");");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} finally {
			System.out.println("DBmake Done!");
		}
	}

	static void ReadData() throws IOException {

		try {
			String path = "C:\\Users\\최신\\Desktop\\gourmet_image.csv";
			String line;
			BufferedReader reader = new BufferedReader(new FileReader(path));
			ArrayList<String> lines = new ArrayList<String>();

			// 1. 자료 한줄씩 받고 ArrayList에 넣기
			rowsCnt = 0;
			while ((line = reader.readLine()) != null) {
				String[] column = line.split(",");
				lines.add(line);
//				System.out.println(line);
				rowsCnt++;
			}
			// 2. 한줄씩 되어있는 자료를 ,로 잘라서 String[][] 에 넣기
			// words[][0]: 상호명 words[][1] : 이미지
			String[][] words = new String[lines.size()][];
			for (int i = 1; i < lines.size(); i++) {
				words[i] = lines.get(i).split(",");
//				 System.out.println(Arrays.toString(words[i]));
			}

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://34.83.91.32:3306/kopoctc", "root", "2356");
			Statement stmt = conn.createStatement();

			for (int iRow = 1; iRow < rowsCnt; iRow++) {
				stmt.execute(
						"insert into gourmet_image (gourmet_name,gourmet_image)"
								+ "values('" + words[iRow][0] + "','" + words[iRow][1].replace("photo/#photo", "photo#photo")+"');");
				System.out.println(words[iRow][0]);
				System.out.println(words[iRow][1]);

			}
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Data insert Done!");
		}
	}

}
