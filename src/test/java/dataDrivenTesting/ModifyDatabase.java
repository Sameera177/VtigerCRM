package dataDrivenTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ModifyDatabase
{

	public static void main(String[] args) throws SQLException
	{
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advsel", "root", "root");
		Statement statement = connection.createStatement();
		
		int result = statement.executeUpdate("insert into student(sid, sname, phNo, course) values(105, \"EFG\", \"8765432109\",\"API\")");
		System.out.println(result);
		
		connection.close();
	}

}