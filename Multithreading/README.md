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
