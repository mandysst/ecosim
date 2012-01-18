package ecosim.web.view;

import java.util.LinkedList;
import java.util.List;

import framework.web.AbstractParam;


public enum EcoSimParam implements AbstractParam {
	JobId("jobId"),
	AlertMessage("alertMessage");
	
	private String name;

	EcoSimParam(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public static List<AbstractParam> asList() {
		LinkedList<AbstractParam> retval = new LinkedList<AbstractParam>();
		for ( EcoSimParam p : EcoSimParam.values() ) {
			retval.add(p);
		}
		return retval;
	}
}
