package ecosim.web.model;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;

import framework.web.AbstractWebContext;

public class EcoSimWebContext extends AbstractWebContext {

	public EcoSimWebContext(HttpServlet dispatchServlet, HttpServletRequest request, HttpServletResponse response) {
		super(dispatchServlet, request, response);
	}
	@Override
	public String getContextName() {
		return "ecosim";
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}
	
	private Document buildNullPage() {
		Document pageXml = new Document();
	    Element root = new Element("ecosim");
	    pageXml.addContent(root);
	    return pageXml;
	}

	@Override
	public Document buildPageXml() {
		return buildNullPage();
	}

	@Override
	public Document buildErrorPageXml(String errorMessage, Throwable e) {
		return buildNullPage();
	}

}
