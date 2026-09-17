<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
  xmlns:urn="urn:enterprise.soap.sforce.com"
  exclude-result-prefixes="soapenv urn">

  <xsl:output method="xml" indent="yes"/>

  <!-- Match root envelope and rename it -->
  <xsl:template match="soapenv:Envelope">
    <Envelope>
      <xsl:apply-templates select="soapenv:Header"/>
      <xsl:apply-templates select="soapenv:Body"/>
    </Envelope>
  </xsl:template>

  <!-- Rename Header -->
  <xsl:template match="soapenv:Header">
    <Header>
      <xsl:apply-templates select="*"/>
    </Header>
  </xsl:template>

  <!-- Rename Body -->
  <xsl:template match="soapenv:Body">
    <Body>
      <xsl:apply-templates select="*"/>
    </Body>
  </xsl:template>

  <!-- Copy everything else as-is, removing namespace prefixes -->
  <xsl:template match="*">
    <xsl:element name="{local-name()}">
      <xsl:apply-templates select="@* | node()"/>
    </xsl:element>
  </xsl:template>

  <!-- Copy attributes as-is (remove namespaces) -->
  <xsl:template match="@*">
    <xsl:attribute name="{local-name()}">
      <xsl:value-of select="."/>
    </xsl:attribute>
  </xsl:template>

  <!-- Copy text nodes -->
  <xsl:template match="text()">
    <xsl:value-of select="."/>
  </xsl:template>
</xsl:stylesheet>