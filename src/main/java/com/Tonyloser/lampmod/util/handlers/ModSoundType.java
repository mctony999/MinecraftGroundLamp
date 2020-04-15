package com.Tonyloser.lampmod.util.handlers;

import com.Tonyloser.lampmod.util.handlers.SoundsHandler;

import net.minecraft.block.SoundType;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public class ModSoundType extends SoundType {
	
	public static final SoundType LANTERN = new ModSoundType(1.0F, 1.0F, SoundsHandler.BLOCK_LANTERN_BREAK, SoundsHandler.BLOCK_LANTERN_STEP, SoundsHandler.BLOCK_LANTERN_PLACE, SoundsHandler.BLOCK_LANTERN_HIT, SoundsHandler.BLOCK_LANTERN_FALL);
	
    public final float volume;
    public final float pitch;
	 /** The sound played when a block gets broken. */
    private final SoundEvent breakSound;
    /** The sound played when walking on a block. */
    private final SoundEvent stepSound;
    /** The sound played when a block gets placed. */
    private final SoundEvent placeSound;
    /** The sound played when a block gets hit (i.e. while mining). */
    private final SoundEvent hitSound;
    /** The sound played when a block gets fallen upon. */
    private final SoundEvent fallSound;
	
	public ModSoundType(float volumeIn, float pitchIn, SoundEvent breakSoundIn, SoundEvent stepSoundIn,
			SoundEvent placeSoundIn, SoundEvent hitSoundIn, SoundEvent fallSoundIn) {
		super(volumeIn, pitchIn, breakSoundIn, stepSoundIn, placeSoundIn, hitSoundIn, fallSoundIn);
		this.volume = volumeIn;
        this.pitch = pitchIn;
        this.breakSound = breakSoundIn;
        this.stepSound = stepSoundIn;
        this.placeSound = placeSoundIn;
        this.hitSound = hitSoundIn;
        this.fallSound = fallSoundIn;
	}
	
	
	
	
}
