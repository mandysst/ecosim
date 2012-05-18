<?xml version="1.0" encoding="utf-8"?><!-- DWXMLSource="newSpeciesList.xml" -->
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
<title>Species Detail</title>
</head>

<body>

	<form action="speciesDetail" name="speciesDetailsForm" method="POST">
    
    	<input type="hidden" name="selectedRun" value="ecosim/SpeciesInfo/@selectedRun" />
        <input type="hidden" name="targetSpecies" value="ecosim/SpeciesInfo/@targetSpecies" />
    
        <h2> Species Details - <xsl:value-of select = "ecosim/SpeciesInfo/@targetSpecies" /></h2>
        <p> Select Run:
        <select name = "selectedRun" onChange="speciesDetailsForm.submit();">
            <xsl:for-each select = "ecosim/SpeciesInfo/runs/run" >
				<xsl:choose>
				  <xsl:when test = ". = ../../@selectedRun">
					<option value = "{.}" selected = "true"> <xsl:value-of select="."/> </option>
				  </xsl:when>
				  <xsl:otherwise>
					<option value = "{.}"> <xsl:value-of select="."/> </option>
				  </xsl:otherwise>
				</xsl:choose>
            </xsl:for-each>
           	<option value = "-1"> All </option> 	<!-- this is if we want avg of all runs -->
            <!-- not sure if we need this on this page -->
        </select>
		</p>
        
        <table border="1">
            <tr bgcolor="#FFFF66">
    		<th> Year 0 </th>
        	<th> Percentage </th>
            </tr>
        
        	<xsl:for-each select="ecosim/SpeciesInfo/years/year" >
        		<tr>
            		<td> <xsl:value-of select = "." /></td>
            		<xsl:variable name="species" select="/ecosim/SpeciesInfo/@targetSpecies" />
                    <xsl:variable name="run" select="/ecosim/SpeciesInfo/@selectedRun" />
                    <xsl:variable name="year" select="." />
                	<td><xsl:value-of select = "//records/record[@species = $species and @run = $run and @year = $year]" /></td>
           		</tr>
        	</xsl:for-each>
        
    	</table>
    	<br/> <br />
    	<a href = "speciesYear"> Back </a>
    </form>

</body>
</html>

</xsl:template>
</xsl:stylesheet>