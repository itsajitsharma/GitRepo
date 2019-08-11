package com.rajeev.test.locks;

public class ReadWriteAccessLock {

	private volatile int readers = 0;

	private volatile int writers = 0;

	public synchronized void setReadAccess() throws InterruptedException {

		while (writers > 0) {

			wait();

		}

		readers++;

	}

	public synchronized void realeaseReadAccess() {

		readers--;
		System.out.println("Releasing the read");

		notifyAll();

	}

	public synchronized void setWriteAccess() throws InterruptedException {

		while (readers > 0 || writers > 0) {

			wait();

		}

		writers++;

	}

	public synchronized void releaseWriteAccess() throws InterruptedException {

		writers--;
		System.out.println("Releasing the write");

		notifyAll();

	}

}