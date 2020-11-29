package com.afeng.common.cache;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

@SuppressWarnings("unchecked")
@Component
public class JedisUtil {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static JedisUtil instance;

    @Autowired
    private JedisPool jedisPool;


    @PostConstruct
    private void init() {
        instance = this;
        instance.jedisPool = this.jedisPool;
    }

    public static JedisUtil getInstance() {
        return instance;
    }

    //获取符合本项目缓存名规则的KEY
    public String getCacheKey(String key) {
        return "afeng:" + key;
    }

    // String 操作

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public String get(String key) {
        String value = null;
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists(key)) {
                value = jedis.get(key);
                value = StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
            }
        } catch (Exception e) {
            logger.error("get {} = {}", key, value, e);
        }
        return value;
    }

    /**
     * 设置缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     * @throws Exception
     */
    public String set(String key, String value, int cacheSeconds) {
        String result = null;
        try (Jedis jedis = jedisPool.getResource()) {
            if (cacheSeconds > 0) {
                result = jedis.setex(key, cacheSeconds, value);
            } else {
                result = jedis.set(key, value);
            }
        } catch (Exception e) {
            logger.error("set {} = {}", key, value, e);
        }
        return result;
    }

    public String set(String key, String value) {
        return set(key, value, 0);
    }

    // 对象

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public Object getObject(String key) {
        Object value = null;
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists(getBytesKey(key))) {
                value = toObject(jedis.get(getBytesKey(key)));
            }
        } catch (Exception e) {
            logger.error("getObject {} = {}", key, value, e);
        }
        return value;
    }

    /**
     * 设置缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public String setObject(String key, Object value, int cacheSeconds) {
        String result = null;
        try (Jedis jedis = jedisPool.getResource()) {
            result = jedis.set(getBytesKey(key), toBytes(value));
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            logger.error("setObject {} = {}", key, value, e);
        }
        return result;
    }

    public String setObject(String key, Object value) {
        return setObject(key, value, 0);
    }

    // 列表

    /**
     * 获取List缓存
     *
     * @param key 键
     * @return 值
     */
    public List<String> getList(String key) {
        List<String> value = null;
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists(key)) {
                value = jedis.lrange(key, 0, -1);
            }
        } catch (Exception e) {
            logger.error("getList {} = {}", key, value, e);
        }
        return value;
    }

    /**
     * 获取List缓存
     *
     * @param key 键
     * @return 值
     */
    public List<Object> getObjectList(String key) {
        List<Object> value = null;
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists(getBytesKey(key))) {
                List<byte[]> list = jedis.lrange(getBytesKey(key), 0, -1);
                value = new ArrayList<>();
                for (byte[] bs : list) {
                    value.add(toObject(bs));
                }
            }
        } catch (Exception e) {
            logger.error("getObjectList {} = {}", key, value, e);
        }
        return value;
    }

    /**
     * 设置List缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public long setList(String key, List<String> value, int cacheSeconds) {
        long result = 0;
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            Object[] objectArray = value.toArray();
            String[] stringArray = Arrays.copyOf(objectArray, objectArray.length, String[].class);
            result = jedis.rpush(key, stringArray);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            logger.error("setList {} = {}", key, value, e);
        }
        return result;
    }

    public long setList(String key, List<String> value) {
        return setList(key, value, 0);
    }


    /**
     * 设置List缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public long setObjectList(String key, List<Object> value, int cacheSeconds) {
        long result = 0;
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists(getBytesKey(key))) {
                jedis.del(key);
            }
            List<byte[]> list = new ArrayList<>();
            for (Object o : value) {
                list.add(toBytes(o));
            }
            result = jedis.rpush(getBytesKey(key), (byte[][]) list.toArray());
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            logger.error("setObjectList {} = {}", key, value, e);
        }
        return result;
    }

    public long setObjectList(String key, List<Object> value) {
        return setObjectList(key, value, 0);
    }

    /**
     * 向List缓存中添加值，放入队尾
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public long listRpush(String key, String... value) {
        long result = 0;
        try (Jedis jedis = jedisPool.getResource()) {
            result = jedis.rpush(key, value);
        } catch (Exception e) {
            logger.error("listRpush {} = {}", key, value, e);
        }
        return result;
    }

    /**
     * list的push操作（事件入队列，放入队头）
     *
     * @param key   list的名字，即key
     * @param value 将要放入的值value
     */
    public long listLpush(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.lpush(key, value);
        } catch (Exception e) {
            logger.error("listLpush {} = {}", key, value, e);
        }
        return -1;
    }

    /**
     * 向List缓存中添加值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public long listObjectAdd(String key, Object... value) {
        long result = 0;
        try (Jedis jedis = jedisPool.getResource()) {
            List<byte[]> list = new ArrayList<>();
            for (Object o : value) {
                list.add(toBytes(o));
            }
            result = jedis.rpush(getBytesKey(key), (byte[][]) list.toArray());
        } catch (Exception e) {
            logger.error("listObjectAdd {} = {}", key, value, e);
        }
        return result;
    }


    /**
     * 从列表中移除并返回列表的第一个元素。
     *
     * @author AFeng
     * @createDate 2020/8/12 15:34
     **/
    public String listLpop(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.lpop(key);
        } catch (Exception e) {
            logger.error("listPop {} = {}", key, e);
        }
        return null;
    }


    /**
     * list的brpop操作
     * 假如在指定时间内没有任何元素被弹出，则返回一个 nil 和等待时长。 反之，返回一个含有两个元素的列表，
     * 第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值。
     * Redis Brpop 命令移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param timeout 超时时间
     * @param key     list对应的key
     * @return List<String> 返回list的名字key和对应的元素
     */
    public List<String> listBrpop(int timeout, String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.brpop(timeout, key);
        } catch (Exception e) {
            logger.error("listBrpop {} = {}", key, e);
        }
        return null;
    }


    //获取列表长度
    public int listSize(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            Long size = jedis.llen(key);
            return size.intValue();
        }
    }


    // 列表Set，去重列表

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public Set<String> getSet(String key) {
        Set<String> value = null;
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists(key)) {
                value = jedis.smembers(key);
            }
        } catch (Exception e) {
            logger.error("getSet {} = {}", key, value, e);
        }
        return value;
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public Set<Object> getObjectSet(String key) {
        Set<Object> value = null;
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists(getBytesKey(key))) {
                value = new HashSet<>();
                Set<byte[]> set = jedis.smembers(getBytesKey(key));
                for (byte[] bs : set) {
                    value.add(toObject(bs));
                }
            }
        } catch (Exception e) {
            logger.error("getObjectSet {} = {}", key, value, e);
        }
        return value;
    }

    /**
     * 设置Set缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public long setSet(String key, Set<String> value, int cacheSeconds) {
        long result = 0;
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            Object[] objectArray = value.toArray();
            String[] stringArray = Arrays.copyOf(objectArray, objectArray.length, String[].class);
            result = jedis.sadd(key, stringArray);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            logger.error("setSet {} = {}", key, value, e);
        }
        return result;
    }

    public long setSet(String key, Set<String> value) {
        return setSet(key, value, 0);
    }

    /**
     * 设置Set缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public long setObjectSet(String key, Set<Object> value, int cacheSeconds) {
        long result = 0;
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists(getBytesKey(key))) {
                jedis.del(key);
            }
            Set<byte[]> set = new HashSet<>();
            for (Object o : value) {
                set.add(toBytes(o));
            }
            result = jedis.sadd(getBytesKey(key), (byte[][]) set.toArray());
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            logger.error("setObjectSet {} = {}", key, value, e);
        }
        return result;
    }

    public long setObjectSet(String key, Set<Object> value) {
        return setObjectSet(key, value, 0);
    }

    /**
     * 向Set缓存中添加值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public long setSetAdd(String key, String... value) {
        long result = 0;
        try (Jedis jedis = jedisPool.getResource()) {
            result = jedis.sadd(key, value);
        } catch (Exception e) {
            logger.error("setSetAdd {} = {}", key, value, e);
        }
        return result;
    }

    /**
     * 向Set缓存中添加值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public long setSetObjectAdd(String key, Object... value) {
        long result = 0;
        try (Jedis jedis = jedisPool.getResource()) {
            Set<byte[]> set = new HashSet<>();
            for (Object o : value) {
                set.add(toBytes(o));
            }
            result = jedis.rpush(getBytesKey(key), (byte[][]) set.toArray());
        } catch (Exception e) {
            logger.error("setSetObjectAdd {} = {}", key, value, e);
        }
        return result;
    }

    // 集合 map

    /**
     * 获取Map缓存
     *
     * @param key 键
     * @return 值
     */
    public Map<String, String> getMap(String key) {
        Map<String, String> value = null;
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists(key)) {
                value = jedis.hgetAll(key);
            }
        } catch (Exception e) {
            logger.error("getMap {} = {}", key, value, e);
        }
        return value;
    }

    /**
     * 获取Map缓存
     *
     * @param key 键
     * @return 值
     */
    public Map<String, Object> getObjectMap(String key) {
        Map<String, Object> value = null;
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists(getBytesKey(key))) {
                value = new HashMap<>();
                Map<byte[], byte[]> map = jedis.hgetAll(getBytesKey(key));
                for (Map.Entry<byte[], byte[]> e : map.entrySet()) {
                    value.put(toString(e.getKey()), toObject(e.getValue()));
                }
            }
        } catch (Exception e) {
            logger.error("getObjectMap {} = {}", key, value, e);
        }
        return value;
    }

    /**
     * 设置Map缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public String setMap(String key, Map<String, String> value, int cacheSeconds) {
        String result = null;
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.hmset(key, value);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            logger.error("setMap {} = {}", key, value, e);
        }
        return result;
    }

    public String setMap(String key, Map<String, String> value) {
        return setMap(key, value, 0);
    }

    /**
     * 设置Map缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public String setObjectMap(String key, Map<String, Object> value, int cacheSeconds) {
        String result = null;
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists(getBytesKey(key))) {
                jedis.del(key);
            }
            Map<byte[], byte[]> map = new HashMap<>();
            for (Map.Entry<String, Object> e : value.entrySet()) {
                map.put(getBytesKey(e.getKey()), toBytes(e.getValue()));
            }
            result = jedis.hmset(getBytesKey(key), (Map<byte[], byte[]>) map);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            logger.error("setObjectMap {} = {}", key, value, e);
        }
        return result;
    }

    public String setObjectMap(String key, Map<String, Object> value) {
        return setObjectMap(key, value, 0);
    }

    /**
     * 向Map缓存中添加值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public String mapPut(String key, Map<String, String> value) {
        String result = null;
        try (Jedis jedis = jedisPool.getResource()) {
            result = jedis.hmset(key, value);
            logger.debug("mapPut {} = {}", key, value);
        } catch (Exception e) {
            logger.error("mapPut {} = {}", key, value, e);
        }
        return result;
    }

    /**
     * 向Map缓存中添加值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public String mapObjectPut(String key, Map<String, Object> value) {
        String result = null;
        try (Jedis jedis = jedisPool.getResource()) {
            Map<byte[], byte[]> map = new HashMap<>();
            for (Map.Entry<String, Object> e : value.entrySet()) {
                map.put(getBytesKey(e.getKey()), toBytes(e.getValue()));
            }
            result = jedis.hmset(getBytesKey(key), (Map<byte[], byte[]>) map);
        } catch (Exception e) {
            logger.error("mapObjectPut {} = {}", key, value, e);
        }
        return result;
    }

    /**
     * 移除Map缓存中的值
     *
     * @param key    键
     * @param mapKey 值
     * @return
     */
    public long mapRemove(String key, String mapKey) {
        long result = 0;
        try (Jedis jedis = jedisPool.getResource()) {
            result = jedis.hdel(key, mapKey);
        } catch (Exception e) {
            logger.error("mapRemove {}  {}", key, mapKey, e);
        }
        return result;
    }

    /**
     * 移除Map缓存中的值
     *
     * @param key    键
     * @param mapKey 值
     * @return
     */
    public long mapObjectRemove(String key, String mapKey) {
        long result = 0;
        try (Jedis jedis = jedisPool.getResource()) {
            result = jedis.hdel(getBytesKey(key), getBytesKey(mapKey));
        } catch (Exception e) {
            logger.error("mapObjectRemove {}  {}", key, mapKey, e);
        }
        return result;
    }

    /**
     * 判断Map缓存中的Key是否存在
     *
     * @param key    键
     * @param mapKey 值
     * @return
     */
    public boolean mapExists(String key, String mapKey) {
        boolean result = false;
        try (Jedis jedis = jedisPool.getResource()) {
            result = jedis.hexists(key, mapKey);
        } catch (Exception e) {
            logger.error("mapExists {}  {}", key, mapKey, e);
        }
        return result;
    }

    /**
     * 判断Map缓存中的Key是否存在
     *
     * @param key    键
     * @param mapKey 值
     * @return
     */
    public boolean mapObjectExists(String key, String mapKey) {
        boolean result = false;
        try (Jedis jedis = jedisPool.getResource()) {
            result = jedis.hexists(getBytesKey(key), getBytesKey(mapKey));
        } catch (Exception e) {
            logger.error("mapObjectExists {}  {}", key, mapKey, e);
        }
        return result;
    }


    // 判断


    /**
     * 设置过期时间
     *
     * @param key          键
     * @param cacheSeconds 过期时间
     */
    public void setExpire(String key, int cacheSeconds) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.expire(key, cacheSeconds);
        } catch (Exception e) {
            logger.error("setExpire {} = {}", key, e);
        }
    }

    /**
     * 获取key剩余存活时间
     * 单位毫秒
     */
    public long getPttl(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.pttl(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 删除缓存
     *
     * @param key 键
     * @return 0为失败，1为成功
     */
    public long del(String key) {
        long result = 0;
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists(key)) {
                result = jedis.del(key);
            }
            if (jedis.exists(getBytesKey(key))) {
                result = jedis.del(getBytesKey(key));
            }
        } catch (Exception e) {
            logger.error("del {}", key, e);
        }
        return result;
    }


    /**
     * 模糊删除缓存
     *
     * @param key 键，例如 u*  ，其中 * 为匹配符
     * @return 成功删除个数
     */
    public long delByScan(String key) {
        long result = 0;
        List<String> keys = getKeysByScan(key);
        if (keys != null && keys.size() > 0) {
            for (String item : keys) {
                if (del(item) == 1) {
                    result++;
                }
            }
        }
        return result;
    }


    /**
     * 缓存是否存在
     *
     * @param key 键
     * @return
     */
    public boolean exists(String key) {
        boolean result = false;
        try (Jedis jedis = jedisPool.getResource()) {
            result = jedis.exists(key);
        } catch (Exception e) {
            logger.error("exists {}", key, e);
        }
        return result;
    }

    /**
     * 缓存是否存在
     *
     * @param key 键
     * @return
     */
    public boolean existsObject(String key) {
        boolean result = false;
        try (Jedis jedis = jedisPool.getResource()) {
            result = jedis.exists(getBytesKey(key));
        } catch (Exception e) {
            logger.error("existsObject {}", key, e);
        }
        return result;
    }

    /**
     * 模糊返回key列表
     *
     * @param key      例如匹配以 test:xttblog:* 为前缀的 key
     * @param count    count并非限定返回结果的数量而是单次遍历的数组元素数量
     * @param overTime 超时时间，-1为不限时，毫秒
     * @return
     */
    public List<String> getKeysByScan(String key, int count, long overTime) {
        List<String> list = new ArrayList<>();
        ScanParams params = new ScanParams();
        params.match(key);
        params.count(count);
        try (Jedis jedis = jedisPool.getResource()) {
            long beginTime = System.currentTimeMillis();
            while (true) {
                if (overTime > -1) {
                    long nowTime = System.currentTimeMillis();
                    if ((nowTime - beginTime) > overTime) {
                        // 超时 退出
                        break;
                    }
                }
                ScanResult scanResult = jedis.scan("0", params);
                List<String> elements = scanResult.getResult();
                if (elements != null && elements.size() > 0) {
                    list.addAll(elements);
                }
                String cursor = scanResult.getCursor();
                if ("0".equals(cursor)) {
                    // 返回0 说明遍历完成
                    break;
                }
            }
        } catch (Exception e) {
            logger.error("getKeysByScan {}", key, e);
        }

        return list;
    }

    public List<String> getKeysByScan(String key) {
        return getKeysByScan(key, 100, -1);
    }


    // 其它工具

    /**
     * 转换为字节数组
     *
     * @param str
     * @return
     */
    public static String toString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * 获取byte[]类型Key
     *
     * @return
     */
    private byte[] getBytesKey(Object object) {
        if (object instanceof String) {
            return getBytes((String) object);
        } else {
            return toBytes(object);
        }
    }

    /**
     * 转换为字节数组
     *
     * @param str
     * @return
     */
    private byte[] getBytes(String str) {
        if (str != null) {
            return str.getBytes(StandardCharsets.UTF_8);
        } else {
            return null;
        }
    }

    /**
     * Object转换byte[]类型
     */
    private byte[] toBytes(Object object) {
        return serialize(object);
    }

    /**
     * byte[]型转换Object
     */
    private Object toObject(byte[] bytes) {
        return unSerialize(bytes);
    }


    /**
     * 序列化对象
     */
    private byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            if (object != null) {
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                return baos.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化对象
     */
    private Object unSerialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            if (bytes != null && bytes.length > 0) {
                bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                return ois.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
