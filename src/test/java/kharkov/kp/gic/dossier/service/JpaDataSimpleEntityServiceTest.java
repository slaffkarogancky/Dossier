package kharkov.kp.gic.dossier.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import kharkov.kp.gic.dossier.domain.SimpleEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("h2")
public class JpaDataSimpleEntityServiceTest {

	@Autowired
	SimpleEntityService service;
	
	@Test
	public void test() {
		List<SimpleEntity> list = service.getAll();
		assertTrue(list.size() > 0);
		for(SimpleEntity se : list) {
			System.out.println("I'm " + se);
		}
	}

}
