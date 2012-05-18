package ecosim.web.controllers;

import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
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

public class TreePage extends AbstractController
{

	public TreePage() {
		super();
		this.supportedUrls.add("/treePage");
	}
	@Override
	public Response processRequest(AbstractWebContext c)
	{
		EcoSimWebContext context = (EcoSimWebContext) c;
		Document pageXml = context.buildPageXml();
		
		DatabaseExtractor db=new DatabaseExtractor();
		Object[][] test=db.runQuery("SELECT SNAPSHOT.ID, SNAPSHOT.RUN, SNAPSHOT.YEAR, TREE.SPECIES_NAME FROM SNAPSHOT JOIN TREE ON SNAPSHOT.ID = TREE.ID ORDER BY TREE.SPECIES_NAME, SNAPSHOT.RUN, SNAPSHOT.YEAR;");
		Simulation sim = (Simulation) c.get(EcoSimParam.Simulation);
		
		TreeResultXMLGenerator gen2=new TreeResultXMLGenerator(db.runQuery("SELECT * FROM SNAPSHOT JOIN TREE ON SNAPSHOT.ID = TREE.ID ORDER BY SNAPSHOT.ID, SNAPSHOT.RUN, SNAPSHOT.YEAR;"));

		
		Element e=gen2.getXMLElement();
		if(context.getString("targetId")==null){e.setAttribute("targetId","0");}
		else{e.setAttribute("targetId", context.getString("targetId"));}
		if(context.getString("selectedRun")==null){e.setAttribute("selectedRun", "0");}
		else{e.setAttribute("selectedRun", context.getString("selectedRun"));}
		pageXml.getRootElement().addContent(e);

		
		//Species
		//SELECT SNAPSHOT.ID, SNAPSHOT.RUN, SNAPSHOT.YEAR, TREE.SPECIES_NAME FROM SNAPSHOT JOIN TREE ON SNAPSHOT.ID = TREE.ID ORDER BY TREE.SPECIES_NAME, SNAPSHOT.RUN, SNAPSHOT.YEAR;
		
		//Trees
		//SELECT * FROM SNAPSHOT JOIN TREE ON SNAPSHOT.ID = TREE.ID ORDER BY SNAPSHOT.ID, SNAPSHOT.RUN, SNAPSHOT.YEAR;
		
		db.closeConnection();
		   
	    return new PageResponse(new ModelView(XslViews.TreeResults, pageXml));
	}

}
