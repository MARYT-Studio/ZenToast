package world.maryt.ZenToast.toast;

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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
public class SimpleToast implements IToast {
    public String titleJson;
    public String textJson;
    public ItemStack icon;
    public SimpleToast(String titleJson, String textJson, ItemStack icon) throws JsonParseException {
        try {
            ITextComponent.Serializer.jsonToComponent(titleJson);
            this.titleJson = titleJson;
        } catch (JsonSyntaxException e) {
            CraftTweakerAPI.logError("Title JSON has syntax error.");
            CraftTweakerAPI.logError(titleJson);
            this.titleJson = null;
        } catch (JsonParseException e) {
            CraftTweakerAPI.logError("Title JSON syntax is correct, but cannot be transformed into ITextComponent. Rewrite it following the vanilla Minecraft rules.");
            CraftTweakerAPI.logError(titleJson);
            this.titleJson = null;
        }
        try {
            ITextComponent.Serializer.jsonToComponent(textJson);
            this.textJson = textJson;
        } catch (JsonSyntaxException e) {
            CraftTweakerAPI.logError("Text JSON has syntax error.");
            CraftTweakerAPI.logError(textJson);
            this.textJson = null;
        } catch (JsonParseException e) {
            CraftTweakerAPI.logError("Title JSON syntax is correct, but cannot be transformed into ITextComponent. Rewrite it following the vanilla Minecraft rules.");
            CraftTweakerAPI.logError(textJson);
            this.textJson = null;
        }
        this.icon = icon;
    }
    @Override
    @SuppressWarnings("NullableProblems")
    public Visibility draw(GuiToast toastGui, long delta) {

        if (titleJson == null || textJson == null) return Visibility.HIDE;
        ITextComponent title = ITextComponent.Serializer.jsonToComponent(this.titleJson);
        ITextComponent text = ITextComponent.Serializer.jsonToComponent(this.textJson);
        if (title == null || text == null) return Visibility.HIDE;

        toastGui.getMinecraft().getTextureManager().bindTexture(TEXTURE_TOASTS);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        toastGui.drawTexturedModalRect(0, 0, 0, 0, 160, 32);

        List<String> list = toastGui.getMinecraft().fontRenderer.listFormattedStringToWidth(text.getFormattedText().trim(), 125);
        int i = 16776960;

        if (list.size() == 1) {
            toastGui.getMinecraft().fontRenderer.drawString(title.getFormattedText().trim() ,30, 7, i | -16777216);
            toastGui.getMinecraft().fontRenderer.drawString(text.getFormattedText().trim(), 30, 18, -1);
        } else {
            if (delta < 1500L) {
                int k = MathHelper.floor(MathHelper.clamp((float)(1500L - delta) / 300.0F, 0.0F, 1.0F) * 255.0F) << 24 | 67108864;
                toastGui.getMinecraft().fontRenderer.drawString(title.getFormattedText().trim(), 30, 11, i | k);
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

