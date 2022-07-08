package com.twhupup.collectiontype;

import java.util.List;

/**
 * @Project: SpringTest
 * @Package: com.twhupup.collectiontype
 * @Date: 2022/2/24 15:57
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class Book {
    private List<String> list;

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Book{" +
                "list=" + list +
                '}';
    }
}
