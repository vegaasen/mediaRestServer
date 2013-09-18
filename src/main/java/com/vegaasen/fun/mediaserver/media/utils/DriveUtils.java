package com.vegaasen.fun.mediaserver.media.utils;

import com.google.common.base.Strings;

import java.io.File;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public final class DriveUtils {

    private static final File[] EMPTY_FILES = {};

    private static Set<String> ignoreList = new HashSet<String>();

    private DriveUtils() {
    }

    public static File[] getActiveDrives() {
        final File[] files = File.listRoots();
        if (files != null && files.length > 0) {
            return files;
        }
        return EMPTY_FILES;
    }

    public static Set<File> findFiles(final String path, final String expression) {
        if (Strings.isNullOrEmpty(expression)) {
            throw new IllegalArgumentException("Path of expression, which is required, is null or empty.");
        }
        File[] roots;
        if (Strings.isNullOrEmpty(path)) {
            roots = getActiveDrives();
        } else {
            roots = new File[]{new File(path)};
        }
        if (roots != null && roots.length > 0) {
            Set<File> filesFound = new TreeSet<File>();
            for (File file : roots) {
                if (file != null) {
                    filesFound.addAll(findFile(file.listFiles(), expression));
                }
            }
            if (!filesFound.isEmpty()) {
                return filesFound;
            }
        }
        return Collections.emptySet();
    }

    public static Set<File> findFile(final File[] files, final String expression) {
        Set<File> foundFiles = new TreeSet<File>();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (validateNotIgnored(file.getPath())) {
                    if (file.isDirectory()) {
                        foundFiles.addAll(findFile(file.listFiles(), expression));
                    } else {
                        if (file.getName().matches(expression)) {
                            foundFiles.add(file);
                        }
                    }
                }
            }
            return foundFiles;
        }
        return Collections.emptySet();
    }

    public static void setIgnoredFiles(Set<String> ignoredPaths) {
        ignoreList.addAll(ignoredPaths);
    }

    /**
     * Add a single file to the ignore-list
     *
     * @param path filename with full path. Can also be directory.
     */
    public static void addIgnoreFile(String path) {
        if (Strings.isNullOrEmpty(path)) {
            throw new IllegalArgumentException("Unable to add file to ignore-list. Missing filename.");
        }
        ignoreList.add(path);
    }

    public static File getFile(final String path) {
        if (Strings.isNullOrEmpty(path)) {
            throw new IllegalArgumentException("Cannot operate on a empty string.");
        }
        return new File(path);
    }

    public static InputStream getFileFromResource(final String path) {
        if (Strings.isNullOrEmpty(path)) {
            throw new IllegalArgumentException("Cannot operate on a empty string.");
        }
        return DriveUtils.class.getResourceAsStream(path);
    }

    public static boolean exists(final File file) {
        return (file != null && file.isFile() && file.exists() && file.canRead());
    }

    private static boolean validateNotIgnored(final String path) {
        if (ignoreList.isEmpty()) {
            return true;
        }
        if (Strings.isNullOrEmpty(path)) {
            return true;
        }
        for (String ignoredPath : ignoreList) {
            if (path.equals(ignoredPath)) {
                return false;
            }
        }
        return true;
    }

}
