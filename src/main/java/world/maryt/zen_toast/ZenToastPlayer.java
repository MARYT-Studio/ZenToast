package world.maryt.zen_toast;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.data.IData;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.player.IPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;
import world.maryt.zen_toast.network.MessageToast;
import world.maryt.zen_toast.network.NetworkManager;
import world.maryt.zen_toast.util.Data2JsonUtil;

@ZenRegister
@ZenExpansion("crafttweaker.player.IPlayer")
@SuppressWarnings("unused")
public class ZenToastPlayer {
    @ZenMethod
    public static void sendToast(IPlayer player, IData titleJson, IData textJson, IItemStack icon) {
        EntityPlayer playerMC = CraftTweakerMC.getPlayer(player);
        if (playerMC.world.isRemote) {
            CraftTweakerAPI.logError("player's world must not be remote");
            return;
        }
        EntityPlayerMP playerMP = (EntityPlayerMP)(playerMC);
        NetworkManager.INSTANCE.sendTo(new MessageToast(Data2JsonUtil.data2Json(titleJson), Data2JsonUtil.data2Json(textJson), CraftTweakerMC.getItemStack(icon)), playerMP);
    }
    @ZenMethod
    public static void sendToast(IPlayer player, String titleJson, String textJson, IItemStack icon) {
        EntityPlayer playerMC = CraftTweakerMC.getPlayer(player);
        if (playerMC.world.isRemote) {
            CraftTweakerAPI.logError("player's world must not be remote");
            return;
        }
        EntityPlayerMP playerMP = (EntityPlayerMP)(playerMC);
        NetworkManager.INSTANCE.sendTo(new MessageToast(titleJson, textJson, CraftTweakerMC.getItemStack(icon)), playerMP);
    }
}
