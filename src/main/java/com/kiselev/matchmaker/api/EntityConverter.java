package com.kiselev.matchmaker.api;

import com.kiselev.matchmaker.api.model.User;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public interface EntityConverter<ExternalType> {

    User convertUser(ExternalType externalEntity);
}
