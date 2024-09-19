package libx;

import com.alibaba.fastjson.JSONObject;
import libx.util.Md5Util;
import okhttp3.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/** xingwoda
 * API-发起支付demo
 *
 * @author robot.eureka
 * @date 2021/01/28 13:55
 */
public class PaymentApiDemo {

    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient.Builder()
            .readTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS)
            .connectTimeout(10L, TimeUnit.SECONDS)
            .build();

    public static void main(String[] args) throws Exception {

        String payGateWay = "{{支付下单网关；请联系客服获取}}";
        String mchKey = "{{商户ID；可到 商户后台=>商户管理=>商户信息 中获取}}";
        String mchSecret = "{{商户秘钥；可到 商户后台=>商户管理=>商户信息 中获取}}";
        String productCode = "{{通道编码；可到 商户后台=>支付通道=>支付编码 中提取}}";

        /**** 必填参数 ****/
        //<必填>商户订单号，由商户系统生成，必须保证唯一
        String mchOrderNo = "order000000000002";
        //<必填>商户用户id
        String mchUserId = "user_0001";
        //<必填>交易金额；单位：分
        Integer amount = 20000;
        //<必填>随机字符串；长度：8 ~ 32 个字符
        String nonce = Md5Util.encrypt(String.valueOf(new Random().nextLong()));
        //<必填>时间戳
        Long timestamp = System.currentTimeMillis();
        //<必填>支付结果回调地址
        String notifyUrl = "http://www.xxx.com/notify/url";

        /**** 非必填参数 ****/
        //<非必填>用户ip
        String userIp = "234.121.134.32";
        //<非必填>支付成功跳转地址
        String returnUrl = "http://www.xxxx.com/api/order/success";
        //<非必填>用户姓名
        String userName = "zhangsan";
        //<非必填>用户姓
        String userFirstName = "zhang";
        //<非必填>用户名
        String userLastName = "san";
        //<非必填>用户手机号
        String mobile = "18888888888";
        //<非必填>用户邮箱
        String email = "zhangsan@gmail.com";
        //<非必填>透传值，订单回调时原样返回
        String attach = "name=test";
        //<非必填>订单备注
        String remark = "会员充值订单";

        Map<String, Object> params = new HashMap<>(64);
        params.put("mchKey", mchKey);
        params.put("product", productCode);
        params.put("mchOrderNo", mchOrderNo);
        params.put("mchUserId", mchUserId);
        params.put("amount", amount);
        params.put("nonce", nonce);
        params.put("timestamp", timestamp);
        params.put("notifyUrl", notifyUrl);
        params.put("returnUrl", returnUrl);
        params.put("userIp", userIp);
        params.put("userName", userName);
        params.put("userFirstName", userFirstName);
        params.put("userLastName", userLastName);
        params.put("mobile", mobile);
        params.put("email", email);
        params.put("attach", attach);
        params.put("remark", remark);

        String needSignParamStr = getNeedSignParamString(params, mchSecret);
        System.out.println("要验签的参数: " + needSignParamStr);
        // out>> amount=20000&attach=name=test&email=zhangsan@gmail.com&mchKey=10008&mchOrderNo=order000000000002&mchUserId=user_0001&mobile=18888888888&nonce=10ff070de3adcee3607b967fbb44a3a5&notifyUrl=http://www.xxx.com/notify/url&product=wechat-h5&remark=会员充值订单&returnUrl=http://www.xxxx.com/api/order/success&timestamp=1637304432867&userFirstName=zhang&userIp=234.121.134.32&userLastName=san&userName=zhangsankys-iYuCXJG3OV-M5sJxUPVH6

        String sign = Md5Util.encrypt(needSignParamStr);
        System.out.println("生成参数签名: " + sign);
        // out>> f0c9ab6e9c58cff76511ca26344b1208

        params.put("sign", sign);
        System.out.println("请求参数: " + params);
        //out>> {mchOrderNo=order000000000002, sign=f0c9ab6e9c58cff76511ca26344b1208, remark=会员充值订单, mchKey=10008, attach=name=test, returnUrl=http://www.xxxx.com/api/order/success, email=zhangsan@gmail.com, timestamp=1637304432867, product=wechat-h5, amount=20000, mobile=18888888888, userName=zhangsan, userFirstName=zhang, userLastName=san, nonce=10ff070de3adcee3607b967fbb44a3a5, mchUserId=user_0001, notifyUrl=http://www.xxx.com/notify/url, userIp=234.121.134.32}

        String response = doPost(payGateWay, params, new HashMap<>());
        System.out.println("响应结果：" + response);
        //out>> {"code":"200","msg":"请求成功","data":{"mchKey":"10008","mchOrderNo":"order000000000002","serialOrderNo":"PAY020211118QGM9isDvPVJWeOLg8JPa","amount":20000,"realAmount":null,"payStatus":"PROCESSING","product":"wechat-h5","url":{"payUrl":"http://pay.xxx.top/api/wechat/PAY020211118QGM9isDvPVJWeOLg8JPa.html","expire":1637171130446,"expireTime":"2021/11/18 01:45:30"},"createTime":"2021/11/18 01:40:27","payTime":null}}
    }

    /**
     * 生成商户请求签名
     *
     * @param paramMap  待签名数据
     * @param mchSecret 商户秘钥
     * @return 签名
     */
    public static String getNeedSignParamString(Map<String, Object> paramMap, String mchSecret) {
        SortedMap<String, Object> data = new TreeMap<>(paramMap);
        Iterator<String> iterator = data.keySet().iterator();
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = data.get(key);
            if (Objects.nonNull(value) && String.valueOf(value).trim().length() > 0) {
                if (!first) {
                    sb.append("&");
                }
                sb.append(key).append("=").append(value);
                first = false;
            }
        }
        sb.append(mchSecret);
        return sb.toString();
    }

    /**
     * 发起post请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @param header 请求头
     * @return 响应结果
     * @throws IOException 网络IO异常
     */
    public static String doPost(String url, Map<String, Object> params, Map<String, String> header) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject.toJSONString(params));
        Request.Builder builder = new Request.Builder();
        if (Objects.nonNull(header) && !header.isEmpty()) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        builder.url(url).post(body);
        Response response = HTTP_CLIENT.newCall(builder.build()).execute();
        return response.body().string();
    }
}
