package ecosim.model.architecture.functions;

import org.jdom.Element;

public interface ArchitectureFunction {

	double evaluate(double parameter);
	Element getXMLElement();
	
}
