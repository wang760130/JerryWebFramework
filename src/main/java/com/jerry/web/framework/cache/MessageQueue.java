package com.jerry.web.framework.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class MessageQueue {
	private BlockingQueue<Object> queue = new LinkedBlockingQueue<Object>(10);

	private BlockingQueue<Object> bufferQueue = new LinkedBlockingQueue<Object>();

	// 模拟数据库
	private BlockingQueue<Object> dbQueue = new LinkedBlockingQueue<Object>();

	private final AtomicBoolean isQueueAdded = new AtomicBoolean(true);

	private final ReentrantLock putLock = new ReentrantLock();

	public void add(Object o) {
		if (isQueueAdded.get()) {
			if (!queue.offer(o)) {
				addBuffer(o);
				isQueueAdded.set(false);
			}
		} else {
			addBuffer(o);
		}
	}

	private void addBuffer(Object o) {
		bufferQueue.offer(o);

		// 模拟向数据库写入
		if (bufferQueue.size() >= 20) {
			List<Object> ary = new ArrayList<Object>();
			bufferQueue.drainTo(ary);

			for (Object i : ary) {
				dbQueue.offer(i);
			}
		}
	}

	/**
	 * 先从queue开始消费，消费完了开始消费数据库缓存的，然后在消费bufferQueue队列中的
	 * 
	 * @return
	 */
	public Object poll() {
		Object o = queue.poll();
		if (o != null) {
			return o;
		}

		final ReentrantLock putLock = this.putLock;
		putLock.lock();
		try {
			List<Object> ary = new ArrayList<Object>(10);
			int s = dbQueue.drainTo(ary, 10);

			if (s == 0) {
				o = bufferQueue.poll();

				if (o == null) {
					isQueueAdded.set(true);
				}

				return o;
			} else {
				for (Object i : ary) {
					queue.offer(i);
				}

				return queue.poll();
			}

		} finally {
			putLock.unlock();
		}
	}

	public void display() {
		System.out.println(queue.size() + "\t" + bufferQueue.size() + "\t"
				+ dbQueue.size() + "\t"
				+ (queue.size() + bufferQueue.size() + dbQueue.size()));
	}
}