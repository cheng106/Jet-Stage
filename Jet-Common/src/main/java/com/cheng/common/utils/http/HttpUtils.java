package com.cheng.common.utils.http;

import com.cheng.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.*;
import java.io.*;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * HTTP發送共用方法
 */
@Slf4j
public class HttpUtils {

    /**
     * 指定URL發送GET請求
     *
     * @param url   要發送請求的URL
     * @param param 請求參數 (name1=value1&name2=value2)
     * @return 請求後的回應結果
     */
    public static String sendGet(String url, String param) {
        return sendGet(url, param, Constants.UTF8);
    }

    /**
     * 指定URL發送GET請求
     *
     * @param url         要發送請求的URL
     * @param param       請求參數 (name1=value1&name2=value2)
     * @param contentType 編碼類型
     * @return 請求後的回應結果
     */
    public static String sendGet(String url, String param, String contentType) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            log.info("###sendGet -> {}", urlNameString);
            URL u = new URL(urlNameString);
            URLConnection connection = u.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), contentType));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            log.info("###recv -> {}", result);
        } catch (ConnectException e) {
            log.error("###HttpUtils.sendGet ConnectException url={}, param={}", url, param, e);
        } catch (SocketTimeoutException e) {
            log.error("###HttpUtils.sendGet SocketTimeoutException, url={}, param={}", url, param, e);
        } catch (IOException e) {
            log.error("###HttpUtils.sendGet IOException, url={}, param={}", url, param, e);
        } catch (Exception e) {
            log.error("###HttpsUtil.sendGet Exception, url={}, param={}", url, param, e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
                log.error("###in.close Exception, url={}, param={}", url, param, ex);
            }
        }
        return result.toString();
    }

    /**
     * 指定URL發送POST請求
     *
     * @param url   要發送POST請求的URL
     * @param param 請求參數 (name1=value1&name2=value2)
     * @return 請求後的回應結果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            log.info("###sendPost -> {}", url);
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", Constants.UTF8);
            conn.setRequestProperty("contentType", Constants.UTF8);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            log.info("###recv -> {}", result);
        } catch (ConnectException e) {
            log.error("###HttpUtils.sendPost ConnectException, url={}, param={}", url, param, e);
        } catch (SocketTimeoutException e) {
            log.error("###HttpUtils.sendPost SocketTimeoutException, url={}, param={}", url, param, e);
        } catch (IOException e) {
            log.error("###HttpUtils.sendPost IOException, url={}, param={}", url, param, e);
        } catch (Exception e) {
            log.error("###HttpsUtil.sendPost Exception, url={}, param={}", url, param, e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                log.error("###in.close Exception, url={}, param={}", url, param, ex);
            }
        }
        return result.toString();
    }

    public static String sendSSLPost(String url, String param) {
        StringBuilder result = new StringBuilder();
        String urlNameString = url + "?" + param;
        try {
            log.info("###sendSSLPost -> {}", urlNameString);
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new SecureRandom());
            URL console = new URL(urlNameString);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", Constants.UTF8);
            conn.setRequestProperty("contentType", Constants.UTF8);
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String ret;
            while ((ret = br.readLine()) != null) {
                if (!"".equals(ret.trim())) {
                    result.append(new String(ret.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                }
            }
            log.info("###recv -> {}", result);
            conn.disconnect();
            br.close();
        } catch (ConnectException e) {
            log.error("###HttpUtils.sendSSLPost ConnectException, url={}, param={}", url, param, e);
        } catch (SocketTimeoutException e) {
            log.error("###HttpUtils.sendSSLPost SocketTimeoutException, url={}, param={}", url, param, e);
        } catch (IOException e) {
            log.error("###HttpUtils.sendSSLPost IOException, url={}, param={}", url, param, e);
        } catch (Exception e) {
            log.error("###HttpsUtil.sendSSLPost Exception, url={}, param={}", url, param, e);
        }
        return result.toString();
    }

    private static class TrustAnyTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}