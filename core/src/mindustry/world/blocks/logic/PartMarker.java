package mindustry.world.blocks.logic;

import arc.graphics.g2d.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.annotations.Annotations.*;
import mindustry.ctype.*;
import mindustry.gen.*;
import mindustry.io.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.meta.*;

/** For internal use only; marks base part edges. */
public class PartMarker extends Block{
    public @Load("@-arrow") TextureRegion arrowRegion;

    public PartMarker(String name){
        super(name);

        update = true;
        rotate = true;
        configurable = true;
        saveConfig = true;
        group = BlockGroup.transportation;

        config(Item.class, (PartMarkerBuild tile, Item item) -> tile.content = item);
        config(Liquid.class, (PartMarkerBuild tile, Liquid item) -> tile.content = item);
        configClear((PartMarkerBuild tile) -> tile.content = null);
    }

    public class PartMarkerBuild extends Building{
        public @Nullable UnlockableContent content;

        @Override
        public void draw(){
            super.draw();
            Draw.rect(arrowRegion, x, y);

            if(content != null){
                float size = 4f;
                Draw.rect(content.fullIcon, x, y, size, size);
            }
        }

        @Override
        public UnlockableContent config(){
            return content;
        }

        @Override
        public void write(Writes write){
            super.write(write);

            TypeIO.writeContent(write, content);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);

            content = TypeIO.readContent(read) instanceof UnlockableContent con ? con : null;
        }
    }
}
