package ecosim.record;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import ecosim.model.Forest;
import ecosim.model.Stratum;
import ecosim.model.Tree;
import ecosim.sim.Simulation;
import ecosim.sim.SimulationRun;

public class DatabaseRecorder extends Recorder
{

	private Connection dbConnection;
	private Statement st;
	
	public DatabaseRecorder(Simulation sim, SimulationRun run)
	{
		super(sim, run);
		try
		{
			Class.forName("org.h2.Driver");
			dbConnection=DriverManager.getConnection("jdbc:h2:~/TreeSim", "sa", "");
			st=dbConnection.createStatement();
			
			//Drop tables before SimulationRun is instantiated
			if(run==null){dropTables();}
			
			String deathTable="CREATE TABLE IF NOT EXISTS DEATH (ID integer, RUN integer, YEAR integer);";
			String promotionTable="CREATE TABLE IF NOT EXISTS PROMOTION (ID integer, RUN integer, YEAR integer, OLD varchar, NEW varchar);";
			String snapshotTable="CREATE TABLE IF NOT EXISTS SNAPSHOT (ID integer, RUN integer, YEAR integer, TH float, TD float, STRATUM varchar, TYPE varchar, HEALTH float);";
			String treeTable="CREATE TABLE IF NOT EXISTS TREE (ID integer, LOCATION_X float, LOCATION_Y float, SPECIES_ABREV varchar, SPECIES_NAME varchar);";
			st.execute(deathTable);
			st.execute(promotionTable);
			st.execute(snapshotTable);
			st.execute(treeTable);
		} catch (SQLException e)
		{e.printStackTrace();} catch (ClassNotFoundException e){e.printStackTrace();}
	}

	public void recordTrees(Forest forest)
	{
		for ( Tree tree : forest.getTrees() )
		{
			String treeEntry="INSERT INTO TREE VALUES ("+tree.getId()+", "+tree.getLocation().getX()+", "+tree.getLocation().getY()+", '"+tree.getSpeciesAbrev()+"', '"+tree.getSpeciesName()+"');";
			try
			{
				st.execute(treeEntry);
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	@Override
	public void recordDeath(Tree tree, int year)
	{
		String deathEntry="INSERT INTO DEATH VALUES ("+tree.getId()+", "+run.getRunNumber()+", "+year+");";
		try
		{
			st.execute(deathEntry);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void recordPromotion(Tree tree, Stratum previous)
	{
		String promotionEntry="INSERT INTO PROMOTION VALUES ("+tree.getId()+", "+run.getRunNumber()+", "+run.getCurrentYear()+", '"+previous.toString()+"', '"+tree.getStrata()+"');";
		try
		{
			st.execute(promotionEntry);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void recordSnapshot(Forest forest)
	{
		for ( Tree tree : forest.getTrees() ){
			snapshot(tree);
		}
	}
	
	private void snapshot(Tree tree) {
		if(!tree.isDead())
		{
			String snapshotEntry="INSERT INTO SNAPSHOT VALUES ("+tree.getId()+", "+run.getRunNumber()+", "+run.getCurrentYear()+", "
					+tree.getArchitecture().getTrunkHeight()+", "+tree.getArchitecture().getTrunkDiameter()+", '"
					+tree.getStrata().toString()+"', '"+tree.getType().toString()+"', "+tree.getHealth().getHealthScore()+");";
			try
			{
				st.execute(snapshotEntry);
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	// Database methods
	public void closeConnection()
	{
		try
		{
			st.close();
			dbConnection.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	private void dropTables()
	{
		String drop="DROP TABLE DEATH;DROP TABLE PROMOTION;DROP TABLE SNAPSHOT;DROP TABLE TREE;";
		try
		{
			st.execute(drop);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
