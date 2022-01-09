package uk.co.innoxium.candor.sims4;

import org.apache.commons.io.FileUtils;
import uk.co.innoxium.candor.mod.Mod;
import uk.co.innoxium.candor.module.AbstractModInstaller;
import uk.co.innoxium.candor.module.AbstractModule;
import uk.co.innoxium.cybernize.archive.Archive;
import uk.co.innoxium.cybernize.archive.ArchiveBuilder;

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
        if (extract) {

//            File modDir = new File(this.module.getModsFolder(), mod.getReadableName());
            if (!modDir.exists()) modDir.mkdirs();

            try {

                Archive archive = new ArchiveBuilder(mod.getFile()).outputDirectory(modDir).build();
                archive.extract();
//                System.out.println(modDir.getCanonicalPath());
//                ZipUtils.unZipIt(mod.getFile().getCanonicalPath(), modDir.getCanonicalPath());
            } catch (IOException e) {

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

    @Override
    public boolean uninstall(Mod mod) {


        mod.getAssociatedFiles().forEach(element -> {

            File toDelete = new File(module.getModsFolder(), element.getAsString());
            System.out.println("Deleting: " + toDelete.getAbsolutePath());
            FileUtils.deleteQuietly(toDelete);
        });
        return true;
//        String extension = getExtension(mod);
//
//        switch(extension) {
//
//            case "zip", "7z", "rar" -> {
//
//                mod.getAssociatedFiles().forEach(element -> {
//
//                    File toDelete = new File(module.getModsFolder(), element.getAsString());
//                    System.out.println("Deleting: " + toDelete.getAbsolutePath());
//                    FileUtils.deleteQuietly(toDelete);
//                });
//                return true;
//            }
//            case "package", "ts4script" -> {
//
//                String fileName = mod.getName() + "." + extension;
//
//                File toDelete = new File(module.getModsFolder(), fileName);
//                System.out.println("Deleting: " + toDelete.getAbsolutePath());
//                return FileUtils.deleteQuietly(toDelete);
//            }
//        }
//        return false;
    }

    private boolean determineShouldExtractMod(Mod mod) {

        return switch (getExtension(mod)) {

            case "zip", "7z", "rar" -> true;
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
