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



