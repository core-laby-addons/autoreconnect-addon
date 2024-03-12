package dev.jumpingpxl.addons.autoreconnect;

import dev.jumpingpxl.addons.autoreconnect.activity.DisconnectedOverlay;
import dev.jumpingpxl.addons.autoreconnect.listener.ServerKickListener;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.gui.screen.NamedScreen;
import net.labymod.api.client.network.server.ConnectableServerData;
import net.labymod.api.client.network.server.ServerData;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class AutoReconnect extends LabyAddon<AutoReconnectConfiguration> {

  private ConnectableServerData serverData;
  private boolean autoReconnect;

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.registerListener(new ServerKickListener(this));

    // unregister laby overlay
    this.labyAPI().activityOverlayRegistry().unregister(NamedScreen.DISCONNECTED.getId());
    this.labyAPI().activityOverlayRegistry().register(
        NamedScreen.DISCONNECTED,
        DisconnectedOverlay.class,
        parentScreen -> new DisconnectedOverlay(parentScreen, this)
    );
  }

  @Override
  protected Class<AutoReconnectConfiguration> configurationClass() {
    return AutoReconnectConfiguration.class;
  }

  public void resetServerData() {
    this.serverData = null;
  }

  public void updateServerData(ServerData serverData, boolean autoReconnect) {
    this.autoReconnect = autoReconnect;
    if (serverData == null) {
      this.serverData = null;
      return;
    }

    if (!(serverData instanceof ConnectableServerData)) {
      this.serverData = ConnectableServerData.from(serverData);
      return;
    }

    this.serverData = (ConnectableServerData) serverData;
  }

  public boolean reconnectToLastServer() {
    if (this.serverData == null) {
      return false;
    }

    this.serverData.connect();
    this.serverData = null;
    return true;
  }

  public boolean canReconnect() {
    return this.serverData != null;
  }

  public boolean isAutoReconnect() {
    return this.autoReconnect;
  }
}
