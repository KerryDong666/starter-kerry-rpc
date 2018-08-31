package com.kerry.rpc.framework.serializer.util;

import com.kerry.rpc.framework.serializer.SerializerHandler;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author kerry dong
 * @date 2018/7/29
 */
public class ProtoStuffSerializer implements SerializerHandler {

	private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();

	private static Objenesis objenesis = new ObjenesisStd(true);

	/**
	 * 获取类的schema
	 * @param cls
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <T> Schema<T> getSchema(Class<T> cls) {
		Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
		if (schema == null) {
			schema = RuntimeSchema.createFrom(cls);
			if (schema != null) {
				cachedSchema.put(cls, schema);
			}
		}
		return schema;
	}

	/**
	 * 反序列化:
	 * 	如果一个类没有参数为空的构造方法时候，那么你直接调用newInstance方法试图得到一个实例对象的时候是会抛出异常的,通过ObjenesisStd可以完美的避开这个问题
	 * @param data 需要烦序列化的数据
	 * @param clz 反序列化后类型
	 * @param <T>
	 * @return
	 */
	@Override
	public <T> T deserialize(byte[] data, Class<T> clz) {
		try {
			//实例化
			T message = (T) objenesis.newInstance(clz);
			//获取类的schema
			Schema<T> schema = getSchema(clz);
			ProtostuffIOUtil.mergeFrom(data, message, schema);
			return message;
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}


	/**
	 * 序列化（对象 -> 字节数组）
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> byte[] serialize(T obj) {
		Class<T> cls = (Class<T>) obj.getClass();
		LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		try {
			Schema<T> schema = getSchema(cls);
			//序列化
			return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		} finally {
			buffer.clear();
		}
	}
}
