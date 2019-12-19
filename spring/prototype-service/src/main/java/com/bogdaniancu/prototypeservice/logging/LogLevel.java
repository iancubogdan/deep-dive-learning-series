package com.bogdaniancu.prototypeservice.logging;

import org.slf4j.Logger;

public enum LogLevel {

    INFO{
        @Override
        public void log(Logger logger, String message) {
            logger.info(message);
        }

        @Override
        public void log(Logger logger, String format, Object... arguments) {
            logger.info(format, arguments);
        }
    },
    DEBUG {
        @Override
        public void log(Logger logger, String message) {
            logger.debug(message);
        }

        @Override
        public void log(Logger logger, String format, Object... arguments) {
            logger.debug(format, arguments);
        }
    },
    ERROR {
        @Override
        public void log(Logger logger, String message) {
            logger.error(message);
        }

        @Override
        public void log(Logger logger, String format, Object... arguments) {
            logger.error(format, arguments);
        }
    },
    TRACE {
        @Override
        public void log(Logger logger, String message) {
            logger.trace(message);
        }

        @Override
        public void log(Logger logger, String format, Object... arguments) {
            logger.trace(format, arguments);
        }
    },
    WARN {
        @Override
        public void log(Logger logger, String message) {
            logger.warn(message);
        }

        @Override
        public void log(Logger logger, String format, Object... arguments) {
            logger.warn(format, arguments);
        }
    };

    public abstract void log(Logger logger, String message);
    public abstract void log(Logger logger, String format, Object... arguments);

}
