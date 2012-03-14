<?xml version="1.0" encoding="utf-8"?><!-- DWXMLSource="ecoSpecies.xml" -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">

<html>

<head>
<title>Species</title>
</head>

<body>

<!-- I know we were going to pass in the name of the species here somehow, based on which link is clicked if we get that name, we can just display that on the top and not all the species. But for right now its displaying all the tables in one page for all the species -->

<xsl:for-each select = "ecosim/species/name[../@target=@value]">
    <h1><xsl:value-of select="@value"/></h1>
    
    <h2>Mortality Information</h2>
    <table border="1">
        <tr bgcolor="#9acd32">
          <th>Type</th>
          <th>Stratum</th>
          <th>Percent</th>
        </tr>
        <xsl:for-each select="m_map/m_entry">
        <tr>
          <td><xsl:value-of select="m_key/type"/></td>
          <td><xsl:value-of select="m_key/stratum"/></td>
          <td><xsl:value-of select="m_calc/P"/></td>
        </tr>
   		</xsl:for-each>
    </table>
    
    <h2>Growth Information</h2>
    <table border="1">
        <tr bgcolor="#9acd32">
          <th>Type</th>
          <th>Stratum</th>
          <th>Class</th>
        </tr>
        <xsl:for-each select="g_map/g_entry">
        <tr>
          <td><xsl:value-of select="g_key/type"/></td>
          <td><xsl:value-of select="g_key/stratum"/></td>
          <td><xsl:value-of select="g_calc/percent"/></td>
        </tr>
   		</xsl:for-each>
    </table>
    
    <h2>Architectural Information</h2>
    <table border="1">
        <tr bgcolor="#9acd32">
          <th>Type</th>
          <th>Stratum</th>
          <th>Input Property</th>
          <th>Result Property</th>
          <th>x0</th>
          <th>x1</th>
        </tr>
        <xsl:for-each select="a_map/a_entry">
        <tr>
          <td><xsl:value-of select="a_key/type"/></td>
          <td><xsl:value-of select="a_key/stratum"/></td>
          <td><xsl:value-of select="a_key/inputProperty"/></td>
          <td><xsl:value-of select="a_key/resultProperty"/></td>
          <td><xsl:value-of select="a_function/x0"/></td>
          <td><xsl:value-of select="a_function/x1"/></td>
        </tr>
   		</xsl:for-each>
    </table>
    
</xsl:for-each>


</body>
</html>

</xsl:template>
</xsl:stylesheet>