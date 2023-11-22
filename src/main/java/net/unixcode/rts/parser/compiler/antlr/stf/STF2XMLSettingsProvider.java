package net.unixcode.rts.parser.compiler.antlr.stf;

import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTF2XMLSettingsProvider;
import net.unixcode.rts.parser.api.compiler.xml.IXMLSettings;
import net.unixcode.rts.parser.api.compiler.xml.XMLType;
import net.unixcode.rts.parser.compiler.xml.CabinXMLSettings;
import net.unixcode.rts.parser.compiler.xml.StateXMLSettings;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class STF2XMLSettingsProvider implements ISTF2XMLSettingsProvider {
  @Override
  public IXMLSettings apply(@NotNull XMLType type) {
    return switch (type) {
      case CABIN -> cabinSettings();
      case STATE -> stateSettings();
      default -> throw new IllegalArgumentException(
        MessageFormat.format("Unprocessable XML TYPE. Settings not found. [.{0}]", type)
      );
    };
  }

  @Lookup
  protected CabinXMLSettings cabinSettings() {
    return null;
  }

  @Lookup
  protected StateXMLSettings stateSettings() {
    return null;
  }
}
