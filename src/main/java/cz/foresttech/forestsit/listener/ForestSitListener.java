package cz.foresttech.forestsit.listener;


import cz.foresttech.forestsit.ForestSit;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
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
    private ForestSit forestSit;

    public ForestSitListener() {
        this.forestSit = ForestSit.getInstance();
    }

    @EventHandler
    public void onPlayerRightClickEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        if (!forestSit.getForestSitManager().isEventStairsSit()) {
            return;
        }

        Material blockType = event.getClickedBlock().getBlockData().getMaterial();

        if (!forestSit.getForestSitManager().getMaterials().contains(blockType.toString())) {
            return;
        }

        if (!blockType.toString().contains("STAIRS")) {
            Bukkit.getLogger().warning(" ");
            Bukkit.getLogger().warning(" Materials list cant contains: " + blockType.toString());
            Bukkit.getLogger().warning(" ");
            return;
        }

        Stairs stairs = (Stairs) event.getClickedBlock().getBlockData();

        if (stairs.getHalf().equals(Bisected.Half.BOTTOM)) {
            forestSit.getForestSitController().sitForEvent(player, event.getClickedBlock().getLocation());
        }

    }


    @EventHandler
    public void onPlayerStatusChange(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (!forestSit.getForestSitManager().getArmorStandHashMap().containsKey(player.getName().toLowerCase())) {
            return;
        }
        forestSit.getForestSitManager().removeSeat(player);

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (!forestSit.getForestSitManager().getArmorStandHashMap().containsKey(player.getName().toLowerCase())) {
            return;
        }
        forestSit.getForestSitManager().removeSeat(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (!forestSit.getForestSitManager().getArmorStandHashMap().containsKey(player.getName().toLowerCase())) {
            return;
        }
        forestSit.getForestSitManager().removeSeat(player);
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if (!forestSit.getForestSitManager().getArmorStandHashMap().containsKey(player.getName().toLowerCase())) {
            return;
        }
        forestSit.getForestSitManager().removeSeat(player);

    }

    @EventHandler
    public void onPlayer(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!forestSit.getForestSitManager().getArmorStandHashMap().containsKey(player.getName().toLowerCase())) {
            return;
        }
        forestSit.getForestSitManager().removeSeat(player);
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
        if (forestSit.getForestSitManager().getArmorStandHashMap().containsKey(player.getName())) {
            forestSit.getForestSitManager().getArmorStandHashMap().remove(player.getName());
        }
    }

}
