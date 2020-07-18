package com.rmolives.updater;

import java.util.LinkedList;
import java.util.List;

public class VersionManager {
    private final List<Version> versions = new LinkedList<>();
    private Version latestVersion = null;
    private static final VersionManager aClass = new VersionManager();

    private VersionManager() {
    }

    public Version addVersion(Version version) {
        latestVersion = version;
        versions.add(version);
        return version;
    }

    public Version getLatestVersion() {
        return latestVersion;
    }

    public static VersionManager getVersionManager() {
        return aClass;
    }
}
