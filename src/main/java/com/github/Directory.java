package com.github;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * @author 康盼Java开发工程师
 */
public class Directory {
    /**
     * @param dir
     * @param regex
     * @return
     */
    public static File[] list(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(new File(name).getName()).matches();
            }
        });
    }

    /**
     * @param file
     * @param regex
     * @return
     */
    public static FileList get(File file, String regex) {
        FileList list = new FileList();
        for (File item : file.listFiles()) {
            if (item.isDirectory()) {
                list.getDirs().add(item);
                list.addAll(get(item, regex));
            } else {
                if (item.getName().endsWith(regex)) {
                    list.getFiles().add(item);
                }
            }
        }
        return list;
    }

}
