package ecosim.web.controllers;

import org.jdom.Document;

import ecosim.sim.Simulation;
import ecosim.sim.SimulationParameters;
import ecosim.web.model.EcoSimWebContext;
import ecosim.web.view.XslViews;
import framework.web.AbstractController;
import framework.web.AbstractWebContext;
import framework.web.ModelView;
import framework.web.response.PageResponse;
import framework.web.response.Response;

public class Execute extends AbstractController {

	public Execute() {
		super();
		this.supportedUrls.add("/execute");
		
	}
	
	public Response processRequest(AbstractWebContext c) {
		EcoSimWebContext context = (EcoSimWebContext) c;
		
		
		// demonstration... this will be done as a background job...

		SimulationParameters p = new SimulationParameters(10, 50);
		Simulation sim = new Simulation("Console Demonstration", p);
		sim.run();
		
		Document pageXml = context.buildPageXml();
		
	    return new PageResponse(new ModelView(XslViews.Execute, pageXml));
	}
}
