---
typora-copy-images-to: assets
---

# jvm

+ 定义： Java Virtual Machine ,即java程序的运行环境（java二进制字节码的运行环境）

+ JVM JRE JDK的区别

![image-20220216155640284](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220216155640284.png)

# 内存结构

+ 分为五大块：**堆，方法区，虚拟机栈，本地方法栈，程序计数器**

![image-20220216155713626](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220216155713626.png)

## 1.程序计数器

+ 作用：是记住线程中下一条JVM指令执行的地址
+ 线程私有
+ 不会存在内存溢出

## 2.虚拟机栈

+ 定义：每个线程运行所需要的内存，称为虚拟机栈，是线程私有的
+ 每个线程对应一个虚拟机栈
+ 每个虚拟机栈由每个栈帧组成，对应着每次方法调用时所占用的内存
+ 每个线程只能由一个活动栈帧，对应着正在执行的那个方法
+ 垃圾回收是否涉及栈内存？
  - **不需要**。因为虚拟机栈中是由一个个栈帧组成的，在方法执行完毕后，对应的栈帧就会被弹出栈。所以无需通过垃圾回收机制去回收内存。
+ 栈内存的分配越大越好吗？
  - 不是。因为**物理内存是一定的**，栈内存越大，可以支持更多的递归调用，但是可执行的线程数就会越少。
+ 方法内的局部变量是否是线程安全的？
  - 如果方法内**局部变量没有逃离方法的作用范围**，则是**线程安全**的
  - 如果如果**局部变量引用了对象**，并**逃离了方法的作用范围**，则需要考虑线程安全问题

#### 栈内存溢出（stackOverflowError）

+ 栈帧过多

+ 每个栈帧的内存占用过大

## 3.本地方法栈

+ 一些带有**native关键字**的方法就是需要JAVA去调用本地的C或者C++方法，因为JAVA有时候没法直接和操作系统底层交互，所以需要用到本地方法

## 4.堆

+ 定义：所有new关键词创建的对象都会被放在堆内存中
+ 所有线程共享，堆中的对象都需要考虑线程安全
+ 有垃圾回收机制
+ 堆溢出：**java.lang.OutofMemoryError**

## 5.方法区

![image-20220216162946779](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220216162946779.png)

+ 方法区是一种规范，永久代和元空间是对方法区的不同实现
+ JDK1.6 方法区的实现是永久代，占用的是JVM的内存，JDK1.8中方法区的实现是元空间，占用的是本地内存
+ StringTable本来是在方法区里面的，1.8后就在堆里面了，因为StringTable也需要垃圾回收
+ 永久代是1.8之前，用的是虚拟机中的堆的内存，元空间用的是操作系统的内存
+ 方法区逻辑上包含类，类加载，运行时常量池，

### 运行时常量池

+ **常量池**，就是一张表，虚拟机指令根据这张常量表找到要执行的类名、方法名、参数类型、字面量

等信息

+ **运行时常量池**，常量池是 *.class 文件中的，当该类被加载，它的常量池信息就会放入运行时常量

池，并把里面的符号地址变为真实地址

### StringTable(串池)

##### **串池**StringTable

**特征**

- 常量池中的字符串仅是符号，只有**在被用到时才会转化为对象**
- 利用串池的机制，来避免重复创建字符串对象
- 字符串**变量**拼接的原理是**StringBuilder**
- 字符串**常量**拼接的原理是**编译器优化**
- 可以使用**intern方法**，主动将串池中还没有的字符串对象放入串池中
- **注意**：无论是串池还是堆里面的字符串，都是对象

用来放字符串对象且里面的**元素不重复**

```
public class StringTableStudy {
	public static void main(String[] args) {
		String a = "a"; 
		String b = "b";
		String ab = "ab";
	}
}Copy
```

常量池中的信息，都会被加载到运行时常量池中，但这是a b ab 仅是常量池中的符号，**还没有成为java字符串**

```
0: ldc           #2                  // String a
2: astore_1
3: ldc           #3                  // String b
5: astore_2
6: ldc           #4                  // String ab
8: astore_3
9: returnCopy
```

当执行到 ldc #2 时，会把符号 a 变为 “a” 字符串对象，**并放入串池中**（hashtable结构 不可扩容）

当执行到 ldc #3 时，会把符号 b 变为 “b” 字符串对象，并放入串池中

当执行到 ldc #4 时，会把符号 ab 变为 “ab” 字符串对象，并放入串池中

最终**StringTable [“a”, “b”, “ab”]**

**注意**：字符串对象的创建都是**懒惰的**，只有当运行到那一行字符串且在串池中不存在的时候（如 ldc #2）时，该字符串才会被创建并放入串池中。

使用拼接**字符串变量对象**创建字符串的过程

```
public class StringTableStudy {
	public static void main(String[] args) {
		String a = "a";
		String b = "b";
		String ab = "ab";
		//拼接字符串对象来创建新的字符串
		String ab2 = a+b; 
	}
}Copy
```

反编译后的结果

```
	 Code:
      stack=2, locals=5, args_size=1
         0: ldc           #2                  // String a
         2: astore_1
         3: ldc           #3                  // String b
         5: astore_2
         6: ldc           #4                  // String ab
         8: astore_3
         9: new           #5                  // class java/lang/StringBuilder
        12: dup
        13: invokespecial #6                  // Method java/lang/StringBuilder."<init>":()V
        16: aload_1
        17: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String
;)Ljava/lang/StringBuilder;
        20: aload_2
        21: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String
;)Ljava/lang/StringBuilder;
        24: invokevirtual #8                  // Method java/lang/StringBuilder.toString:()Ljava/lang/Str
ing;
        27: astore        4
        29: returnCopy
```

通过拼接的方式来创建字符串的**过程**是：StringBuilder().append(“a”).append(“b”).toString()

最后的toString方法的返回值是一个**新的字符串**，但字符串的**值**和拼接的字符串一致，但是两个不同的字符串，**一个存在于串池之中，一个存在于堆内存之中**

```
String ab = "ab";
String ab2 = a+b;
//结果为false,因为ab是存在于串池之中，ab2是由StringBuffer的toString方法所返回的一个对象，存在于堆内存之中
System.out.println(ab == ab2);Copy
```

使用**拼接字符串常量对象**的方法创建字符串

```
public class StringTableStudy {
	public static void main(String[] args) {
		String a = "a";
		String b = "b";
		String ab = "ab";
		String ab2 = a+b;
		//使用拼接字符串的方法创建字符串
		String ab3 = "a" + "b";
	}
}Copy
```

反编译后的结果

```
 	  Code:
      stack=2, locals=6, args_size=1
         0: ldc           #2                  // String a
         2: astore_1
         3: ldc           #3                  // String b
         5: astore_2
         6: ldc           #4                  // String ab
         8: astore_3
         9: new           #5                  // class java/lang/StringBuilder
        12: dup
        13: invokespecial #6                  // Method java/lang/StringBuilder."<init>":()V
        16: aload_1
        17: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String
;)Ljava/lang/StringBuilder;
        20: aload_2
        21: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String
;)Ljava/lang/StringBuilder;
        24: invokevirtual #8                  // Method java/lang/StringBuilder.toString:()Ljava/lang/Str
ing;
        27: astore        4
        //ab3初始化时直接从串池中获取字符串
        29: ldc           #4                  // String ab
        31: astore        5
        33: returnCopy
```

- 使用**拼接字符串常量**的方法来创建新的字符串时，因为**内容是常量，javac在编译期会进行优化，结果已在编译期确定为ab**，而创建ab的时候已经在串池中放入了“ab”，所以ab3直接从串池中获取值，所以进行的操作和 ab = “ab” 一致。
- 使用**拼接字符串变量**的方法来创建新的字符串时，因为内容是变量，只能**在运行期确定它的值，所以需要使用StringBuilder来创建**

##### intern方法 1.8

调用字符串对象的intern方法，会将该字符串对象尝试放入到串池中

- 如果串池中没有该字符串对象，则放入成功
- 如果有该字符串对象，则放入失败

无论放入是否成功，都会返回**串池中**的字符串对象

**注意**：此时如果调用intern方法成功，堆内存与串池中的字符串对象是同一个对象；如果失败，则不是同一个对象

**例1**

```
public class Main {
	public static void main(String[] args) {
		//"a" "b" 被放入串池中，str则存在于堆内存之中
		String str = new String("a") + new String("b");
		//调用str的intern方法，这时串池中没有"ab"，则会将该字符串对象放入到串池中，此时堆内存与串池中的"ab"是同一个对象
		String st2 = str.intern();
		//给str3赋值，因为此时串池中已有"ab"，则直接将串池中的内容返回
		String str3 = "ab";
		//因为堆内存与串池中的"ab"是同一个对象，所以以下两条语句打印的都为true
		System.out.println(str == st2);
		System.out.println(str == str3);
	}
}Copy
```

**例2**

```
public class Main {
	public static void main(String[] args) {
        //此处创建字符串对象"ab"，因为串池中还没有"ab"，所以将其放入串池中
		String str3 = "ab";
        //"a" "b" 被放入串池中，str则存在于堆内存之中
		String str = new String("a") + new String("b");
        //此时因为在创建str3时，"ab"已存在与串池中，所以放入失败，但是会返回串池中的"ab"
		String str2 = str.intern();
        //false
		System.out.println(str == str2);
        //false
		System.out.println(str == str3);
        //true
		System.out.println(str2 == str3);
	}
}Copy
```

##### intern方法 1.6

调用字符串对象的intern方法，会将该字符串对象尝试放入到串池中

- 如果串池中没有该字符串对象，会将该字符串对象复制一份，再放入到串池中
- 如果有该字符串对象，则放入失败

无论放入是否成功，都会返回**串池中**的字符串对象

**注意**：此时无论调用intern方法成功与否，串池中的字符串对象和堆内存中的字符串对象**都不是同一个对象**

### StringTable 垃圾回收

StringTable在内存紧张时，会发生垃圾回收

### StringTable调优

- 因为StringTable是由HashTable实现的，所以可以**适当增加HashTable桶的个数**，来减少字符串放入串池所需要的时间

  ```
  -XX:StringTableSize=xxxxCopy
  ```

- 考虑是否需要将字符串对象入池

  可以通过**intern方法减少重复入池**

# 垃圾回收

+ 引用计数法
  + 当对象被引用的时候，计数就加1，不引用就减1，但是无法解决循环依赖的问题
  + JVM没有用到引用计数法

+ 可达性分析

  + 根对象：肯定不能被当做垃圾的对象

  + 扫描堆中对象，判断该对象是否是根对象或者与根对象有关，如果没有关系的话，就进行垃圾回收
  + 官方：扫描堆中的对象，看是否能在GC root为起点的引用链中找到该对象，找不到，就说明该对象可以被回收
  + JVM中垃圾回收就是用的可达性分析

+ 哪些对象可以作为GC root
  + 虚拟机栈中变量所引用的对象
  + 本地方法栈中引用的对象
  + 方法区中常量引用的对象
  + 方法区中类静态属性引用的对象

## java里面有哪几种引用吗

+ 强引用，软引用，弱引用，虚引用        
+ JAVA的GC机制再判断对象是否需要回收的时候，就需要依据“引用”这个概念
+ 强引用
  + Java中默认声明的就是强引用
  + **String** s; 这个仅仅是创建了一个引用，并不一定要和对象绑定在一起
  + **String s = new String("s")** 此时便把该引用指向对象，就可以通过该引用来操作对象了
  + 如果想中断强引用与对象之间的联系，可以显示的将强引用赋值为null，这样一来，JVM就可以适时的回收对象了

+ 软引用

  + 在内存足够的时候，软引用对象不会被回收，只有在内存不足时，系统则会回收软引用对象，如果回收了软引用对象之后仍然没有足够的内存，才会抛出内存溢出异常

  + 引用队列，ReferenceQueue

    ```java
            ReferenceQueue<byte[]> referenceQueue = new ReferenceQueue<>();
            SoftReference<byte[]> softReference = new SoftReference<byte[]>(new byte[4*1024*1024],referenceQueue);
    ```

  + 当软引用所关联的对象被回收时，软引用自己就会加入引用队列中去

  + ```java
           ArrayList<Reference> references = new ArrayList<>();
           ReferenceQueue<byte[]> referenceQueue = new ReferenceQueue<>();
           for (int i = 0; i < 5; i++) {
               SoftReference<byte[]> softReference = new SoftReference<byte[]>(new byte[4*1024*1024],referenceQueue);
               references.add(softReference);
         
           }
           
           Reference<? extends byte[]> poll = referenceQueue.poll();
           while (poll != null){
               references.remove(poll);
               poll = referenceQueue.poll();
           }
       ```
    ```
  
  + 可以配合引用队列，把引用对象为null的软引用从list中给去掉
    ```

+ 弱引用
  + 无论内存是否足够，只要 JVM 开始进行垃圾回收，那些被弱引用关联的对象都会被回收
  + 弱引用与软引用差不多，也可以用引用队列

+ 虚引用

  + 必须配合引用队列使用

## 垃圾回收具体算法

+ 标记清除

  + 将不在GC root 链上的对象进行标记，然后进行“清除”

  + ![image-20220210164351784](typora-user-images\image-20220210164351784.png)
  + 优点：只需要把某一块地址标记为可覆盖就行，不需要把该内存清零，速度很快
  + 缺点：可能会造成内存的碎片化

+ 标记整理

  + 第一步和上面一样，也是标记
  + ![image-20220210164728393](typora-user-images\image-20220210164728393.png)
  + 多了个整理得过程，不会出现碎片内存，缺点就是整理牵扯到对象内存的移动，效率会受到影响

+ 复制

  ![image-20220210164929366](typora-user-images\image-20220210164929366.png)

  + 两块区域，在移动的过程中进行整理，并且速度相对于标记整理较快，但空间要求高，需要占用双倍的内存空间

+ 这三种算法JVM会根据不同的情况采用不同的回收算法

## 分代回收

+ ![image-20220210185348624](typora-user-images\image-20220210185348624.png)

+ 新生代（伊甸园，幸存区from，幸存区to），老年代

+ 新生代：生命周期比较短，垃圾回收比较频繁，频率高
+ 新生代与老年代就相当于 平时吃午饭和周末聚餐，一个频率低，一个高
+ 新创建的对象被分配在新生代的伊甸园中，当伊甸园满了以后，触发一次GC，叫做Minor GC
+ 在Minor GC后，伊甸园和幸存区from中存活的对象会**复制**进幸存区to，并把寿命加1，并且交换from和to两个区域
+ 当幸存区的对象寿命超过一定阈值（最大寿命是15 4BIT存储）后，就会放入老年代，Minor gc 并不会对老年代进行回收
+ 当老年代的空间不足的时候，会触发一次FULL GC

+ Minor GC 会触发stop the world，即会暂停用户的线程，因为GC中进行复制的时候，对象的地址会改变，如果多个线程同时运行的话，会造成混乱

+ 当老年代空间不足的时候，回先触发一次minor gc ,空间仍然不足的话，会触发full gc,
+ FULL GC的STW时间更长
+ 一个线程的堆溢出不会影响主线程的运行

## 相关VM参数

![image-20220210191405066](typora-user-images\image-20220210191405066.png)

## 垃圾回收器

+ 串行

  + 单线程，运行的时候其它线程都暂停，等垃圾回收完毕其他线程再运行

  + 适用于堆内存比较小，适合个人电脑
  + 新生代采用复制的算法，老年代采用标记整理得算法

+ 吞吐量优先（让单位时间内的STW时间最短）

  + 多线程
  + 适用于堆内存较大，多核CPU

+ 响应时间优先(让STW的时间最短)

  + 多线程
  + 适用于堆内存较大，多核CPU


## G1,CMS

+ Garbage First

+ Concurrent Mark Sweep
+ 两个划时代的垃圾回收器

## GC调优

+ 低延迟还是高吞吐量，选择合适的回收器

+ 高吞吐量选择ParallelGC
+ 低延迟选择CMS,G1,ZGC

+ 最快的GC是不发生GC，先检查是不是自己代码的问题导致频繁GC
  + 数据是不是太多
  + 数据的表示是不是太臃肿
  + 是否存在内存泄漏
    + 就是申请了内存，但是没有释放，导致内存空间浪费。通俗说法就是有人占着茅坑不拉屎

# 类的加载和字节码

## 语法糖

+ 所谓的语法糖，就是java编译器把.java文件编译成.class文件的过程中，自动生成或转化一些代码，主要是减轻程序员的负担的，算是编译器给的一些福利（给糖吃），**在编译期间处理**

+ **默认构造器**：类中没有任何构造器的情况下，会默认生成一个无参构造
+ **自动拆装箱**：

```java
Integer x = 1;	
//Integer.valueOf();
int y = x;
//y = x.intValue;
```

+ **泛型擦除**：即泛型信息在便以为字节码之后就丢失了，实际的类型都当做object类型来处理

+ **可变参数**：

+ **foreach循环**（增强for循环）：如果遍历的是数组，语法糖转化成普通for循环，如果遍历的是集合，则语法糖转化成iterator迭代器
+ **Switch**：从JDK7开始，Switch就可以用于字符串和枚举类，这个功能其实也是语法糖,字符串的Switch 转化成两个整数的Switch

+ **枚举类**：单例

## 类的加载

+ 类的加载分为三个阶段：
  + 加载
  + 链接
  + 初始化
+ **类加载**：将类的字节码加入到方法区中，内部采用C++的instanceKlass描述java类

+ 如果一个类的父类还没有加载，则会先加载父类
+ ![image-20220216143347903](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220216143347903.png)

+ 将类的字节码载入方法区中，内部采用 C++ 的 instanceKlass 描述 java 类，它的重要 fifield 有：

  + **_java_mirror** 即 java 的类镜像，例如对 String 来说，就是 String.class，作用是把 klass 暴

    露给 java 使用

  + **_super** 即父类

  + **_fields** 即成员变量

  + **_methods** 即方法

  + **_constants** 即常量池
  + **_class_loader** 即类加载器
  + **_vtable** 虚方法表
  + **_itable** 接口方法表

+ **类链接**
  + 验证：
  + 准备：为静态变量分配空间，设置默认值
  + 解析：

+ **初始化**：初始化即调用 <cinit>()V 

## 类加载器

| 名称                                      | 加载的类              | 说明                            |
| ----------------------------------------- | --------------------- | ------------------------------- |
| Bootstrap ClassLoader（启动类加载器）     | JAVA_HOME/jre/lib     | 无法直接访问                    |
| Extension ClassLoader(拓展类加载器)       | JAVA_HOME/jre/lib/ext | 上级为Bootstrap，**显示为null** |
| Application ClassLoader(应用程序类加载器) | classpath             | 上级为Extension                 |
| 自定义类加载器                            | 自定义                | 上级为Application               |

+ 扩展类加载器在JAVA代码中显示上级为null，因为启动类加载器是用C++写的，无法用java代码访问
+ 双亲委派机制（这里的双亲，翻译成上级更好）

+ 就是指调用类加载器的 loadClass 方法时，查找类的规则，先看上级类加载器有没有加载，没有才能加载

```java
protected Class<?> loadClass(String name, boolean resolve)
    throws ClassNotFoundException
{
    synchronized (getClassLoadingLock(name)) {
        // 首先查找该类是否已经被该类加载器加载过了
        Class<?> c = findLoadedClass(name);
        //如果没有被加载过
        if (c == null) {
            long t0 = System.nanoTime();
            try {
                //看是否被它的上级加载器加载过了 Extension的上级是Bootstarp，但它显示为null
                if (parent != null) {
                    c = parent.loadClass(name, false);
                } else {
                    //看是否被启动类加载器加载过
                    c = findBootstrapClassOrNull(name);
                }
            } catch (ClassNotFoundException e) {
                // ClassNotFoundException thrown if class not found
                // from the non-null parent class loader
                //捕获异常，但不做任何处理
            }

            if (c == null) {
                //如果还是没有找到，先让拓展类加载器调用findClass方法去找到该类，如果还是没找到，就抛出异常
                //然后让应用类加载器去找classpath下找该类
                long t1 = System.nanoTime();
                c = findClass(name);

                // 记录时间
                sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                sun.misc.PerfCounter.getFindClasses().increment();
            }
        }
        if (resolve) {
            resolveClass(c);
        }
        return c;
    }
}
```

+ **线程上下文加载器**：SPI

