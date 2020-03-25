/**
 * @program: demo
 * @description:
 * @author: KaiTao.wu
 * @create: 2020-03-24 05:10
 **/
package com.example.service.base;

import com.example.repository.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class BaseService<T> {
    @Autowired
    protected BaseRepository<T> repository;


    public void add(){

        System.out.println("add");
        System.out.println(repository);
    }
}
