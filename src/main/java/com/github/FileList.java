package com.github;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 康盼Java开发工程师
 */
public class FileList implements Iterable<File> {
    private List<File> files = new ArrayList<>();
    private List<File> dirs = new ArrayList<>();

    @Override
    public Iterator<File> iterator() {
        return files.iterator();
    }

    protected void addAll(FileList fileList) {
        files.addAll(fileList.files);
        dirs.addAll(fileList.dirs);
    }

    public List<File> getFiles() {
        return files;
    }

    public List<File> getDirs() {
        return dirs;
    }

}
