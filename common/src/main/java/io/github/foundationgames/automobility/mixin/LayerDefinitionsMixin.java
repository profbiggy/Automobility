package io.github.foundationgames.automobility.mixin;


import com.google.common.collect.ImmutableMap;
import io.github.foundationgames.automobility.entity.render.ShellModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LayerDefinitions.class)

public class LayerDefinitionsMixin {
    @ModifyVariable(
            method = "createRoots",
            at = @At("STORE")
    )
    private static ImmutableMap.Builder<ModelLayerLocation, LayerDefinition> registerShellModel(ImmutableMap.Builder<ModelLayerLocation, LayerDefinition> var) {
        var.put(ShellModel.MODEL_LAYER, ShellModel.createLayer());
        return var;
    }
}
