package me.kall.blockblocker.common.mixin;

import me.kall.blockblocker.common.api.IBlock;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Block.class)
public class BlockMixin implements IBlock {
    @Unique
    private boolean blockBlocker$blocked;

    @Override
    public boolean blockBlocker$isBlocked() {
        return this.blockBlocker$blocked;
    }

    @Override
    public void blockBlocker$setBlocked() {
        this.blockBlocker$blocked = true;
    }
}
