<style>
    .error{
        color:red;
    }
</style>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins" >
                <div class="">
                    <form class="form-horizontal form-bordered" id="gzxantForm">
                        <input type="hidden" name="id" value="${enrollEnter.id}"/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">姓名：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="name"
                                       value="${enrollEnter.name}" placeholder="请输入注册姓名"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">是否成功：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <select name="state" class="form-control">
                                    <#if "${enrollEnter.state}"='Y'>
                                          <option value="${enrollEnter.state}">成功</option>
                                    <#else>
                                          <option value="${enrollEnter.state}">禁用</option>
                                    </#if>
                                    <option value="Y">成功</option>
                                    <option value="N">失败</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">分数：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="fraction"
                                       value="${enrollEnter.fraction}" placeholder="请输入分数"/>

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">歌曲：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="song"
                                       value="${enrollEnter.song}" placeholder="请输入报名地点"/>

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">报名地点：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="place"
                                       value="${enrollEnter.place}" placeholder="请输入工作单位"/>

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">类型：</label>
                            <div class="col-sm-8">
                                <select name="type" class="form-control">
                                    <option value="">${enrollEnter.type}</option>
                                            <#list enterType as type>
		  									<option value="${type.jvalue}">${type.jvalue}</option>
                                            </#list>
                                </select>

                            </div>
                        </div>



                        <#if action !='detail'>
                            <div class="form-actions fluid">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="submit" onclick="infoNextStep()" class="btn btn-success">保存</button>
                            </div>
                        </div>
                    </#if>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">

    action = "${action}";
    function  cusFunction() {
        console.info("提交之前，最后执行自定义的函数");
    }

    function infoNextStep() {
        info_validate.form();
    }


    function infoNextStep() {
        info_validate.form();
    }

    var info_validate = $('#gzxantForm');
    var error = $('.alert-danger', info_validate);
    info_validate.validate({
        errorElement: 'span',
        errorClass: 'error',
        focusInvalid: true,
        rules: {
            name: {
                required: true
            },
            type:{
                required: true
            },
            fraction:{
                required: true
            },
            song:{
                required: true
            },
            state:{
                required: true
            },
            place:{
                required: true
            }
        },
        messages: {
            name:{
                required:  "请输入登录名"
            },
            place:{
                required: "请输入报名地点"
            },
            type:{
                required: "请选择类型"
            },
            fraction:{
                required: "请输入分数"
            },
            state:{
                equired: "请选择状态"
            },
            song:{
                equired: "请输入歌曲"
            }


        }
    });



</script>
