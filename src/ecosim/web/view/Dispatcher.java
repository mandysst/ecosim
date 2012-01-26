package ecosim.web.view;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecosim.web.model.EcoSimWebContext;
import framework.db.DatabaseConnectionParameters;
import framework.web.AbstractController;
import framework.web.AbstractDispatch;
import framework.web.AbstractParam;
import framework.web.AbstractWebContext;

public class Dispatcher extends AbstractDispatch {

	
	
	public Dispatcher() {
		super();
	}
	
	@Override
	protected void applyRedirectForAuthentication(AbstractWebContext context) {
		throw new RuntimeException("Authentication not implemented for EcoSim");
	}

	@Override
	protected boolean redirectForAuthentication(AbstractWebContext context, AbstractController controller) {
		return false;// no authentication yet for qgrs
	}

	@Override
	protected AbstractWebContext buildContext(HttpServlet dispatchServlet, HttpServletRequest request, HttpServletResponse response) {
		return new EcoSimWebContext(dispatchServlet, request, response);
	}
	
	@Override
	protected List<AbstractParam> getPersistableParamList() {
		return EcoSimParam.asList();
	}

	@Override
	protected String getErrorView() {
		return XslViews.Error;
	}

	@Override
	protected String getControllerPackage() {
		return "ecosim.web.controllers";
	}

	@Override
	protected DatabaseConnectionParameters getDbParams() {
		// TODO Auto-generated method stub
		return null;
	}

}
