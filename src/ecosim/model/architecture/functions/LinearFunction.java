package ecosim.model.architecture.functions;

import org.jdom.Element;

public class LinearFunction implements ArchitectureFunction {

	private final double M;
	private final double B;
	
	public LinearFunction(double m, double b) {
		this.M = m;
		this.B = b;
	}
	@Override
	public double evaluate(double x) {
		return M * x + B;
	}
	
	public Element getXMLElement()
	{
		Element retVal = new Element("a_function");
		Element bVal = new Element("x0");
		Element mVal = new Element("x1");
		bVal.setText(""+B);
		mVal.setText(""+M);
		retVal.addContent(bVal);
		retVal.addContent(mVal);
		return retVal;
	}

}
