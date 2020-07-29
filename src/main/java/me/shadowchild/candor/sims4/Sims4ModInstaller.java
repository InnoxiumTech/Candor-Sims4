package me.shadowchild.candor.sims4;

import me.shadowchild.candor.mod.Mod;
import me.shadowchild.candor.module.AbstractModInstaller;
import me.shadowchild.candor.module.AbstractModule;
import me.shadowchild.cybernize.zip.ZipUtils;

import java.io.File;
import java.io.IOException;

public class Sims4ModInstaller extends AbstractModInstaller {

    public Sims4ModInstaller(AbstractModule module) {

        super(module);
    }

    @Override
    public boolean canInstall(Mod mod) {

        return true;
    }

    @Override
    public boolean install(Mod mod) {

        // Handle install of sims 4 mods - should be copy/paste
        boolean extract = determineShouldExtractMod(mod);

        if(extract) {

            File modDir = this.module.getModsFolder();
            if(!modDir.exists()) modDir.mkdirs();

            try {

                System.out.println(modDir.getCanonicalPath());
                ZipUtils.unZipIt(mod.getFile().getCanonicalPath(), modDir.getCanonicalPath());
            } catch(IOException e) {

                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    private boolean determineShouldExtractMod(Mod mod) {

        return true;
    }
}
