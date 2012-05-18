package ecosim.record;

import org.jdom.Element;
import org.jdom.Namespace;

public class TreeResultXMLGenerator
{
	Object[][] data;
	Element generatedXML;

	public TreeResultXMLGenerator(Object[][] d)
	{
		data=d;
		generatedXML=new Element("TreeList");
	}
	
	public Element getXMLElement()
	{
		int curId=-1, curRun=-1, curYear=-1;
		Element tree, species, locX, locY, run, year, health, height, width, type, stratum;
		tree = new Element("Tree");
		run = new Element("Run");
		for(int i=0;i<data.length;i++)
		{
			if((Integer)data[i][0]!=curId)
			{
				curId=(Integer)data[i][0];curRun=(Integer)data[i][1];curYear=(Integer)data[i][2];
				tree=new Element("Tree");
				tree.setAttribute("Id", ""+(Integer)data[i][0]);
				species=new Element("Species");species.addContent((String)data[i][12]);
				locX=new Element("LocX");locX.addContent(""+data[i][9]);
				locY=new Element("LocY");locY.addContent(""+data[i][10]);
				
				run=new Element("Run");run.setAttribute("value", ""+(Integer)data[i][1]);
				
				year=new Element("Year");year.setAttribute("value", ""+(Integer)data[i][2]);
				health=new Element("Health");health.addContent(""+data[i][7]);
				height=new Element("Height");height.addContent(""+data[i][3]);
				width=new Element("Width");width.addContent(""+data[i][4]);
				type=new Element("Type");type.addContent(""+data[i][6]);
				stratum=new Element("Stratum");stratum.addContent(""+data[i][5]);
				
				year.addContent(health);year.addContent(height);year.addContent(width);year.addContent(type);year.addContent(stratum);
				run.addContent(year);
				tree.addContent(species);tree.addContent(locX);tree.addContent(locY);tree.addContent(run);
				generatedXML.addContent(tree);
			}
			else if((Integer)data[i][1]!=curRun)
			{
				curRun=(Integer)data[i][1];curYear=(Integer)data[i][2];
				run=new Element("Run");run.setAttribute("value", ""+(Integer)data[i][1]);
				
				year=new Element("Year");year.setAttribute("value", ""+(Integer)data[i][2]);
				health=new Element("Health");health.addContent(""+data[i][7]);
				height=new Element("Height");height.addContent(""+data[i][3]);
				width=new Element("Width");width.addContent(""+data[i][4]);
				type=new Element("Type");type.addContent(""+data[i][6]);
				stratum=new Element("Stratum");stratum.addContent(""+data[i][5]);
				
				year.addContent(health);year.addContent(height);year.addContent(width);year.addContent(type);year.addContent(stratum);
				run.addContent(year);
				tree.addContent(run);
			}
			else if((Integer)data[i][2]!=curYear)
			{
				curYear=(Integer)data[i][2];
				year=new Element("Year");year.setAttribute("value", ""+(Integer)data[i][2]);
				health=new Element("Health");health.addContent(""+data[i][7]);
				height=new Element("Height");height.addContent(""+data[i][3]);
				width=new Element("Width");width.addContent(""+data[i][4]);
				type=new Element("Type");type.addContent(""+data[i][6]);
				stratum=new Element("Stratum");stratum.addContent(""+data[i][5]);
				
				year.addContent(health);year.addContent(height);year.addContent(width);year.addContent(type);year.addContent(stratum);
				run.addContent(year);
			}
		}
		return generatedXML;
	}
}
