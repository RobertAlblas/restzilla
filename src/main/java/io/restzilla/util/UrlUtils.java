/*
 * (C) 2014 42 bv (www.42.nl). All rights reserved.
 */
package io.restzilla.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * URL parsing functionalities.
 *
 * @author Jeroen van Schagen
 * @since Aug 26, 2015
 */
public class UrlUtils {
    
    public static final String SLASH = "/";
    
    private static final String VARIABLE_PATTERN = "\\{.*\\}";

    /**
     * Retrieve the path from a request.
     * 
     * @param request the request
     * @return the path
     */
    public static String getPath(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return request.getRequestURI().substring(contextPath.length());
    }
    
    /**
     * Retrieve the root path from a request.
     * 
     * @param request the request
     * @return the root path
     */
    public static String getRootPath(HttpServletRequest request) {
        String path = getPath(request);
        return stripSlashes(path);
    }

    /**
     * Removes the slashes from a path.
     * 
     * @param path the raw path
     * @return the same path without slashes
     */
    public static String stripSlashes(String path) {
        if (!path.startsWith(SLASH)) {
            path = SLASH + path;
        }
        if (!path.endsWith(SLASH)) {
            path = path + SLASH;
        }
        return StringUtils.substringBetween(path, SLASH, SLASH);
    }
    
    /**
     * Determine if two paths are similar.
     * 
     * @param leftPath the left path
     * @param rightPath the right path
     * @return {@code true} if similar, else {@code false}
     */
    public static boolean isSamePath(String leftPath, String rightPath) {
        final String[] left = leftPath.split(SLASH);
        final String[] right = rightPath.split(SLASH);
        
        if (left.length == right.length) {
            for (int index = 0; index < left.length; index++) {
                String a = left[index];
                String b = right[index];
                if (a.equals(b) || (a.matches(VARIABLE_PATTERN) && b.matches(VARIABLE_PATTERN))) {
                    return true;
                }
            }
        }
        
        return false;
    }

}