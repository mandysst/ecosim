<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html"  indent="yes" />
	<xsl:variable name="imagePath" select="'images/'"/>
	<xsl:variable name="cssPath" select="'css/'"/>
	<xsl:variable name="jsPath" select="'javascript/'"/>
	
	<xsl:include href="utils.xsl"/>
	<xsl:include href="header.xsl"/>
	
	<xsl:template match="/">
		<xsl:text disable-output-escaping="yes">
			&lt;!doctype html> 
		</xsl:text>
		<html>
			<head>
				<xsl:call-template name="ie_meta"/>
				<xsl:call-template name="css_includes"/>
				<xsl:call-template name="javascript_includes"/>
				<style type="text/css">
				</style>
				
				<script type="text/javascript">
				</script>
				
			</head>
			<body>
				<xsl:call-template name="header">
					<xsl:with-param name="title">: Species List</xsl:with-param>
				</xsl:call-template>
				
				
				
				<form id="speciesListForm" action="confirm" name="speciesListForm" method="POST" style="padding:1em;">
					<p>On this page the user is shown the list of species that will be used in the simulation.  They can
					click on any of the species links to see (and edit) details such as growth and mortality rates and 
					customized tree architectural data</p>
					
					<ul>
				    	<xsl:for-each select = "ecosim/species/name">
				    		<li><a href = "speciesTable?value={@value}"> <xsl:value-of select="@value"/> </a></li>
				            <!-- Right now I have made the links to google, because I was not sure on how we are going to the page where the tables will be displayed -->
				        </xsl:for-each>
    				</ul>
					
					<button type="submit">Continue</button>
				
				</form>
				
				
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>