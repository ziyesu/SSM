<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>在线购物商城</title>
    <link rel="stylesheet" href="../css/bootstrap.css"/>
    <link rel="stylesheet" href="../css/style.css"/>
    <link rel="stylesheet" href="../iconfont/iconfont.css">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.js"></script>
    <script src="../js/zshop.js"></script>
    <script src="../layer/layer.js"></script>
    <style>
        .layui-skin{
            background: #1f1a1a;
            color: #c5bdbd;
        }
    </style>
    <script>
        var demo=function(item){
            // console.log(item);
            //
            var img=item.querySelector("img").src;
            var name=item.querySelector("h4").innerText;
            var price=item.querySelector(".user").innerText;
            var info=item.querySelector(".desc").innerText;
            let type= item.querySelector('input[name="typeName"]').value;
            let id= item.querySelector('input[name="id"]').value;
            console.log(img) ;
            console.log(name) ;
            console.log(price);
            console.log(info);

            let show="<div><center><img  src='"+ img +"' style='width: 210px;height: 210px;border-radius: 5px'/></center>" +
                "<br><div style='margin-left: 66px'>商品名称：&nbsp;&nbsp;<b style='color: #1cbcec'>" + name + "</b>" +
                "<br> 商品类型：&nbsp;&nbsp;<b>" + type + "</b>" +
                "<br> 价格：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b style='color: #ff7200'>￥ " + price + "</b>" +
                "<br> 详情：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; " + info + "</br></div></div>" ;

            layer.open({
                // type: 1, //类型，解析url
                closeBtn: 1, //关闭按钮是否显示 1显示0不显示
                title: name+"详情", //页面标题
                shadeClose: true, //点击遮罩区域是否关闭页面
                shade: 0.5,  //遮罩透明度
                skin: "layui-skin",
                offset: "50px",
                area: [350,"auto"],  //弹出层页面比例
                content: show
            });

            $.ajax({
                type:'get',
                url:'${pageContext.request.contextPath}/hadoop',
                data:{'id': id,"name":name},
                dataType:'json',
                success:function(result){
                    if(result.status==1){
                        console.log("ok");
                    }else{
                        console.log("fail");
                    }
                }
            });
        }
    </script>
</head>

<body>
<div id="wrapper">
    <!-- navbar start -->
    <div class="navbar navbar-default clear-bottom">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand logo-style" href="">
                    <img class="brand-img" src="../images/com-logo1.png" alt="logo" height="70">
                </a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active">
                        <a href="#">商城主页</a>
                    </li>
                    <li>
                        <a href="myOrders.html">我的订单</a>
                    </li>
                    <li>
                        <a href="cart.html">购物车</a>
                    </li>
                    <li class="dropdown">
                        <a href="center.html">会员中心</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="#" data-toggle="modal" data-target="#loginModal">登陆</a>
                    </li>
                    <li>
                        <a href="#" data-toggle="modal" data-target="#registModal">注册</a>
                    </li>
                    <li class="userName">
                        您好：user！
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle user-active" data-toggle="dropdown" role="button">
                            <img class="img-circle" src="../images/user.jpeg" height="30"/>
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#" data-toggle="modal" data-target="#modifyPasswordModal">
                                    <i class="glyphicon glyphicon-cog"></i>修改密码
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <i class="glyphicon glyphicon-off"></i> 退出
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- navbar end -->
    <!-- content start -->
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <div class="page-header" style="margin-bottom: 0px;">
                    <h3>商品列表</h3>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <form class="form-inline hot-search" action="/findProduct" method="post">
                    <div class="form-group">
                        <label class="control-label">商品：</label>
                        <input type="text" class="form-control" placeholder="商品名称" name="name">
                    </div>
                    &nbsp;
                    <div class="form-group">
                        <label class="control-label">价格：</label>
                        <input type="text" class="form-control" placeholder="最低价格" name="minPrice"> --
                        <input type="text" class="form-control" placeholder="最高价格" name="maxPrice">
                    </div>
                    &nbsp;
                    <div class="form-group">
                        <label class="control-label">种类：</label>
                        <select class="form-control input-sm" name="productType.id">
                            <option value="" selected="selected">查询全部</option>
                            <c:forEach items="${productTypes}" var="productType">
                                <option value="${productType.id}">${productType.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    &nbsp;
                    <div class="form-group">
                        <button type="submit" class="btn btn-warning">
                            <i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="content-back">
        <div class="container" id="a">
            <div class="row">

                <c:forEach items="${productList}" var="product">

                    <div class="col-xs-3  hot-item" onclick="demo(this)">
                        <div class="panel clear-panel">
                            <div class="panel-body">
                                <div class="art-back clear-back">
                                    <div class="add-padding-bottom">
                                        <img src="images/${product.image}" class="shopImg">
                                    </div>
                                    <h4><a href="">${product.name}</a></h4>
                                    <div class="user clearfix pull-right">
                                            ${product.price}
                                    </div>
                                    <div class="desc">${product.info}
                                    </div>
                                    <div class="attention pull-right">
                                        加入购物车 <i class="icon iconfont icon-gouwuche"></i>
                                    </div>
                                    <input type="hidden"  name="id" value="${product.id}">
                                    <input type="hidden"  name="typeName" value="${product.productType.name}">
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--<div class="col-xs-3  hot-item">--%>
                        <%--<div class="panel clear-panel">--%>
                            <%--<div class="panel-body">--%>
                                <%--<div class="art-back clear-back">--%>
                                    <%--<div class="add-padding-bottom">--%>
                                        <%--<img src="../images/shop2.jpg" class="shopImg">--%>
                                    <%--</div>--%>
                                    <%--<h4><a href="">${product.name}</a></h4>--%>
                                    <%--<div class="user clearfix pull-right">--%>
                                        <%--￥${product.price}--%>
                                    <%--</div>--%>
                                    <%--<div class="desc">平时穿也不会显得夸张，很大方洋气 我已经自留了，还和小姐妹准备人手一件圣诞节穿着出去玩！ 经典的圆领，大气休闲，长袖设计，休闲舒适--%>
                                        <%--宽松的版型，怕冷可以里面穿保暖衣）--%>
                                    <%--</div>--%>
                                    <%--<div class="attention pull-right">--%>
                                        <%--加入购物车 <i class="icon iconfont icon-gouwuche"></i>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </c:forEach>

            </div>
        </div>
    </div>
    <!-- content end-->
    <!-- footers start -->
    <div class="footers">
        版权所有：xxxx
    </div>
    <!-- footers end -->
</div>

<!-- 修改密码模态框 start -->
<div class="modal fade" id="modifyPasswordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">修改密码</h4>
            </div>
            <form action="" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">原密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">新密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">重复密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">
                        关&nbsp;&nbsp;闭
                    </button>
                    <button type="reset" class="btn btn-warning">重&nbsp;&nbsp;置</button>
                    <button type="submit" class="btn btn-warning">修&nbsp;&nbsp;改</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 修改密码模态框 end -->

<!-- 登录模态框 start  -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <!-- 用户名密码登陆 start -->
        <div class="modal-content" id="login-account">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">用户名密码登录</h4>
            </div>
            <form action="" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户名：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password" placeholder="请输入密码">
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="text-align: center">
                    <a class="btn-link">忘记密码？</a> &nbsp;
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">
                        关&nbsp;&nbsp;闭
                    </button>
                    <button type="submit" class="btn btn-warning">登&nbsp;&nbsp;陆</button> &nbsp;&nbsp;
                    <a class="btn-link" id="btn-sms-back">短信快捷登录</a>
                </div>
            </form>
        </div>
        <!-- 用户名密码登陆 end -->
        <!-- 短信快捷登陆 start -->
        <div class="modal-content" id="login-sms" style="display: none;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">短信快捷登陆</h4>
            </div>
            <form class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">手机号：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" placeholder="请输入手机号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">验证码：</label>
                        <div class="col-sm-4">
                            <input class="form-control" type="text" placeholder="请输入验证码">
                        </div>
                        <div class="col-sm-2">
                            <button class="pass-item-timer" type="button">发送验证码</button>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="text-align: center">
                    <a class="btn-link">忘记密码？</a> &nbsp;
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">
                        关&nbsp;&nbsp;闭
                    </button>
                    <button type="submit" class="btn btn-warning">登&nbsp;&nbsp;陆</button> &nbsp;&nbsp;
                    <a class="btn-link" id="btn-account-back">用户名密码登录</a>
                </div>
            </form>
        </div>
        <!-- 短信快捷登陆 end -->
    </div>
</div>
<!-- 登录模态框 end  -->

<!-- 注册模态框 start -->
<div class="modal fade" id="registModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">会员注册</h4>
            </div>
            <form action="" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户姓名:</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">登陆账号:</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">登录密码:</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">联系电话:</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">联系地址:</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">
                        关&nbsp;&nbsp;闭
                    </button>
                    <button type="reset" class="btn btn-warning">重&nbsp;&nbsp;置</button>
                    <button type="submit" class="btn btn-warning">注&nbsp;&nbsp;册</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 注册模态框 end -->
</body>

</html>
