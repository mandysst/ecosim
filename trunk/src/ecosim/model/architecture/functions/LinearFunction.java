package ecosim.model.architecture.functions;

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

}
