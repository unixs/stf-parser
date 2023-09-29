<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema">


  <xsl:output method="text" encoding="UTF-8"/>
  <xsl:strip-space elements="*"/>



  <xsl:variable name='newline'>
    <xsl:text>&#xa;</xsl:text>
  </xsl:variable>

  <xsl:variable name="cab1_max_id" as="xs:integer" select="99"/>
  <xsl:variable name="cab2_max_id" as="xs:integer" select="199"/>
  <xsl:variable name="cab2_disp_max_id" as="xs:integer" select="161"/>
  <xsl:variable name="unnamed" as="xs:string" select="'UNNAMED'"/>
  <xsl:variable name="disp_lamp" as="xs:string" select="'LAMP'"/>
  <xsl:variable name="disp_arrow" as="xs:string" select="'ARROW'"/>



  <xsl:template name="switch">
    <xsl:param name="switch"/>
    <xsl:param name="location"/>
      /**
        ### <xsl:call-template name="dev_name"><xsl:with-param name="name" select="$switch/Hint/string"/></xsl:call-template>
         **<xsl:value-of select="$location" />**
        Type: <xsl:call-template name="switch_type_hint"/>
        Name: <xsl:value-of select="$switch/Name/word/text()" />
        Flags: <xsl:value-of select="$switch/Flags/word" separator=", "/>
       **/
    <xsl:text>  </xsl:text>
    <xsl:call-template name="nameable">
      <xsl:with-param name="nameable" select="."/>
    </xsl:call-template>
  </xsl:template>

  <xsl:template name="switch_type_hint">
    <xsl:choose>
      <xsl:when test="Flags/word[contains(text(), 'NONFIXED')]">
        <xsl:text>Кнопка</xsl:text>
      </xsl:when>
      <xsl:when test="States/number/text() = 2">
        <xsl:text>Выключатель/тумблер</xsl:text>
      </xsl:when>
      <xsl:when test="States/number/text() &gt; 2">
        <xsl:text>Мультипереключатель</xsl:text>
      </xsl:when>
    </xsl:choose>
  </xsl:template>

  <xsl:template name="switches">
    <xsl:param name="switches"/>
    <xsl:param name="location"/>
    /**
      ## Переключатели.
      ###  <xsl:value-of select="$location" />
     **/
    enum class sw : unsigned short {
      <xsl:for-each select="$switches">
        <xsl:call-template name="switch">
          <xsl:with-param name="switch" select="."/>
          <xsl:with-param name="location" select="$location"/>
        </xsl:call-template>
      </xsl:for-each>
    };
  </xsl:template>

  <xsl:template name="dev_name">
    <xsl:param name="name" />
    <xsl:if test="string-length($name/text()) = 0">
      <xsl:value-of select="$unnamed"/>
    </xsl:if>
    <xsl:value-of select="$name/text()" />
  </xsl:template>

  <xsl:template name="switch_state_hint">
    <xsl:param name="sw" />
    <xsl:param name="st" />
    <xsl:if test="not($sw/StateHints/(string|word)[number($st) + 1])">
      <xsl:value-of select="$unnamed"/>
    </xsl:if>
    <xsl:value-of select="$sw/StateHints/(string|word)[number($st) + 1]"/>
  </xsl:template>

  <xsl:template name="switch_state">
    <xsl:param name="switch"/>
    <xsl:param name="location"/>
      /**
        #### Состояния переключателя
        ### <xsl:call-template name="dev_name">
              <xsl:with-param name="name" select="$switch/Hint/string"/>
            </xsl:call-template>
          **<xsl:value-of select="$location" />**
        Name: `<xsl:value-of select="$switch/Name/word/text()" />`
        Default: `<xsl:value-of select="$switch/States/number[2]" />`
        States:<xsl:for-each select="1 to $switch/States/number[1]">
          - <xsl:number value=". - 1" format="`1` - "/>
        <xsl:call-template name="switch_state_hint">
          <xsl:with-param name="st" select=". - 1"/>
          <xsl:with-param name="sw" select="$switch"/>
        </xsl:call-template>
      </xsl:for-each>
       **/
      enum class <xsl:value-of select="$switch/Name/word/text()"/> : unsigned short {
        /**
          #### <xsl:call-template name="dev_name">
                 <xsl:with-param name="name" select="$switch/Hint/string"/>
               </xsl:call-template>
          ### Исходное состояние
         **/
        DEFAULT = <xsl:value-of select="$switch/States/number[2]"/><xsl:text>,</xsl:text>
        <xsl:for-each select="1 to $switch/States/number[1]">&#xa;
        /**
          #### <xsl:value-of select="$switch/Hint/string"/>
          ### <xsl:call-template name="switch_state_hint">
                <xsl:with-param name="st" select=". - 1"/>
                <xsl:with-param name="sw" select="$switch"/>
              </xsl:call-template>
         **/
        ST_<xsl:number value="position() - 1"/>
          <xsl:text> = </xsl:text>
          <xsl:number value="position() - 1"/>
          <xsl:text>,</xsl:text>
        </xsl:for-each>
      };
  </xsl:template>

  <xsl:template name="states">
    <xsl:param name="location"/>
    <xsl:param name="switches"/>
    /**
      ## Состояния приборов
      ###  <xsl:value-of select="$location" />
     **/
    namespace st {
      <xsl:for-each select="$switches">
        <xsl:call-template name="switch_state">
          <xsl:with-param name="switch" select="."/>
          <xsl:with-param name="location" select="$location"/>
        </xsl:call-template>
      </xsl:for-each>
    };
  </xsl:template>


  <xsl:template name="nameable">
    <xsl:param name="nameable"/>
    <xsl:value-of select="$nameable/Name/word/text()" />
    <xsl:text>_</xsl:text>
    <xsl:value-of select="$nameable/ID/number/text()" />
    <xsl:text> = </xsl:text>
    <xsl:value-of select="$nameable/ID/number/text()" />
    <xsl:text>,&#xa;</xsl:text>
  </xsl:template>


  <xsl:template name="display_values">
    <xsl:choose>
      <xsl:when test="DisplayType/word = $disp_lamp">
        <xsl:text>#### Values:
          `0` - OFF
          `1` - ON</xsl:text>
      </xsl:when>
      <xsl:when test="DisplayType/word = $disp_arrow">
        <xsl:text>#### Values:</xsl:text>
          MIN: `<xsl:value-of select="ValuesRange/number[1]"/>`
          MAX: `<xsl:value-of select="ValuesRange/number[2]"/>`
          ZERO: `<xsl:value-of select="ValuesRange/number[3]"/>
            <xsl:text>`</xsl:text>
      </xsl:when>
    </xsl:choose>
  </xsl:template>

  <xsl:template name="display">
    <xsl:param name="display"/>
    <xsl:param name="location"/>
      /**
        ### <xsl:call-template name="dev_name">
           <xsl:with-param name="name" select="$display/Hint/string"/>
         </xsl:call-template>
        #### <xsl:value-of select="$location" />
        Type: <xsl:value-of select="$display/DisplayType/word/text()" />
        Name: `<xsl:value-of select="$display/Name/word/text()" />
        <xsl:text>`</xsl:text>
        <xsl:call-template name="display_values" />
       **/
    <xsl:text>  </xsl:text>
    <xsl:call-template name="nameable">
      <xsl:with-param name="nameable" select="."/>
    </xsl:call-template>
  </xsl:template>


  <xsl:template name="displays">
    <xsl:param name="displays"/>
    <xsl:param name="location"/>
    /**
      ## Указатели.
      ###  <xsl:value-of select="$location" />
     **/
    enum class disp : unsigned short {
      <xsl:for-each select="$displays">
        <xsl:call-template name="display">
          <xsl:with-param name="display" select="."/>
          <xsl:with-param name="location" select="$location"/>
        </xsl:call-template>
      </xsl:for-each>
    };
  </xsl:template>


  <xsl:template match="//ESD_Interior">
    <xsl:variable name="cab1_name" as="xs:string" select="'Кабина 1'"/>
    <xsl:variable name="cab2_name" as="xs:string" select="'Кабина 2'"/>
    <xsl:variable name="deck_name" as="xs:string" select="'Кузов'"/>
/**
  # Интерфейс 3D модели
 **/
namespace model {

  /**
    ## <xsl:value-of select="$cab1_name"/>
   **/
  namespace cab1 {
    <xsl:variable name="cab1_switches" select="Switches/Switch[ID/number/text() &lt;= $cab1_max_id]"/>
    <xsl:variable name="cab1_displays" select="Displays/Display[ID/number/text() &lt;= $cab1_max_id]"/>
    <xsl:call-template name="switches">
      <xsl:with-param name="switches" select="$cab1_switches"/>
      <xsl:with-param name="location" select="$cab1_name"/>
    </xsl:call-template>
    <xsl:call-template name="displays">
      <xsl:with-param name="displays" select="$cab1_displays"/>
      <xsl:with-param name="location" select="$cab1_name"/>
    </xsl:call-template>
    <xsl:call-template name="states">
      <xsl:with-param name="location" select="$cab1_name"/>
      <xsl:with-param name="switches" select="$cab1_switches"/>
    </xsl:call-template>
  };

  /**
    ## <xsl:value-of select="$cab2_name"/>
   **/
  namespace cab2 {
    <xsl:call-template name="switches">
      <xsl:with-param name="switches" select="Switches/Switch[ID/number/text() &gt; $cab1_max_id and ID/number/text() &lt;= $cab2_max_id]"/>
      <xsl:with-param name="location" select="$cab2_name"/>
    </xsl:call-template>
    <xsl:call-template name="displays">
      <xsl:with-param name="displays" select="Displays/Display[ID/number/text() &gt; $cab1_max_id and ID/number/text() &lt;= $cab2_disp_max_id]"/>
      <xsl:with-param name="location" select="$cab2_name"/>
    </xsl:call-template>
  };

  /**
    ## <xsl:value-of select="$deck_name"/>
   **/
  namespace deck {
    <xsl:call-template name="switches">
      <xsl:with-param name="switches" select="Switches/Switch[ID/number/text() &gt; $cab2_max_id]"/>
      <xsl:with-param name="location" select="$deck_name"/>
    </xsl:call-template>
    <xsl:call-template name="displays">
      <xsl:with-param name="displays" select="Displays/Display[ID/number/text() &gt; $cab2_disp_max_id]"/>
      <xsl:with-param name="location" select="$deck_name"/>
    </xsl:call-template>
  };
};&#xa;
  </xsl:template>


  <xsl:template match="/root">
    <xsl:apply-templates />
  </xsl:template>


  <xsl:template match="text()"/>

</xsl:stylesheet>
