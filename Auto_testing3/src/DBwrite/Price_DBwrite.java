package DBwrite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;


//���� ����,���� ������ ��ü DB�� �ֱ�
public class Price_DBwrite {

	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";
//	public static final String IP = "192.168.171.18"; 
	public static final String IP = "192.168.23.87"; 
	public static final String filePath = "C:\\Users\\�ֽ�\\Desktop\\gourmet_price.csv"; 
	public static String base_url;
	static int rowsCnt;
	public static BufferedWriter bfw;
	public static boolean isFileExist;

	public static void main(String[] args) throws InterruptedException, IOException {
		
		DBMake();
		ReadData();

	}
	
	//�ڵ� ���� �ϼ��ؾ���
	static void DBMake() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root",
					"2356");
			Statement stmt = conn.createStatement();

			stmt.execute("create table gourmet_price("
					+ "gourmet_name varchar(20),"
					+ "gourmet_menu varchar(200),"
					+ "gourmet_price varchar(200)"
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
			String path = filePath;
			String line;
			BufferedReader reader = new BufferedReader(new FileReader(path));
			ArrayList<String> lines = new ArrayList<String>();

			// 1. �ڷ� ���پ� �ް� ArrayList�� �ֱ�
			rowsCnt = 0;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
//				System.out.println(line);
				rowsCnt++;
			}
			reader.close();
			// 2. ���پ� �Ǿ��ִ� �ڷḦ ,�� �߶� String[][] �� �ֱ�
			// words[][0]: ��ȣ�� words[][1] : �޴���, words[][2] : ����
			String[][] words = new String[lines.size()][];
			for (int i = 1; i < lines.size(); i++) {
				words[i] = lines.get(i).split(",");
//				 System.out.println(Arrays.toString(words[i]));
			}

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/kopoctc", "root", "2356");
			Statement stmt = conn.createStatement();

			for (int iRow = 1; iRow < rowsCnt; iRow++) {
				stmt.execute(
						"insert into gourmet_price (gourmet_name,gourmet_menu,gourmet_price)"
								+ "values('" + words[iRow][0].replace("'", "") + "','" + words[iRow][1].replace("'", "") + "','" + words[iRow][2].replace("'", "") +"');");

				System.out.println(words[iRow][0]);
				System.out.println(words[iRow][1]);
				System.out.println(words[iRow][2]);

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

