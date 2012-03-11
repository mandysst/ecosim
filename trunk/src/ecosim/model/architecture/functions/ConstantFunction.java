package ecosim.model.architecture.functions;

import org.jdom.Element;

public class ConstantFunction implements ArchitectureFunction {

	private final double C;
	
	public ConstantFunction(double c) {
		C = c;
	}

	@Override
	public double evaluate(double parameter) {
		return C;
	}
	
	public Element getXMLElement()
	{
		Element retVal = new Element("a_function");
		Element cVal = new Element("x0");
		cVal.setText(""+C);
		retVal.addContent(cVal);
		return retVal;
	}
}
