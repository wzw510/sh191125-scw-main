import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JedisTest {

    @Test
    public void test(){
        Jedis jedis = new Jedis("192.168.64.168", 6379);

        String ping = jedis.ping();

        System.out.println("ping = "+ ping);

        jedis.setex("k1",200,"v1");

        Long k11 = jedis.ttl("k1");

        System.out.println("ttl = "+k11);

        Boolean k1 = jedis.exists("k1");
        System.out.println("k1键存在：exists =  "+k1);

        Long setnx = jedis.setnx("k1", "hehe");
        System.out.println("setnx = "+(setnx==1?"设置成功":"设置失败"));

        jedis.del("k1");

        System.out.println("=============================================");

        Map<String,Double> map = new HashMap<>();
        map.put("huoying",1000d);
        map.put("haizei",800d);
        map.put("kenan",500d);
        map.put("sishen",300d);

        jedis.zadd("topn",map);

        Set<Tuple> top = jedis.zrevrangeWithScores("topn", 0, 2);

        for (Tuple tuple:top){
            System.out.println("element = "+tuple.getElement()+",tuple.getScore() = "+tuple.getScore());
        }

        jedis.close();
    }
}
