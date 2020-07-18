package com.rmolives.updater;

import com.rmolives.updater.http.HttpUpdate;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        VersionManager.getVersionManager().addVersion(new Version("10001", Version.VersionType.SCRIPT, System.currentTimeMillis(), "C:\\Users\\rmoli\\Downloads\\BullshitGenerator-master"));
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
        httpServer.createContext("/update", new HttpUpdate());
        httpServer.setExecutor(Executors.newFixedThreadPool(10));
        httpServer.start();
    }
}
