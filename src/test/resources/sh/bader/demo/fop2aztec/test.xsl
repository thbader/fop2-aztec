<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                version="1.0">
    <xsl:output encoding="ISO-8859-1"/>

    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="only" page-height="297mm" page-width="210mm" margin="20mm">
                    <fo:region-body region-name="xsl-region-body"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="only">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                        <xsl:apply-templates select="data/value"/>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

    <xsl:template name="value">
        <fo:block>
            Value: <xsl:value-of select="."/>
        </fo:block>
        <fo:block margin-top="20mm">Datamatrix-Code</fo:block>
        <fo:block>
            <fo:instream-foreign-object >
                <j4lbarcode xmlns="http://java4less.com/j4lbarcode/fop" mode="inline">
                    <datamatrix>
                        <code>This is a Datamatrix</code>
                        <moduleSize>2</moduleSize>
                        <processTilde>false</processTilde>
                        <margin>30</margin>
                        <encoding>AUTO</encoding>
                        <format>C24X24</format>
                    </datamatrix>
                </j4lbarcode>
            </fo:instream-foreign-object>
        </fo:block>
        <fo:block margin-top="20mm">Aztec</fo:block>
        <fo:block>
            <fo:instream-foreign-object>
                <j4lbarcode xmlns="http://java4less.com/j4lbarcode/fop" mode="inline">
                    <aztec>
                        <CODE>123</CODE>
                        <TEXTFONT>NULL</TEXTFONT>
                        <BACKCOLOR>#ffffff</BACKCOLOR>
                        <CONFIGURATIONTYPE>0</CONFIGURATIONTYPE>
                        <MARGIN>0</MARGIN>
                        <ROTATE>0</ROTATE>
                        <CONFIGURATION>1</CONFIGURATION>
                        <PROCESSTILDE>false</PROCESSTILDE>
                        <ENCODING>Normal</ENCODING>
                        <BARCOLOR>#000000</BARCOLOR>
                        <ECLEVEL>23</ECLEVEL>
                        <MODULESIZE>2</MODULESIZE>
                    </aztec>
                </j4lbarcode>
            </fo:instream-foreign-object>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>