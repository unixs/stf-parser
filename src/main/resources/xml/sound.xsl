<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xpath-default-namespace="http://rts.unixcode.net/xml/sound/1.0.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema">


  <xsl:output method="text" encoding="UTF-8"/>
  <xsl:strip-space elements="*"/>


  <xsl:template match="text()"/>

  <xsl:template name="siblings">
    <xsl:param name="me"/>
    <xsl:text>&#xa;</xsl:text>
    <xsl:text>            | is ME? | ID | TYPE | COMMENT |&#xa;</xsl:text>
    <xsl:text>            | :----: | :---: | :-------: | :-----: |</xsl:text>
    <xsl:for-each select="$me/ancestor::Triggers/Discrete_Trigger">
      <xsl:text>&#xa;</xsl:text>
      <xsl:value-of select="if (./number/text() eq $me/number/text()) then '            |ME|`' else '            ||`'"/>
      <xsl:value-of select="./number/text()"/>
      <xsl:text>`|`</xsl:text>
      <xsl:value-of select="./number/following-sibling::*[1]/name()" />
      <xsl:text>`|</xsl:text>
      <xsl:value-of select="if (./comment) then ./comment/text() else 'UNCOMMENTED TRIGGER'"/>
    </xsl:for-each>
  </xsl:template>

  <xsl:template match="//Discrete_Trigger">
    <xsl:variable name="id" select="./number/text()"/>
    <xsl:variable name="pair" select="./ancestor::ScalabiltyGroup/DiscreteTriggerPairs/Pair"/>
        /**
          ##### <xsl:value-of select="if (./ancestor::ScalabiltyGroup/comment) then ./ancestor::ScalabiltyGroup/comment/text() else 'UNCOMMENTED GROUP'"/>
          #### <xsl:value-of select="if (./ancestor::Stream/comment) then ./ancestor::Stream/comment/text() else 'UNCOMMENTED STREAM'"/>
          ### <xsl:value-of select="if (./comment) then ./comment/text() else 'UNCOMMENTED TRIGGER'"/>
          ID: `<xsl:value-of select="./number/text()" />`
          Type: `<xsl:value-of select="./number/following-sibling::*/name()" />`
          File: `<xsl:value-of select=".//File/string/text()" />`
          Triggers group:<xsl:call-template name="siblings"><xsl:with-param name="me" select="."/></xsl:call-template>
          <xsl:if test="$pair//number[2][text() eq $id]">
          <xsl:variable name="me" select="$pair//number[2][text() eq $id]"/>
          Paired as: `<xsl:value-of select="if ($me/parent::*/name() eq 'On') then 'On' else 'Off'"/>`
          </xsl:if>
         */
        trg_<xsl:value-of select="./number[1]" /> = <xsl:value-of select="./number[1]" />,
  </xsl:template>

  <xsl:template match="//ScalabiltyGroup">
    <xsl:param name="isCabin" select="if (./Activation/CabCam) then true() else false()" />
    /**
      ## Звуки <xsl:value-of select="if ($isCabin) then 'Кабины' else 'Внешние'"/>
     */
    namespace <xsl:value-of select="if ($isCabin) then 'cab' else 'ext'"/> {

      /**
        ## Группа <xsl:value-of select="position()"/>
        ### <xsl:value-of select="if (./comment) then ./comment/text() else 'UNCOMMENTED GROUP'"/>
       */
      enum class gr<xsl:value-of select="position()"/> : unsigned short {
      <xsl:apply-templates />
      };
    }
  </xsl:template>

  <xsl:template match="/sound">// DO NOT EDIT!!! GENERATED FILE - <xsl:value-of  select="current-dateTime()"/>
#pragma once

/**
 # Звуки
 */
namespace sound {
    <xsl:apply-templates/>
}
  </xsl:template>

</xsl:stylesheet>
