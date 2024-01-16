package world.maryt.CommandToast;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.data.IData;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.player.IPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;
import world.maryt.CommandToast.network.MessageToast;
import world.maryt.CommandToast.network.NetworkManager;

@ZenRegister
@ZenExpansion("crafttweaker.player.IPlayer")
@SuppressWarnings("unused")
public class ZenToastPlayer {
    @ZenMethod
    public static void sendToast(IPlayer player, IData titleJson, IData textJson, IItemStack icon) {
        EntityPlayerMP playerMP = (EntityPlayerMP)(CraftTweakerMC.getPlayer(player));
        NetworkManager.INSTANCE.sendTo(new MessageToast(titleJson, textJson, CraftTweakerMC.getItemStack(icon)), playerMP);
    }
    @ZenMethod
    public static void sendToast(IPlayer player, String titleJson, String textJson, IItemStack icon) {
        EntityPlayerMP playerMP = (EntityPlayerMP)(CraftTweakerMC.getPlayer(player));
        NetworkManager.INSTANCE.sendTo(new MessageToast(titleJson, textJson, CraftTweakerMC.getItemStack(icon)), playerMP);
    }
}
