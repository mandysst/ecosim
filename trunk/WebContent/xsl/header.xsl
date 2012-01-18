<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<xsl:template name="header">
		<xsl:param name="title"></xsl:param>
		<div id="heading">
			EcoSim<xsl:value-of select="$title"/>
		</div>
	</xsl:template>
</xsl:stylesheet>