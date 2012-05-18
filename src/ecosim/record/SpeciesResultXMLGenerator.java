package ecosim.record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.jdom.Element;




public class SpeciesResultXMLGenerator
{
	
	
	class YearTotal {
		private double total = 1;

		public double getTotal()
		{
			return total;
		}

		public void increment() {
			total += 1;
		}
		
		public YearTotal(double total) {
			this.total = total;
		}
		public YearTotal() {
			this.total = 1;
		}
		
	}
	
	class YearAverage {
		private double total;
		private int count;
		
		public YearAverage(double  value) {
			total = value;
			count = 1;
		}
		
		public void registerValue(double value) {
			total += value;
			count++;
		}
		
		public double getAverage() {
			return total / count;
		}
		
	}
	class SpeciesRunYear {
		public final String species;
		public final int run;
		public final int year;
		
		public SpeciesRunYear (String species, int run, int year) {
			this.species = species;
			this.run = run;
			this.year = year;
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + run;
			result = prime * result
					+ ((species == null) ? 0 : species.hashCode());
			result = prime * result + year;
			return result;
		}

		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SpeciesRunYear other = (SpeciesRunYear) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (run != other.run)
				return false;
			if (species == null)
			{
				if (other.species != null)
					return false;
			} else if (!species.equals(other.species))
				return false;
			if (year != other.year)
				return false;
			return true;
		}

		private SpeciesResultXMLGenerator getOuterType()
		{
			return SpeciesResultXMLGenerator.this;
		}
		
		
	}
	
	class SpeciesYear {
		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result
					+ ((species == null) ? 0 : species.hashCode());
			result = prime * result + year;
			return result;
		}

		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SpeciesYear other = (SpeciesYear) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (species == null)
			{
				if (other.species != null)
					return false;
			} else if (!species.equals(other.species))
				return false;
			if (year != other.year)
				return false;
			return true;
		}

		public final String species;
		public final int year;
		
		public SpeciesYear (String species, int year) {
			this.species = species;
			this.year = year;
		}

		private SpeciesResultXMLGenerator getOuterType()
		{
			return SpeciesResultXMLGenerator.this;
		}
		
	}
	
	Element generatedXML;
	HashMap<SpeciesRunYear, YearTotal> masterMap;
	int[][] runYearTotals;
	int[] yearlyTreeAverages;
	
	public SpeciesResultXMLGenerator(int runs, int years)
	{
		masterMap=new HashMap<SpeciesRunYear, YearTotal>();
		generatedXML=new Element("SpeciesInfo");
		runYearTotals=new int[runs][years];
		yearlyTreeAverages=new int[years];
		for(int i=0;i<yearlyTreeAverages.length;i++){yearlyTreeAverages[i]=0;}
		for(int i=0;i<runYearTotals.length;i++){for(int j=0;j<runYearTotals[0].length;j++){runYearTotals[i][j]=0;}}
	}
	
	public void populateMasterMap(Object[][] data)
	{
		/**
		 * row (tree id, species name, run year)
		 * 
		 *    for each species, find all the runs
		 * 		for each run, find all years
		 * 			for each year, keep track of how many trees are alive
		 */
		
		for(int i=0;i<data.length;i++)
		{
			String species = (String) data[i][3];
			int run = (Integer) data[i][1];
			int year = (Integer) data[i][2];
			SpeciesRunYear sry = new SpeciesRunYear(species, run, year);
			YearTotal yt = this.masterMap.get(sry);
			if ( yt == null ) {
				this.masterMap.put(sry, new YearTotal());
			}
			else {
				yt.increment();
			}
			runYearTotals[run][year]++;
		}
		
		populateSpeciesAverage();
		
		System.out.println(masterMap.toString());
		for(int i=0;i<runYearTotals.length;i++){
			for(int j=0;j<runYearTotals[0].length;j++){
				System.out.print(runYearTotals[i][j]+", ");
			}
			System.out.println();
		}
		
		return;
		
	}
	
	private void populateSpeciesAverage()
	{
		/* Build a SpeciesRunYear / YearTotal, where Run = -1, and YearTotal is the average # of trees in a year, across all runs 
		 */
		HashMap<SpeciesYear, YearAverage> yearlyAverages = new HashMap<SpeciesYear, YearAverage>();
		for ( SpeciesRunYear sry : masterMap.keySet() ) {
			SpeciesYear sy = new SpeciesYear(sry.species, sry.year);
			YearAverage avg = yearlyAverages.get(sy);
			if ( avg == null ) {
				yearlyAverages.put(sy, new YearAverage(masterMap.get(sry).getTotal()));
			}
			else {
				avg.registerValue(masterMap.get(sry).getTotal());
			}
		}
		for ( SpeciesYear sy :  yearlyAverages.keySet() ) {
			SpeciesRunYear sry = new SpeciesRunYear(sy.species, -1, sy.year);
			masterMap.put(sry, new YearTotal(yearlyAverages.get(sy).getAverage()));
		}
		
		
		/*
		 * Then build the avereage of all of those, across all species.
		*/
		
		int numYears=runYearTotals[0].length, numRuns=runYearTotals.length;
		for(int year=0;year<numYears;year++)
		{
			for(int run=0;run<numRuns;run++)
			{
				yearlyTreeAverages[year]+=runYearTotals[run][year];
			}
			yearlyTreeAverages[year]/=numRuns;
		}
		
	}
	
	public Element getXMLElement()
	{
		HashSet<String> speciesSet=new HashSet<String>();
		Iterator mapIt=masterMap.entrySet().iterator();
		Element records=new Element("records");
		Element elRec;
		while(mapIt.hasNext())
		{
			Map.Entry<SpeciesRunYear, YearTotal> pairs = (Map.Entry<SpeciesRunYear, YearTotal>)mapIt.next();
			elRec=new Element("record");
			elRec.setAttribute("species",pairs.getKey().species);
			elRec.setAttribute("run",""+pairs.getKey().run);
			elRec.setAttribute("year",""+pairs.getKey().year);
			if(pairs.getKey().run==-1)//All runs
			{
				elRec.addContent(""+(pairs.getValue().getTotal()/yearlyTreeAverages[pairs.getKey().year]));
			}
			else{elRec.addContent(""+(pairs.getValue().getTotal()/runYearTotals[pairs.getKey().run][pairs.getKey().year]));}
			records.addContent(elRec);
			speciesSet.add(pairs.getKey().species);
		}
		generatedXML.addContent(records);
		
		Iterator setIt=speciesSet.iterator();
		Element specList=new Element("speciesList");
		Element spec=new Element("species");
		while(setIt.hasNext())
		{
			spec=new Element("species");
			spec.addContent(""+setIt.next());
			specList.addContent(spec);
		}
		generatedXML.addContent(specList);
		
		Element runList=new Element("runs");
		Element run=new Element("run");
		for(int i=0;i<runYearTotals.length;i++)
		{
			run=new Element("run");
			run.addContent(""+i);
			runList.addContent(run);
		}
		generatedXML.addContent(runList);
		
		Element yearList=new Element("years");
		Element year=new Element("year");
		for(int i=0;i<runYearTotals[0].length;i++)
		{
			year=new Element("year");
			year.addContent(""+i);
			yearList.addContent(year);
		}
		generatedXML.addContent(yearList);
		
		return generatedXML;
	}
}
