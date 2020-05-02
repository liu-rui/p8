package com.liurui.Demo8;

import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 实践MappedByteBuffer
 * 会对一个文件进行映射，对buffer的操作会映射到文件中
 */
@Slf4j
public class Demo6 {
    public static void main(String[] args) throws  Exception {
        try(RandomAccessFile outputStream  = new RandomAccessFile("hello.txt","rw")){
            try (FileChannel channel = outputStream.getChannel()) {
                MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 2, 2);

                log.info(mappedByteBuffer.toString());
                log.info("hasArray:{}", mappedByteBuffer.hasArray());//直接内存，所以为false


                for(int i = 0; i<  mappedByteBuffer.limit();i++){
                    log.info("{}:{}"  ,  i ,  mappedByteBuffer.get(i));//打印原有值
                    mappedByteBuffer.put(i,(byte)('A' + i));  //设置新值
                }
            }
        }
    }
}
