## 1. 概览



### 1.1 Java8的核心思想

- Stream API
- 向方法传递代码的技巧
- 接口中的默认方法



### 1.2 Java中的函数

> 把函数作为一等公民

- 方法应用 ( :: )
- Lambda — 匿名函数





### 1.3 流

几乎每个Java应用都会**制造**和**处理**集合。但集合用起来并不总是那么理想。



- 外部迭代
- 内部迭代



>- Collection: 主要是为了存储和访问数据
>- Stream: 主要用于描述对数据的计算



### 1.4 默认方法

> 问题：如何改变已发布的接口而不破坏已有的实现呢？

接口如今可以包含实现类没有提供实现的方法签名了。缺失的方法主体随接口提供（就是默认实现），而不是由实现类提供。这就给接口设计者提供了一个扩充接口的方式，而不会破坏现有的代码。Java 8在接口声明中使用新的default关键字来表示这一点。



## 2. 通过行为参数化传递代码

**行为参数化**就是可以帮助你处理频繁变更需求的一种软件开发模式。一言以蔽之，它意味着拿出一个代码块，把它准备好却不去执行它。这个代码块以后可以被你程序的其他部分调用，这表明你可以推迟这块代码的执行。



### 2.1 初试牛刀：筛选绿苹果



### 2.2  再展身手：把颜色作为参数（加入一个新的维度）



### 2.3 第三次尝试：对你能想到的每个属性做筛选



### 2.4 第四次尝试：根据抽象条件筛选



### 2.5 第五次尝试：使用匿名类



### 2.6 第六次尝试：使用Lambda表达式



### 2.7 第七次尝试：将List类型抽象化





## 3. Lambda表达式

基本语法

> // 表达式
>
> (parameters)	->	expression
>
> // 代码块
>
> (parameters)	->	{ statements;  } 



- 参数列表
- 箭头
- Lambda主体



示例：

```java
// 布尔表达式
(List<String> list) -> list.isEmpty()
  
// 创建对象
() -> new Apple(10)
  
// 消费一个对象
(Apple a) -> {
  System.out.println(a.getWeight());
}

// 从一个对象中选择/抽取
(String s) -> s.length()
  
// 组合两个值
(int a, int b) -> a * b
  
// 比较两个对象
(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())

```



### 3.4 使用函数式接口



**异常、Lambda、函数式接口的关系？**

> 任何函数式接口都不允许抛出受检异常（checked exception）。如果你需要Lambda表达式来抛出异常，有两种方法：
>
> - 定义一个自己的函数式接口，并声明受检异常
> - 把Lambda包在一个try/catch块中

```java
@FuntionalInterface
public interface BufferedReaderProcessor {
  String process(BufferedReader br) throws IOException;
}

BufferedReaderProcessor p = (BufferedReader br) -> br.readLine();
```

但是你可能是在使用一个接受函数式接口的API，比如Function<T, R>，没有办法自己创建一个。这种情况下，你可以显示捕捉受检异常。

```java
Function<BufferedReader, String> f = (BufferedReader br) -> {
  try {
    return br.readLine();
  } catch (IOException e) {
    throw new RuntimeException(e);
  }
};
```



### 3.5 类型检查、类型推断以及限制

Lambda的类型是从使用Lambda的上下文推断出来的。上下文（比如，接受它传递的方法的参数，或接受它的值的局部变量）中Lambda表达式需要的类型称为**目标类型**。



特殊的void兼容规则

> 如果一个Lambda的主体是一个语句表达式，它就和一个返回void的函数描述符兼容（当然前提条件是参数列表也需要兼容）。



**使用局部变量**

自由变量：不是参数，是定义在Lambda表达式主体外层作用域中定义的变量。

Lambda也允许使用**自用变量**，就像匿名类一样。它们被称作捕获Lambda。但是关于能对这些变量做什么有一些限制。Lambda可以没有限制地捕获(也就是在其主体中引用)实例变量和静态变量。但局部变量必须显示声明为final或者事实上是final。

换句话说，Lambda表达式只能捕获指派给它们的局部变量一次。实例变量的捕获可以被看作捕获Final局部变量this。



### 3.6 方法引用

> 如果一个Lambda代表的只是“直接调用这个方法”，那最好还是用名称来调用它，而不是去描述如何调用它。



构建方法引用的三大类：

- 指向静态方法的方法引用
- 指向任意类型实例方法的引用
- 指向现有对象的实例方法引用



```java
List<String> str = Arrays.asList("a", "b", "A", "B");

// Lambda表达式
str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));

// 使用方法引用改写
str.sort(String::compareToIgnoreCase);
```

请注意，编译器会进行一种与Lambda表达式类似的类型检查过程，来确定对于给定的函数式接口，这个方法引用是否有效：方法引用的签名必须和上下文类型匹配。



#### 构造函数引用

对于一个现有构造函数，你可以利用它的名称和关键字new来创建它的一个引用：`ClassName::new`。它的功能与指向静态方法的引用类似。

**Apple无参构造方法**

```java
Supplier<Apple> c1 = Apple::new;	// () -> new Apple();
Apple a1 = c1.get();
```

**Apple一个参数构造方法，如：Apple(Integer weight)**

```java
Function<Integer, Apple> c2 = Apple::new;
Apple a1 = c2.apply(100);
```

**Apple两个参数构造方法，如：Apple(String color, Integer weight)**

```java
BiFunction<String, Integer, Apple> c3 = Apple::new;
Apple a1 = c3.apply("red", 120);
```





















