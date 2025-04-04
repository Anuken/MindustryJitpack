package mindustry.type.unit;

import mindustry.ai.types.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.meta.*;

/** Field template for unit types. No new functionality. */
public class MissileUnitType extends UnitType{

    public MissileUnitType(String name){
        super(name);

        playerControllable = false;
        createWreck = false;
        createScorch = false;
        logicControllable = false;
        isEnemy = false;
        useUnitCap = false;
        drawCell = false;
        allowedInPayloads = false;
        controller = u -> new MissileAI();
        flying = true;
        constructor = TimedKillUnit::create;
        envEnabled = Env.any;
        envDisabled = Env.none;
        physics = false;
        bounded = false;
        trailLength = 7;
        hidden = true;
        hoverable = false;
        speed = 4f;
        lifetime = 60f * 1.7f;
        rotateSpeed = 2.5f;
        range = 6f;
        targetPriority = -1f;
        outlineColor = Pal.darkOutline;
        fogRadius = 2f;
        loopSound = Sounds.missileTrail;
        loopSoundVolume = 0.05f;
        drawMinimap = false;
        //TODO weapon configs, etc?
    }
}
