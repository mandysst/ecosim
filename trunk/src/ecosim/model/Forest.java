package ecosim.model;

import java.util.LinkedList;
import java.util.List;

public final class Forest {

	private final List<Tree> trees = new LinkedList<Tree>();
		
	
	public List<Tree> getTrees() {
		return trees;
	}

	
	public List<Tree> findTreesAbove(Tree tree) {
		List<Tree> retval = new LinkedList<Tree>();
		System.out.println("Forest.findTreesAbove not implemented");
		return retval;
	}
	
	public int nextTreeId() {
		return this.trees.size();
	}
	
}
