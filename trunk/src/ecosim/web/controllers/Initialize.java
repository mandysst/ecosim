package ecosim.web.controllers;

import ecosim.web.model.EcoSimWebContext;
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
		System.out.println("Simulation initialized");
	    return new RedirectResponse("load");
	}
}