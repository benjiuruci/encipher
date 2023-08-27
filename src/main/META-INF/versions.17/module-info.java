/**
 * @author qinxianzhong
 * @since 2023/8/11 11:24:23
 */
module encipher {

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.core;
    requires spring.web;
    requires spring.webmvc;
    requires org.apache.tomcat.embed.core;
    opens spring.core;


}
