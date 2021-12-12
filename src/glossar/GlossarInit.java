package glossar;

import api.DebugFile;
import api.mod.StarMod;
import api.utils.gui.ModGUIHandler;
import org.schema.game.client.data.GameClientState;

import java.util.Random;

/**
 * STARMADE MOD
 * CREATOR: Max1M
 * DATE: 06.12.2021
 * TIME: 18:44
 */
public class GlossarInit {
    private static GlossarControlManager glossarInstance;

    /**
     * safe method of initializing the glossar GUI. can be called many times, only will add once.
     * @param mod
     */
    public static void initGlossar(StarMod mod) {
        if (glossarInstance != null)
            return;
        glossarInstance = new GlossarControlManager(GameClientState.instance);
        ModGUIHandler.registerNewControlManager(mod.getSkeleton(),glossarInstance);
        DebugFile.log("Glossar Manager added for mod: " + mod.getName());

    }

    public static void addCategory(GlossarCategory cat) {
        GlossarPageList.addGlossarCat(cat);
    }
}
