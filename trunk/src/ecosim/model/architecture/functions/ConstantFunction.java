package ecosim.model.architecture.functions;

public class ConstantFunction implements ArchitectureFunction {

	private final double C;
	
	public ConstantFunction(double c) {
		C = c;
	}

	@Override
	public double evaluate(double parameter) {
		return C;
	}
	
}
