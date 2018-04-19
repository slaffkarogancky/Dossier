package kharkov.kp.gic.dossier.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("h2,jpa")
public class JPASimpleEntityServiceTest extends SimpleEntityServiceTest{

}

