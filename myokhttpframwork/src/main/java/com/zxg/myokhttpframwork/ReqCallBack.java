package com.zxg.myokhttpframwork;

/**
 * 一般数据请求
 * @param <T>
 */
public interface ReqCallBack<T> {
	/**
	 * 执行Http Task前的预处理
	 */
	public void onPreTask();

	/**
	 * Http Task出现错误
	 */
	public void failedCallBack(Exception throwable);

	/**
	 * Http Task成功执行结束
	 * 
	 * @param
	 */
	public void successCallBack(T bean);
}
