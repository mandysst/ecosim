package ecosim.web.controllers;

import ecosim.model.PlotLayout;
import ecosim.sim.Simulation;
import ecosim.sim.SimulationParameters;
import ecosim.web.model.EcoSimWebContext;
import ecosim.web.view.EcoSimParam;
import framework.web.AbstractController;
import framework.web.AbstractWebContext;
import framework.web.response.RedirectResponse;
import framework.web.response.Response;

public class Initialize extends AbstractController {

	public Initialize() {
		super();
		this.supportedUrls.add("/initialize");
		
	}
	
	public Response processRequest(AbstractWebContext c) {
		
		// Create the simulation object, initialize it with the parameters, store it in the session.
		EcoSimWebContext context = (EcoSimWebContext) c;
		SimulationParameters p = new SimulationParameters(10, 1, new PlotLayout());
		Simulation sim = new Simulation("Simple Demonstration", p);
		context.put(EcoSimParam.Simulation, sim);
		System.out.println("Simulation initialized");
	    return new RedirectResponse("load");
	}
}