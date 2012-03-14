package ecosim.web.controllers;

import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import ecosim.sim.Simulation;
import ecosim.web.model.EcoSimWebContext;
import ecosim.web.view.EcoSimParam;
import ecosim.web.view.XslViews;
import framework.web.AbstractController;
import framework.web.AbstractWebContext;
import framework.web.ModelView;
import framework.web.response.PageResponse;
import framework.web.response.Response;

public class SpeciesTable extends AbstractController {

	public SpeciesTable() {
		super();
		this.supportedUrls.add("/speciesTable");
		
	}
	
	public Response processRequest(AbstractWebContext c) {
		EcoSimWebContext context = (EcoSimWebContext) c;
		
		Document pageXml = context.buildPageXml();
		
		Simulation sim = (Simulation) c.get(EcoSimParam.Simulation);
		if(sim.getSpeciesMap()!=null)
		{
			Element e=sim.getSpeciesMap().getXMLElement();
			e.setAttribute("target", context.getString("value"));
			pageXml.getRootElement().addContent(e);
		}
		
	    return new PageResponse(new ModelView(XslViews.SpeciesTable, pageXml));
	}
}
