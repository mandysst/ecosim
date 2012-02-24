package ecosim.web.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import com.oreilly.servlet.MultipartRequest;

import ecosim.model.PlotLayout;
import ecosim.model.loaders.ExcelForestLoader;
import ecosim.model.loaders.TestLoader;
import ecosim.sim.Simulation;
import ecosim.web.view.EcoSimParam;
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
		File excelFile=new File("");
		try {
			File tempDir = (File) c.getServlet().getServletContext().getAttribute("javax.servlet.context.tempdir");
			MultipartRequest mreq = new MultipartRequest(c.getRequest(), tempDir.getAbsolutePath());
			Enumeration<?> files = mreq.getFileNames(); 
			while (files.hasMoreElements()) { 
				String name = (String)files.nextElement(); 
				String filename = mreq.getFilesystemName(name); 
				excelFile = mreq.getFile(name);
				String type = mreq.getContentType(name); 
				System.out.println("name: " + filename); 
				System.out.println("filename: " + filename); 
				System.out.println("type: " + type); 
				
				// Need to load the data from this excel file.
			} 

		} catch (IOException e) {
			throw new RuntimeException (e);
		}

		// Read the excel file and load in all the tree data into the simulation object
		Simulation sim = (Simulation) c.get(EcoSimParam.Simulation);
		
		sim.setSpeciesMap(c.getServlet().getServletContext().getRealPath("/")+"data");

		ExcelForestLoader tl = new ExcelForestLoader(excelFile, sim.getSimParams().getPlotLayout());
		sim.setOriginalForest(tl.loadForest(sim.getSpeciesMap()));
		
		System.out.println(sim.getOriginalForest().getTrees().toString()); //DEBUG

		System.out.println("Tree data read from input file");
		return new RedirectResponse("speciesList");
	}
}
