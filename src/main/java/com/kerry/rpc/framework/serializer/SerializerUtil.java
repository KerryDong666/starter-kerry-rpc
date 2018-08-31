package com.kerry.rpc.framework.serializer;


import com.kerry.rpc.framework.serializer.exception.SerializeException;
import com.kerry.rpc.framework.serializer.util.HessianSerializer;
import com.kerry.rpc.framework.serializer.util.JavaSerializer;
import com.kerry.rpc.framework.serializer.util.JsonSerializer;
import com.kerry.rpc.framework.serializer.util.ProtoStuffSerializer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liyebing created on 17/1/23.
 * @version $Id$
 */
public class SerializerUtil {

    public static final Map<SerializeType, SerializerHandler> serializerMap = new ConcurrentHashMap<>();

	static {
		serializerMap.put(SerializeType.HESSIAN_SERIALIZER, new HessianSerializer());
		serializerMap.put(SerializeType.JAVA_SERIALIZER, new JavaSerializer());
		serializerMap.put(SerializeType.JSON_SERIALIZER, new JsonSerializer());
		serializerMap.put(SerializeType.PROTOSTUFF_SERIALIZER, new ProtoStuffSerializer());
	}


    public static <T> byte[] serialize(T obj, String serializeType) {
        SerializeType serialize = SerializeType.queryByType(serializeType);
        // default serialize is hessian
        if (serialize == null) {
			serialize = SerializeType.HESSIAN_SERIALIZER;
        }

		SerializerHandler serializer = serializerMap.get(serialize);
        if (serializer == null) {
            throw new SerializeException("serialize error");
        }

        try {
            return serializer.serialize(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static <T> T deserialize(byte[] data, Class<T> clazz, String serializeType) {

        SerializeType serialize = SerializeType.queryByType(serializeType);
		// default serialize is hessian
		if (serialize == null) {
			serialize = SerializeType.HESSIAN_SERIALIZER;
		}
		SerializerHandler serializer = serializerMap.get(serialize);
        if (serializer == null) {
            throw new SerializeException("serialize error");
        }

        try {
            return serializer.deserialize(data, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
