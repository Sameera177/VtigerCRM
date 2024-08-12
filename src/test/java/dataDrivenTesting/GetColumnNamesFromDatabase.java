package dataDrivenTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class GetColumnNamesFromDatabase {

	public static void main(String[] args) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advsel", "root", "root");
		
		Statement statement = connection.createStatement();
		
		ResultSet result = statement.executeQuery("select * from student;");
		
		ResultSetMetaData rsMetaData = result.getMetaData();
		
		for(int i=1; i<= rsMetaData.getColumnCount();i++)
		{
			System.out.println( rsMetaData.getColumnName(i)+"\t");
		}
		
		System.out.println();
		
		while(result.next())
		{
			System.out.println(result.getInt("sid") + "\t" + result.getString("sname") + "\t" + result.getString("phNo") + "\t" + result.getString("course"));
		}
		
		connection.close();
	}

}
