package net.unixcode.rts.parser.compiler.antlr.stf;

import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTF2XMLSettingsProvider;
import net.unixcode.rts.parser.api.compiler.xml.IXMLSettings;
import net.unixcode.rts.parser.api.compiler.xml.XMLType;
import net.unixcode.rts.parser.compiler.xml.settings.CabinXMLSettings;
import net.unixcode.rts.parser.compiler.xml.settings.SoundXMLSettings;
import net.unixcode.rts.parser.compiler.xml.settings.StateXMLSettings;
import net.unixcode.rts.parser.compiler.xml.settings.UnknownXMLSettings;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;


@Component
public class STF2XMLSettingsProvider implements ISTF2XMLSettingsProvider {
  @Override
  public IXMLSettings apply(@NotNull XMLType type) {
    return switch (type) {
      case CABIN -> cabinSettings();
      case STATE -> stateSettings();
      case SOUND -> soundSettings();
      default -> unknownSettings();
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

  @Lookup
  protected UnknownXMLSettings unknownSettings() {
    return null;
  }

  @Lookup
  protected SoundXMLSettings soundSettings() {
    return null;
  }
}
