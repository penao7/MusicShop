package oinonen.MusicStore;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import oinonen.MusicStore.web.MusicStoreController;

@RunWith(SpringRunner.class)
@SpringBootTest
class MusicStoreApplicationTests {
 
 @Autowired
 private MusicStoreController musicStoreController;
 
 @Autowired
 private MusicStoreController cartController;

	@Test
	public void contextLoads() throws Exception {
	 assertNotNull(musicStoreController);
	 assertNotNull(cartController);
	}
		
};

