//import com.calf.cn.utils.PingyingUtil;
//import com.calf.module.product.entity.Product;
//import com.gexin.fastjson.JSON;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//
///**
// * 测试
// *
// * @author len.song
// * @date 2018/05/31
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//
//public class lambdaTest2 {
//    public static void main(String[] args) {
//        Product product1 = new Product();
//        product1.setProductName("中");
//        Product product2 = new Product();
//        product2.setProductName("啊");
//        Product product3 = new Product();
//        product3.setProductName("吧");
//
//        List<Product> products = new ArrayList<>(Arrays.asList(product1,product2,product3));
//
//        Collections.sort(products,(Product p1, Product p2) -> PingyingUtil.ToPinYinString(p1.getProductName()).
//                compareTo(PingyingUtil.ToPinYinString(p2.getProductName())));
//
//        System.out.println(JSON.toJSONString(products));
//    }
//}

