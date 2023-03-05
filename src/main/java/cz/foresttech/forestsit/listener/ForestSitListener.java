package cz.foresttech.forestsit.listener;

import cz.foresttech.forestsit.ForestSit;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.spigotmc.event.entity.EntityDismountEvent;

public class ForestSitListener implements Listener {

    private ForestSit plugin;

    public ForestSitListener() {
        this.plugin = ForestSit.getInstance();
    }

    @EventHandler
    public void onPlayerRightClickEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        // Is enabled?
        if (!plugin.getForestSitManager().isEventStairsSit()) {
            return;
        }

        if (player.getInventory().getItemInMainHand().getType() != Material.AIR){
            return;
        }

        // Type of block
        Material blockType = event.getClickedBlock().getBlockData().getMaterial();

        // Config list, that contains type of blocks (stairs)
        if (!plugin.getForestSitManager().getMaterials().contains(blockType.toString())) {
            return;
        }

        // It checks if it is a staircase
        if (!blockType.toString().contains("STAIRS")) {
            Bukkit.getLogger().warning(" ");
            Bukkit.getLogger().warning(" Materials list cant contains: " + blockType.toString());
            Bukkit.getLogger().warning(" ");
            return;
        }

        Stairs stairs = (Stairs) event.getClickedBlock().getBlockData();

        // Control the place of that stairs
        if (stairs.getHalf().equals(Bisected.Half.BOTTOM)) {
            plugin.getForestSitController().sitForEvent(player, event.getClickedBlock().getLocation());
        }

    }

    @EventHandler
    public void onPlayerStatusChange(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (!plugin.getForestSitManager().getArmorStandHashMap().containsKey(player.getName().toLowerCase())) {
            return;
        }
        plugin.getForestSitManager().removeSeat(player);

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (!plugin.getForestSitManager().getArmorStandHashMap().containsKey(player.getName().toLowerCase())) {
            return;
        }
        plugin.getForestSitManager().removeSeat(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (!plugin.getForestSitManager().getArmorStandHashMap().containsKey(player.getName().toLowerCase())) {
            return;
        }
        plugin.getForestSitManager().removeSeat(player);
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if (!plugin.getForestSitManager().getArmorStandHashMap().containsKey(player.getName().toLowerCase())) {
            return;
        }
        plugin.getForestSitManager().removeSeat(player);

    }

    @EventHandler
    public void onPlayer(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!plugin.getForestSitManager().getArmorStandHashMap().containsKey(player.getName().toLowerCase())) {
            return;
        }
        plugin.getForestSitManager().removeSeat(player);
    }

    @EventHandler
    public void onPlayerDismount(EntityDismountEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();

        if (!(event.getDismounted() instanceof ArmorStand)) {
            return;
        }
        if (plugin.getForestSitManager().getArmorStandHashMap().containsKey(player.getName())) {
            plugin.getForestSitManager().getArmorStandHashMap().remove(player.getName());
        }
    }

}
