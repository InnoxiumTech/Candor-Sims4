package me.shadowchild.candor.sims4;

import me.shadowchild.candor.ConfigHandler;
import me.shadowchild.candor.Settings;
import me.shadowchild.candor.module.AbstractModInstaller;
import me.shadowchild.candor.module.AbstractModule;
import me.shadowchild.candor.module.RunConfig;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class Sims4Module extends AbstractModule {

    public File game;

    @Override
    public File getGame() {

        return game;
    }

    @Override
    public File getModsFolder() {

        return new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath(), "Electronic Arts/The Sims 4/Mods");
    }

    @Override
    public void setGame(File file) {

        this.game = game;
    }

    @Override
    public void setModsFolder(File file) {

    }

    @Override
    public String getModuleName() {

        return "sims4";
    }

    @Override
    public String getReadableGameName() {

        return "The Sims 4";
    }

    @Override
    public AbstractModInstaller getModInstaller() {

        return new Sims4ModInstaller(this);
    }

    @Override
    public boolean requiresModFolderSelection() {

        return false;
    }

    @Override
    public String[] acceptedExe() {

        return new String[] {"TS4_x64"};
    }

    @Override
    public String getModFileFilterList() {

        return "zip,7z,ts4script,package,rar";
    }

    @Override
    public RunConfig getDefaultRunConfig() {

        return new RunConfig() {

            @Override
            public String getStartCommand() {

                return Settings.gameExe;
            }

            @Override
            public String getProgramArgs() {

                return "";
            }

            @Override
            public String getWorkingDir() {

                return null;
            }
        };
    }

    @Override
    public String getExeName() {

        return "TS4_x64";
    }
}
