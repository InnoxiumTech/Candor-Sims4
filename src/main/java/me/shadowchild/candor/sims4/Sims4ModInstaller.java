package me.shadowchild.candor.sims4;

import me.shadowchild.candor.mod.Mod;
import me.shadowchild.candor.module.AbstractModInstaller;
import me.shadowchild.candor.module.AbstractModule;
import me.shadowchild.cybernize.zip.ZipUtils;
import org.apache.commons.io.FileUtils;

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
        File modDir = this.module.getModsFolder();

        // For archives or files
        if(extract) {

//            File modDir = new File(this.module.getModsFolder(), mod.getReadableName());
            if(!modDir.exists()) modDir.mkdirs();

            try {

                System.out.println(modDir.getCanonicalPath());
                ZipUtils.unZipIt(mod.getFile().getCanonicalPath(), modDir.getCanonicalPath());
            } catch(IOException e) {

                e.printStackTrace();
                return false;
            }
        } else {

            // For single ".package" or ".ts4script" files
            try {

                FileUtils.copyFileToDirectory(mod.getFile().getCanonicalFile(), modDir.getCanonicalFile());
            } catch (IOException e) {

                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    private boolean determineShouldExtractMod(Mod mod) {

        return switch (getExtension(mod)) {

            case "zip", "7z" -> true;
            case "package", "ts4script" -> false;
            default -> throw new IllegalArgumentException("This file is not a proper mod");
        };
    }

    private String getExtension(Mod mod) {

        String filePath = mod.getFile().getAbsolutePath();
        String ret = filePath.substring(filePath.lastIndexOf(".") + 1);
        System.out.println(ret);
        return ret;
    }
}
