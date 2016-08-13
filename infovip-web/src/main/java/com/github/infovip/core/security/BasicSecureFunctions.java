/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.core.security;

/**
 *
 * @author attila
 */
public class BasicSecureFunctions {

    /**
     * Check if the given string contains a pattern which could be lead to a
     * possible attack . This function only checks the existence of the given pattern.
     *
     * @param arg
     *
     * @url https://www.owasp.org/index.php/Path_Traversal
     *
     * @return
     */
    public static boolean directoryTraversalInputCheck(String arg) {
        return !(arg.contains("%2e") || arg.contains("%5c") || arg.contains("%25")
                || arg.contains("%c0") || arg.contains("%c1") || arg.contains("."));
    }
}
