package com.rmolives.updater.http;

import com.google.gson.Gson;
import com.rmolives.updater.Version;
import com.rmolives.updater.VersionManager;
import com.rmolives.updater.http.util.Query;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpUpdate implements HttpHandler {
    private static final VersionManager versionManager = VersionManager.getVersionManager();
    private static final Gson gson = new Gson();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("GET")) {
            Map<String, String> query = Query.parseQueryString(exchange.getRequestURI().getRawQuery());
            if (query.containsKey("version")) {
                Version latestVersion = versionManager.getLatestVersion();
                if (query.get("version").equals(latestVersion.getId()))
                    handleResponse(exchange, "{\"code\": 200, \"latestVersion\":" + gson.toJson(latestVersion) + ", \"result\":\"The current version is the latest!\"}");
                else
                    handleResponse(exchange, "{\"code\": 200, \"latestVersion\":" + gson.toJson(latestVersion) + ", \"result\":\"The current version is not the latest version, please update as soon as possible!\"}");
            } else
                handleResponse(exchange, "{\"code\": 201, \"result\":\"Missing version parameter!\"}");
        }
    }

    private static void handleResponse(HttpExchange httpExchange, String responseContent) throws IOException {
        byte[] responseContentByte = responseContent.getBytes(StandardCharsets.UTF_8);
        httpExchange.getResponseHeaders().add("Content-Type:", "text/html;charset=utf-8");
        httpExchange.sendResponseHeaders(200, responseContentByte.length);
        OutputStream out = httpExchange.getResponseBody();
        out.write(responseContentByte);
        out.flush();
        out.close();
    }

    private static String readToString(String fileName) throws IOException {
        FileInputStream in = new FileInputStream(new File(fileName));
        String result = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        in.close();
        return result;
    }
}

