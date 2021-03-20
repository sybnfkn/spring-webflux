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

import java.util.concurrent.TimeUnit
import scala.concurrent.duration._
import scala.language.postfixOps

/**
 * mvn命令执行压测工具，-D传入参数
 * mvn gatling:test -Dgatling.simulationClass=com.zhangyan.test.LoadSimulation -Dbase.url=http://localhost:8081/ -Dtest.path=user/1 -Dsim.users=3
 *
 *
 * 测试场景：
 * 1。指定的用户量是在30秒时间内匀速增加上来的；
 * 2。每个用户重复请求30次指定的URL，中间会随机间隔1~2秒的思考时间。
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

