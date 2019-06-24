package com.example.demo.dao;


import com.example.demo.domain.Price;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

/**
 CREATE TABLE `price` (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
 `total` decimal(12,2) DEFAULT '0.00' COMMENT '总值',
 `front` decimal(12,2) DEFAULT '0.00' COMMENT '消费前',
 `end` decimal(12,2) DEFAULT '0.00' COMMENT '消费后',
 PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1268 DEFAULT CHARSET=utf8;
 INSERT INTO `demo`.`price`(`id`, `total`, `front`, `end`) VALUES (1, 100.00, 100.00, 0.00);
 */

@Mapper
public interface PriceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Price record);

    int insertSelective(Price record);

    Price selectByPrimaryKey(Integer id);

    /**
     * 悲观锁
     *
     * @param id
     * @return
     */
    @Select("select * from price where id= #{id} for update")
    Price selectByPrimaryKey4Update(@RequestParam("id") Integer id);

    int updateByPrimaryKeySelective(Price record);

    int updateByPrimaryKey(Price record);
}