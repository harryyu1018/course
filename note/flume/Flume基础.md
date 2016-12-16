# Flume基础

Flume是一个分布式、可靠、高可用的海量日志聚合系统，支持在系统中定制各类数据发送方，用于收集数据；同时，Flume提供对数据简单处理，并写到各种数据接收方的能力。

Flume在0.9.x和1.x之间有较大的架构调整，1.x版本之后的改称FlumeNG，0.9.x的称为Flume OG。New和Old的分界岭。



![](img/core-architecture.png)



​			图 FlumeNG的体系结构





## 核心组件

- Source
- Channel
- Sink



### Source

> 完成对日志数据的收集，分成transition和event打入到channel之中。

Flume提供了各种source的实现

- Avro Source
- Exec Source
- Spooling Directory Source
- NetCat Source
- Syslog Source
- Syslog TCP
- Syslog UDP Source
- HTTP Source
- HDFS Source
- ... ... 



对现有程序改动最小的使用方式使用时直接读取程序原来记录的日志文件，基本可以实现无缝接入。不需要对现有程序进行任何改动。直接读取文件Source，比如有两种方式：

- Exec Source
- Spool Source



### Sink

> 取出Channel中的数据，进行相应的存储文件系统，数据库，或者提交到远程服务器。

多种实现：

- HDFS Sink
- Logger sink
- Avro sink
- File Roll sink
- Null sink
- HBase sink
- ... ...



### Channel

> 主要提供一个队列的功能，对source提供中的数据进行简单的缓存。

多种实现：

- Memory Channel
  - 可以实现高速吞吐，但是无法保证数据的完整性
- JDBC Channel
- File Channel
  - 保证数据的完整性与一致性。在具体配置不现的FileChannel时，建议File Channel设置的目录和程序日志文件保存的目录设成不同的磁盘，以便提高效率。
- ......





