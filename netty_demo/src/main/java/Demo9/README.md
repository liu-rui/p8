测试零拷贝的性能相比传统IO方式提升多少
1.通过传递一个189M大小的文件，测试性能
OldIOServer和OldIOClient为传统的方式
NewIOServer和NewIOClient为NIO方式并采用了零拷贝方式

执行情况：
old: 300多
new:100多

结论：
零拷贝是传统IO差不多3倍,性能提供很大