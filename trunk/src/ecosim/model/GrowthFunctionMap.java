package ecosim.model;

import java.util.concurrent.ConcurrentHashMap;

import ecosim.model.growth.GrowthCalculator;
import ecosim.model.growth.GrowthFactorKey;

public class GrowthFunctionMap extends ConcurrentHashMap<GrowthFactorKey, GrowthCalculator>{

}
