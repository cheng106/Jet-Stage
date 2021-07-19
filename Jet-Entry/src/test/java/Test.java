import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import lombok.Data;

/**
 * @ClassName Test
 * @Description TODO
 * @Author cheng
 * @Date 2021/7/18 23:42
 * @Version 1.0
 **/
@Data
public class Test {

    private static final SerializeConfig SERIALIZE_CONFIG;
    private String token;

    static {
        SERIALIZE_CONFIG = new SerializeConfig();
        SERIALIZE_CONFIG.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.setToken("SSSS");
        JSONObject j = JSONObject.parseObject("{\"tok\":\"sss\"}");
        JSONObject json = (JSONObject) JSON.toJSON(j, SERIALIZE_CONFIG);
        System.out.println("json = " + json);
    }
}
