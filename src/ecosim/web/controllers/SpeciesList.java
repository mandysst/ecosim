package ecosim.web.controllers;

import java.io.IOException;

import org.jdom.Document;
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

public class SpeciesList extends AbstractController {

	public SpeciesList() {
		super();
		this.supportedUrls.add("/speciesList");
		
	}
	
	public Response processRequest(AbstractWebContext c) {
		EcoSimWebContext context = (EcoSimWebContext) c;
		
		Document pageXml = context.buildPageXml();
		
		Simulation sim = (Simulation) c.get(EcoSimParam.Simulation);
		if(sim.getSpeciesMap()!=null)
		{
			pageXml.getRootElement().addContent(sim.getSpeciesMap().getXMLElement());
		}
		
		
		//XMLOutputter for DEBUG
		//XMLOutputter o = new XMLOutputter(Format.getPrettyFormat());
		//try{o.output(pageXml, System.out);} catch (IOException e){e.printStackTrace();}
	   
		
	    return new PageResponse(new ModelView(XslViews.SpeciesList, pageXml));
	}
}