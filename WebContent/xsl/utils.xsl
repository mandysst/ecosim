<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" version="4.0" indent="yes"/>
	<xsl:template name="css_includes">
		<link rel="stylesheet/less" type="text/css" href="{$cssPath}ecosim.less" />
		<link rel="shortcut icon" href="{$imagePath}favicon.png" />
	</xsl:template>
	
	
	<xsl:template name="ie_meta">
		 <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	</xsl:template>
	
	<xsl:template name="footer">
		<div class="footer">
			Footer
		</div>
	</xsl:template>
	
	<xsl:template name="javascript_includes">
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="{$jsPath}less-1.2.0.min.js" type="text/javascript"></script>
	</xsl:template>
	
	
</xsl:stylesheet>
