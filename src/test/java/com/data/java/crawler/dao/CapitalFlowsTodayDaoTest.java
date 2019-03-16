package com.data.java.crawler.dao;

import com.data.java.crawler.dto.CapitalFlowsTodayDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CapitalFlowsTodayDaoTest {
    @Autowired
    private CapitalFlowsTodayDao capitalFlowsTodayDao;

    @Test
    public void testFind(){
        List<CapitalFlowsTodayDTO> capitalFlowsTodayDTOS = capitalFlowsTodayDao.find();
        System.err.println(capitalFlowsTodayDao);
    }
}
