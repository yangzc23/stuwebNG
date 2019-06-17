function list(){
	$.ajax({
		type: "get",
		url: "welcome",
		cache: false,
		async: true,
		dataType: "json",
		success: function(data){
			var html = "<div class=\"panel-heading\"><h3 class=\"panel-title\">学生列表</h3></div>";
			html += "<div><p></p></div>";
			html += "<table class=\"table table-hover\">";
			html += "<tr><th>学号</th><th>姓名</th><th>性别</th><th>生日</th><th>操作</th></tr>";
			$.each(data.data,function(index,obj){
				html += "<tr><td>"+obj.sno+"</td><td>"+obj.sname+
				"</td><td>"+obj.isMale+"</td><td>"+obj.birth+"</td><td>"+
				"<a href=\"javascript:void(0)\" onclick=\"detail(this);\">详情</a>&nbsp;"+
				"<a href=\"javascript:void(0)\" onclick=\"edit(this);\">编辑</a>&nbsp;"+
				"<a href=\"javascript:void(0)\" onclick=\"del(this);\">删除</a>"+				
				"</td></tr>";
			});
			html += "</table>";
			//console.log(html);
			$("#list").html(html);
		}
	});
}

function add(){
	$("#subject").text("注册学生信息");
	$("#first").hide();
	$("#register")[0].reset();
    $("#photo2").attr("src","images/default.png");
	//$("#edit").css("display","block");
    $('.form_date').datetimepicker("setDate", new Date()); 
    $('#edit').modal('show');
}

function edit(e){
	$("#register")[0].reset();        		
	$("#subject").text("修改学生信息");
	$("#first").show();
    $("#photo2").attr("src","images/default.png");
	var sid = $(e).parent().siblings().first().text();
	//alert(sid);
    $.ajax({
    	//几个参数需要注意一下
        type: "GET",//方法类型
        cache: false,
        dataType: "json",//预期服务器返回的数据类型
        url: "edit?sid="+sid ,//url
        success: function (data) {
            //console.log(data);//打印服务端返回的数据(调试用)
            if (data.result == "success") {
            	var stu = data.data;
            	$("#sid").val(stu.sno);
            	$("#sname").val(stu.sname);
            	if(stu.isMale){
            		$("#male").prop("checked",true);
            	}else{
            		$("#female").prop("checked",true);
            	}
            	$("#birth").val(stu.birth);
            	$("#filePath").val(stu.filePath);
                $("#photo2").attr("src",stu.filePath);
                $('#edit').modal('show');
            }
        },
        error : function() {
            alert("异常！");
        }
    });
}

function save(){
    $.ajax({
        	//几个参数需要注意一下
            type: "post",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "save" ,//url
            data: $("#register").serialize(),
            success: function (data) {
                //console.log(data);//打印服务端返回的数据(调试用)
                if (data.result == "success") {
                	$('#edit').modal('hide');
                    //$("#edit").css("display","none");
                    //$("#register")[0].reset();
                    //$("#photo2").attr("src","images/default.png");
                    //alert(data.message);
                    list();
                }
            },
            error : function() {
                alert("异常！");
            }
        });
}

function upload(){
	var file = $("#photo").get(0).files[0];
	var formData = new FormData();
	formData.append("source",file);
	$.ajax({
		url:"upload/file",
		type:"post",
		dataType:"json",
		cache:false,
		data:formData,
		contentType:false,
		processData: false,
		success:function(data){
			if(data.result=="success"){
				$("#photo2").attr("src",data.url);
				$("#filePath").val(data.url);
			}
			console.log("hello test");
		}
	});
}

function del(e){
	var sid = $(e).parent().siblings().first().text();
	var yesOrNo = confirm("确定要删除该学生么？");
	if(yesOrNo){
	    $.ajax({
	    	//几个参数需要注意一下
	        type: "GET",//方法类型
	        dataType: "json",//预期服务器返回的数据类型
	        url: "delete?sid="+sid ,//url
	        success: function (data) {
	            console.log(data.message);//打印服务端返回的数据(调试用)
	            if (data.result == "success") {
	            	list();
	            }
	        },
	        error : function() {
	            alert("异常！");
	        }
	    });	
	}
}