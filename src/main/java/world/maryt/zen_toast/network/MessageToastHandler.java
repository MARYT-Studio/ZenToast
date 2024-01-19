package world.maryt.zen_toast.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import world.maryt.zen_toast.ZenToast;
import world.maryt.zen_toast.toast.SimpleToast;

public class MessageToastHandler implements IMessageHandler<MessageToast, IMessage> {
    @SideOnly(Side.CLIENT)
    @Override
    public IMessage onMessage(MessageToast message, MessageContext ctx) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            IToast toast = new SimpleToast(message.titleJson, message.textJson, message.iconStack);
            ZenToast.LOGGER.debug("about to toast");
            Minecraft.getMinecraft().getToastGui().add(toast);
        });
        return null;
    }
}