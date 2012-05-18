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

public class SpeciesDetail extends AbstractController
{

	public SpeciesDetail() {
		super();
		this.supportedUrls.add("/speciesDetail");
	}
	@Override
	public Response processRequest(AbstractWebContext c)
	{
		EcoSimWebContext context = (EcoSimWebContext) c;
		Document pageXml = context.buildPageXml();
		
		DatabaseExtractor db=new DatabaseExtractor();
		Object[][] test=db.runQuery("SELECT SNAPSHOT.ID, SNAPSHOT.RUN, SNAPSHOT.YEAR, TREE.SPECIES_NAME FROM SNAPSHOT JOIN TREE ON SNAPSHOT.ID = TREE.ID ORDER BY TREE.SPECIES_NAME, SNAPSHOT.RUN, SNAPSHOT.YEAR;");
		Simulation sim = (Simulation) c.get(EcoSimParam.Simulation);
		
		SpeciesResultXMLGenerator gen=new SpeciesResultXMLGenerator(sim.getSimParams().getNumRuns(), sim.getSimParams().getNumYears());
		gen.populateMasterMap(test);
		
		
		Element e=gen.getXMLElement();
		if(context.getString("targetSpecies")==null){e.setAttribute("targetSpecies","default");}
		else{e.setAttribute("targetSpecies", context.getString("targetSpecies"));}
		if(context.getString("selectedRun")==null){e.setAttribute("selectedRun", "0");}
		else{e.setAttribute("selectedRun", context.getString("selectedRun"));}
		if(context.getString("selectedYear")==null){e.setAttribute("selectedYear", "0");}
		else{e.setAttribute("selectedYear", context.getString("selectedYear"));}
		pageXml.getRootElement().addContent(e);

		
		try
		{
			new XMLOutputter(Format.getPrettyFormat()).output(pageXml, System.out);
		} catch (IOException ex)
		{
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
		//Species
		//SELECT SNAPSHOT.ID, SNAPSHOT.RUN, SNAPSHOT.YEAR, TREE.SPECIES_NAME FROM SNAPSHOT JOIN TREE ON SNAPSHOT.ID = TREE.ID ORDER BY TREE.SPECIES_NAME, SNAPSHOT.RUN, SNAPSHOT.YEAR;
		
		//Trees
		//SELECT * FROM SNAPSHOT JOIN TREE ON SNAPSHOT.ID = TREE.ID ORDER BY SNAPSHOT.ID, SNAPSHOT.RUN, SNAPSHOT.YEAR;
		
		db.closeConnection();
		   
	    return new PageResponse(new ModelView(XslViews.SpeciesDetail, pageXml));
	}

}
