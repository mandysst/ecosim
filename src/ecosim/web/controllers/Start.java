package ecosim.web.controllers;

import org.jdom.Document;

import ecosim.web.model.EcoSimWebContext;
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
		
		Document pageXml = context.buildPageXml();
	   
	    return new PageResponse(new ModelView(XslViews.Start, pageXml));
	}
}
