package com.liurui;

import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ProtectionDomain protectionDomain = App.class.getProtectionDomain();
        CodeSource codeSource = protectionDomain.getCodeSource();

        System.out.println(codeSource.getLocation());
    }
}
