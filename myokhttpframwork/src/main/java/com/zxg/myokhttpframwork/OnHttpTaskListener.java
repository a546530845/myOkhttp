package com.zxg.myokhttpframwork;

/**
 * 一般数据请求
 * @param <T>
 */
public interface OnHttpTaskListener<T> {
	/**
	 * 执行Http Task前的预处理
	 */
	public void onPreTask();

	/**
	 * Http Task出现错误
	 */
	public void onTaskError(Exception throwable);

	/**
	 * Http Task成功执行结束
	 * 
	 * @param
	 */
	public void onFinishTask(T bean);
}
