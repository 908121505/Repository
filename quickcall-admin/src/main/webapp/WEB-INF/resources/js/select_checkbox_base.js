$(function(){
  /*
* 下拉选择
* 多选
*/
//点击得到下拉区域
    $(".region-box").click(function(){
        debugger;
        $(this).toggleClass("shrink");
        if($(this).hasClass("shrink")){
            $(".region-list").show();
        }else{
            $(".region-list").hide();
        }
        //阻止冒泡
        return false;
    })
    //隐藏下拉区域
    /*$(".page-content").click(function(){
        debugger;
        $(this).find(".region-list").hide();
        $(".region-box").removeClass("shrink");
    })
    $(".region-list").click(function(){
        debugger;
        //阻止冒泡
        return false;
    })*/

    //选择所在区域 checkbox
    $(".region-list ul").on("click",".check",function(){
        debugger;
        if($(this).parent().hasClass("selected")){
           $(this).parent().removeClass("selected");
           var val = $(this).parent().find(".text").html();
           $(".region-com li").find(".text").each(function(){
               if($(this).html() == val){
                   $(this).parents("li").remove();
               }
           })
       }else{
           //判断是否已存在三个
            if($("input[name='productIds']").length > 2){
                alert("只能选择三个产品");
                return false;
            }


           $(this).parent().addClass("selected");
           var val = $(this).parent().find(".text").html();
           var productIds = $(this).parent().find(".check.icon").attr("value");
           var text = "<li><span class=\"text\">"+val+"</span><i class=\"icon\"></i><input hidden='hidden' name='productIds' value='"+productIds+"' />  </li>";
           $(".region-com ul").append(text);
       }
    })
    //删除所在区域 checkbox
    $(".region-com").on("click","i",function(){
        debugger;
        $(this).parents("li").remove();
        var clear_val = $(this).parents("li").find(".text").html();
        $(".region-list li").find(".text").each(function(){
            if($(this).html() == clear_val){
                $(this).parents("li").removeClass("selected");
            }
        })
        event.stopPropagation();
    })
})