package com.kiselev.matchmaker.job.implementation;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.job.Job;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ClearOldSerializedFileJob implements Job {

    private static final List<String> directoryPaths;

    static {
        directoryPaths = Lists.newArrayList();
        directoryPaths.add("export/csv");
        directoryPaths.add("export/excel");
        directoryPaths.add("export/json");
        directoryPaths.add("export/xml");
    }

    @Override
    @PostConstruct
    public void start() {
        new Thread(this::run).start();
    }

    private void run() {
        while (true) {
            for (String directoryPath : directoryPaths) {
                File directory = new File(directoryPath);

                if (directory.isDirectory()) {
                    for (File file : Objects.requireNonNull(directory.listFiles())) {
                        long diff = new Date().getTime() - file.lastModified();

                        if (diff > 60 * 60 * 1000) { // 1 hour
                            file.delete();
                        }
                    }
                }
            }

            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
