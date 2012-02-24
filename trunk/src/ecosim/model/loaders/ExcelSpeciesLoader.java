package ecosim.model.loaders;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ecosim.model.ArchitectureMap;
import ecosim.model.GrowthFunctionMap;
import ecosim.model.MortalityMap;
import ecosim.model.Species;
import ecosim.model.Stratum;
import ecosim.model.TreeType;
import ecosim.model.architecture.ArchitectureKey;
import ecosim.model.architecture.ArchitectureProperty;
import ecosim.model.architecture.functions.ArchitectureFunction;
import ecosim.model.architecture.functions.ConstantFunction;
import ecosim.model.architecture.functions.LinearFunction;
import ecosim.model.growth.BasicCanapyGrowthCalculator;
import ecosim.model.growth.GrowthCalculator;
import ecosim.model.growth.GrowthFactorKey;
import ecosim.model.mortality.BasicMortalityCalculator;
import ecosim.model.mortality.MortalityCalculator;
import ecosim.model.mortality.MortalityKey;
import ecosim.sim.SpeciesMap;
import framework.excel.BasicCell;
import framework.excel.BasicSheet;
import framework.excel.RowRange;

public class ExcelSpeciesLoader implements SpeciesLoader {

	private final File excelFile;
	
	final int EXCEL_START_ROW=1;
	final int EXCEL_INFO_WIDTH=1, EXCEL_MORT_WIDTH=2, EXCEL_GROW_WIDTH=2, EXCEL_ARC_WIDTH=6;
	final int EXCEL_SHEET_INFO=0, EXCEL_SHEET_MORT=1, EXCEL_SHEET_GROWTH=2, EXCEL_SHEET_ARC=3;
	final int EXCEL_KEY=0, EXCEL_VALUE=1; // Info
	final int EXCEL_TYPE=0, EXCEL_STRATUM=1; // General
	final int EXCEL_PERCENT=2; // Mortality
	final int EXCEL_CLASS=2; // Growth
	final int EXCEL_INDEP=2, EXCEL_DEP=3, EXCEL_X0=4, EXCEL_X1=5, EXCEL_X2=6; // Architecture
	
	public RowRange rowIter;
	public ArrayList<String> speciesData;
	
	
	public ExcelSpeciesLoader (File excelFile) {
		this.excelFile = excelFile;
	}
	
		
	@Override
	public void loadSpecies(SpeciesMap speciesMap) {

		try{
		FileInputStream speciesStream = new FileInputStream(excelFile);
		XSSFWorkbook speciesWorkbook = new XSSFWorkbook(speciesStream);
		XSSFSheet mortSheet = speciesWorkbook.getSheetAt(EXCEL_SHEET_MORT);
		BasicSheet mortBasic = new BasicSheet(speciesWorkbook, mortSheet);
		RowRange rowIter = mortBasic.getRowRange(EXCEL_START_ROW);
		BasicCell tempCell;
		speciesData = new ArrayList<String>();
		
		TreeType type=TreeType.Adult;
		Stratum stratum=Stratum.Canopy;
		
		MortalityMap mMap = new MortalityMap();
		
		loadMortality(mMap, mortBasic);
		
		
		
		XSSFSheet growSheet = speciesWorkbook.getSheetAt(EXCEL_SHEET_GROWTH);
		BasicSheet growBasic = new BasicSheet(speciesWorkbook, growSheet);
		GrowthFunctionMap gMap = new GrowthFunctionMap();
		
		loadGrowth(gMap, growBasic);
		
		
		
		XSSFSheet arcSheet = speciesWorkbook.getSheetAt(EXCEL_SHEET_ARC);
		BasicSheet arcBasic = new BasicSheet(speciesWorkbook, arcSheet);
		rowIter = arcBasic.getRowRange(EXCEL_START_ROW);
		
		ArchitectureFunction arcFunc = new LinearFunction(1, 0.25);
		ArchitectureMap aMap = new ArchitectureMap();
		ArchitectureProperty ind=ArchitectureProperty.TrunkDiameter, dep=ArchitectureProperty.TrunkHeight;
		String independent="", dependent="";
		while(rowIter.getCell().hasValue())
		{
			speciesData.clear();
			for(int i=0;i<=EXCEL_ARC_WIDTH;i++)
			{
				tempCell = rowIter.getCellAndMove();
				speciesData.add(tempCell.read());
			}
			ind=ArchitectureProperty.TrunkDiameter;
			dep=ArchitectureProperty.TrunkHeight;
			independent="";
			dependent="";
			
			type=parseType(speciesData.get(EXCEL_TYPE));
			
			stratum=Stratum.parseString(speciesData.get(EXCEL_STRATUM));
			
			if(speciesData.get(EXCEL_INDEP).equalsIgnoreCase("TD")){independent="TD";ind=ArchitectureProperty.TrunkDiameter;}
			else if(speciesData.get(EXCEL_INDEP).equalsIgnoreCase("TH")){independent="TH";ind=ArchitectureProperty.TrunkHeight;}
			else{/*bad data*/}
			
			if(independent.equalsIgnoreCase("TD"))
			{
				if(speciesData.get(EXCEL_DEP).equalsIgnoreCase("TH")){dependent="TH";dep=ArchitectureProperty.TrunkHeight;}
				else if(speciesData.get(EXCEL_DEP).equalsIgnoreCase("CH")){dependent="CH";dep=ArchitectureProperty.CanapyHeight;}
				else if(speciesData.get(EXCEL_DEP).equalsIgnoreCase("CD")){dependent="CD";dep=ArchitectureProperty.CanapyDiameter;}
				else{/*bad data*/}
			}
			else
			{
				if(speciesData.get(EXCEL_DEP).equalsIgnoreCase("TD")){dependent="TD";dep=ArchitectureProperty.TrunkDiameter;}
				else if(speciesData.get(EXCEL_DEP).equalsIgnoreCase("CH")){dependent="CH";dep=ArchitectureProperty.CanapyHeight;}
				else if(speciesData.get(EXCEL_DEP).equalsIgnoreCase("CD")){dependent="CD";dep=ArchitectureProperty.CanapyDiameter;}
				else{/*bad data*/}
			}
			
			if(!speciesData.get(EXCEL_X2).equalsIgnoreCase(""))
			{
				double x2, x1, x0;
				x2=Double.parseDouble(speciesData.get(EXCEL_X2));
				x1=Double.parseDouble(speciesData.get(EXCEL_X1));
				x0=Double.parseDouble(speciesData.get(EXCEL_X0));
				arcFunc = new LinearFunction(x1, x0);
			}
			else if(!speciesData.get(EXCEL_X1).equalsIgnoreCase(""))
			{
				double x1, x0;
				x1=Double.parseDouble(speciesData.get(EXCEL_X1));
				x0=Double.parseDouble(speciesData.get(EXCEL_X0));
				arcFunc = new LinearFunction(x1, x0);
			}
			else if(!speciesData.get(EXCEL_X0).equalsIgnoreCase(""))
			{
				double x0;
				x0=Double.parseDouble(speciesData.get(EXCEL_X0));
				arcFunc = new ConstantFunction(x0);
			}
			aMap.put(new ArchitectureKey(type, stratum, ind, dep), arcFunc);
			
			rowIter.setRowIndex(rowIter.getRowIndex()+1);
			rowIter.setColIndex(0);
		}
		
		
		XSSFSheet infoSheet = speciesWorkbook.getSheetAt(EXCEL_SHEET_INFO);
		BasicSheet infoBasic = new BasicSheet(speciesWorkbook, infoSheet);
		
		Species oak = new Species(infoBasic.getCell(1, "B").read(), aMap, mMap, gMap);
		speciesMap.put(infoBasic.getCell(2, "B").read(), oak);
		
		
		//speciesMap.put(abrev, speciesData);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		/*  Take a look at DefaultSpeciesLoader.
		 * 
		 *  This code simply adds a SINGLE species.  Each species requires mortality rate calculators 
		    a number of parameters (encapsulated by a MortalityKey), growth calculators (similarly key'd), 
			and architecture functions.
			
			We need to create (offline) an Excel format to embed all these a parameters, and then 
			enhance this loader to read from that excel file.
				
			The excel file would have many species in it.
			
			Later, we could allow the user to upload a spreadsheet of similar format in order to manipulate these params
			but for now we'll just store it in WebContent/excel
		*/

	}
	
	public String loadMortality(MortalityMap m, BasicSheet mSheet)
	{
		BasicCell tempCell;
		rowIter = mSheet.getRowRange(EXCEL_START_ROW);
		MortalityCalculator mCalc;
		TreeType type=TreeType.Adult;
		Stratum stratum=Stratum.Canopy;
		String error="";
		
		while(rowIter.getCell().hasValue())
		{
			speciesData.clear();
			
			for(int i=0;i<=EXCEL_MORT_WIDTH;i++)
			{
				tempCell = rowIter.getCellAndMove();
				speciesData.add(tempCell.read());
			}
			mCalc = new BasicMortalityCalculator(Double.parseDouble(speciesData.get(EXCEL_PERCENT)));
			
			type=parseType(speciesData.get(EXCEL_TYPE));
			
			stratum=Stratum.parseString(speciesData.get(EXCEL_STRATUM));
			
			m.put(new MortalityKey(type,stratum),mCalc);
			
			rowIter.setRowIndex(rowIter.getRowIndex()+1);
			rowIter.setColIndex(0);
		}
		
		return error;
	}
	
	public String loadGrowth(GrowthFunctionMap g, BasicSheet gSheet)
	{
		BasicCell tempCell;
		rowIter = gSheet.getRowRange(EXCEL_START_ROW);
		GrowthCalculator growthCalc=new BasicCanapyGrowthCalculator();
		TreeType type=TreeType.Adult;
		Stratum stratum=Stratum.Canopy;
		String error="";
		
		while(rowIter.getCell().hasValue())
		{
			speciesData.clear();
			for(int i=0;i<=EXCEL_GROW_WIDTH;i++)
			{
				tempCell = rowIter.getCellAndMove();
				speciesData.add(tempCell.read());
			}
			type=parseType(speciesData.get(EXCEL_TYPE));
			
			stratum=Stratum.parseString(speciesData.get(EXCEL_STRATUM));
			
			if(speciesData.get(EXCEL_CLASS).equalsIgnoreCase("basic")){growthCalc=new BasicCanapyGrowthCalculator();}
			
			g.put(new GrowthFactorKey(type, stratum), growthCalc);
			
			rowIter.setRowIndex(rowIter.getRowIndex()+1);
			rowIter.setColIndex(0);
		}
		
		return error;
	}
	
	public TreeType parseType(String ty)
	{
		TreeType retVal=TreeType.Adult;
		
		if(ty.equalsIgnoreCase("Adult")){retVal=TreeType.Adult;}
		else if(ty.equalsIgnoreCase("Sapling")){retVal=TreeType.Sapling;}
		else if(ty.equalsIgnoreCase("Seedling")){retVal=TreeType.Seedling;}
		
		return retVal;
	}

}
