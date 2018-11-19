package cn.bfay.wechat;

import cn.bfay.commons.okhttp.OkHttpUtils;
import cn.bfay.wechat.model.WechatToken;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * WechatCoreManager.
 *
 * @author wangjiannan
 */
public class WechatCoreManager {
    private static final Logger log = LoggerFactory.getLogger(WechatCoreManager.class);

    private final String appid;
    private final String secret;
    private final String token;

    public WechatCoreManager(String appid, String secret, String token) {
        this.appid = appid;
        this.secret = secret;
        this.token = token;
    }

    /**
     * 微信服务验证.
     *
     * @param request  req
     * @param response rep
     */
    public void getCore(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (checkSignature(request.getParameter("signature"),
                    request.getParameter("timestamp"),
                    request.getParameter("nonce"))) {
                out.print(request.getParameter("echostr"));
            }
        } catch (Exception e) {
            log.error("wechat get core error", e);
            throw new RuntimeException("wechat get core error");
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    // 验证签名-检验signature对请求进行校验
    private boolean checkSignature(String signature, String timestamp, String nonce) throws NoSuchAlgorithmException {
        String[] arr = new String[]{token, timestamp, nonce};
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (String anArr : arr) {
            content.append(anArr);
        }
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        // 将三个参数字符串拼接成一个字符串进行sha1加密
        byte[] digest = md.digest(content.toString().getBytes());
        String tmpStr = byteToStr(digest);
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return signature.equalsIgnoreCase(tmpStr);
    }

    private String byteToStr(byte[] byteArray) {// 将字节数组转换为十六进制字符串
        StringBuilder strDigest = new StringBuilder();
        for (byte aByteArray : byteArray) {
            strDigest.append(byteToHexStr(aByteArray));
        }
        return strDigest.toString();
    }

    private String byteToHexStr(byte mByte) {// 将字节转换为十六进制字符串
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        return new String(tempArr);
    }

    /**
     * 微信服务核心.
     *
     * @param request     req
     * @param response    rep
     * @param respMessage 调用核心业务类接收消息、处理消息
     */
    public void postCore(HttpServletRequest request, HttpServletResponse response, String respMessage) {
        PrintWriter out = null;
        try {
            // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            // 调用核心业务类接收消息、处理消息
            //String respMessage = wechatMessageService.processRequest(request);
            // 响应消息
            out = response.getWriter();
            out.print(respMessage);
        } catch (Exception e) {
            log.error("wechat post core error", e);
            throw new RuntimeException("wechat post core error");
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 获取access_token.
     *
     * @return {@link WechatToken}
     */
    public WechatToken processAccessToken() {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("grant_type", "client_credential");
        paramMap.put("appid", appid);
        paramMap.put("secret", secret);
        String result = OkHttpUtils.executeGet("https://api.weixin.qq.com/cgi-bin/token", paramMap, String.class);
        JSONObject json = JSON.parseObject(result);
        String accessToken = json.getString("access_token");
        if (!StringUtils.hasText(accessToken)) {
            throw new RuntimeException("access_token is null");
        }
        Long expiresIn = json.getLong("expires_in");
        if (expiresIn == null) {
            throw new RuntimeException("expires_in is null");
        }
        WechatToken wechatToken = new WechatToken();
        //log.info("获取微信accessToken-end");
        wechatToken.setAccessToken(accessToken);
        wechatToken.setExpiresIn(expiresIn);
        return wechatToken;
    }
}
