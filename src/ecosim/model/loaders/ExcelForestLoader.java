package ecosim.model.loaders;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ecosim.model.Forest;
import ecosim.model.Location;
import ecosim.model.Species;
import ecosim.model.Stratum;
import ecosim.model.Tree;
import ecosim.model.TreeHealth;
import ecosim.model.TreeType;
import ecosim.model.architecture.DiameterBasedArchitectureCalculator;
import ecosim.model.architecture.TreeArchitecture;
import ecosim.sim.SpeciesMap;
import framework.excel.BasicCell;
import framework.excel.BasicSheet;
import framework.excel.RowRange;

public class ExcelForestLoader implements ForestLoader {
	
	final File excelFile;
	
	final int EXCEL_PLOTX=0, EXCEL_PLOTY=3, EXCEL_XPOS=4, EXCEL_YPOS=5, EXCEL_SPECIES=6, EXCEL_MEASUREMENT=7, EXCEL_STRATUM=8, EXCEL_ISDEAD=10;
	final int EXCEL_COL_WIDTH=10;
	final int EXCEL_START_ROW=1;
	
	
	public ExcelForestLoader(File excelFile) {
		this.excelFile = excelFile;
	}
	
	@Override
	public Forest loadForest(SpeciesMap speciesMap) {

		/* Create a POI Workbook/Worksheet for the excel file sent in through the constructor.
		 * 
		 * See TestLoader for a little bit of an example...
		 * 
		 * Create a Forest Object with the layout sent in through the constructor.
		 * 
		 * For each row, get the Plot X, Plot Y
		 * 		This part is a little undetermined - they numbered these in a way that is different than
		 * 		we'd like.  For now, use column A to indicate PlotX, and column C to indicate PlotY.  Ignore column B
		 * 		For column C, "lower" means PlotY = 0, "Upper" means PlotY = 1*/
		Forest loadForest = new Forest();
		try
		{
			FileInputStream forestStream = new FileInputStream(excelFile);
			XSSFWorkbook forestWorkbook = new XSSFWorkbook(forestStream);
			XSSFSheet forestSheet = forestWorkbook.getSheetAt(0);
			BasicSheet forestBasic = new BasicSheet(forestWorkbook, forestSheet);
			RowRange rowIter = forestBasic.getRowRange(EXCEL_START_ROW);
			BasicCell tempCell;
			ArrayList<String> treeData = new ArrayList<String>();
			
			while(rowIter.getCell().hasValue())
			{
				treeData.clear();
				for(int i=0;i<=EXCEL_COL_WIDTH;i++)
				{
					tempCell = rowIter.getCellAndMove();
					treeData.add(tempCell.read());
				}
				
				if(!validInput(treeData))
				{
					//reset col and go to next row
					rowIter.setRowIndex(rowIter.getRowIndex()+1);
					rowIter.setColIndex(0);
					continue;
				}
				
				if(treeData.get(EXCEL_ISDEAD).equalsIgnoreCase("dead"))
				{
					//reset col and go to next row
					rowIter.setRowIndex(rowIter.getRowIndex()+1);
					rowIter.setColIndex(0);
					continue;
				}
				
				
				//Species treeSpecies = speciesMap.get(treeData.get(EXCEL_SPECIES));
				Species treeSpecies = speciesMap.get("Oak");
				Tree rowTree = new Tree(loadForest.nextTreeId(),treeSpecies.getName(),new Location(Double.parseDouble(treeData.get(EXCEL_XPOS)),Double.parseDouble(treeData.get(EXCEL_YPOS))));
				rowTree.setHealth(new TreeHealth(8));
				rowTree.setType(TreeType.Adult);
				//rowTree.setType(TreeType.Sapling);
				
				rowTree.setStrata(Stratum.parseString(treeData.get(EXCEL_STRATUM)));
				
				DiameterBasedArchitectureCalculator dbac = new DiameterBasedArchitectureCalculator(Double.parseDouble(treeData.get(EXCEL_MEASUREMENT)) , rowTree, treeSpecies);
				rowTree.setArchitecture(new TreeArchitecture(dbac));
				
				loadForest.getTrees().add(rowTree);
				
				//reset col and go to next row
				rowIter.setRowIndex(rowIter.getRowIndex()+1);
				rowIter.setColIndex(0);				
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		 * 
		 * 		Create a Plot object with the plotx/y values in the row and see if it already exists in the forest  
		 * 		If the plot doesn't exist, then add it.
		 * 
		 * 		Next read the species abbreviation from column F
		 * 
		 * 		You will need to look up the full name of the species based on the abbreviation (we'll need code to
		 * 		do this) and then use the speciesMap passed into this function to get the species object.
		 * 		ex. Species species = speciesMap.get("Oak"); <- The tree name comes from the excel column F
		 * 
		 * 		Now you need to actually create the tree.
		 * 		Read the x y location from D an E.  Then create the tree object
		 * 		ex. Tree tree = new Tree(forest.nextTreeId(), species.getName(), new Location(x, y), plot);
		 * 
		 * 		The file we are using right now doesn't have a health value for the trees, so just use a default
		 *		tree.setHealth(new TreeHealth(8));
		 *		
		 *		These are all Sapling
		 *		tree.setType(TreeType.Sapling);
		 *	
		 *		Their strata is set from column H
		 *		tree.setStrata(Stratum.XYX);  <-  Where XYZ corresponds with whats in column H
		 *
		 *		These will all be diameter-based architectures.  We might need to convert column G from cm to m
		 *		DiameterBasedArchitectureCalculator c = new DiameterBasedArchitectureCalculator("column G" , tree, oak);
		 *		tree.setArchitecture(new TreeArchitecture(c));
		 *
		 *		Once the tree is constructed, add it to the plot and the forest
		 *		plot.getTrees().add(tree);
		 *		forest.getTrees().add(tree);
		 * 
		 * 		Move to the next row.  Skip any tree that is already marked as dead.
		 * 
		 */
		
		// return the forest.
		return loadForest;
	}
	
	public boolean validInput(ArrayList<String> treeValid)
	{
		boolean isValid=true;
		String reason="";
		if(treeValid.get(EXCEL_PLOTX).equalsIgnoreCase(""))
		{
			isValid=false;
			reason+="INVALID TREE DATA: Plot in column A must have a value.\n";
		}
		if(treeValid.get(EXCEL_PLOTY).equalsIgnoreCase(""))
		{
			isValid=false;
			reason+="INVALID TREE DATA: Plot in column D must have a value (Upper or Lower).\n";
		}
		if(treeValid.get(EXCEL_XPOS).equalsIgnoreCase(""))
		{
			isValid=false;
			reason+="INVALID TREE DATA: X Coordinate in column E must have a value.\n";
		}
		if(treeValid.get(EXCEL_YPOS).equalsIgnoreCase(""))
		{
			isValid=false;
			reason+="INVALID TREE DATA: Y Coordinate in column F must have a value.\n";
		}
		if(treeValid.get(EXCEL_SPECIES).equalsIgnoreCase(""))
		{
			isValid=false;
			reason+="INVALID TREE DATA: Tree must have an assigned species.\n";
		}
		if(treeValid.get(EXCEL_MEASUREMENT).equalsIgnoreCase(""))
		{
			isValid=false;
			reason+="INVALID TREE DATA: DBH column must have a numeric value.\n";
		}
		if(treeValid.get(EXCEL_STRATUM).equalsIgnoreCase(""))
		{
			isValid=false;
			reason+="INVALID TREE DATA: Stratum column must have a valid stratum value.\n";
		}
		
		if(!reason.equalsIgnoreCase("")){System.out.println(reason);}
		return isValid;
	}

}
