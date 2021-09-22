package com.github;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * @author 康盼Java开发工程师
 */
public class ResourceUtil {

    /**
     *获取指定路径下的资源Iterator
     *路径格式必须为目录格式,用/分隔 eg:
     * <pre>
     * config/a
     * spring/xml
     * </pre>
     *
     * @param resource 资源路径
     * @return 资源列表
     */
    public static EnumerationIter<URL> getResourcesIterator(String resource) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            final Enumeration<URL> resources = classLoader.getResources(resource);
            return new EnumerationIter<>(resources);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
