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
					<xsl:with-param name="title">: Start Page</xsl:with-param>
				</xsl:call-template>
				
				
				
				<form id="startForm" action="initialize" name="startForm" method="POST" style="padding:1em;">
					<p>On this page the user needs to enter the number years to simulate, the number of runs they plan on using
					and the plot layout information (plot size, number of plots (grid)</p>
					Number of Years: <input type="text" name="numYears" value="10" /><br />
					Number of Simulations: <input type="text" name="numSim" value="1" /><br />
					Number of X Plots: <input type="text" name="numX" value="1" /><br />
					Number of Y Plots: <input type="text" name="numY" value="1" /><br />
					Size of X: <input type="text" name="sizeX" value="10" /><br />
					Size of Y: <input type="text" name="sizeY" value="10" /><br />
					<button type="submit">Start</button>
				
				</form>
				
				
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>