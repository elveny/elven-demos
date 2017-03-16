/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Filename CardAuthOrderRepository.java
 * @description
 * @Version 1.0
 * @author qiusheng.wu
 * @History
 * <br/>
 * <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/3/15 14:41</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
@Repository
public interface CardAuthOrderRepository extends JpaRepository<CardAuthOrder, Long>, JpaSpecificationExecutor<CardAuthOrder> {

    List<CardAuthOrder> findByAcctNo(String acctNo);

    List<CardAuthOrder> findByAcctNoAndAcctName(String acctNo, String acctName);

    List<CardAuthOrder> findByAcctNoAndAcctNameAndCertNo(String acctNo, String acctName, String certNo);

    List<CardAuthOrder> findByAcctNoAndAcctNameAndCertNoAndAcctNoIn(String acctNo, String acctName, String certNo, List<String> acctNos);

    List<CardAuthOrder> findByAcctNoAndAcctNameAndCertNoAndAcctNoInAndIdLessThan(String acctNo, String acctName, String certNo, List<String> acctNos, long id);

    List<CardAuthOrder> findByAcctNoAndAcctNameAndCertNoAndAcctNoInAndIdLessThanAndCreateTimeAfter(String acctNo, String acctName, String certNo, List<String> acctNos, long id, java.util.Date createTime);

    List<CardAuthOrder> findByAcctNoAndAcctNameAndCertNoAndAcctNoInAndIdLessThanAndCreateTimeAfterOrErrorCode(String acctNo, String acctName, String certNo, List<String> acctNos, long id, java.util.Date createTime, String errorCode);
}