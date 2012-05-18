<?xml version="1.0" encoding="utf-8"?><!-- DWXMLSource="treeList.xml" -->
<!DOCTYPE xsl:stylesheet  [
	<!ENTITY nbsp   "&#160;">
	<!ENTITY copy   "&#169;">
	<!ENTITY reg    "&#174;">
	<!ENTITY trade  "&#8482;">
	<!ENTITY mdash  "&#8212;">
	<!ENTITY ldquo  "&#8220;">
	<!ENTITY rdquo  "&#8221;"> 
	<!ENTITY pound  "&#163;">
	<!ENTITY yen    "&#165;">
	<!ENTITY euro   "&#8364;">
]>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" encoding="utf-8" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
<xsl:template match="/">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>Tree Results</title>
</head>

<body>

	<form action="treeDetail" name="treeResultsForm" method="POST">
	
    <!-- I dont't think the following statement is required in this file since it does not have a drop down for run
    	
        <input type="hidden" name="targetId" value="ecosim/SpeciesInfo/@selectedRun" />
        
        If we do need it, then the abolve statement should work -->
    
    <h2> Tree Results </h2>
    <br/>
        
        <table border="1">
            <tr bgcolor="#FFFF66">
                <th> Tree ID </th>
                <th> Species </th>
                <th> Location (X, Y) </th>  
            </tr>
            <xsl:for-each select = "ecosim/treeList/tree">
                <tr>
                    <td><a href="treeDetail?id={@id}">
                        <!-- or either <a href = "treeDetail?targetId={@id}"> -->
                        <xsl:value-of select = "@id" /></a>
                    </td>
                    <td><xsl:value-of select = "species" /></td>
                    <td><xsl:value-of select = "locationX" />, 
                    <xsl:value-of select = "locationY" /></td>
                </tr>
            </xsl:for-each>
         </table>
         <br/> <br/>
         <a href="speciesYear">Species Data</a>
        
     </form>
    
</body>
</html>

</xsl:template>
</xsl:stylesheet>
