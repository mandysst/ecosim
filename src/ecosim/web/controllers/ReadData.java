package ecosim.web.controllers;

import framework.web.AbstractController;
import framework.web.AbstractWebContext;
import framework.web.response.RedirectResponse;
import framework.web.response.Response;

public class ReadData extends AbstractController {
	public ReadData() {
		super();
		this.supportedUrls.add("/read-data");
		
	}
	
	public Response processRequest(AbstractWebContext c) {
		
		// Read the excel file and load in all the tree data into the simulation object
		System.out.println("Tree data read from input file");
	    return new RedirectResponse("speciesList");
	}
}
