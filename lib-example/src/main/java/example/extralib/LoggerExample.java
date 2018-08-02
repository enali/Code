package example.extralib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * slf4j只是一个日志接口, 其底层要绑定到一个具体的日志框架中, 本项目使用log4j
 */
public class LoggerExample {
    private static Logger log = LoggerFactory.getLogger(LoggerExample.class);

    public static void main(String[] args) {
        log.info("hello, world!");
    }
}
