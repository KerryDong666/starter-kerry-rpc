package com.kerry.rpc.framework.serializer;

import org.apache.commons.lang3.StringUtils;

/**
 * @author liyebing created on 17/2/11.
 * @version $Id$
 */
public enum SerializeType {

    JAVA_SERIALIZER("JavaSerializer"),
    HESSIAN_SERIALIZER("HessianSerializer"),
    JSON_SERIALIZER("JSONSerializer"),
    PROTOSTUFF_SERIALIZER("ProtoStuffSerializer");

    private String serializeType;

	SerializeType(String serializeType) {
        this.serializeType = serializeType;
    }


    public static SerializeType queryByType(String serializeType) {
        if (StringUtils.isBlank(serializeType)) {
            return null;
        }

        for (SerializeType serialize : SerializeType.values()) {
            if (StringUtils.equals(serializeType, serialize.getSerializeType())) {
                return serialize;
            }
        }
        return null;
    }

    public String getSerializeType() {
        return serializeType;
    }
}
