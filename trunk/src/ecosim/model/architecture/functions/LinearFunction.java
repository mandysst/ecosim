package ecosim.model.architecture.functions;

public class LinearFunction implements ArchitectureFunction {

	private final float M;
	private final float B;
	
	public LinearFunction(float m, float b) {
		this.M = m;
		this.B = b;
	}
	@Override
	public float evaluate(float x) {
		return M * x + B;
	}

}
