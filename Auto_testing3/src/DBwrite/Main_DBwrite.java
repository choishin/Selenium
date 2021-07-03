package DBwrite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;


//맛집 정보,가격 데이터 전체 DB에 넣기
public class Main_DBwrite {

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

	// 코드 마저 완성해야함
	static void DBMake() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://34.83.91.32:3306/kopoctc", "root", "2356");
			Statement stmt = conn.createStatement();

			stmt.execute("create table gourmet (" + "gourmet_index int auto_increment primary key,"
					+ "gourmet_name varchar(30)," + "gourmet_star varchar(20),"
					+ "gourmet_visitor_review_cnt varchar(20)," + "gourmet_blogger_review_cnt varchar(20),"
					+ "gourmet_address varchar(200)," + "gourmet_openinghour varchar(200),"
					+ "gourmet_service varchar(500)," + "gourmet_info varchar(500)" + ");");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} finally {
			System.out.println("DBmake Done!");
		}
	}

	static void ReadData() throws IOException {

		try {
			String path = "C:\\Users\\최신\\Desktop\\gourmet_main.csv";
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
			// words[][0]: 인덱스 words[][1] : 상호명, words[][2] : 별점, words[][3] : 방문자리뷰수,
			// words[][4] : 블로거리뷰수, words[][5]: 주소, words[][6] : 영업시간, words[][7] : 편의, words[][8]: 설명
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
						"insert into gourmet (gourmet_name,gourmet_star,gourmet_visitor_review_cnt,gourmet_blogger_review_cnt,gourmet_address,gourmet_openinghour,gourmet_service,gourmet_info)"
								+ "values('" + words[iRow][1].replace("'","") + "','" + words[iRow][2].replace("'","") + "','" + words[iRow][3].replace("'","") + "','"
								+ words[iRow][4].replace("'","") + "','" + words[iRow][5].replace("'","") + "','" + words[iRow][6].replace("'","") + "','" + words[iRow][7].replace("'","")+ "','" + words[iRow][8].replace("'","")+"');");
				System.out.println(words[iRow][1]);
				System.out.println(words[iRow][2]);
				System.out.println(words[iRow][3]);
				System.out.println(words[iRow][4]);
				System.out.println(words[iRow][5]);
				System.out.println(words[iRow][6]);
				System.out.println(words[iRow][7]);
				System.out.println(words[iRow][8]);

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
