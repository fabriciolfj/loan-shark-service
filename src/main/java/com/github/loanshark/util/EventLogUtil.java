package com.github.loanshark.util;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@Value
@ToString
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public class EventLogUtil {

    protected static Map<Class, EventLogUtil> cache = new LinkedHashMap<>();

    Class to;

    Logger log;

    @Singular
    Map<String, Object> params;

    public Log event() {
        return event((String) null);
    }

    public Log event(String operationId) {
        return new Log(operationId, null);
    }

    public Log event(Log reference) {
        return new Log(null, reference.specificParams);
    }

    public LogTimer eventTime() {
        return eventTime((String) null);
    }

    public LogTimer eventTime(String operationId) {
        return new LogTimer(operationId, null);
    }

    public LogTimer eventTime(Log reference) {
        return new LogTimer(null, reference.specificParams);
    }

    public boolean isLevelEnabled(Level logLevel) {
        switch (logLevel) {
            case DEBUG:
                return log.isDebugEnabled();
            case INFO:
                return log.isInfoEnabled();
            case WARN:
                return log.isWarnEnabled();
            case ERROR:
                return log.isErrorEnabled();
            case TRACE:
            default:
                return log.isTraceEnabled();
        }
    }

    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    public boolean isErrorEnabled() {
        return log.isErrorEnabled();
    }

    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    public boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

    public boolean isWarnEnabled() {
        return log.isWarnEnabled();
    }

    protected EventLogUtil(Class to, Logger log, Map<String, Object> params) {
        if (to == null && log == null)
            throw new NullPointerException("to() and log() can not be both null");

        this.to = to;
        this.log = log == null ? LoggerFactory.getLogger(to) : log;
        this.params = params;
    }

    public static EventLogUtil defaults(Class category) {
        return defaults(category, c -> EventLogUtil.builder()
                .to(c)
                .build());
    }

    public static EventLogUtil defaults(Class category, final Function<? super Class, ? extends EventLogUtil> builderFunc) {
        return cache.computeIfAbsent(category, builderFunc);
    }

    public class LogTimer extends Log {

        protected final long startedAt;

        protected long spentMs;

        private boolean stopped = false;

        protected LogTimer(String operationId, Map<String, Object> customParams) {
            super(operationId, customParams);
            startedAt = System.currentTimeMillis();

            if (!specificParams.containsKey("state")) {
                state(State.PROCESSING);
            }
        }

        public LogTimer stop() {
            if (stopped)
                return this;

            spentMs = System.currentTimeMillis() - startedAt;

            if (State.PROCESSING.equals(specificParams.get("state")))
                state(State.PROCESSED);

            param("tm", spentMs + "ms");
            stopped = true;

            return this;
        }

        public long getSpentTimeMs() {
            return spentMs;
        }

        public void error(Throwable t, Object input, Object... params) {
            super.error(t, input, params);
        }

        public void warn(Throwable t, Object input, Object... params) {
            super.warn(t, input, params);
        }

        public void debug(Throwable t, Object input, Object... params) {
            super.debug(t, input, params);
        }

        public void info(Throwable t, Object input, Object... params) {
            super.info(t, input, params);
        }

        public void trace(Throwable t, Object input, Object... params) {
            super.trace(t, input, params);
        }
    }

    public enum State {
        NEW, PROCESSING, PROCESSED, CANCELLED, SCHEDULED, SUCCESS, FAILURE, START, ERROR, UPLOAD
    }

    public class Log {

        protected Map<String, Object> specificParams;

        protected Log(String operationId, Map<String, Object> customParams) {
            this.specificParams = new LinkedHashMap(params);
            if (customParams != null)
                this.specificParams.putAll(customParams);
            if (operationId != null)
                operationId(operationId);
        }

        public <T extends Log> T param(String key, Object value) {
            this.specificParams.put(key, value);
            return (T) this;
        }

        public <T extends Log> T operationId(String operationId) {
            return (T) param("operationId", operationId);
        }

        public <T extends Log> T m(String method) {
            return (T) param("M", method);
        }

        public <T extends Log> T action(String action) {
            return (T) param("action", action);
        }

        public <T extends Log> T state(State state) {
            return (T) param("state", state);
        }

        public void logLevel(Level logLevel, Throwable t, Object input, Object... params) {
            switch (logLevel) {
                case DEBUG:
                    debug(t, input, params);
                    return;
                case INFO:
                    info(t, input, params);
                    return;
                case WARN:
                    warn(t, input, params);
                    return;
                case ERROR:
                    error(t, input, params);
                    return;
                case TRACE:
                default:
                    debug(t, input, params);
                    return;
            }
        }

        public void error(Throwable t, Object input, Object... params) {
            var msg = getMsg(input);
            log.error(formatMsg((t == null ? "" : t.toString()) + ": " + msg, params), t);
        }

        public void warn(Throwable t, Object input, Object... params) {
            var msg = getMsg(input);
            log.warn(formatMsg((t == null ? "" : t.toString()) + ": " + msg, params), t);
        }

        public void debug(Throwable t, Object input, Object... params) {
            if (log.isDebugEnabled()) {
                var msg = getMsg(input);
                log.debug(formatMsg((t == null ? "" : t.toString()) + ": " + msg, params), t);
            }
        }

        public void info(Throwable t, Object input, Object... params) {
            String msg = getMsg(input);

            log.info(formatMsg((t == null ? "" : t.toString()) + ": " + msg, params), t);
        }

        public void trace(Throwable t, Object input, Object... params) {
            if (log.isTraceEnabled()) {
                var msg = getMsg(input);
                log.trace(formatMsg((t == null ? "" : t.toString()) + ": " + msg, params), t);
            }
        }

        public void logLevel(Level logLevel, String formattedMsg, Object... params) {
            switch (logLevel) {
                case DEBUG:
                    debug(formattedMsg, params);
                    return;
                case INFO:
                    info(formattedMsg, params);
                    return;
                case WARN:
                    warn(formattedMsg, params);
                    return;
                case ERROR:
                    error(formattedMsg, params);
                    return;
                case TRACE:
                default:
                    debug(formattedMsg, params);
            }
        }

        public void error(String formattedMsg, Object... params) {
            error((Throwable) null, formattedMsg, params);
        }

        public void warn(String formattedMsg, Object... params) {
            warn((Throwable) null, formattedMsg, params);
        }

        public void debug(String formattedMsg, Object... params) {
            debug(null, formattedMsg, params);
        }

        public void info(String formattedMsg, Object... params) {
            info(null, formattedMsg, params);
        }

        public void trace(String formattedMsg, Object... params) {
            trace(null, formattedMsg, params);
        }

        public void debug() {
            debug((Throwable) null, null);
        }

        public void info() {
            info((Throwable) null, null);
        }

        public void error() {
            error((Throwable) null, null);
        }

        public void trace() {
            trace((Throwable) null, null);
        }

        public void debug(Object input) {
            debug((Throwable) null, input);
        }

        public void info(Object input) {
            info((Throwable) null, input);
        }

        public void trace(Object input) {
            trace((Throwable) null, input);
        }

        private String formatMsg(String formattedMsg, Object... params) {
            StringBuilder output = new StringBuilder(String.format(formattedMsg, params));
            for (String key : this.specificParams.keySet()) {
                output.append(", ").append(key).append(" ").append(this.specificParams.get(key));
            }
            return output.toString();
        }
    }

    private String getMsg(final Object input) {
        return input == null ? "" : String.valueOf(input);
    }
}