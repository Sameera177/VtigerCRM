package genericUtillities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtility
{
	Connection connection;
	java.sql.Statement statement;
	
	public void databaseInit(String url,String user,String pwd)
	{
		try 
		{
			connection = DriverManager.getConnection(url, user, pwd);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		try 
		{
			statement = connection.createStatement();
		}
		catch(SQLException e2)
		{
			e2.printStackTrace();	
		}
	}
		public List<Object> readFromDatabase(String query, String colName)
		{
			ResultSet result = null;
			try
			{
				result =  statement.executeQuery(query);
			}
			catch(SQLException e1)
			{
				e1.printStackTrace();
			}
		List<Object> list = new ArrayList<Object>();
		try {
			while(result.next())
			{
				list.add(result.getObject(colName));	
			}
		}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
	return list;
		}	
}
