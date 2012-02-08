package ecosim.model.loaders;

import java.io.File;

import ecosim.model.Forest;
import ecosim.model.Location;
import ecosim.model.PlotLayout;
import ecosim.model.Species;
import ecosim.model.Stratum;
import ecosim.model.Tree;
import ecosim.model.TreeHealth;
import ecosim.model.TreeType;
import ecosim.model.architecture.DiameterBasedArchitectureCalculator;
import ecosim.model.architecture.TreeArchitecture;
import ecosim.sim.SpeciesMap;

public class ExcelForestLoader implements ForestLoader {
	
	final File excelFile;
	final PlotLayout layout;
	
	
	public ExcelForestLoader(File excelFile, PlotLayout layout) {
		this.excelFile = excelFile;
		this.layout = layout;
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
		 * 		For column C, "lower" means PlotY = 0, "Upper" means PlotY = 1
		 * 
		 * 		Create a Plot object with the plotx/y values in the row and see if it already exists in the forrest  
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
		return null;
	}

}
