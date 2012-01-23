package ecosim.web.controllers;

import org.jdom.Document;

import ecosim.sim.Simulation;
import ecosim.sim.SimulationParameters;
import ecosim.web.model.EcoSimWebContext;
import ecosim.web.view.EcoSimParam;
import ecosim.web.view.XslViews;
import framework.web.AbstractController;
import framework.web.AbstractWebContext;
import framework.web.ModelView;
import framework.web.response.PageResponse;
import framework.web.response.Response;

public class Start extends AbstractController {

	public Start() {
		super();
		this.supportedUrls.add("/start");
		
	}
	
	public Response processRequest(AbstractWebContext c) {
		EcoSimWebContext context = (EcoSimWebContext) c;
		
		SimulationParameters p = new SimulationParameters(10, 1);
		Simulation sim = new Simulation("Simple Demonstration", p);
		context.put(EcoSimParam.Simulation, sim);
		
		Document pageXml = context.buildPageXml();
	   
	    return new PageResponse(new ModelView(XslViews.Start, pageXml));
	}
}
