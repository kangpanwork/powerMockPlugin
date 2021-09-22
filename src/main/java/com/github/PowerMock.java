package com.github;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


/**
 * @author 康盼Java开发工程师
 * 本地需要mvn install 其它项目才可以用
 */
@Mojo(name = "powerMock", defaultPhase = LifecyclePhase.PACKAGE)
public class PowerMock extends AbstractMojo {

    /**
     * 运行命令：mvn org.example:powerMockPlugin:1.0-SNAPSHOT:powerMock
     * com.github.model,com.github.service.impl
     */
    @Parameter(property = "directory")
    private String path;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            getLog().info("正在扫描需要测试的文件：");
            String[] packagePaths = path.split(",");
            String servicePath = packagePaths[0];
            String testPath = packagePaths[1];
            String packagePath = servicePath.replace(".", "/");
            Map<String,Integer> servicePathMap = getMap(testPath);
            Map<String,Integer> testPathMap = getMap(packagePath);
            for( String key : servicePathMap.keySet()) {
                String newKey = key + "Test";
                if(!testPathMap.containsKey(newKey)) {
                    getLog().info("没有写测试类的文件名："+ key +"\n");
                }
            }

        } catch (Exception exception) {

        }

    }

    private Map<String, Integer> getMap(String packagePath) throws UnsupportedEncodingException {
        Map<String, Integer> map = new HashMap<>();
        for (URL url : ResourceUtil.getResourcesIterator(packagePath)) {
            File file = new File(URLDecoder.decode(url.toString(), StandardCharsets.UTF_8.name()).substring(6));
            FileList list = Directory.get(file, ".class");
            list.getFiles().forEach(ele -> {
                String fileName = ele.getName();
                String className = path + "." + fileName.substring(0, fileName.lastIndexOf("."));
                map.put(className, 1);
            });
        }
        return map;
    }


}
