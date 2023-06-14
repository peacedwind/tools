package com.oetsky.project.observer;

public abstract class Subject {

	//增加观察者方法
	public abstract void addObserver(SerialObserver observer) ;

	//删除观察者方法
	public abstract void removeObserver(SerialObserver observer) ;
	/**
	 * 通知观察者方法
	 * @param longTime 时间翟
	 * @param obj 数据
	 */
	public abstract void notifyObserver(Long longTime,Object obj);
}
