import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
import org.junit.Test;

public class MyTest {
    @Test
    public void test(){
        Long toCustomerId = 1810161659557030302L;
        RongYunUtil.sendSystemMessage(toCustomerId,"技能被封禁","prohibition_login_out");
    }
}
