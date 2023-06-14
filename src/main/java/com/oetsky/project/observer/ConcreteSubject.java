package com.oetsky.project.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式接口类
 *
 * @author xiangzc
 * @date 2021-07-06
 */
public class ConcreteSubject extends Subject {

	private List<SerialObserver> observers ;

	public ConcreteSubject (){
		observers = new ArrayList<SerialObserver>();
	}

	public List<SerialObserver> getObservers() {
		return observers;
	}

	public void setObservers(List<SerialObserver> observers) {
		this.observers = observers;
	}

	@Override
	public void addObserver(SerialObserver observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(SerialObserver observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObserver(Long longTime,Object msg){
		for (Object object : observers) {
			((SerialObserver) object).response(longTime,msg);
		}
	}
}
