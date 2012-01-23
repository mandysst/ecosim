package ecosim.model;

import java.util.concurrent.ConcurrentHashMap;

import ecosim.model.mortality.MortalityCalculator;
import ecosim.model.mortality.MortalityKey;

public class MortalityMap extends ConcurrentHashMap<MortalityKey, MortalityCalculator>{

}
