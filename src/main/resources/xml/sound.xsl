<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xpath-default-namespace="http://rts.unixcode.net/xml/sound/1.0.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema">


  <xsl:output method="text" encoding="UTF-8"/>
  <xsl:strip-space elements="*"/>

  <xsl:param name="default_state_class" select="'State'"/>

  <xsl:template name="vars">
    <xsl:param name="vars"/>
    <xsl:for-each select="$vars">
      <xsl:variable name="ident" select="./ident/@name"/>
      <xsl:variable name="type" select="./def/@type"/>
      <xsl:variable name="brief" select="./brief"/>

      /**
      * ### get: <xsl:value-of select="$brief"/>
      */
      <xsl:text>inline </xsl:text><xsl:value-of select="$type"/>
      <xsl:text>&#xa;    </xsl:text>
      <xsl:text>get_</xsl:text>
      <xsl:value-of select="$ident"/>
      <xsl:text>() {</xsl:text>
      return (<xsl:value-of select="$type"/>) <xsl:text>var[varOffset + </xsl:text>
      <xsl:value-of select="position() - 1" />
      <xsl:text>];</xsl:text>
      <xsl:text>&#xa;    </xsl:text>
      <xsl:text>}</xsl:text>
      <xsl:text>&#xa;    </xsl:text>
      /**
      * ### set: <xsl:value-of select="$brief"/>
      */
      <xsl:text>inline void</xsl:text>
      <xsl:text>&#xa;    </xsl:text>
      <xsl:text>set_</xsl:text><xsl:value-of select="$ident"/>(<xsl:value-of select="$type"/> val) {
      <xsl:text>var[varOffset + </xsl:text>
      <xsl:value-of select="position() - 1" />
      <xsl:text>] = val;</xsl:text>
      }
    </xsl:for-each>
  </xsl:template>


  <xsl:template match="//vars">
    <xsl:variable name="klass" select="if (//vars/@klass) then //vars/@klass else $default_state_class"/>
    /**
    * ## Интерфейс состояния кабины
    * Через методы этого класса получаем доступ к `eng->var`
    */
    class <xsl:value-of select="$klass" /> {
    private:
    const size_t baseOffset = <xsl:value-of select="//vars/@baseOffset" />;
    const size_t pointerOffset = <xsl:value-of select="//vars/@pointerOffset" />;
    const size_t innerOffset = <xsl:value-of select="//vars/offset" />;
    const size_t varOffset = baseOffset + pointerOffset + innerOffset;
    float *var = nullptr;

    public:
    /**
    * ### Суммарный размер стейта с учётом всех смещений и кол-ва ячеек
    */
    const size_t size = varOffset + <xsl:value-of select="count(//var)" />;

    <xsl:value-of select="$klass" />() {
    }

    /**
    * ### Устанавливает указатель на оригинальный массив стэйта
    */
    inline void
    setVar(float *engVar) {
    var = engVar + varOffset;
    }

    // Определение доступа к элементам состояния
    <xsl:call-template name="vars">
      <xsl:with-param name="vars" select="./var"/>
    </xsl:call-template>
    };
  </xsl:template>

  <xsl:template match="//timers">
    /**
    * ## Таймеры
    */
    namespace timers {
    <xsl:apply-templates />
    };
  </xsl:template>

  <xsl:template match="/state">
    #pragma once
    <xsl:text>#include &lt;cstddef&gt;</xsl:text>

    using namespace std;

    /**
    * # Кабина
    */
    namespace cabin {
    <xsl:apply-templates />
    }
  </xsl:template>


  <xsl:template match="text()"/>


</xsl:stylesheet>
