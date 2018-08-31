package com.kerry.rpc.framework.model;


import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 服务注册中心的服务提供者注册信息
 *
 * @author kerry dong
 */
public class ProviderService implements Serializable {

	/**
	 * 服务接口
	 */
	private Class<?> serviceInterface;
	/**
	 * 服务实现
	 */
	private transient Object serviceObject;
	/**
	 * 服务方法
	 */
	private transient Method serviceMethod;
	/**
	 * 服务ip
	 */
	private String serverIp;
	/**
	 * 服务端口
	 */
	private int serverPort;
	/**
	 * 服务超时时间
	 */
	private long timeout;
	/**
	 * 权重,用于负载均衡
	 */
	private int weight;
	/**
	 * 服务端线程数
	 */
	private int workerThreads;
	/**
	 * 服务提供者唯一标识
	 */
	private String appKey;
	/**
	 * 服务分组组名
	 */
	private String groupName;

	public ProviderService copy() {
		ProviderService providerService = new ProviderService();
		providerService.setServiceItf(serviceInterface);
		providerService.setServiceObject(serviceObject);
		providerService.setServiceMethod(serviceMethod);
		providerService.setServerIp(serverIp);
		providerService.setServerPort(serverPort);
		providerService.setTimeout(timeout);
		providerService.setWeight(weight);
		providerService.setWorkerThreads(workerThreads);
		providerService.setAppKey(appKey);
		providerService.setGroupName(groupName);
		return providerService;
	}

	public Class<?> getServiceItf() {
		return serviceInterface;
	}

	public void setServiceItf(Class<?> serviceItf) {
		this.serviceInterface = serviceItf;
	}

	public Object getServiceObject() {
		return serviceObject;
	}

	public void setServiceObject(Object serviceObject) {
		this.serviceObject = serviceObject;
	}

	public Method getServiceMethod() {
		return serviceMethod;
	}

	public void setServiceMethod(Method serviceMethod) {
		this.serviceMethod = serviceMethod;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getWorkerThreads() {
		return workerThreads;
	}

	public void setWorkerThreads(int workerThreads) {
		this.workerThreads = workerThreads;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
