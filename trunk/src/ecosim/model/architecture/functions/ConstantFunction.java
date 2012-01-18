package ecosim.model.architecture.functions;

public class ConstantFunction implements ArchitectureFunction {

	private final float C;
	
	public ConstantFunction(float c) {
		C = c;
	}

	@Override
	public float evaluate(float parameter) {
		return C;
	}
	
}
