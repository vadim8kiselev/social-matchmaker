package com.kiselev.matchmaker.api.network.vk.exception;

import com.kiselev.matchmaker.api.network.vk.utils.VKUtils;
import com.vk.api.sdk.exceptions.ApiTooManyException;
import com.vk.api.sdk.exceptions.ApiUserDeletedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    public static void handleException(Exception exception) {
        if (exception instanceof ApiUserDeletedException) {
            LOGGER.info("Skipped: User was deleted or banned");
        } else if (exception instanceof ApiTooManyException) {
            LOGGER.info("Skipped: Too many requests per second");
            VKUtils.timeout();
        } else {
            exception.printStackTrace();
        }
    }
}
