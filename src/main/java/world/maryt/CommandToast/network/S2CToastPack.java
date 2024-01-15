package world.maryt.CommandToast.network;

import world.maryt.CommandToast.command.ToastType;
import world.maryt.CommandToast.toast.ServerToast;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class S2CToastPack implements IMessage {
    public boolean isCorrect=false;
    public ServerToast toastServer;
    public S2CToastPack(){}
    public S2CToastPack(ServerToast serverToast){
        toastServer=serverToast;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        try{
            NBTTagCompound compound= ByteBufUtils.readTag(buf);
            ToastType.ServerToastFactory factory=ToastType.REGISTRY.get(compound.getString("type"));
            ServerToast serverToast=factory.createToast();
            serverToast.deserializeNBT(compound.getCompoundTag("toast"));
            toastServer=serverToast;
            isCorrect=true;
        } catch (Exception ignored) {}
    }

    @Override
    public void toBytes(ByteBuf buf) {
        NBTTagCompound compound=new NBTTagCompound();
        compound.setString("type",toastServer.name);
        compound.setTag("toast",toastServer.serializeNBT());
        ByteBufUtils.writeTag(buf,compound);
    }
    public static class Handler implements IMessageHandler<S2CToastPack,IMessage>{

        @Override
        public IMessage onMessage(S2CToastPack message, MessageContext ctx) {
            if (message.isCorrect){
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    IToast toast=message.toastServer.get();
                    if (toast!=null){
                        Minecraft.getMinecraft().getToastGui().add(toast);
                    }
                });
            }
            return null;
        }
    }
}
