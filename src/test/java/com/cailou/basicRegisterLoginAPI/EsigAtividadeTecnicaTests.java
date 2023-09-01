package com.cailou.basicRegisterLoginAPI;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class EsigAtividadeTecnicaTests {

    @Test
    @Sql({"/import_roles.sql"})
    public void testLoadDataForTestClass() {

    }
}

