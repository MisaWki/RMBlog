package com.rmolives.updater;

import com.rmolives.updater.util.Hash;

import java.io.IOException;

public class Version {
    private final String id;
    private final String hash;
    private final VersionType type;
    private transient final String path;
    private final long time;

    public Version(String id, VersionType type, long time, String path) throws IOException {
        this.id = id;
        this.type = type;
        this.path = path;
        this.time = time;
        this.hash = Hash.hashFiles(path);
    }

    public long getTime() {
        return time;
    }

    public VersionType getType() {
        return type;
    }

    public String getHash() {
        return hash;
    }

    public String getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public enum VersionType {
        APP("app"), SCRIPT("script");
        String name;

        VersionType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
