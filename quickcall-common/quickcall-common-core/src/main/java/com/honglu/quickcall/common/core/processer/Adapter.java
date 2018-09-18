package com.honglu.quickcall.common.core.processer;


import org.springframework.context.ApplicationContext;

import javax.swing.text.html.parser.Entity;

/**
 * 
 * @author conly.wang
 *
 */
public class Adapter<T extends Entity>{
    private static ApplicationContext context = null;

    public static void setContext(ApplicationContext context) {
    	Adapter.context = context;
    }

 
}
