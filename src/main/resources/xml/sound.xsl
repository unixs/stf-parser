<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xpath-default-namespace="http://rts.unixcode.net/xml/sound/1.0.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema">


  <xsl:output method="text" encoding="UTF-8"/>
  <xsl:strip-space elements="*"/>

  <xsl:param name="isCabin" select="if (/sound//Activation/CabCam) then true() else false()" />


  <xsl:template name="siblings">
    <xsl:param name="me"/>
    <xsl:for-each select="$me/ancestor::Triggers/Discrete_Trigger">
      <xsl:text>&#xa;</xsl:text>
      <xsl:value-of select="if (./number/text() eq $me/number/text()) then '        ME: `' else '       `'"/>
      <xsl:value-of select="./number/text()"/>
      <xsl:text>` - `</xsl:text>
      <xsl:value-of select="./number/following-sibling::*/name()" />
      <xsl:text>`</xsl:text>
    </xsl:for-each>
  </xsl:template>

  <xsl:template match="//Discrete_Trigger">
    /**
      #### <xsl:value-of select="if (./ancestor::Stream/comment) then ./ancestor::Stream/comment/text() else 'UNCOMMENTED STREAM'"/>
      ### Триггер
      Type: `<xsl:value-of select="./number/following-sibling::*/name()" />`
      File: `<xsl:value-of select=".//File/string/text()" />`
      Triggers group:<xsl:call-template name="siblings">
 <xsl:with-param name="me" select="."/>
</xsl:call-template>
     */
    trg_<xsl:value-of select="./number[1]" /> = <xsl:value-of select="./number[1]" />,
  </xsl:template>

  <xsl:template match="/sound">
#pragma once

/**
 # Звуки
 */
namespace sound {
  /**
   ## Звуки <xsl:value-of select="if ($isCabin) then 'Кабины' else 'Внешние'"/>
   */
  enum class <xsl:value-of select="if ($isCabin) then 'cab' else 'ext'"/> : unsigned short {
    <xsl:apply-templates />
  };
}
  </xsl:template>


  <xsl:template match="text()"/>


</xsl:stylesheet>
