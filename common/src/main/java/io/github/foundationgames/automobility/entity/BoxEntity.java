package io.github.foundationgames.automobility.entity;

import java.util.List;

import io.github.foundationgames.automobility.item.AutomobilityItems;
import io.github.foundationgames.automobility.item.BananaItem;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BoxEntity extends EndCrystal {

    public int timeSincePickup = 0;
    public BoxEntity(EntityType<?> type, Level level) {
        super((EntityType<EndCrystal>)type, level);
    }

    public BoxEntity(Level level) {
        super(AutomobilityEntities.BOX.require(), level);
    }

    public BoxEntity(Level $$0, double $$1, double $$2, double $$3) {
        this($$0);
        setPos($$1, $$2, $$3);
    }

    @Override
    public boolean hurt(DamageSource $$0, float $$1) {
        return true;
    }

    @Override
    public void tick() {
        super.tick();

        List<AutomobileEntity> vehicles = level().getEntitiesOfClass(AutomobileEntity.class, this.getBoundingBox());

        if(timeSincePickup >= 20 && !vehicles.isEmpty()){
            AutomobileEntity vehicle = vehicles.get(0);
            //level().addParticle();

            if(!level().isClientSide){
                var passenger = vehicle.getControllingPassenger();
                if(passenger instanceof Player){
                    ((Player) passenger).getInventory().add(new ItemStack(AutomobilityItems.BANANA_THROWABLE.require(), 3));
                }
            }
            timeSincePickup = 0;
        }
        else {
            timeSincePickup++;
        }
    }
}