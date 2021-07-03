### [Multithreading](https://docs.oracle.com/javase/tutorial/essential/concurrency/procthread.html)

## Contents

1. [Process based and Thread based](#process-based-and-thread-based)
2. [Thread States](#thread-states)
3. [Synchronization in Multithreading](#synchronization-in-multithreading)

### Process based and Thread based

- There are two distinct types of multitasking:
  - Process-based
  - Thread-based.
- **Process-based multitasking** is the feature that
  allows your computer to run two or more programs concurrently. For example, process based multitasking enables you to run the Java compiler at the same time that you are using a text editor or visiting a web site. In process-based multitasking, a program is the smallest
  unit of code that can be dispatched by the scheduler
- It is important to
  understand that Java’s multithreading features work in both types of systems, single-core and multi-core. In a single-core system, concurrently executing threads share the CPU, with each thread receiving a slice of CPU time. Therefore, in a single-core system, two or more threads do not actually
  run at the same time, but idle CPU time is utilized. However, in multi-core systems, it is possible for two or more threads to actually execute simultaneously. In many cases, this can further improve program efficiency and increase the speed of certain operations.

### Thread states

- Threads exist in several states. Here is a general description.

  - A thread can be **runnable**. It can be ready to run as soon as it gets CPU time.
  - A **running** thread can be **suspended**, which temporarily halts its activity. A suspended thread can then be resumed, allowing it to pick up where it left off. A thread can be blocked when waiting for a resource.
  - At any time, a thread can be **terminated**, which halts its execution immediately. Once terminated, a thread
    cannot be resumed.

    <img src="img\threadLifecycle.JPG"
     alt="thread life cycle"
     style="float: left; margin-right: 10px;" />
    _src-_ https://www.geeksforgeeks.org/lifecycle-and-states-of-a-thread-in-java/

- When a Java program starts up, one thread begins running immediately. This is usually
  called the main thread of your program, because it is the one that is executed when your
  program begins. The main thread is important for two reasons:
  - It is the thread from which other “child” threads will be spawned.
  - Often, it must be the last thread to finish execution because it performs various
    shutdown actions.

### Synchronization in Multithreading

- In a multi-threaded environment, a race condition occurs when two or more threads attempt to update **mutable shared data** at the same time.
- Synchronization is built around an internal entity known as the intrinsic lock or monitor lock. (The API specification often refers to this entity simply as a "monitor.")
- Every object has an intrinsic lock associated with it. By convention, a thread that needs exclusive and consistent access to an object's fields has to acquire the object's intrinsic lock before accessing them, and then release the intrinsic lock when it's done with them. A thread is said to own the intrinsic lock between the time it has acquired the lock and released the lock. As long as a thread owns an intrinsic lock, no other thread can acquire the same lock. The other thread will block when it attempts to acquire the lock.
- When a thread releases an intrinsic lock, a happens-before relationship is established between that action and any subsequent acquisition of the same lock.
- The synchronized keyword can be used on different levels:
  - Instance methods
  - Static methods
  - Code blocks

### Questions

<details>
<summary>How do you handle an unhandled exception in the thread ?</summary>
<br>
Exceptions are local to a thread, and your main thread or other thread doesn't actually see the run method. The call to join simply waits for it to be done. An exception that is thrown in a thread and never caught terminates it, which is why join returns on your main thread, but the exception itself is lost. We can use <strong>UncaughtExceptionHandler</strong>.
<br>
  <pre>
    Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread th, Throwable ex) {
            System.out.println("Uncaught exception: " + ex);
        }
    };
    Thread t = new Thread() {
        @Override
        public void run() {
            System.out.println("Sleeping ...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted.");
            }
            System.out.println("Throwing exception ...");
            throw new RuntimeException();
        }
    };
    t.setUncaughtExceptionHandler(h);
    t.start();
  </pre>
</details>

<details>
<summary>You have thread T1, T2, and T3. How will you ensure that thread T2 is run after T1 and thread T3 after T2 ?</summary>
<br>
It can be achieved by using the <strong>join</strong> method of Thread class.
</details>

<details>
<summary>What is the advantage of the new Lock interface over a synchronized block in Java? You need to implement a high-performance cache, which allows multiple readers, but how will you implement the single writer to keep the integrity?</summary>
<br>
The major advantage of lock interfaces on multithreaded and concurrent programming is that they provide two separate locks for reading and writing, which enables you to write high-performance data structures, like ConcurrentHashMap and conditional blocking.
</details>

<details>
<summary>What are differences between wait and sleep method in Java ?</summary>
<br>
The only major difference is to wait to release the lock or monitor, while sleep doesn't release any lock or monitor while waiting.
</details>

<details>
<summary>Write code to implement blocking queue in Java.</summary>
<br>
</details>

<details>
<summary>Write code to solve the produce consumer problem in Java.</summary>
<br>
https://www.java67.com/2015/12/producer-consumer-solution-using-blocking-queue-java.html
</details>

<details>
<summary>Write a program that will result in a deadlock. How will you fix deadlock in Java ?</summary>
<br>
https://javarevisited.blogspot.com/2018/08/how-to-avoid-deadlock-in-java-threads.html#axzz6zEd8GH3v
</details>

<details>
<summary>What is an atomic operation? What are atomic operations in Java?</summary>
<br>
https://javarevisited.blogspot.com/2011/04/synchronization-in-java-synchronized.html#axzz6zEd8GH3v
</details>

<details>
<summary>What is a volatile keyword in Java? How do you use it? How is it different from the synchronized method in Java?</summary>
<br>
</details>

<details>
<summary>What is a race condition? How will you find and solve race condition?</summary>
<br>
</details>

<details>
<summary>Why do we call start() method which in turns calls run() method, why not we directly call run() method?</summary>
<br>
</details>
<details>
<summary>How will you awake a blocked thread in Java?</summary>
<br>
</details>
<details>
<summary>What is the difference between CyclicBarriar and CountdownLatch in Java?</summary>
<br>
</details>
<details>
<summary>What is an immutable object? How does it help in writing a concurrent application?</summary>
<br>
</details>
<details>
<summary>Difference between executorservice, executors and ForkJoinPool</summary>
<br>
https://javarevisited.blogspot.com/2017/02/difference-between-executor-executorservice-and-executors-in-java.html#axzz6zEd8GH3v
https://javarevisited.blogspot.com/2016/12/difference-between-executor-framework-and-ForkJoinPool-in-Java.html#axzz6zEd8GH3v
</details>
