package hw.zako.zakohealthindicator.client.mixin;

import hw.zako.zakohealthindicator.util.ColorUtil;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class NickRenderMixin<T extends Entity>  {

    @Shadow
    protected abstract boolean hasLabel(T entity);

    @Inject(method = "renderLabelIfPresent", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/util/math/MatrixStack;push()V") )
    private void onRender(T entity, Text text, MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                          int light, CallbackInfo ci) {
        if (entity.getType() != EntityType.PLAYER) return;
        final MutableText mt = (MutableText) text;
        final float health = ((PlayerEntity)entity).getHealth();
        mt.append(ColorUtil.getColor(health)+" "+String.format("%.1f",health));
    }
}
