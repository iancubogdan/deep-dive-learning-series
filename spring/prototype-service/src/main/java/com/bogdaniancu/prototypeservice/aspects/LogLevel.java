package com.bogdaniancu.prototypeservice.aspects;

import org.slf4j.Logger;

public enum LogLevel {

    INFO{
        @Override
        public void log(Logger logger, String message) {
            logger.info(message);
        }
    },
    DEBUG {
        @Override
        public void log(Logger logger, String message) {
            logger.debug(message);
        }
    },
    ERROR {
        @Override
        public void log(Logger logger, String message) {
            logger.error(message);
        }
    },
    TRACE {
        @Override
        public void log(Logger logger, String message) {
            logger.trace(message);
        }
    },
    WARN {
        @Override
        public void log(Logger logger, String message) {
            logger.warn(message);
        }
    };

    public abstract void log(Logger logger, String message);

}
