<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xpath-default-namespace="http://rts.unixcode.net/xml/cabin/state/1.0.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema">


  <xsl:output method="text" encoding="UTF-8"/>
  <xsl:strip-space elements="*"/>

  <xsl:template name="ident">
    <xsl:param name="node"/>
    <xsl:variable name="ident" select="$node/*/text()"/>
    <xsl:if test="matches($ident, '^\d')">
      <xsl:text>_</xsl:text>
    </xsl:if>
    <xsl:value-of select="translate($ident, '- ', '__')"/>
  </xsl:template>

  <xsl:template name="lights">
    <xsl:param name="lights"/>
    <xsl:for-each select="$lights">
      /**
      #### Источник света<xsl:if test="./comment">
      #### <xsl:value-of select="./comment/text()" />
    </xsl:if>
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
      #### Анимация<xsl:if test="./comment">
      #### <xsl:value-of select="./comment/text()" />
    </xsl:if>
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
    /**
    ### <xsl:call-template name="dev_name"><xsl:with-param name="name" select="$switch/Hint/string"/></xsl:call-template>
    Type: <xsl:call-template name="switch_type_hint"/>
    Name: <xsl:value-of select="$switch/Name/word/text()" />
    Flags: <xsl:value-of select="$switch/Flags/word" separator=", "/>
    **/
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
    /**
    ## Переключатели.
    **/
    enum class sw : unsigned short {
    <xsl:for-each select="$switches">
      <xsl:call-template name="switch">
        <xsl:with-param name="switch" select="."/>
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
    /**
    #### Состояния переключателя
    ### <xsl:call-template name="dev_name">
    <xsl:with-param name="name" select="$switch/Hint/string"/>
  </xsl:call-template>
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
    enum class <xsl:call-template name="ident">
    <xsl:with-param name="node" select="$switch/Name"/>
  </xsl:call-template> : unsigned short {
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
    <xsl:param name="switches"/>
    /**
    ## Состояния приборов
    **/
    namespace st {
    <xsl:for-each select="$switches">
      <xsl:call-template name="switch_state">
        <xsl:with-param name="switch" select="."/>
      </xsl:call-template>
    </xsl:for-each>
    };
  </xsl:template>


  <xsl:template name="nameable">
    <xsl:param name="nameable"/>
    <xsl:call-template name="ident">
      <xsl:with-param name="node" select="$nameable/Name"/>
    </xsl:call-template>
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
    /**
    ### <xsl:call-template name="dev_name">
    <xsl:with-param name="name" select="$display/Hint/string"/>
  </xsl:call-template>
    Type: <xsl:value-of select="$display/DisplayType/word/text()" />
    Name: `<xsl:value-of select="$display/Name/word/text()" />
    <xsl:text>`</xsl:text>
    <xsl:call-template name="display_values" />
    **/
    <xsl:call-template name="nameable">
      <xsl:with-param name="nameable" select="."/>
    </xsl:call-template>
  </xsl:template>


  <xsl:template name="displays">
    <xsl:param name="displays"/>
    /**
    ## Указатели.
    **/
    enum class disp : unsigned short {
    <xsl:for-each select="$displays">
      <xsl:call-template name="display">
        <xsl:with-param name="display" select="."/>
      </xsl:call-template>
    </xsl:for-each>
    };
  </xsl:template>



  <xsl:variable name="unnamed" as="xs:string" select="'UNNAMED'"/>
  <xsl:variable name="disp_lamp" as="xs:string" select="'LAMP'"/>
  <xsl:variable name="disp_arrow" as="xs:string" select="'ARROW'"/>


  <xsl:template match="//ESD_Interior">
    /**
    # Интерфейс 3D модели
    **/
    namespace model {

    <xsl:call-template name="switches">
      <xsl:with-param name="switches" select="Switches/Switch"/>
    </xsl:call-template>
    <xsl:call-template name="displays">
      <xsl:with-param name="displays" select="Displays/Display"/>
    </xsl:call-template>
    <xsl:call-template name="states">
      <xsl:with-param name="switches" select="Switches/Switch"/>
    </xsl:call-template>



    /**
    ## Источники света
    **/
    enum class lights : unsigned short {
    <xsl:call-template name="lights">
      <xsl:with-param name="lights" select="LightSources/LightSource"/>
    </xsl:call-template>
    };

    /**
    ## Анимации
    **/
    enum class anims : unsigned short {
    <xsl:call-template name="anims">
      <xsl:with-param name="anims" select="Animations/AnimNode"/>
    </xsl:call-template>
    };
    };&#xa;
  </xsl:template>


  <xsl:template match="/cabin">
    <xsl:apply-templates />
  </xsl:template>


  <xsl:template match="text()"/>

</xsl:stylesheet>
