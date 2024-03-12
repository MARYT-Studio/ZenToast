package world.maryt.zen_toast.toast;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import crafttweaker.CraftTweakerAPI;
import net.minecraft.client.gui.toasts.GuiToast;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import world.maryt.zen_toast.ZenToast;

import java.util.List;

@SideOnly(Side.CLIENT)
public class SimpleToast implements IToast {
    public String title;
    public String titleArgs;
    public String text;
    public String textArgs;
    public ItemStack icon;
    public SimpleToast(String title, String titleArgs, String text, String textArgs, ItemStack icon) {
        this.title = title;
        this.titleArgs = titleArgs;
        this.text = text;
        this.textArgs = textArgs;
        this.icon = icon;
    }
    @Override
    @SuppressWarnings("NullableProblems")
    public Visibility draw(GuiToast toastGui, long delta) {
        if (title == null || text == null) {
            ZenToast.LOGGER.info("hidden.");
            return Visibility.HIDE;
        }

        String formattedTitle = titleArgs.isEmpty() ? new TextComponentTranslation(title).getFormattedText() : new TextComponentTranslation(title, titleArgs).getFormattedText();
        String formattedText = textArgs.isEmpty() ? new TextComponentTranslation(text).getFormattedText() : new TextComponentTranslation(text, textArgs).getFormattedText();
        ZenToast.LOGGER.info("draw.");
        toastGui.getMinecraft().getTextureManager().bindTexture(TEXTURE_TOASTS);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        toastGui.drawTexturedModalRect(0, 0, 0, 0, 160, 32);

        List<String> list = toastGui.getMinecraft().fontRenderer.listFormattedStringToWidth(formattedText.trim(), 125);
        int i = 16776960;

        if (list.size() == 1) {
            toastGui.getMinecraft().fontRenderer.drawString(formattedTitle.trim() ,30, 7, i | -16777216);
            toastGui.getMinecraft().fontRenderer.drawString(formattedText.trim(), 30, 18, -1);
        } else {
            if (delta < 1500L) {
                int k = MathHelper.floor(MathHelper.clamp((float)(1500L - delta) / 300.0F, 0.0F, 1.0F) * 255.0F) << 24 | 67108864;
                toastGui.getMinecraft().fontRenderer.drawString(formattedTitle.trim(), 30, 11, i | k);
            } else {
                int i1 = MathHelper.floor(MathHelper.clamp((float)(delta - 1500L) / 300.0F, 0.0F, 1.0F) * 252.0F) << 24 | 67108864;
                int l = 16 - list.size() * toastGui.getMinecraft().fontRenderer.FONT_HEIGHT / 2;

                for (String s : list) {
                    toastGui.getMinecraft().fontRenderer.drawString(s, 30, l, 16777215 | i1);
                    l += toastGui.getMinecraft().fontRenderer.FONT_HEIGHT;
                }
            }
        }

        RenderHelper.enableGUIStandardItemLighting();
        toastGui.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(null,icon, 8, 8);
        return delta >= 5000L ? Visibility.HIDE : Visibility.SHOW;
    }
}

