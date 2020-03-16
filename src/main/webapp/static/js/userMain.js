$(document).ready(function() {
    // 一个判断是否提交的flag
    var updateUserFlag=true;
    //点击编辑头像时，弹出文件选择
    $("#editUserPic").click(function (){
        //触发input
        $("#userPicInput").click();
        $("#userPicInput").unbind().change(function(){
            var picFile=$("#userPicInput").prop("files")[0];
            // console.log(picFile);
            var tmpName=picFile.name.split('.');
            var suffix=tmpName[tmpName.length-1].toUpperCase();
            //检查后缀
            if(!(suffix == "PNG" || suffix == "JPEG" || suffix == "JPG")){
                updateUserFlag=false;
                alert("存在不合格式的文件!");
            }else{
                //alert("合格");
                var reader=new FileReader();
                reader.readAsDataURL(picFile);
                reader.onload= function () {
                    $("#userPic").attr("src",this.result);
                }
            }
        });
    });

    //点击提交修改时
    $("#updateUser").click(function(){
        var judge=$("#userPicInput").prop("files")[0];
        if(!updateUserFlag){
            alert("您选择的图片文件不合格或者没有选择图片");
        }else{

            var userEditFrom=new FormData();
            userEditFrom.set("userPic",$("#userPicInput").prop("files")[0]);
            userEditFrom.set("uName",$("input[name='uname']").val());
            userEditFrom.set("sex",$("input[name='userSex']:checked").val());
            userEditFrom.set("email",$("input[name='email']").val());
            userEditFrom.set("tel",$("input[name='phoneNum']").val());

            // console.log(editData);
            $.ajax({
                url:'User/updateUser',
                data:userEditFrom,
                type : 'POST',
                cache : false,
                processData : false,
                contentType : false,
                success:function(data){
                    // 要求返回json字符串，键名为result
                    if(data.result){
                        //alert("ok");
                        window.location.href="FrontForward/personalMain";
                    }else{
                        alert("请检查格式是否正确！");
                    }
                },
                error(xhr,status,error){
                    $("#userEditResult-main").html("请检查格式是否正确！");
                    $("#userEditResult").removeAttr("hidden");

                }
            });
        }
    });
});