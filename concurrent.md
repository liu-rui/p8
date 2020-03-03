1. 大纲
目标，高级教程，从源码角度分析并发编程并总结出常用的模式
1. 线程
1.1. 线程状态
线程一共有6中状态
NEW
RUNNABLE
BLOCKED
WAITING
TIMED_WAITING
TERMINATED


参考： 
https://blog.csdn.net/pange1991/article/details/53860651




object: wait/notify
每个对象对应着一个对象监视器(object monitor，有的地方成为对象锁(object lock)），锁队列（entry set）和等待队列（wait set）。
锁队列中的线程状态为BLOCKED，等待队列中的线程状态为WAITING或者TIMED_WAITING。


总结下：
（1）所有期待获得锁的线程，在锁已经被其它线程拥有的时候，这些期待获得锁的线程就进入了Object Lock的entry set区域。
（2）所有曾经获得过锁，但是由于其它必要条件不满足而需要wait的时候，线程就进入了Object Lock的wait set区域 。
（3）在wait set区域的线程获得Notify/notifyAll通知的时候，随机的一个Thread（Notify）或者是全部的Thread（NotifyALL）从Object Lock的wait set区域进入了entry set中。
（4）在当前拥有锁的线程释放掉锁的时候，处于该Object Lock的entryset区域的线程都会抢占该锁，但是只能有任意的一个Thread能取得该锁，而其他线程依然在entry set中等待下次来抢占到锁之后再执行。

理论基础   
线程安全：当多个线程访问同一个对象时，如果不用考虑这些线程在运行时环境下的调度和交替运行，也不需要进行额外的同步，或者在调用方进行任何其他的协调操作，调用这个对象的行为都可以获取正确的结果，那这个对象是线程安全的。

内存模型 

目标：定义程序中各个变量的访问规则，即在虚拟机中将变量存储到内存和从内存中取出变量这样底层细节；JMM是一种规范，目的是解决由于多线程通过共享内存进行通信时，存在的本地内存数据不一致、编译器会对代码指令重排序、处理器会对代码乱序执行等带来的问题。目的是保证并发编程场景中的原子性、可见性和有序性。
这里的变量只包括实例字段，静态字段和数组对象；局部变量属于线程私有。

JVM内存模型包括：
(1) 程序计数器。一块很小的内存空间，用于记录下一条要运行的指令。是线程私有的内存。
(2)java虚拟机栈。它和java线程同一时间创建，保存了局部变量、部分结果，并参与方法的调用和返回。是线程私有的内存。
  
(3)本地方法栈。它和java虚拟机栈的功能相似，主要为Native方法服务。是线程私有的内存。
(4)java堆。为所有创建的对象和数组分配内存空间。是线程共有的内存。
(5)方法区。也被称为永久区，与堆空间相似。是线程共有的内存。

对于方法区，Java8之后的变化：
移除了永久代（PermGen），替换为元空间（Metaspace）；
永久代中的 class metadata 转移到了 native memory（本地内存，而不是虚拟机）；
永久代中的 interned Strings 和 class static variables 转移到了 Java heap；
永久代参数 （PermSize MaxPermSize） -> 元空间参数（MetaspaceSize MaxMetaspaceSize）
参考：
https://blog.csdn.net/laomo_bible/article/details/83067810


操作：
lock（锁定）：作用于主内存的变量，把一个变量标识为一条线程独占状态。
unlock（解锁）：作用于主内存变量，把一个处于锁定状态的变量释放出来，释放后的变量才可以被其他线程锁定。
read（读取）：作用于主内存变量，把一个变量值从主内存传输到线程的工作内存中，以便随后的 load 动作使用
load（载入）：作用于工作内存的变量，把 read 操作从主内存中得到的变量值放入工作内存的变量副本中。
use（使用）：作用于工作内存的变量，把工作内存中的一个变量值传递给执行引擎，每当虚拟机遇到一个需要使用变量的值的字节码指令时将会执行这个操作。
assign（赋值）：作用于工作内存的变量，它把一个从执行引擎接收到的值赋值给工作内存的变量，每当虚拟机遇到一个给变量赋值的字节码指令时执行这个操作。
store（存储）：作用于工作内存的变量，把工作内存中的一个变量的值传送到主内存中，以便随后的 write 的操作。
write（写入）：作用于主内存的变量，它把 store 操作从工作内存中一个变量的值传送到主内存的变量中。
原语：
1 不允许 read 和 load、store 和write 操作之一单独出现
2 不允许一个线程丢弃它的最近 assign 的操作，即变量在工作内存中改变了之后必须同步到主内存中。
3 不允许一个线程无原因地（没有发生过任何 assign 操作）把数据从工作内存同步回主内存中。
4 一个新的变量只能在主内存中诞生，不允许在工作内存中直接使用一个未被初始化（load 或 assign）的变量。即对一个变量实施 use 和 store 操作之前，必须先执行过了 assign 和 load 操作。
5 一个变量在同一时刻只允许一条线程对其进行 lock 操作，lock 和 unlock 必须成对出现
6 如果对一个变量执行 lock 操作，将会清空工作内存中此变量的值，在执行引擎使用这个变量前需要重新执行 load 或 assign 操作初始化变量的值
7 如果一个变量事先没有被 lock 操作锁定，则不允许对它执行 unlock 操作；也不允许去 unlock 一个被其他线程锁定的变量
8 对一个变量执行 unlock 操作之前，必须先把此变量同步到主内存中（执行 store 和 write 操作）

3大性质：原子性，可见性和有序性
原子性： 8个原语是原子性的。保证原子性，提供了两个高级的字节码指令monitorenter和monitorexit
可见性： 要求一个线程修改了主内存中的值之后，其它的线程能立即得知这个修改
有序性：主要体现在在单线程时逻辑上的有序

> **synchronized: 具有原子性，有序性和可见性**；
> **volatile：具有有序性和可见性**

重排序：为了提高性能，编译器和处理器常常会对指令进行重排序；一般分为3中：
1. 编译器优化的重排序。编译器在不改变单线程程序语义的前提下，可以重新安排语句的执行顺序；
2. 指令级并行的重排序。现代处理器采用了指令级并行技术来将多条指令重叠执行。如果**不存在数据依赖性**，处理器可以改变语句对应机器指令的执行顺序；
3. 内存系统的重排序。由于处理器使用缓存和读/写缓冲区，这使得加载和存储操作看上去可能是在乱序执行的。
happens-before
JSR-133使用happens-before的概念来指定两个操作之间的执行顺序。由于这两个操作可以在一个线程之内，也可以是在不同线程之间。因此，**JMM可以通过happens-before关系向程序员提供跨线程的内存可见性保证**（如果A线程的写操作a与B线程的读操作b之间存在happens-before关系，尽管a操作和b操作在不同的线程中执行，但JMM向程序员保证a操作将对b操作可见）。具体的定义为：

happens-before规则
1. 程序顺序规则：一个线程中的每个操作，happens-before于该线程中的任意后续操作。
2. 监视器锁规则：对一个锁的解锁，happens-before于随后对这个锁的加锁。
3. volatile变量规则：对一个volatile域的写，happens-before于任意后续对这个volatile域的读。
4. 传递性：如果A happens-before B，且B happens-before C，那么A happens-before C。
5. start()规则：如果线程A执行操作ThreadB.start()（启动线程B），那么A线程的ThreadB.start()操作happens-before于线程B中的任意操作。
6. join()规则：如果线程A执行操作ThreadB.join()并成功返回，那么线程B中的任意操作happens-before于线程A从ThreadB.join()操作成功返回。
7. 程序中断规则：对线程interrupted()方法的调用先行于被中断线程的代码检测到中断时间的发生。
7. 对象finalize规则：一个对象的初始化完成（构造函数执行结束）先行于发生它的finalize()方法的开始。


参考： https://www.cnblogs.com/YJK923/p/10478716.html


synchronized

CAS    
CAS的实现需要硬件指令集的支撑，在JDK1.5后虚拟机才可以使用处理器提供的CMPXCHG指令实现。
处理器指令CMPXCHG；比如linux内核中的cmpxchg函数签名为：
cmpxchg(void *ptr, unsigned long old, unsigned long new);
功能是：将old和ptr指向的内容比较，如果相等，则将new写入到ptr中，返回old，如果不相等，则返回ptr指向的内容。

|锁|优点|缺点|使用场景|
|---|---|---|---|
|CAS|竞争的线程不需要阻塞，没有线程切换消耗，通过了程序的响应速度 |始终得不到锁的竞争线程会消耗cpu|追求响应时间；同步块执行速度非常快|
|重量级锁|线程竞争不使用自旋，不会消耗cpu|线程阻塞，响应时间慢|最求吞吐量；同步块执行速度较长|

volatile
被volatile修饰的变量能够保证每个线程能够获取该变量的最新值，从而避免出现数据脏读的现象
在生成汇编代码时会在volatile修饰的共享变量进行写操作的时候会多出**Lock前缀的指令**（具体的大家可以使用一些工具去看一下，这里我就只把结果说出来）。我们想这个**Lock**指令肯定有神奇的地方，那么Lock前缀的指令在多核处理器下会发现什么事情了？主要有这两个方面的影响：
1. 将当前处理器缓存行的数据写回系统内存；
2. 这个写回内存的操作会使得其他CPU里缓存了该内存地址的数据无效
结论：
1. Lock前缀的指令会引起处理器缓存写回内存；
2. 一个处理器的缓存回写到内存会导致其他处理器的缓存失效；
3. 当处理器发现本地缓存失效后，就会从内存中重读该变量数据，即可以获取当前最新值。

ThreadLocal

juc
锁，并发容器，原子操作，线程池，并发工具
并发基础类Unsafe 
锁
ReentrantLock
ReentrantReadWriteLock
Condition
AbstractQueuedSynchronizer（AQS）
LockSupport
StampedLock

原子操作类
AtomicBoolean
AtomicInteger
AtomicLong
AtomicIntegerArray
AtomicLongArray
AtomicReferenceArray
AtomicReference
AtomicReferenceFieldUpdater
AtomicMarkableReference
AtomicIntegeFieldUpdater
AtomicLongFieldUpdater
AtomicStampedReference
DoubleAdder
LongAdder
DoubleAccumulator
LongAccumulator

并发容器
ConcurrentMap
ConcurrentHashmap，ConcurrentSkipListMap
CopyOnWriteArraySet
ConcurrentSkipListSet


ConcurrentLinkedQueue
CopyOnWriteArrayList的简介
BlockingQueue
ArrayBlockingQueue, DelayQueue,LinkedBlockingQueue, LinkedTransferQueue, PriorityBlockingQueue, SynchronousQueue
BlockingDeque
LinkedBlockingDeque


并发工具
CountDownLatch
CyclicBarrier
Semaphore
Exchanger
ThreadLocalRandom
TimeUnit


线程池
Callable
Future
FutureTask
1. ThreadPoolExecutor

ScheduledThreadPoolExecutor
2. ExecutorCompletionService
3. RecursiveTask

模式
Single Threaded Execution模式
Immutable模式 不可变对象
Guarded Suspension模式 保护性暂挂
Balking模式
Producer-Consumer模式
Read-Write Lock模式
Thread-Per-Message模式
Worker Thread模式
Future模式
Two-Phase Termination模式 两阶段终止
Thread-Specific Storage模式 线程特有存储
Active Object模式
 Promise（承诺）模式 
 Serial Thread Confinement（串行线程封闭）模式
 Thread Pool（线程池）模式
 Master-Slave（主仆）模式 
  Pipeline（流水线）模式
  Half-sync/Half-async（半同步/半异步）模式
试题




参考：
书：
图解Java多线程设计模式
Java多线程编程实战指南（设计模式篇）
源码分析：
https://github.com/wupeixuan/JDKSourceCode1.8
https://github.com/liu-rui/LearningJDK

