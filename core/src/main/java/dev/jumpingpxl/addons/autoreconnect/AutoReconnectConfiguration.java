package dev.jumpingpxl.addons.autoreconnect;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingRequires;
import net.labymod.api.configuration.settings.annotation.SettingSection;

@ConfigName("settings")
public class AutoReconnectConfiguration extends AddonConfig {

  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SettingSection("autoReconnect")
  @SwitchSetting
  private final ConfigProperty<Boolean> autoReconnect = new ConfigProperty<>(true);

  @SliderSetting(min = 3, max = 30)
  @SettingRequires("autoReconnect")
  private final ConfigProperty<Integer> delay = new ConfigProperty<>(5);

  @SettingSection("scenarios")
  @SwitchSetting
  private final ConfigProperty<Boolean> reconnectOnLoginDeny = new ConfigProperty<>(true);

  @SwitchSetting
  private final ConfigProperty<Boolean> reconnectOnKick = new ConfigProperty<>(true);

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<Boolean> autoReconnect() {
    return this.autoReconnect;
  }

  public ConfigProperty<Integer> delay() {
    return this.delay;
  }

  public ConfigProperty<Boolean> reconnectOnLoginDeny() {
    return this.reconnectOnLoginDeny;
  }

  public ConfigProperty<Boolean> reconnectOnKick() {
    return this.reconnectOnKick;
  }
}
