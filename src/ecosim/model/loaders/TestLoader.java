package ecosim.model.loaders;

import ecosim.model.Forest;
import ecosim.model.Location;
import ecosim.model.Plot;
import ecosim.model.PlotLayout;
import ecosim.model.Species;
import ecosim.model.Tree;

public class TestLoader implements ForestLoader {

	@Override
	public Forest loadForest() {
		
		PlotLayout layout = new PlotLayout(2, 2, 10, 33);
		Forest forest = new Forest(layout);
		
		Plot p1 = new Plot(0, 0, layout);
		forest.getPlots().add(p1);
		
		
		
		Species oak =new Species("Oak", null, null);
		
		Tree tree = new Tree(oak.getName(), new Location(5.4, 2.3),p1);
		p1.getTrees().add(tree);
		forest.getTrees().add(tree);
		
		return forest;
	}
	


}
