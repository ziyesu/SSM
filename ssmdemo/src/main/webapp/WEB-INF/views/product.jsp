
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/index.css" />
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/userSetting.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/zshop.css" />

    <script language="JavaScript">
        window.onload=()=>{

            // 触发选择
            $("#tb").on("click",()=> {
                let isSel = doAll.selected().length ? false : true;
            $("#deleteSelect").attr("disabled", isSel);
        });

        }
        /*
         * 显示下一页
         * */
        let nextPage=function(page){
            document.forms[0].pageIndex.value=page;
            document.forms[0].action="${pageContext.request.contextPath}/findProductType";
            //提交表单
            document.forms[0].submit();

        }



        let goTo=()=>{
            location.href='${pageContext.request.contextPath}/findProductType?pageIndex=${product.pageIndex}';
        }

        let ajax=(data,path,andThen)=>{
            // data=["12",'13']
            // data={"ids":["sdf","ds"]}
            $.post(
                '${pageContext.request.contextPath}'+path,
                data,
                function(result){
                    if(result.status==1){
                        layer.msg(result.message,{
                            time:2000,
                            skin:'successMsg'
                        },()=>{
                            andThen();
                    });
                    }else{
                        layer.msg(result.message,{
                            time:2000,
                            skin:'errorMsg'
                        });
                    }
                }
            );
        }


        // 操作
        let productOperation={
            // 添加商品
            addProductType:function(){
                let data={'name':$('#productTypeName').val()};
                ajax(data,'/add',goTo);
            },
            // 修改状态
            modifyStatus:function(id){
                let data={'id':id};
                ajax(data,"/setStatus",goTo);
            },
            // 删除商品
            showDeleteModal:function(id){
                let data={'id':id};
                ajax(data,'/delete',goTo);
            },
            // 更改数据
            upProductType:function () {
                let data={
                    'id': $('#upproductTypeId').val(),
                    'name': $('#upproductTypeName').val(),
                    'status': $('#upproductTypeStatus').val(),
                };
                console.log(data);
                ajax(data,'/update',goTo);
            }
        }

        // 展示修改数据的原始数据
        let showProductType=function(id,name,status) {
            $('#upproductTypeId').val(id);
            $('#upproductTypeName').val(name);
            $('#upproductTypeStatus').val(status);
        }

        // 选择操作
        let doAll={
            init:{
                selectAllA: true,
            },
            selectAll:function(){
                let checkboxs=document.querySelectorAll(".productTypeId");
                checkboxs.forEach((elem)=>{
                    elem.checked=this.init.selectAllA;
            })
                this.init.selectAllA = !this.init.selectAllA;
                $("#selectAll").val(this.init.selectAllA ? "全选":"全不选");
                // 唤醒删除键
                $("#tb").trigger("click");
            },
            deleteSelect:function () {
                let data={"ids":doAll.selected()};
                console.log(doAll.selected());
                ajax(data,'/selectDelete',goTo);
                // console.log(doAll.selected());
            },
            // 选择的商品
            selected:function () {
                let checkboxs=document.querySelectorAll(".productTypeId");
                let sels=[];
                checkboxs.forEach((elem)=>{
                    if(elem.checked){

                    sels.push(elem.value);
                }
            })
                return sels;
            }
        }







    </script>

</head>
<body>
<div class="panel panel-default" id="userSet">
    <div class="panel-heading">
        <h3 class="panel-title">商品类型管理</h3>
    </div>

    <div class="panel-body">
        <div class="showmargersearch">
            <form class="form-inline" action="${pageContext.request.contextPath}/findProductType" method="post" id="frmSearch">
                <input type="hidden" name="pageIndex" value="${productType.pageIndex}" id="pageIndex"/>
                <div class="form-group">
                    <label for="produceTypeName">商品类别名：</label>
                    <input type="text" class="form-control" id="produceTypeName" placeholder="请输入商品类别名" name="name" value="${productType.name}">
                </div>
                <input type="submit" value="查询" class="btn btn-primary" id="doSearch">
            </form>
        </div>
        <br/>

        <p>
            <input type="button" value="全选" class="btn btn-primary" id="selectAll" onclick="doAll.selectAll()">
            <input type="button" disabled value="删除" class="btn btn-primary" id="deleteSelect" onclick="doAll.deleteSelect()">
        </p>
        <div class="show-list text-center">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">编号</th>
                    <th class="text-center">类型名称</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${productList}" var="product">
                    <tr>
                        <td><input type="checkbox" class="productId" name="productId" style="margin-right: 1em" value="${product.id}">${product.id}</td>
                        <td>${productType.name}</td>
                        <td>
                            <c:if test="${productType.status==1}">启用</c:if>
                            <c:if test="${productType.status==0}">禁用</c:if>
                        </td>
                        <td class="text-center">
                            <input type="button" class="btn btn-warning btn-sm doProTypeModify" value="修改" onclick="showProductType(${product.id},'${product.name}',${productType.status})">
                            <input type="button" class="btn btn-warning btn-sm doProTypeDelete" onclick="productOperation.showDeleteModal(${product.id})" value="删除">
                            <c:if test="${productType.status==1}">
                                <input type="button" class="btn btn-danger btn-sm doProTypeDisable" value="禁用" onclick="productOperation.modifyStatus(${productType.id},this)">
                            </c:if>
                            <c:if test="${productType.status==0}">
                                <input type="button" class="btn btn-success btn-sm doProTypeDisable" value="启用" onclick="productOperation.modifyStatus(${productType.id},this)">
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <ul ><a href="#" onclick="nextPage(${productType.pageIndex-1})">上一页</a><a href="#" onclick="nextPage(${productType.pageIndex+1})">下一页</a> 总页数：${productType.totalPageNum} 当前页：${productType.pageIndex}
            </ul>
            <p class="text-right">
                <input type="button" value="添加商品类型" class="btn btn-primary" id="doAddProTpye">
            </p>
        </div>
    </div>
</div>


<!-- 添加商品类型 start -->
<div class="modal fade" tabindex="-1" id="ProductType">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">添加商品类型</h4>
            </div>
            <div class="modal-body text-center">
                <div class="row text-right">
                    <label for="productTypeName" class="col-sm-4 control-label">类型名称：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="productTypeName">
                    </div>
                </div>
                <br>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary addProductType" onclick="productOperation.addProductType()">添加</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 添加商品类型 end -->



<!-- 修改商品类型 start -->
<div class="modal fade" tabindex="-1" id="myProductType">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">修改商品类型</h4>
            </div>
            <div class="modal-body text-center">
                <div class="row text-right">
                    <label for="upproductTypeId" class="col-sm-4 control-label">编号：</label>
                    <div class="col-sm-4">
                        <input type="text" value="" readonly="readonly" class="form-control" id="upproductTypeId">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="upproductTypeName" class="col-sm-4 control-label">类型名称：</label>
                    <div class="col-sm-4">
                        <input type="text" value="" class="form-control" id="upproductTypeName">
                    </div>
                </div>
                <br>
                <input type="hidden"  name="status" id="upproductTypeStatus" >
                <br>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary doProTypeModify" onclick="productOperation.upProductType()">修改</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


</body>
</html>
