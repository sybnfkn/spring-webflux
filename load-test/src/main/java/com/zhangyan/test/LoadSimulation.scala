package com.zhangyan.test

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/03/21/1:01 上午
 * @Description:
 */
import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.language.postfixOps

/**
 * mvn命令执行压测工具，-D传入参数
 * mvn gatling:test -Dgatling.simulationClass=com.zhangyan.test.LoadSimulation -Dbase.url=http://localhost:8081/ -Dtest.path=user/1 -Dsim.users=3
 *
 *
 * 测试场景：
 * 1。指定的用户量是在30秒时间内匀速增加上来的；
 * 2。每个用户重复请求30次指定的URL，中间会随机间隔1~2秒的思考时间。
 *
 *
 *
 *模拟用户数2000，webmvc表现
> request count                                      60000 (OK=57460  KO=2540  )
> min response time                                      0 (OK=101    KO=0     )
> max response time                                  28442 (OK=28442  KO=9718  )
> mean response time                                   448 (OK=361    KO=2420  )
> std deviation                                       1350 (OK=1272   KO=1543  )
> response time 50th percentile                        108 (OK=108    KO=2415  )
> response time 75th percentile                        126 (OK=118    KO=3540  )
> response time 95th percentile                       2718 (OK=794    KO=4630  )
> response time 99th percentile                       7305 (OK=7383   KO=6527  )
> mean requests/sec                                594.059 (OK=568.911 KO=25.149)
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         54604 ( 91%)
> 800 ms < t < 1200 ms                                 479 (  1%)
> t > 1200 ms                                         2377 (  4%)
> failed                                              2540 (  4%)
---- Errors --------------------------------------------------------------------
> status.find.in(200,304,201,202,203,204,205,206,207,208,209), b   2482 (97.72%)
ut actually found 500
> j.n.ConnectException: connection timed out: localhost/0:0:0:0:     32 ( 1.26%)
0:0:0:1:8080
> j.n.ConnectException: connection timed out: localhost/127.0.0.     16 ( 0.63%)
1:8080
> j.n.ConnectException: Can't assign requested address: localhos     10 ( 0.39%)
t/127.0.0.1:8080



模拟用户数2000，webflux表现
> request count                                      60000 (OK=60000  KO=0     )
> min response time                                    101 (OK=101    KO=-     )
> max response time                                    274 (OK=274    KO=-     )
> mean response time                                   109 (OK=109    KO=-     )
> std deviation                                         13 (OK=13     KO=-     )
> response time 50th percentile                        106 (OK=106    KO=-     )
> response time 75th percentile                        109 (OK=109    KO=-     )
> response time 95th percentile                        125 (OK=125    KO=-     )
> response time 99th percentile                        176 (OK=176    KO=-     )
> mean requests/sec                                731.707 (OK=731.707 KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                         60000 (100%)
> 800 ms < t < 1200 ms                                   0 (  0%)
> t > 1200 ms                                            0 (  0%)
> failed                                                 0 (  0%)
 */
class LoadSimulation extends Simulation {

  // 从系统变量读取 baseUrl、path和模拟的用户数
  val baseUrl = System.getProperty("base.url")
  val testPath = System.getProperty("test.path")
  val sim_users = System.getProperty("sim.users").toInt

  val httpConf = http.baseURL(baseUrl)

  // 定义模拟的请求，重复30次
  val helloRequest = repeat(30) {
    // 自定义测试名称
    exec(http("hello-with-latency")
      // 执行get请求
      .get(testPath))
      // 模拟用户思考时间，随机1~2秒钟
      .pause(1, 2)
  }

  // 定义模拟的场景
  val scn = scenario("hello")
    // 该场景执行上边定义的请求
    .exec(helloRequest)

  // 配置并发用户的数量在30秒内均匀提高至sim_users指定的数量
  setUp(scn.inject(rampUsers(sim_users).over(30)).protocols(httpConf))
}

