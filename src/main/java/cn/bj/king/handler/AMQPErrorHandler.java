package cn.bj.king.handler;

import cn.bj.king.exception.GlobalException;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.FatalExceptionStrategy;
import org.springframework.util.ErrorHandler;

public class AMQPErrorHandler implements ErrorHandler {

    private final FatalExceptionStrategy exceptionStrategy = new ExceptionStrategy();

    public static final int AMQP_REJECT_AND_DONT_REQUEUE = 50010;

    @Override
    public void handleError(Throwable t) {
        // 如果是致命异常，则转为 AmqpRejectAndDontRequeueException 抛出
        if (!this.causeChainContainsARADRE(t) && this.exceptionStrategy.isFatal(t)) {
            throw new AmqpRejectAndDontRequeueException("Error Handler converted exception to fatal", t);
        }
    }

    /**
     * @return true if the cause chain already contains an
     * {@link AmqpRejectAndDontRequeueException}.
     */
    private boolean causeChainContainsARADRE(Throwable t) {
        Throwable cause = t.getCause();
        while (cause != null) {
            if (cause instanceof AmqpRejectAndDontRequeueException) {
                return true;
            }
            cause = cause.getCause();
        }
        return false;
    }

    /**
     * 异常时的重新入队处理策略
     */
    public class ExceptionStrategy extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy {
        /**
         * 通过重写该方法来添加自定义的异常
         * Subclasses can override this to add custom exceptions.
         *
         * @param cause the cause
         * @return true if the cause is fatal.
         */
        @Override
        protected boolean isUserCauseFatal(Throwable cause) {

            if (cause instanceof GlobalException) {
                GlobalException e = (GlobalException) cause;

                // 如果异常为无需入队异常则直接返回 true
                if (e.getCode() == AMQP_REJECT_AND_DONT_REQUEUE) {
                    return true;
                }
            }

            return false;
        }
    }
}
