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
<title>Tree Details</title>

</head>

<body>

	<form action="treeDetail" name="treeDetailsForm" method="POST">
    
    	<input type="hidden" name="selectedRun" value="ecosim/treeList/@selectedRun" />
        <input type="hidden" name="targetId" value="ecosim/SpeciesInfo/@targetId" />
        
        <xsl:variable name="run" select="/ecosim/treeList/@selectedRun" />
        <xsl:variable name="id" select="/ecosim/treeList/@targetId" />
    
        <h2> Tree Details </h2>
        <p> Select Run:
        <select name = "selectedRun" onChange="treeDetailsForm.submit();">
        
            <xsl:for-each select = "ecosim/treeList/tree[last()]/run/@value" >
                <xsl:choose>
				  <xsl:when test = ". = $run">
					<option value = "{.}" selected = "true"> <xsl:value-of select="."/> </option>
				  </xsl:when>
				  <xsl:otherwise>
					<option value = "{.}"> <xsl:value-of select="."/> </option>
				  </xsl:otherwise>
				</xsl:choose>
            </xsl:for-each>
        </select>
        
        <!-- whenever the run changes, the form will submit itself and the url will have the selectedRun value
        it will be selectedRun = x, x will be the selected run value
        once the form submits itself, the xml should update itself so that the selectedRun 
        attribute in the tree element is updated
        -->
        
        <p>Details for tree with ID = <xsl:value-of select="ecosim/treeList/@targetId"/></p>
        
        </p>
        
        <table border="1">
            <tr bgcolor="#FFFF66">
            <th> Year </th>
            <th> Trunk Height </th>
            <th> Trunk Diameter </th>
            <th> Health </th>
            <th> Type </th>
            <th> Stratum </th>
            </tr>
    
            <xsl:for-each select = "ecosim/treeList/tree[@id = $id]/run[@value = $run]/year">
                <tr>
                    <td><xsl:value-of select = "@value"/></td>
                    <td><xsl:value-of select = "th"/></td>
                    <td><xsl:value-of select = "td"/></td>
                    <td><xsl:value-of select = "health"/></td>
                    <td><xsl:value-of select = "type"/></td>
                    <td><xsl:value-of select = "stratum"/></td>
                </tr>
            </xsl:for-each>
            
        </table>
    
        <br/> <br />
        <a href = "treePage"> Back </a>
	</form>
</body>
</html>

</xsl:template>
</xsl:stylesheet>