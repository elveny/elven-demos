
/**
 * @author: qiusheng.wu
 * @version: 1.0
 * @since 2017-01-19
 */
package com.elven.demo.springboot1.test.grid.service;

import com.elven.demo.springboot1.test.grid.domain.AnkInfo;

import java.util.List;
import java.util.Collection;

public interface AnkInfoService {

   public void saveAnkInfo(AnkInfo ankInfo);

   public void deleteAnkInfo(AnkInfo ankInfo);

}
