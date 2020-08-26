package example;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.springframework.http.MediaType;

public class DiscoveryIT extends AbstractIntegrationTestBase {
    @Test
    public void discoveryServer() throws InterruptedException {
        Assume.assumeTrue(isCloudConfigEnabled());
        waitForServiceRegistration();
        String body = get("http://localhost:8761/eureka/apps", MediaType.APPLICATION_XML);
        Assert.assertTrue(body.contains("user-repository"));
    }
}
