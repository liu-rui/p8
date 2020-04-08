package com.liurui.defines.structures;

public interface ArrayListable {
    void add(int data);

    void insert(int index, int data);

    boolean contains(int data);

    int remove();

    int remove(int index);

    int getSize();

    boolean isEmpty();

    void clear();

    int get(int index);


    /**
     * 打印
     *
     * @return 列表，用逗号隔开
     */
    String print();


    /**
     * 反转
     */
    void reverse();

    /**
     * 排序
     *
     * @param asc 是否是升序,否则是降序
     */
    void sort(boolean asc);

    /**
     * 与另一个列表合并
     *
     * @param another 另一个列表
     * @param asc     是否是升序,否则是降序
     */
    void marge(ArrayListable another, boolean asc);
}
