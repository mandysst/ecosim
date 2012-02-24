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
		//read in form
		int numX, numY;
		double sizeX, sizeY;
		
		numX=Integer.parseInt(c.getFromRequest("numX"));
		numY=Integer.parseInt(c.getFromRequest("numY"));
		sizeX=Double.parseDouble(c.getFromRequest("sizeX"));
		sizeY=Double.parseDouble(c.getFromRequest("sizeY"));
		
		System.out.println(numX+" "+numY);
		
		PlotLayout pLayout = new PlotLayout(numX, numY, sizeX, sizeY);
		SimulationParameters p = new SimulationParameters(Integer.parseInt(c.getFromRequest("numYears")), Integer.parseInt(c.getFromRequest("numSim")), pLayout);
		Simulation sim = new Simulation("Simple Demonstration", p);
		context.put(EcoSimParam.Simulation, sim);
		System.out.println("Simulation initialized");
	    return new RedirectResponse("load");
	}
}