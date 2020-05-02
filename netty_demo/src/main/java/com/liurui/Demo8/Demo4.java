package com.liurui.Demo8;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

/**
 * 文件读取实例
 */
@Slf4j
public class Demo4 {

    public static void main(String[] args) throws Exception {
        File file = Paths.get(System.getProperty("user.dir"), ".gitignore").toFile();
        ByteBuffer buffer = ByteBuffer.allocate(256);

        try (FileInputStream stream = new FileInputStream(file)) {
            FileChannel channel = stream.getChannel();
            while (true) {
                buffer.clear();
                if (-1 == channel.read(buffer)) {
                    break;
                }

                System.out.write(buffer.array());
            }
        }
    }
}
