package com.air


import net.mamoe.mirai.console.plugins.*
import net.mamoe.mirai.event.events.MessageRecallEvent
import net.mamoe.mirai.event.events.MessageSendEvent
import net.mamoe.mirai.event.subscribeAlways
import net.mamoe.mirai.event.subscribeGroupMessages
import net.mamoe.mirai.event.subscribeMessages
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.utils.info
import kotlin.random.Random.Default.nextInt

enum class 回复 {
    拿去开冲吧少年, 你的兴趣很特别, 原来你好这口, 这个不错不错, 选得好少年, 我也很喜欢这个, 很符合你的口味嘛这个
}


// 继承了 PluginBase 类
// object 关键字 单例模式
object MapGetPluginMain : PluginBase() {
//    允许在构造函数之外初始化非空属性
//    有些时候，我们在声明变量的时候，并不能初始化这个变量。比如说在使用Spring的时候，我们会声明一个变量，但是在afterProperties里面进行初始化。
//
//但是我们又想使用kotlin非null变量带来的便利，这个时候，你需要的就是lateinit了。它告诉编译器，这个变量会被初始化，并且不会为null，但是在声明这里，我暂时还不知道什么时候会被初始化。
//链接：https://www.jianshu.com/p/24fdd70fdbce

    lateinit var images: Config

    //    lateinit var noral: List<ConfigSection>
//    lateinit var r18: List<ConfigSection>
//    读入可读取的用户配置文件
    val config = loadConfig("user.yml")

    // 写配置   by 代理 当 车速 变量被使用时会自动从配置中读取 如果没有读取到就使用设置的默认值并且往文件中写入此值
    val abc by config.withDefaultWriteSave { "50" }

    //    读配置
    val 图集 = config.getLongList("图集").toMutableList() // 读取列表并转换为程序可读的列表类型
    val 种子 = config.getLongList("种子").toMutableList()


    val 枚举信息数量 = 回复.values().size


    /*
     *   插件关闭时
    */
    override fun onDisable() {
        super.onDisable()
//    把读出来的配置写回去配置文件中
        config["图集"] = 图集
        config["种子"] = 种子
    }

    /*
     *   插件被加载 重写
    */
    override fun onLoad() {
        super.onLoad()
//        读取 resources 中的文件 可读不可写
        logger.info { "插件加载" }
        logger.info { "读取配置文件 data.yml" }
        try {
            images = getResourcesConfig("data.yml")
        } catch (e: Exception) {
            e.printStackTrace()
            logger.info { "读取文件失败 data.yml" }
        }
        logger.info("本地图片版本 ${images.getString("version")} 车速：${abc}")
        logger.info(
            "枚举列表 ${回复.values()} 获取单个文本值 ${回复.values()[0]} 长度：${回复.values().size} " +
                    "bbb ${回复.你的兴趣很特别}"
        )
//        for (v in 回复.values()) {
//            logger.info("枚举成员 ${v}")
//        }
    }

    /*
     *   插件启动时 重写
    */

    override fun onEnable() {
        super.onEnable()

        logger.info("插件调用....")

// 所有聊天事件   login 
//       say 48504706 以可遮苦辣嘛
        subscribeMessages {
//            "greeting" reply { "Hello ${sender.nick}" }
            "abc" reply { "Hello ${sender.nick}" }
            "ls map" reply { "来了老弟 ${回复.values()[nextInt(枚举信息数量)]}" }


        }
//  群聊事件
        subscribeGroupMessages {
//            创建类
            has<At> {
//              this 也就是 it 等于 机器人收到的群消息的事件 GroupMessageEvent
//              如果 target 标记ID 等于 机器人的Q号
                if (it.target == bot.id && sender.id == 250740270L) {
                    reply("表哥")
                }
            }
            "呼叫表哥" reply { "来了老弟" }
        }

        subscribeAlways<MessageRecallEvent> { event ->
            logger.info { "${event.authorId}测试" }
        }

// 临时会话消息事件
//        MessageSendEvent.TempMessageSendEvent {
//
//        }


    }
}