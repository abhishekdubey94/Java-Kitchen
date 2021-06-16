### Multithreading

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
    ![](img\threadLifecycle.JPG)
    <img src="img\threadLifecycle.JPG"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />
- When a Java program starts up, one thread begins running immediately. This is usually
  called the main thread of your program, because it is the one that is executed when your
  program begins. The main thread is important for two reasons:
  - It is the thread from which other “child” threads will be spawned.
  - Often, it must be the last thread to finish execution because it performs various
    shutdown actions.
