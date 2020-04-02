package com.liurui;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;

import static jdk.internal.org.objectweb.asm.Opcodes.ASM5;

/**
 * @author liu-rui
 * @date 2020/4/2 下午3:58
 * @description
 * @since
 */
public class MyClassPrinter extends ClassVisitor {

    public MyClassPrinter() {
        super(ASM5);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.printf("%s %s extends %s {\n" , access , name , superName);
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.printf(" %s %s %s\n", access, name, desc);
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

    /**
     * Visits the end of the class. This method, which is the last one to be
     * called, is used to inform the visitor that all the fields and methods of
     * the class have been visited.
     */
    @Override
    public void visitEnd() {
        System.out.println("}");
        super.visitEnd();
    }
}
