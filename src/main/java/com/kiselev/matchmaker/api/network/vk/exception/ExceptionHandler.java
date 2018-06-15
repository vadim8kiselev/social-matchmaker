package com.kiselev.matchmaker.api.network.vk.exception;

import com.kiselev.matchmaker.api.network.vk.utils.VKUtils;
import com.vk.api.sdk.exceptions.ApiAuthException;
import com.vk.api.sdk.exceptions.ApiParamUserIdException;
import com.vk.api.sdk.exceptions.ApiTooManyException;
import com.vk.api.sdk.exceptions.ApiUserDeletedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    public static void rethrowException(Throwable throwable) {
        // skip
    }

    public static void handleException(Throwable throwable) throws Throwable {
        Throwable cause = throwable.getCause();

        if (cause instanceof ApiAuthException) {
            LOGGER.info("Skipped: User authorization failed: access_token has expired");
        } else if (cause instanceof ApiUserDeletedException) {
            LOGGER.info("Skipped: User was deleted or banned");
        } else if (cause instanceof ApiTooManyException) {
            LOGGER.info("Skipped: Too many requests per second");
            VKUtils.timeout();
        } else if (cause instanceof ApiParamUserIdException) {
            LOGGER.info("Invalid user id");
        } else {
            if (cause != null) {
                cause.printStackTrace();
            } else {
                throwable.printStackTrace();
            }
        }

        throw cause != null ? cause : throwable;
    }
}
