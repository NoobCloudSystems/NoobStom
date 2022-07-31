package xyz.luccboy.noobstom;

import net.minestom.server.extras.optifine.OptifineSupport;
import xyz.luccboy.noobstom.config.ConfigManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.extras.velocity.VelocityProxy;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.Block;

@Getter
public class NoobStom {

    @Getter
    private static NoobStom instance;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final MinecraftServer minecraftServer;
    private final ConfigManager configManager;

    public NoobStom() {
        instance = this;
        this.configManager = new ConfigManager();

        // These properties are passed by NoobCloud when starting the server
        final String address = System.getProperty("address");
        final int port = Integer.parseInt(System.getProperty("port"));

        this.minecraftServer = MinecraftServer.init();
        MinecraftServer.setBrandName("NoobStom");
        OptifineSupport.enable();
        enableProxySupport();

        this.minecraftServer.start(address, port);
        handleJoin();
    }

    private void enableProxySupport() {
        if (this.configManager.getConfig().isVelocityEnabled()) {
            final String velocitySecret = this.configManager.getConfig().getVelocitySecret();
            VelocityProxy.enable(velocitySecret);
            MinecraftServer.LOGGER.info("Enabled Velocity support with the following secret: " + velocitySecret);
        } else {
            MojangAuth.init();
            MinecraftServer.LOGGER.info("Enabled Mojang auth because Velocity support is disabled");
        }
    }

    // Modify this method to suit your needs
    private void handleJoin() {
        final InstanceContainer instanceContainer = MinecraftServer.getInstanceManager().createInstanceContainer();
        instanceContainer.setGenerator(unit -> {
            unit.modifier().fillHeight(0, 10, Block.GRASS_BLOCK);
        });
        MinecraftServer.getGlobalEventHandler().addListener(PlayerLoginEvent.class, event -> {
            event.setSpawningInstance(instanceContainer);
            event.getPlayer().setRespawnPoint(new Pos(0, 42, 0));
        });
    }

}
