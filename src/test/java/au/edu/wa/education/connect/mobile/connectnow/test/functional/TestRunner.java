package au.edu.wa.education.connect.mobile.connectnow.test.functional;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.edu.wa.education.connect.mobile.connectnow.test.functional.configuration.AndroidDriverConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AndroidDriverConfiguration.class})
@ActiveProfiles("test")
public class TestRunner {

}
