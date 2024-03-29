<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema">

  <xsl:variable name='newline'>
    <xsl:text>&#xa;</xsl:text>
  </xsl:variable>

  <xsl:template name="lights">
    <xsl:param name="lights"/>
    <xsl:for-each select="$lights">
      /**
      #### Источник света
      ### <xsl:call-template name="dev_name">
      <xsl:with-param name="name" select="Name/string"/>
    </xsl:call-template>
      **/
      <xsl:value-of select="Name" />
      <xsl:text>_</xsl:text>
      <xsl:value-of select="position() - 1" />
      <xsl:text> = </xsl:text>
      <xsl:value-of select="position() - 1" />
      <xsl:text>,&#xa;</xsl:text>
    </xsl:for-each>
  </xsl:template>

  <xsl:template name="anims">
    <xsl:param name="anims"/>
    <xsl:for-each select="$anims">
      /**
      #### Анимация
      ### <xsl:call-template name="dev_name">
      <xsl:with-param name="name" select="Name/string"/>
    </xsl:call-template>
      **/
      <xsl:value-of select="Name" />
      <xsl:text>_</xsl:text>
      <xsl:value-of select="position() - 1" />
      <xsl:text> = </xsl:text>
      <xsl:value-of select="position() - 1" />
      <xsl:text>,&#xa;</xsl:text>
    </xsl:for-each>
  </xsl:template>

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
      #### <xsl:value-of select="if ($switch/Hint/string) then $switch/Hint/string else 'UNNAMED'"/>
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
        #### Values:
        `0` - OFF
        `1` - ON</xsl:when>
      <xsl:when test="DisplayType/word = $disp_arrow">
        #### Values:
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

</xsl:stylesheet>
