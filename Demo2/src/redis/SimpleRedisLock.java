package redis;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 *  使用redis实现简单的分布式锁
 */

public class SimpleRedisLock {

//    /**
//     * 扣除库存例子
//     *      库存的扣除必须要保证其不能超卖，所以需要锁
//     */
//
//    /*
//        第一版：
//            基础实现，redis的单线程模型保证了线程安全，setnx操作保证每次只有一个线程可以拿到锁，同时后边的线程拿不到而等待
//            主要问题：
//                1. 程序中可以看到会出现扣除失败的案列，加入发生内部错误，那锁将无法释放，从而造成死锁
//     */
//    @RequestMapping("/deduct_stock")
//    public String deductStock1(){
//
//        String lockKey="lockKey";
//        // setnx()，如果当前key不存在则设置成功，否则不设置
//        boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey,"lock"); // setnx(key,value);
//
//        // 表示已经被加锁
//        if(!result){
//            return "error_code";
//        }
//
//        // 获取当前库存
//        int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
//
//        if (stock>0){
//            int realStock = stock -1;
//            // 设置库存-1
//            stringRedisTemplate.opsForValue().set("stock",realStock+"");
//            System.out.println("扣除成功");
//        }else {
//            System.out.println("库存不足，扣除失败");
//        }
//        // 删除key，即释放锁
//        stringRedisTemplate.opsForValue().delete(lockKey);
//
//        return "end";
//    }
//
//    /*
//        第二版：
//            基础实现，redis的单线程模型保证了线程安全，setnx操作保证每次只有一个线程可以拿到锁，同时后边的线程拿不到而等待，
//            新增：
//                1. 使用try /catch /finally 块来防止出现错误，无法释放锁
//            主要问题：
//                1. 如果主机突然宕机，那么仍然会出现，key未被释放，出现死锁情况
//     */
//    @RequestMapping("/deduct_stock")
//    public String deductStock2(){
//
//        String lockKey="lockKey";
//
//        try{
//            // setnx（），如果当前key不存在则设置成功，否则不设置
//            boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey,"lock"); // setnx(key,value);
//
//            // 表示已经被加锁
//            if(!result){
//                return "error_code";
//            }
//            // 获取当前库存
//            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
//
//            if (stock>0){
//                int realStock = stock -1;
//                // 设置库存-1
//                stringRedisTemplate.opsForValue().set("stock",realStock+"");
//                System.out.println("扣除成功");
//            }else {
//                System.out.println("库存不足，扣除失败");
//            }
//        }finally {
//            // 删除key，即释放锁
//            stringRedisTemplate.opsForValue().delete(lockKey);
//        }
//
//        return "end";
//    }
//
//    /*
//       第三版：
//           基础实现，redis的单线程模型保证了线程安全，setnx操作保证每次只有一个线程可以拿到锁，同时后边的线程拿不到而等待，
//           新增：
//               1. 使用try /catch /finally 块来防止出现错误，无法释放锁
//               2. 设置key的自动过期
//           主要问题：
//               1. 如果主机突然宕机，运行到设置key锁后宕机，那么仍然会出现，key未被释放，出现死锁情况
//    */
//    @RequestMapping("/deduct_stock")
//    public String deductStock3(){
//
//        String lockKey="lockKey";
//
//        try{
//            // setnx（），如果当前key不存在则设置成功，否则不设置
//            boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey,"lock"); // setnx(key,value);
//            // 此处宕机，key还未设置过期时间
//
//
//
//            // 设置key的自动过期时间
//            stringRedisTemplate.opsForValue().expire(lockKey,10, TimeUnit.SECONDS);
//            // 表示已经被加锁
//            if(!result){
//                return "error_code";
//            }
//            // 获取当前库存
//            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
//
//            if (stock>0){
//                int realStock = stock -1;
//                // 设置库存-1
//                stringRedisTemplate.opsForValue().set("stock",realStock+"");
//                System.out.println("扣除成功");
//            }else {
//                System.out.println("库存不足，扣除失败");
//            }
//        }finally {
//            // 删除key，即释放锁
//            stringRedisTemplate.opsForValue().delete(lockKey);
//        }
//
//        return "end";
//    }
//
//    /*
//       第四版：
//           基础实现，redis的单线程模型保证了线程安全，setnx操作保证每次只有一个线程可以拿到锁，同时后边的线程拿不到而等待，
//           新增：
//               1. 使用try /catch /finally 块来防止出现错误，无法释放锁
//               2. 设置key的自动过期
//               3. 使用stringRedisTemplate.opsForValue().setIfAbsent(lockKey,"lock",10,TimeUnit.SECONDS); 该操作是一个原子操作
//           主要问题：
//                1. 假如两个线程到来，第一个线程执行时间超过10秒，假设执行了15秒，此时，key在第10秒时就已经过期了，那么第二个线程在第10秒时就会拿到锁
//                而第一个线程会在第15秒执行完时，释放锁，而它本身的锁已经过期自动删除了，此时它会删除第二个线程的锁，因此也会造成超卖问题。
//    */
//    @RequestMapping("/deduct_stock")
//    public String deductStock3(){
//
//        String lockKey="lockKey";
//
//        try{
//            // setnx（），如果当前key不存在则设置成功，否则不设置
////            boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey,"lock"); // setnx(key,value);
////            // 此处宕机，key还未设置过期时间
////
////
////
////            // 设置key的自动过期时间
////            stringRedisTemplate.opsForValue().expire(lockKey,10, TimeUnit.SECONDS);
//            // 原子操作
//            boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey,"lock",10,TimeUnit.SECONDS);
//
//            // 表示已经被加锁
//            if(!result){
//                return "error_code";
//            }
//            // 获取当前库存
//            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
//
//            if (stock>0){
//                int realStock = stock -1;
//                // 设置库存-1
//                stringRedisTemplate.opsForValue().set("stock",realStock+"");
//                System.out.println("扣除成功");
//            }else {
//                System.out.println("库存不足，扣除失败");
//            }
//        }finally {
//            // 删除key，即释放锁
//            stringRedisTemplate.opsForValue().delete(lockKey);
//        }
//
//        return "end";
//    }
//
//    /*
//      第五版：
//          基础实现，redis的单线程模型保证了线程安全，setnx操作保证每次只有一个线程可以拿到锁，同时后边的线程拿不到而等待，
//          新增：
//              1. 使用try /catch /finally 块来防止出现错误，无法释放锁
//              2. 设置key的自动过期
//              3. 使用stringRedisTemplate.opsForValue().setIfAbsent(lockKey,"lock",10,TimeUnit.SECONDS); 该操作是一个原子操作
//              4. 使用uuid来标明每一个线程自己的锁，即将value设置为该线程的uuid，并进行对比，如果不一致即不删除
//          主要问题：
//               1. 假如两个线程到来，第一个线程执行时间超过10秒，假设执行了15秒，此时，key在第10秒时就已经过期了，那么第二个线程在第10秒时就会拿到锁
//               而第一个线程会在第15秒执行完时，释放锁，而它本身的锁已经过期自动删除了，此时它会删除第二个线程的锁，因此也会造成超卖问题。
//   */
//    @RequestMapping("/deduct_stock")
//    public String deductStock3(){
//
//        String lockKey="lockKey";
//        String clientId = UUID.randomUUID().toString();
//        try{
//            // setnx（），如果当前key不存在则设置成功，否则不设置
////            boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey,"lock"); // setnx(key,value);
////            // 此处宕机，key还未设置过期时间
////
////
////
////            // 设置key的自动过期时间
////            stringRedisTemplate.opsForValue().expire(lockKey,10, TimeUnit.SECONDS);
//            // 原子操作
//            boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey,clientId,10,TimeUnit.SECONDS);
//
//            // 表示已经被加锁
//            if(!result){
//                return "error_code";
//            }
//            // 获取当前库存
//            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
//
//            if (stock>0){
//                int realStock = stock -1;
//                // 设置库存-1
//                stringRedisTemplate.opsForValue().set("stock",realStock+"");
//                System.out.println("扣除成功");
//            }else {
//                System.out.println("库存不足，扣除失败");
//            }
//        }finally {
//            // 对比，是自己的锁在删除
//            if (clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
//                // 删除key，即释放锁
//                stringRedisTemplate.opsForValue().delete(lockKey);
//            }
//        }
//
//        return "end";
//    }


}
