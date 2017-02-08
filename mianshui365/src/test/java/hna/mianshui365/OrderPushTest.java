package hna.mianshui365;

import org.testng.annotations.Test;
import org.apache.http.util.EntityUtils;
import org.testng.AssertJUnit;

public class OrderPushTest
{

    public OrderPushTest()
    {
    	OrderPush.orderPush();
    }

    @Test
	public void testApp()
    {
        AssertJUnit.assertTrue( true );
    }
}
