package ecosim.web.controllers;

import java.io.IOException;

import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import ecosim.record.DatabaseExtractor;
import ecosim.record.SpeciesResultXMLGenerator;
import ecosim.record.TreeResultXMLGenerator;
import ecosim.sim.Simulation;
import ecosim.web.model.EcoSimWebContext;
import ecosim.web.view.EcoSimParam;
import ecosim.web.view.XslViews;
import framework.web.AbstractController;
import framework.web.AbstractWebContext;
import framework.web.ModelView;
import framework.web.response.PageResponse;
import framework.web.response.Response;

public class ResultsSummary extends AbstractController {

	public ResultsSummary() {
		super();
		this.supportedUrls.add("/results-summary");
	}
	
	public Response processRequest(AbstractWebContext c) {
		EcoSimWebContext context = (EcoSimWebContext) c;
		
		Document pageXml = context.buildPageXml();
		
		
	   
	    return new PageResponse(new ModelView(XslViews.ResultsSummary, pageXml));
	}
}
