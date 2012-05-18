package ecosim.record;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.jdom.Element;

public class DatabaseExtractor
{
	private Connection dbConnection;
	private Statement st;
	private ResultSet rs;
	private ResultSetMetaData md;
	
	public DatabaseExtractor()
	{
		try
		{
			Class.forName("org.h2.Driver");
			dbConnection=DriverManager.getConnection("jdbc:h2:~/TreeSim", "sa", "");
			st=dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}
		catch (SQLException e)
		{e.printStackTrace();} catch (ClassNotFoundException e){e.printStackTrace();}
	}

	public Object[][] runQuery(String q)
	{
		Object[][] result=null;
		try
		{
			rs=st.executeQuery(q);
			md=rs.getMetaData();
			rs.last();
			result = new Object[rs.getRow()-1][md.getColumnCount()];
			rs.first();
			int row=0;
			while(rs.next())
			{
				for(int col=0;col<md.getColumnCount();col++)
				{
					if(md.getColumnType(col+1)==Types.INTEGER){result[row][col]=Integer.parseInt(rs.getString(col+1));}
					else if(md.getColumnType(col+1)==Types.FLOAT){result[row][col]=Float.parseFloat(rs.getString(col+1));}
					else{result[row][col]=rs.getString(col+1);}
				}
				row++;
			}
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return result;
	}
	
	
	// Database methods
	public void closeConnection()
	{
		try
		{
			rs.close();
			st.close();
			dbConnection.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
