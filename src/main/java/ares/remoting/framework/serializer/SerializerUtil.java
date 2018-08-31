package ares.remoting.framework.serializer;


import ares.remoting.framework.serializer.util.HessianSerializer;
import ares.remoting.framework.serializer.util.JavaSerializer;
import ares.remoting.framework.serializer.util.JsonSerializer;
import ares.remoting.framework.serializer.util.ProtoStuffSerializer;

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
        if (serialize == null) {
            throw new RuntimeException("serialize is null");
        }

		SerializerHandler serializer = serializerMap.get(serialize);
        if (serializer == null) {
            throw new RuntimeException("serialize error");
        }

        try {
            return serializer.serialize(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static <T> T deserialize(byte[] data, Class<T> clazz, String serializeType) {

        SerializeType serialize = SerializeType.queryByType(serializeType);
        if (serialize == null) {
            throw new RuntimeException("serialize is null");
        }
		SerializerHandler serializer = serializerMap.get(serialize);
        if (serializer == null) {
            throw new RuntimeException("serialize error");
        }

        try {
            return serializer.deserialize(data, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
