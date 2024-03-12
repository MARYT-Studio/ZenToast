package world.maryt.zen_toast;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.player.IPlayer;
import crafttweaker.api.text.ITextComponent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;
import world.maryt.zen_toast.network.MessageToast;
import world.maryt.zen_toast.network.NetworkManager;

@ZenRegister
@ZenExpansion("crafttweaker.player.IPlayer")
@SuppressWarnings("unused")
public class ZenToastPlayer {
    @ZenMethod
    public static void sendToast(IPlayer player, String title, String titleArgs, String text, String textArgs, IItemStack icon) {
        EntityPlayer playerMC = CraftTweakerMC.getPlayer(player);
        if (playerMC.world.isRemote) {
            CraftTweakerAPI.logError("player's world must not be remote");
            return;
        }
        EntityPlayerMP playerMP = (EntityPlayerMP)(playerMC);
        NetworkManager.INSTANCE.sendTo(new MessageToast(title, titleArgs, text, textArgs, CraftTweakerMC.getItemStack(icon)), playerMP);
        ZenToast.LOGGER.info("zenmethod has run.");
    }
}
