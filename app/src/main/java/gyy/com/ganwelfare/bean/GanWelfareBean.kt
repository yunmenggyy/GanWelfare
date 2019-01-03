package gyy.com.ganwelfare.bean

/**
 *
 * 创建时间: 2019/1/3
 *
 * 作者：GuanYangYi
 *
 * 功能描述：描述返回值的实体
 *
 *
 */
data class GanWelfareBean(var id:String,
                          var createdAt:String,
                          var desc:String,
                          var publishedAt:String,
                          var source:String,
                          var type:String,  //福利，Android
                          var url:String,  //图片Url
                          var used:String, //描述
                          var who:String)