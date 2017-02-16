/**
 * @author: qiusheng.wu
 * @version: 1.0
 * @since 2017-01-19
 */
package com.elven.demo.springboot1.test.grid.dao;

import java.util.List;
import java.util.Collection;


import com.elven.demo.springboot1.test.grid.domain.AnkInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnkInfoDao extends JpaRepository<AnkInfo,
 Integer
   > {


}

