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
					<xsl:with-param name="title">: Simulation in Progress</xsl:with-param>
				</xsl:call-template>
				
				
				
				<p>This page will use AJAX to report on the progress of the simulation job.  Once the job
				is completed, the user will be redirected to the results-summary page.  For now, use the link below...</p>
				
				<a href="results-summary">Go to the results summary page (temporary link)</a>
				
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>